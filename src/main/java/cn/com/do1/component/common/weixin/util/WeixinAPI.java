package cn.com.do1.component.common.weixin.util;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.do1.component.common.common.utils.ConstConfig;
import cn.com.do1.component.common.common.utils.StringUtil;
import cn.com.do1.component.common.weixin.db.domain.AccessToken;
import cn.com.do1.component.common.weixin.db.domain.TbWxSend;
import cn.com.do1.component.common.weixin.db.domain.WxMsgBase;
import cn.com.do1.component.common.weixin.db.domain.send.WxSendMsgImg;
import cn.com.do1.component.common.weixin.db.domain.send.WxSendMsgMusic;
import cn.com.do1.component.common.weixin.db.domain.send.WxSendMsgNews;
import cn.com.do1.component.common.weixin.db.domain.send.WxSendMsgNewsItem;
import cn.com.do1.component.common.weixin.db.domain.send.WxSendMsgTxt;
import cn.com.do1.component.common.weixin.db.domain.send.WxSendMsgVideo;
import cn.com.do1.component.common.weixin.db.domain.send.WxSendMsgVoice;

public class WeixinAPI {

	private static Logger log = LoggerFactory.getLogger(WeixinAPI.class);
			
	/**
	 * 获得token
	 */
	public static AccessToken getAccessToken(String wxId){
		  log.info("###############调用微信接口获取access_token");
		/*   String url = ConstConfig.WX_ACCESS_TOKEN;
		WxAccountService wxAccountService = (WxAccountService)SpringUtil.getBean("wxAccountService");
		TbWxAccount account = wxAccountService.searchAccountByWxid(wxId);
		if(AssertUtil.isEmpty(account)){
			log.info("#################未查询到账号："+wxId+"；请检查是否配置此账号或此账号是否已启用；获取客服接口token失败");
			return new AccessToken();
		}
		long now = new Date().getTime();
		if(AssertUtil.isEmpty(account.getAccess_token())||AssertUtil.isEmpty(account.getExpires_in())){
			//没有access_token，重新获取access_token
			log.info("###############没有access_token，重新获取access_token");
			JSONObject obj = HttpUtil.httpsRequest(url, "GET", "grant_type=client_credential&appid="+account.getApp_id()+"&secret="+account.getApp_key());
			log.info("###############新得到的access_token:"+obj.toString());
			//更新此账号的access_token信息
			account.setAccess_token(obj.getString("access_token"));
			Long time = Long.parseLong(obj.getString("expires_in"));
			Date expires_in = new Date(now+time*1000);
			account.setExpires_in(expires_in);
			log.info("###############更新账号access_token信息");
			wxAccountService.updateData(account);
			return new AccessToken(obj);
		}
		long expires = account.getExpires_in().getTime();
		if(expires-now<200*1000){
			//access_token剩余有效时间不足200秒，重新获取access_token
			log.info("###############access_token剩余有效时间不足200秒，重新获取access_token");
			JSONObject obj = HttpUtil.httpsRequest(url+"?grant_type=client_credential&appid="+account.getApp_id()+"&secret="+account.getApp_key(), "GET", null);
			System.out.println("==============url============="+url+"?grant_type=client_credential&appid="+account.getApp_id()+"&secret="+account.getApp_key());
			log.info("###############新得到的access_token:"+obj.toString());
			//更新此账号的access_token信息
			account.setAccess_token(obj.getString("access_token"));
			Long time = Long.parseLong(obj.getString("expires_in"));
			Date expires_in = new Date(now+time*1000);
			account.setExpires_in(expires_in);
			log.info("###############更新账号access_token信息");
			wxAccountService.updateData(account);
			return new AccessToken(obj);
		}*/
		AccessToken token = new AccessToken();
		//token.setAccessToken(account.getAccess_token());
		token.setExpiresIn("");		//有效时间不变，此处可为空
		return token;
		
	}
	/**
	 * 自定义菜单查询
	 */
	public static JSONObject queryMenu(String wxId){
		log.info("###############调用微信接口查询菜单");
		AccessToken accessToken = WeixinAPI.getAccessToken(wxId);
		String url = ConstConfig.WX_QUERY_MENU+"?access_token="+accessToken.getAccessToken();
		JSONObject obj = HttpUtil.httpsRequest(url, "GET", "");
		return obj;
	}
	/**
	 * 自定义菜单删除
	 */
	public static JSONObject deleteMenu(String wxId){
		log.info("###############调用微信接口删除菜单");
		AccessToken accessToken = WeixinAPI.getAccessToken(wxId);
		String url = ConstConfig.WX_DELETE_MENU+"?access_token="+accessToken.getAccessToken();
		JSONObject obj = HttpUtil.httpsRequest(url, "GET", "");
		return obj;
	}
	/**
	 * 获得关注者列表
	 */
	public static JSONObject getUserList(String token,String NEXT_OPENID){
		log.info("###############调用微信接口获取关注着列表");
		String url = "";
		if("".equals(NEXT_OPENID)){
			url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token;
		}else{
			url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+token+"&next_openid="+NEXT_OPENID;
		}
		JSONObject obj = HttpUtil.httpsRequest(url, "GET", "");
		return obj;
	}
	/**
	 * 获取用户基本信息
	 */
	public static JSONObject getUserInfo(String wxId,String openid){
		log.info("###############调用微信接口获取用户基本信息");
		AccessToken accessToken = WeixinAPI.getAccessToken(wxId);
		//String s  ="NNqhTGLrwTqn4q8vcp3AKEP1hmJV1sRmTfaMAcELz1oWzC-BzQflY5tabF_nEUo0v0j_7yFQSoC8cIZkzUr03Pq4_0Ide2vmvCyM3Ia7LkLaNqqpRkVqhUyXLcpyQ_j2yoK2lO8skAd2-Kq-rZKLYg";
		String url = ConstConfig.WX_USER_INFO+"?access_token="+accessToken.getAccessToken()+"&openid="+openid;
		System.out.println("======url====="+url);
		JSONObject obj = HttpUtil.httpsRequest(url, "GET", "");
		return obj;
	}
	
	
	public static void main(String[] args) {
		
		JSONObject json = getUserInfo("gh_bb2b3b34354e", "oY1FrtyFYrLZzQYh0--lpV5sBtWU");
		System.out.println(json.toString());
	}
	
	
	/**
	 * 下载多媒体文件
	 */
	public static String getMediaFile(String wxId,String mediaId,String serverPath,String fileType){
		log.info("###############调用微信接口下载多媒体文件");
		AccessToken accessToken = WeixinAPI.getAccessToken(wxId);
		String url = ConstConfig.WX_MEDIA_UPLOAD+"?access_token="+accessToken.getAccessToken()+"&media_id="+mediaId;
		
		String filePath = serverPath + ConstConfig.WX_MEDIA_PATH ;
		
		downloadMedia(url, filePath, mediaId, fileType);
		
		if("amr".equals(fileType)){
			transformMp3(filePath + File.separator + mediaId + "." + fileType, filePath + File.separator + mediaId + ".mp3");
		}
		
		return filePath;
	}
	
	
	/**
	 * 下载指定url文件
	 */
	public static void downloadMedia(String urlString, String filePath , String fileName ,String fileType){
		try {
		    URL url = new URL(urlString);
		    URLConnection con = url.openConnection();
		    InputStream is = con.getInputStream();
		    byte[] bs = new byte[1024];
		    int len;
		    
		    File file =new File(filePath); 
		    if  (!file .exists()  && !file .isDirectory()){       
		        file .mkdir();    
		    }
		    
		    String path = filePath + File.separator + fileName + "." + fileType; 
		    OutputStream os = new FileOutputStream(path);
		    while ((len = is.read(bs)) != -1) {
		      os.write(bs, 0, len);
		    }
		    os.close();
		    is.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}    
	
	public static void transformMp3(String path1, String path2){
		File source = new File(path1);
		File target = new File(path2);
		AudioAttributes audio = new AudioAttributes();
		Encoder encoder = new Encoder();

		audio.setCodec("libmp3lame");
		EncodingAttributes attrs = new EncodingAttributes();
		attrs.setFormat("mp3");
		attrs.setAudioAttributes(audio);
		
		try {
			encoder.encode(source, target, attrs);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InputFormatException e) {
			e.printStackTrace();
		} catch (EncoderException e) {
			e.printStackTrace();
		}finally{
			// 上传原文件 
		    if (source.isFile() && source.exists()) {  
		    	source.delete();  
		    }  
		}
	}

	
	
	
	/**
	 * 发送客服消息
	 */
	public static TbWxSend sendMessage(WxMsgBase msg,String token){
		//WeixinService weixinService = (WeixinService)SpringUtil.getBean("weixinService");
//		AccessToken accessToken = WeixinAPI.getAccessToken(msg.getFromUserName());
		String url = ConstConfig.WX_SEND_SERVICE+"?access_token="+token;
		JSONObject message = new JSONObject();
		message.put("touser", msg.getToUserName());
		//插入下发记录
		TbWxSend send = new TbWxSend();
		send.setId(UUID.randomUUID().toString());
		send.setFromUserName(msg.getFromUserName());
		send.setToUserName(msg.getToUserName());
		send.setRecordId(msg.getRecordId());
//		send.setSend_time(new Date(Long.valueOf(msg.getCreateTime())));
		//文本消息
		if(msg instanceof WxSendMsgTxt){
			message.put("msgtype", "text");
			JSONObject a = new JSONObject();
			a.put("content",((WxSendMsgTxt) msg).getContent());
			message.put("text", a);
			send.setMsgType(1);
			send.setContent(((WxSendMsgTxt) msg).getContent());
		}
		//图文消息
		if(msg instanceof WxSendMsgNews){
			message.put("msgtype", "news");
			WxSendMsgNews msgNesws = (WxSendMsgNews)msg;
			JSONObject n = new JSONObject();
			String articles = "";
			for(WxSendMsgNewsItem i : msgNesws.getItems()){
				JSONObject a = new JSONObject();
				a.put("title", i.getTitle());
				a.put("description", i.getDescription());
				String clickurl = i.getUrl();
				if(clickurl.indexOf("?")<0){
					clickurl = clickurl +"?openId="+msgNesws.getToUserName()+"&wxId="+msgNesws.getFromUserName();
				}else{
					clickurl = clickurl +"&openId="+msgNesws.getToUserName()+"&wxId="+msgNesws.getFromUserName();
				}
				a.put("url", clickurl);
				a.put("picurl", i.getPicUrl());
				articles += a.toString() +",";
				send.setContent(i.getDescription());
				send.setPicUrl(i.getPicUrl());
			}
			n.put("articles", "["+articles.substring(0,articles.length())+"]");
			message.put("news", n);
			send.setMsgType(6);
			send.setArticleCount(msgNesws.getArticleCount());
			
			log.info("############message长度:"+message.size());
			send.setArticles(message.toString());
		}
		//图片消息
		if(msg instanceof WxSendMsgImg){
			message.put("msgtype", "image");
			JSONObject a = new JSONObject();
			a.put("media_id",((WxSendMsgImg) msg).getMediaId());
			message.put("image", a);
			send.setMsgType(2);
			send.setMediaId(((WxSendMsgImg) msg).getMediaId());
		}
		//语音消息
		if(msg instanceof WxSendMsgVoice){
			message.put("msgtype", "voice");
			JSONObject a = new JSONObject();
			a.put("media_id",((WxSendMsgVoice) msg).getMediaId());
			message.put("voice", a);
			send.setMsgType(3);
		}
		//视频消息
		if(msg instanceof WxSendMsgVideo){
			message.put("msgtype", "video");
			JSONObject a = new JSONObject();
			WxSendMsgVideo video = (WxSendMsgVideo) msg;
			a.put("media_id",video.getMediaId());
			a.put("title",video.getTitle());
			a.put("description",video.getDescription());
			message.put("video", a);
			send.setMsgType(4);
			send.setMediaId(((WxSendMsgImg) msg).getMediaId());
		}
		//音乐消息
		if(msg instanceof WxSendMsgMusic){
			message.put("msgtype", "music");
			JSONObject a = new JSONObject();
			WxSendMsgMusic music = (WxSendMsgMusic) msg;
			a.put("title",music.getTitle());
			a.put("description",music.getDescription());
			a.put("musicurl",music.getMusicURL());
			a.put("hqmusicurl",music.gethQMusicUrl());
			a.put("thumb_media_id",music.getThumbMediaId());
			message.put("music", a);
			send.setMsgType(5);
			send.setMusicURL(music.getMusicURL());
		}
		//发送消息
		JSONObject obj = HttpUtil.httpsRequest(url, "POST", message.toString());
		//插入下发记录
		if(!StringUtil.isNullEmpty(obj.get("errcode").toString())){
			  send.setStatus(Integer.parseInt(obj.get("errcode").toString()));
		}else{
			  send.setStatus(-1);
		}
		//weixinService.insertSendRecord(send);
		log.info("#####################客服消息发送结果"+obj);
		return send;
	}
}
