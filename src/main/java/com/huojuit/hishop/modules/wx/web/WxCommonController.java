/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.wx.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.do1.component.common.weixin.util.WxapiConstants;

import com.huojuit.hishop.common.config.Global;
import com.huojuit.hishop.common.utils.FileBtye;
import com.huojuit.hishop.common.utils.FileUtils;
import com.huojuit.hishop.common.web.Servlets;
import com.huojuit.hishop.common.web.WxBaseController;
import com.huojuit.hishop.modules.wx.service.WxUserinfoService;


/**
 * 微信用户信息Controller
 * @autho daiyuxiang
 * @version 2016-12-16
 */
@Controller
@RequestMapping(value = "${frontPath}/wx/wxcommon")
public class WxCommonController extends WxBaseController {

	@Autowired
	private WxUserinfoService wxUserInfoService;
	
	@RequestMapping("/shareApi")
	public @ResponseBody Map<String, Object> signShareApi(
			HttpServletResponse response, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String jsapi_ticket = "";
		try {
			jsapi_ticket = wxUserInfoService.queryJsapiTicket();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("jsapi_ticket=" + jsapi_ticket);

		String url = request.getParameter("url");
		String noncestr = getRandomCode(10);
		long str2 = new Date().getTime() / 1000L;
		String timestamp = String.valueOf(str2).substring(0, 10);

		String signature = "";

		String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr="
				+ noncestr + "&timestamp=" + timestamp + "&url=" + url;

		System.out.println("string1=" + string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		map.put("url", url);
		map.put("jsapi_ticket", jsapi_ticket);
		map.put("nonceStr", noncestr);
		map.put("timestamp", timestamp);
		map.put("signature", signature);
		map.put("appid", WxapiConstants.APPID);

		return map;
	}
	
	private static final char[] UPCASE_ARRY = { 'A', 'B', 'C', 'D', 'E', 'F',
		'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
		'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

	private static final char[] LOWER_ARRY = { 'a', 'b', 'c', 'd', 'e', 'f',
		'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
		't', 'u', 'v', 'w', 'x', 'y', 'z' };

	private static final char[] NUMBER_ARRY = { '1', '2', '3', '4', '5', '6',
		'7', '8', '9', '0' };

	private static final char[] TYPE_ARRY = { 'a', 'A', '1' };

	
	public static String getRandomCode(int len) {
		Random rand = new Random();
		int type = 0;
		char ch = 'a';
		StringBuffer sbCode = new StringBuffer();
		for (int i = 0; i < len; i++) {
			type = rand.nextInt(3);
			do {
				if (i == 0)
					ch = UPCASE_ARRY[rand.nextInt(26)];
				else
					ch = getChar(type);
			} while (sbCode.indexOf(String.valueOf(ch)) != -1);

			sbCode.append(ch);
		}

		return sbCode.toString();
	}
	
	private static char getChar(int type) {
		Random rand = new Random();

		char ch = 'a';
		switch (TYPE_ARRY[type]) {
		case 'a':
			ch = LOWER_ARRY[rand.nextInt(26)];
			break;
		case 'A':
			ch = UPCASE_ARRY[rand.nextInt(26)];
			break;
		case '1':
			ch = NUMBER_ARRY[rand.nextInt(10)];
			break;
		}

		return ch;
	}

	private static String byteToHex(byte[] hash) {
		Formatter formatter = new Formatter();
		byte[] arrayOfByte = hash;
		int j = hash.length;
		for (int i = 0; i < j; i++) {
			byte b = arrayOfByte[i];
			formatter.format("%02x", new Object[] { Byte.valueOf(b) });
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
	
	/**
	 * 获取媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param media_id
	 *            媒体文件id
	 * */
	@RequestMapping("/uploadMedia")
	@ResponseBody
	public Map<String, Object> uploadMedia(String mediaId,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpURLConnection conn = null;
		
		try {
			String accessToken = wxUserInfoService.queryAccessToken();
			
			String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
			requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
					"MEDIA_ID", mediaId);
			
			URL url = new URL(requestUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(30000);
			conn.setReadTimeout(30000);
			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
			byte[] buff = new byte[100];
			int rc = 0;
			while ((rc = bis.read(buff, 0, 100)) > 0) {
				swapStream.write(buff, 0, rc);
			}
			byte[] filebyte = swapStream.toByteArray();
			// 得到图片路径
			String baseURL = FileUtils.path(Global.USERFILES_BASE_URL + "frontUser");
			String fileName = System.currentTimeMillis()+".png";
			String filePath = Servlets.getRequest().getContextPath() + baseURL + File.separator + fileName;
			
			// 图片存到缓存
			FileBtye.byte2File(filebyte,baseURL,fileName);
					
			map.put("status", "1");
			map.put("fileName", fileName);
			map.put("url",filePath);
		} catch (Exception e) {
			map.put("status", "0");
			return map;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}
		return map;
	}
}