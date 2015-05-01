/**
 * Author:beyondboy
 * Gmail:xuguoli.scau@gmail.com
 * Date: 2015-04-29
 * Time: 16:42
 */
package com.scau.beyondboy.weibo;
/**
 * 储存请求各种参数的常量
 */
public class Constants
{
    //https://api.weibo.com/oauth2/authorize?client_id=123050457758183&redirect_uri=http://www.example.com/response&response_type=code
    /**开发者的id*/
    public static final String APP_KEY="3353240694";
    /**开发者网站的回调地址*/
    public static final String REDIRECT_URL="https://api.weibo.com/oauth2/default.html";
    /**应用密匙*/
    public static final String APP_SECRET="64b7c12bd6bf38a3e012a4f6ea8fd2d2";
    /**授权页面引用地址*/
    public static final String URL="https://api.weibo.com/oauth2/authorize?client_id="+APP_KEY+"&redirect_uri="+REDIRECT_URL+"&response_type=code";
}
