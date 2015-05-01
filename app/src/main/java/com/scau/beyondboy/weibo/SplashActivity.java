package com.scau.beyondboy.weibo;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.scau.beyondboy.weibo.oauth.OAuth;
import com.scau.beyondboy.weibo.oauth.OAuthActivity;
import com.scau.beyondboy.weibo.util.NetworkUtils;
public class SplashActivity extends ActionBarActivity implements Animation.AnimationListener
{
    private static final String TAG ="SplashActivity " ;
    private OAuth mOAuth;
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onAnimationStart(Animation animation)
    {
        mOAuth=OAuth.getOAuth(this);
    }
    /**
     * 动漫加载启动时，首先先判断是否有网络
     * 没有网络，则跳转到网络设置那里
     * 若有网络，判断是否有请求令牌
     * 没有，则跳转到令牌申请活动
     * 若有，则直接进入主页面
     */
    @Override
    public void onAnimationEnd(Animation animation)
    {
        if(NetworkUtils.isNetworkReachable(this))
        {
            if(mOAuth.getAccessToken()!=null)
            {
                Log.i(TAG,"已经获得授权");
                Log.i(TAG,mOAuth.toString());
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                this.finish();
            }
            else
            {
                Log.i(TAG,"没有获得access_token,需要获得授权");
                Intent intent=new Intent(this, OAuthActivity.class);
                startActivity(intent);
                this.finish();
            }
        }
        else
        {
            Log.i(TAG,"弹出网络提示");
            showDialog(1);
        }
    }
    @Override
    public void onAnimationRepeat(Animation animation)
    {
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ImageView imageView = (ImageView) findViewById(R.id.splash_image);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.anim_splash);
        Log.i(TAG,"加载动画" );
        imageView.startAnimation(animation);
        animation.setAnimationListener(this);
    }
    /**
     * 如果没有网络则会调用该方法
     * 弹出窗口提示设置网络，按确
     * 定键会跳转到网络设置那里
     * 按退出键会退出该应用
     */
    @Override
    protected Dialog onCreateDialog(int id)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        Resources resources=getResources();
        Drawable drawable=resources.getDrawable(R.drawable.splash_net);
//        drawable=zoomDrawable(drawable,112,112);
        Bitmap bitmap = null;
        if (((BitmapDrawable) drawable) != null)
        {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        }
        drawable= new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 80, 80, true));
        builder.setTitle("温馨提示")
                .setIcon(drawable)
                .setMessage("请设置网络")
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Log.i(TAG, "跳转网络设置界面");
                        Intent intent = new Intent();
                        //跳转网络设置界面
                        intent.setAction(Settings.ACTION_WIRELESS_SETTINGS);
                        startActivity(intent);
                        SplashActivity.this.finish();
                    }
                })
                .setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        SplashActivity.this.finish();
                    }
                });
        return builder.show();
    }
    /**
     * 返回一定大小尺寸的图片
     */
    private Drawable zoomDrawable(Drawable drawable, int w, int h)
    {
        int width = drawable.getIntrinsicWidth();
        int height= drawable.getIntrinsicHeight();
        // drawable 转换成 bitmap
        Bitmap oldbmp = drawableToBitmap(drawable);
        // 创建操作图片用的 Matrix 对象
        Matrix matrix = new Matrix();
        // 计算缩放比例
        float scaleWidth = ((float)w / width);
        float scaleHeight = ((float)h / height);
        // 设置缩放比例
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height, matrix, true);       // 建立新的 bitmap ，其内容是对原 bitmap 的缩放后的图
        // 把 bitmap 转换成 drawable 并返回
        return new BitmapDrawable(newbmp);
    }
    /**
     * 返回drawable的位图
     */
    private Bitmap drawableToBitmap(Drawable drawable)
    {
        // 取 drawable 的长宽
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        // 建立对应 bitmap
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888:Bitmap.Config.RGB_565;         // 取 drawable 的颜色格式
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        //返回画布的画画内容
        return bitmap;
    }
}
