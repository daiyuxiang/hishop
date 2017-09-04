package cn.com.do1.component.common.weixin.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import cn.com.do1.component.common.common.utils.HashKit;
import cn.com.do1.component.common.weixin.db.domain.TbWxSend;
import cn.com.do1.component.common.weixin.db.domain.WxMsgBase;
import cn.com.do1.component.common.weixin.db.domain.send.WxSendMsgNews;
import cn.com.do1.component.common.weixin.db.domain.send.WxSendMsgTxt;


public class WeixinUtil {
	
	private static final Logger log = Logger.getLogger(WeixinUtil.class);
	
	/**
	 * 验证消息真实性
	 * @param token
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean verifySign(String token,String signature,String timestamp,String nonce){
		boolean verify = false;
		if(null != signature&&null != timestamp&&null != nonce){
			List<String> ss = new ArrayList<String>();
			ss.add(timestamp);
			ss.add(nonce);
			ss.add(token);
			
			Collections.sort(ss);
			
			StringBuilder builder = new StringBuilder();
			for(String s : ss) {
				builder.append(s);
			}
			verify = signature.equalsIgnoreCase(HashKit.sha1(builder.toString()));
		}
		return verify;
	}
	
	/**
	 * 回复即时消息
	 * @throws IOException 
	 */
	public static void sendMessage(Object obj, HttpServletResponse response) throws IOException{
		try {
			//WeixinService weixinService = (WeixinService)SpringUtil.getBean("weixinService");
			String message = "";
			WxMsgBase base = (WxMsgBase)obj;
			TbWxSend send = new TbWxSend();
			send.setId(UUID.randomUUID().toString());
			send.setFromUserName(base.getFromUserName());
			send.setToUserName(base.getToUserName());
			send.setSend_time(new Date(Long.valueOf(base.getCreateTime())));
			if(obj instanceof WxSendMsgTxt){
				//发送文本类型消息
				WxSendMsgTxt txt = (WxSendMsgTxt)obj;
				send.setMsgType(1);
				send.setContent(txt.getContent());
				message = txt.toXmlString();
			}
			if(obj instanceof WxSendMsgNews){
				//发送图文消息
				WxSendMsgNews news = (WxSendMsgNews)obj;
				send.setMsgType(6);
				String st="";
			   if(news.getArticleCount()!=null&&news.getArticleCount() > 0){
				for (int i=0;i<news.getArticleCount();i++) {
					st+=news.getItems().get(i).getTitle()+",";
					send.setContent(st);	

				}
			}
				send.setArticleCount(news.getArticleCount());
				send.setArticles(news.getItems().toString());
				message = news.toXmlString();
			}
			//插入发送记录表
			send.setStatus(0);
			//weixinService.insertSendRecord(send);
			log.info("#############回复微信的报文为："+message);
            PrintWriter writer = response.getWriter();
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            log.error("回复微信出错", e);
        }
	}
	public static String replaceRubbish(String cont){
		cont = cont.replaceAll("(?s)\\<script.*?\\</script\\>","");
		cont = cont.replaceAll("(?s)\\<style.*?\\</style\\>","");
		cont = cont.replaceAll("(?s)\\<meta.*?>", "");
		cont = cont.replaceAll("(?s)\\<html>", "");
		cont = cont.replaceAll("(?s)\\</html>", "");
		cont = cont.replaceAll("(?s)\\<body>", "");
		cont = cont.replaceAll("(?s)\\</body>", "");
		cont = cont.replaceAll("(?s)\\<head>", "");
		cont = cont.replaceAll("(?s)\\</head>", "");
		cont = cont.replaceAll("#换行#", "\n");
//		cont = cont.replaceAll("(?s)\\<a .*?\\</a\\>", "");
//		cont = cont.replaceAll("(?s)\\<iframe.*?\\</iframe\\>", "");
//		cont = cont.replaceAll("(?s)\\<img.*?\\</img\\>", "");
//		cont = cont.replaceAll("(?s)\\<form.*?\\</form\\>", "");
//		cont = cont.replaceAll("(?s)\\<textarea.*?\\</textarea\\>", "");
//		cont = cont.replaceAll("(?s)\\<object.*?\\</object\\>", "");
//		cont = cont.replaceAll("(?s)\\<!--.*?--\\>", "");
//		cont = cont.replaceAll("<.*?>", "");
//		cont = cont.replaceAll("<br>", ""); 
//		cont = cont.replaceAll("</br>", ""); 
		return cont;
	}
	
}
