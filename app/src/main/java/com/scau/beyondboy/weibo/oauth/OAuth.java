/**
 * Author:beyondboy
 * Gmail:xuguoli.scau@gmail.com
 * Date: 2015-04-26
 * Time: 01:32
 */
package com.scau.beyondboy.weibo.oauth;
import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;
/**
 * 储存OAuth重要的信息
 */
public class OAuth
{
    private final  static String TAG="OAuth";
    private static OAuth sOAuth;
    private static SharedPreferences mPreferences;
    private String mClientId;
    private String mRedirectUrl;
    private String mAccessToken;
    private OAuth()
    {

    }
    //单利模式返回OAuth对象
    public static OAuth getOAuth(Context context)
    {
        if(sOAuth==null)
        {
            sOAuth=new OAuth();
        }
        mPreferences=context.getSharedPreferences("OAuth",Context.MODE_PRIVATE);
        sOAuth.mClientId=mPreferences.getString("client_id", null);
        sOAuth.mAccessToken=mPreferences.getString("access_token", null);
        sOAuth.mRedirectUrl=mPreferences.getString("redirect_uri",null);
        return sOAuth;
    }
    public String getClientId()
    {
        return mClientId;
    }
    public String getRediretUrl()
    {
        return mRedirectUrl;
    }
    public String getAccessToken()
    {
        return mAccessToken;
    }
    @Override
    public String toString()
    {
        return "client_id="+mClientId+"&access_token="+mAccessToken+"&redirect_url="+mRedirectUrl;
    }
}
