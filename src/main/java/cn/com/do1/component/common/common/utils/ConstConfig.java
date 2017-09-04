package cn.com.do1.component.common.common.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ConstConfig 定义各种静态常量,各个类要用到各个常量,尽量在此类 中定义.并且初始化全局变量
 * 
 */
public class ConstConfig {

	private transient final static Logger log = LoggerFactory.getLogger(ConstConfig.class);
	static {
		// MERCHANDISE_SPEC = "mchdise_spec";
		try {
			//init();// 用于EJB访问时装入相应属性，如果是用WEB启动，会被InitPropertyServlet覆盖
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}
	/** TOMCAT路径 */
	public static String TOMCAT_CONTEXT_PATH;
	/**	图片上传目录 */
	public static String UPLOADFILE_PATH;
	/**	视频上传目录 */
	public static String UPLOADVIDEO_PATH;
	/**	音乐上传目录 */
	public static String UPLOADMUSIC_PATH;
	/** 绑定手机号地址 */
	public static String BIND_MOBILE_PATH;
	/**	微信多媒体下载目录 */
	public static String WX_MEDIA_PATH;
	/** 微信账号  **/
	public static String WX_APP_ID;
	/** 微信密码  **/
	public static String WX_APP_SECRET;
	/** access_token地址  **/
	public static String WX_ACCESS_TOKEN;
	/** 自定义菜单  **/
	public static String WX_DIY_MENU;
	/** 查询菜单  **/
	public static String WX_QUERY_MENU;
	/** 删除菜单  **/
	public static String WX_DELETE_MENU;
	/** 获得关注者详情  **/
	public static String WX_USER_INFO;
	/** 发送客服消息  **/
	public static String WX_SEND_SERVICE = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
	/** 下载多媒体文件  **/
	public static String WX_MEDIA_UPLOAD;
	/** 免费专区业务办理地址 **/
	public static String INTER_BUSI_PATH_FREE;
	/** 注册会员接口地址  **/
	public static String INTER_VIP_REGIST;
	/** 是否为VIP接口地址 **/
	public static String INTER_ISVIP;
	/** 点播优惠券下发接口（普通用户使用） **/
	public static String INTER_DB_COUPON;
	/** 优惠券下发接口（特别订制） **/
	public static String INTER_SPECIAL_COUPON;
	/** 定制化同步优惠券的图片地址上下文 **/
	public static String INTER_SPECIAL_PIC_CONTEXT;
	/** 注册会员地址 **/
	public static String PAGE_VIP_REGIST;
	/** 短信下发接口 **/
	public static String INTER_SMS_SEND;
	/** 应用schema **/
	public static String REQUEST_SCHEMA;
	/** 微信端的投诉页面 **/
	public static String WEIXIN_COMPLAIN;
	/** 飞信access_token地址   **/
	public static String FX_ACCESS_TOKEN;
	/** 获得飞信关注者详情  **/
	public static String FX_USER_INFO;
	/** 飞信发送客服消息  **/
	public static String FX_SEND_SERVICE;
	/** 飞信创建菜单  **/
	public static String FX_CREATE_MENU;
	/** 飞信查询菜单  **/
	public static String FX_QUERY_MENU;
	/** 飞信删除菜单  **/
	public static String FX_DELETE_MENU;
	
	/**appid*/
	public static String APP_ID;
	
	public static String FX_SEND_MESSAGE;
	/**微信搜索功能，图片地址*/
	public static String WX_SEARCH_IMG;
	/**微信搜索功能，请求地址*/
	public static String WX_SEARCH_REQ;
	/**微信搜索功能，详情地址*/
	public static String WX_SEARCH_DETAIL;
	
	public static String WX_LOCLAHOST;
	
	public static String WX_USER;
	
	public static String WX_UDP;
	
	static public void init() {
		final Properties pro = new Properties();
		try {
			pro.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		
		ConstConfig.APP_ID=pro.getProperty("app.id");
		ConstConfig.TOMCAT_CONTEXT_PATH=pro.getProperty("tomcat.context.path");
		ConstConfig.UPLOADFILE_PATH=pro.getProperty("uploadfile.path");
		ConstConfig.UPLOADVIDEO_PATH=pro.getProperty("uploadVideo.path");
		ConstConfig.UPLOADMUSIC_PATH=pro.getProperty("uploadMusic.path");
		ConstConfig.BIND_MOBILE_PATH=pro.getProperty("bind.mobile.path");
		ConstConfig.WX_MEDIA_PATH=pro.getProperty("wx.media.path");
		ConstConfig.WX_APP_ID=pro.getProperty("wx.app.id");
		ConstConfig.WX_APP_SECRET=pro.getProperty("wx.app.secret");
		ConstConfig.WX_ACCESS_TOKEN=pro.getProperty("wx.access.token");
		ConstConfig.WX_DIY_MENU=pro.getProperty("wx.diy.menu");
		ConstConfig.WX_QUERY_MENU=pro.getProperty("wx.query.menu");
		ConstConfig.WX_DELETE_MENU=pro.getProperty("wx.delete.menu");
		ConstConfig.WX_USER_INFO=pro.getProperty("wx.user.info");
		ConstConfig.WX_SEND_SERVICE=pro.getProperty("wx.send.service");
		ConstConfig.WX_MEDIA_UPLOAD=pro.getProperty("wx.media.upload");		
		ConstConfig.INTER_BUSI_PATH_FREE=pro.getProperty("inter.busi.path.free");
		ConstConfig.INTER_VIP_REGIST=pro.getProperty("inter.vip.regist");
		ConstConfig.INTER_ISVIP=pro.getProperty("inter.isvip");
		ConstConfig.INTER_DB_COUPON=pro.getProperty("inter.db.coupon");
		ConstConfig.INTER_SPECIAL_COUPON=pro.getProperty("inter.special.coupon");
		ConstConfig.INTER_SPECIAL_PIC_CONTEXT=pro.getProperty("inter.special.pic.context");
		ConstConfig.PAGE_VIP_REGIST=pro.getProperty("page.vip.regist");
		ConstConfig.INTER_SMS_SEND=pro.getProperty("inter.sms.send");
		ConstConfig.REQUEST_SCHEMA=pro.getProperty("request.schema");
		ConstConfig.WEIXIN_COMPLAIN=pro.getProperty("weixin.complain");
		ConstConfig.FX_ACCESS_TOKEN=pro.getProperty("fx.access.token");
		ConstConfig.FX_USER_INFO=pro.getProperty("fx.user.info");
		ConstConfig.FX_SEND_SERVICE=pro.getProperty("fx.send.service");
		ConstConfig.FX_CREATE_MENU=pro.getProperty("fx.create.menu");
		ConstConfig.FX_QUERY_MENU=pro.getProperty("fx.query.menu");
		ConstConfig.FX_DELETE_MENU=pro.getProperty("fx.delete.menu");
		ConstConfig.FX_SEND_MESSAGE=pro.getProperty("fx.send.message");
		
		ConstConfig.WX_SEARCH_IMG=pro.getProperty("wx.search.img");
		ConstConfig.WX_SEARCH_REQ=pro.getProperty("wx.search.req");
		ConstConfig.WX_SEARCH_DETAIL=pro.getProperty("wx.search.detail");
		ConstConfig.WX_LOCLAHOST=pro.getProperty("wx.localhost");
		ConstConfig.WX_USER=pro.getProperty("wx.user");
		ConstConfig.WX_UDP=pro.getProperty("wx.udp");
	}
}
