package cn.com.do1.component.common.weixin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhanqi on 2015/3/16.
 */
public class WxapiConstants {

    public static final String CP_NAME = "express";
    public static final String SCOPE_BASE = "snsapi_base";
    public static final String SCOPE_USERINFO = "snsapi_userinfo";

    public static final String LOGIN_AUTHORIZE_URL;
    public static final String LOGIN_ACCESS_TOKEN_URL;
    public static final String ACCESS_TOKEN_URL;
    public static final String JSAPI_TICKET_URL;
    public static final String USERINFO_URL;
    public static final String MEDIA_DOWNLOAD_URL;
    public static final String UNIFIEDORDER_URL;
    public static final String REFUND_URL;
    public static final String ORDERQUERY_URL;
    public static final String REFUNDQUERY_URL;

    
    /* 株洲高科公众号2 */
    /*public static String APPID="wx50e042a8a06683e8";
    
    public static String SECRET="5dd2e5a41b8d722a22fba7c776bfa3c0";

    public static String KEY="5m2WnCc180Siv2wgJGkShsoZi03MseGm";
    
    
    public static String PARTNER_ID="1371595302";
    
    public static String NOTIFY_URL="http://wx.huojuit.com/jiangnan/f/wx/payment/weixinNotify";
    
    public static final String HOME_URL = "http://wx.huojuit.com/jiangnan/";*/

    
    
    
    /* Hi铺  */			
    public static String APPID="wx3a51311b3d4e2f55";
    
    public static String SECRET="15dc66770d14b062ac76df65f19ffad2";

    public static String KEY="qd9qvt3jed7ksesnsm44estcqps1vsfa";
    
    
    public static String PARTNER_ID="1454840402";
    
    public static String NOTIFY_URL="http://wx.jnscwsc.com/jiangnan/f/wx/order/weixinNotify";
    
    public static final String HOME_URL = "http://admore.cn/hishop/";
        
    public static String CERT = "E:/test/cert/apiclient_cert.p12";
    public static String CERT_PWD = "qd9qvt3jed7ksesnsm44estcqps1vsfa";
    
    public static final String BODY_1 = "Hi铺";
    public static final String BODY_2 = "";
       
    
    public static final String SENDREDPACK_URL;
    
    static {
//        APPID = ConfigMgr.getStringCfg(CP_NAME, "wx_appid", null);
//        SECRET = ConfigMgr.getStringCfg(CP_NAME, "wx_secret", null);
//        MCHID = ConfigMgr.getStringCfg(CP_NAME, "pay_mchid", null);
//        KEY = ConfigMgr.getStringCfg(CP_NAME, "pay_key", null);
//        CERT = ConfigMgr.getStringCfg(CP_NAME, "pay_cert", null);
//        CERT_PWD = ConfigMgr.getStringCfg(CP_NAME, "pay_cert_pwd", null);
//        NOTIFY_URL = ConfigMgr.getStringCfg(CP_NAME, "notify_url", null);
//        BODY_1 = ConfigMgr.getStringCfg(CP_NAME, "pay_body_1", null);
//        BODY_2 = ConfigMgr.getStringCfg(CP_NAME, "pay_body_2", null);
//        HOME_URL = ConfigMgr.getStringCfg(CP_NAME, "home_url", null);

    	
    	
    	/*final Properties pro = new Properties();
    	try {
    		InputStream instream = new FileInputStream(new File("/WEB-INF/weixinCert/config.properties"));
			pro.load(instream);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	
        LOGIN_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s#wechat_redirect";
        LOGIN_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        JSAPI_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi";
        USERINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";
        MEDIA_DOWNLOAD_URL = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s";
        UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
        ORDERQUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
        REFUNDQUERY_URL = "https://api.mch.weixin.qq.com/pay/refundquery";
        SENDREDPACK_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
    }

    public static String getLoginAuthorizeUrl(String redirectUri, String scope) {
        return String.format(LOGIN_AUTHORIZE_URL, APPID, redirectUri, scope);
    }

    public static String getLoginAccessTokenUrl(String code) {
        return String.format(LOGIN_ACCESS_TOKEN_URL, APPID, SECRET, code);
    }

    public static String getAccessTokenUrl() {
        return String.format(ACCESS_TOKEN_URL, APPID, SECRET);
    }

    public static String getJsapiTicketUrl(String accessToken) {
        return String.format(JSAPI_TICKET_URL, accessToken);
    }

    public static String getMediaDownloadUrl(String accessToken, String mediaId) {
        return String.format(MEDIA_DOWNLOAD_URL, accessToken, mediaId);
    }

    public static String getUserinfoUrl(String accessToken, String openid) {
        return String.format(USERINFO_URL, accessToken, openid);
    }

    public static String getUnifiedorderUrl() {
        return UNIFIEDORDER_URL;
    }

    public static String getRefundUrl() {
        return REFUND_URL;
    }

    public static String getOrderqueryUrl(){
        return ORDERQUERY_URL;
    }

    public static String getRefundqueryUrl(){
        return REFUNDQUERY_URL;
    }
}
