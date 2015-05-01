/**
 * Author:beyondboy
 * Gmail:xuguoli.scau@gmail.com
 * Date: 2015-04-30
 * Time: 11:15
 */
package com.scau.beyondboy.weibo.util;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
/**
 * 主要用来异步发送oauth各种请求
 */
public class OAuthRestUtils
{
    /**URL的基地址*/
    private static final  String BASE_URL="https://api.weibo.com/oauth2/";
    private static AsyncHttpClient sClient=new AsyncHttpClient();
    /**
     * 发送get的请求
     * @param url 发送URL的地址
     * @param params URL的参数设置
     * @param responseHandler 响应服务器处理对象
     */
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
    {
        sClient.get(getAbsoluteUrl(url), params, responseHandler);
    }
    /**
     * 发送post的请求
     * @param url 发送URL的地址
     * @param params URL的参数设置
     * @param responseHandler 响应服务器处理对象
     */
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler)
    {
        sClient.post(getAbsoluteUrl(url), params, responseHandler);
    }
    /**返回URL的绝对地址*/
    private static String getAbsoluteUrl(String relativeUrl)
    {
        return BASE_URL + relativeUrl;
    }
}
