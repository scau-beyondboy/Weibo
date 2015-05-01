/**
 * Author:beyondboy
 * Gmail:xuguoli.scau@gmail.com
 * Date: 2015-04-28
 * Time: 11:57
 */
package com.scau.beyondboy.weibo.util;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
/**
 * 检查手机是否有网络
 */
public class NetworkUtils
{
    private static final String TAG = "NetworkUtils";
    /**
     * 检查手机是否有网络
     * 若有则返回true，没有返回false
     */
    public static boolean isNetworkReachable(Context context)
    {
        ConnectivityManager manager=(ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo current=manager.getActiveNetworkInfo();
        if(current==null)
        {
            Log.i(TAG,"没有网络" );
            return false;
        }
        if(current.getType()==ConnectivityManager.TYPE_WIFI)
        {
            Log.i(TAG,"有wifi网络");
        }
        else if(current.getType()==ConnectivityManager.TYPE_MOBILE)
        {
            Log.i(TAG,"有移动网络");
        }
        else
        {
            Log.i(TAG,"其他网络");
        }
        return true;
    }
}
