package cn.com.do1.component.common.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class InterfaceUtil {
	
	private final static Logger log = Logger.getLogger(InterfaceUtil.class);
	
	/**
	 * GET方式请求接口
	 * @param reqUrl
	 * @param desc
	 * @return
	 */
	public static String doGet(String reqUrl,String desc){
		String line = null;
		try {
			log.info("接口GET调用，名称为："+desc+"，开始时间为：" + DateUtil.fotmatDate1(new Date()) +
					 "，请求地址为："+reqUrl);
			URL url = new URL(reqUrl);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.connect();
			BufferedReader in = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), "utf-8"));
			line = in.readLine();
		} catch (Exception e) {
			log.error("接口GET调用，名称为："+desc+"，请求异常："+e.getMessage(),e);
		}
		log.info("接口GET调用，名称为："+desc+"，结束时间为："+DateUtil.fotmatDate1(new Date())+"，返回数据为：" + line);
		return line;
	}
	
	/**
	 * POST方式提交
	 * @param reqUrl
	 * @param param
	 * @param desc
	 * @return
	 */
	public static String doPost(String reqUrl,String param,String desc){
		String line = null;
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			List<BasicNameValuePair> paramMap = new ArrayList<BasicNameValuePair>();
			//参数json格式
			//String param = "{\"phone\":\"13999999999\",\"spserviceId\":\"-GZ8080101\"}";
			//进行BASE64加密
//			param = Base64Util.encryptBASE64(param);
			System.out.println(">>>>>>>>>加密后的参数为:"+reqUrl+"?"+param);
			paramMap.add(new BasicNameValuePair("Request", param));
			paramMap.add(new BasicNameValuePair("Action", desc));
			
			HttpPost httppost = new HttpPost(reqUrl);
			HttpEntity re = new UrlEncodedFormEntity(paramMap, HTTP.UTF_8);
			httppost.setEntity(re);
			// 返回对象
			HttpResponse response = httpClient.execute(httppost);
			line = EntityUtils.toString(response.getEntity());
			
		} catch (Exception e) {
			log.error("接口POST调用，名称为："+desc+"，请求异常："+e.getMessage(),e);
		}
		log.info("接口POST调用，名称为："+desc+"，结束时间为："+DateUtil.fotmatDate1(new Date())+"，返回数据为：" + line);
		return line;
	}
	
	public static void main(String[] args) {
		//String param = "{\"phone\":\"13973168351\",\"promotionId\":\"6877\"}";
		String param = "{'Ordereid':'WX2015091414092521','channel':'1','userid':'503444444JSyy','policeno':'3333311595312','username':'张三','cardno':'1','recipients':'0','telephone':'0','mobile':'0','cardarea':'123','address':'111','zftype':'0','zfprice':'10000'}";
		String s = doPost("http://61.187.64.221:81/sfz/WxtoSfz",param, "X");
		String param1 = "{\"Ordereid\":\"WX2015091414092521\"}";
		String s1 = doPost("http://61.187.64.221:81/sfz/WxtoSfz",param, "C");
//		JSONObject json = JSONObject.fromObject(s);
//		String result = json.getString("responseData");
		System.out.println(s);
		System.out.println(s1);
	}
	
}
