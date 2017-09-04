package com.huojuit.hishop.modules.job;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.huojuit.hishop.common.config.Global;
import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.common.utils.DateUtils;
import com.huojuit.hishop.modules.shop.entity.Tel;
import com.huojuit.hishop.modules.shop.service.TelService;

@Service
@Lazy(false)
public class TelTask {
	@Autowired
	private TelService telService;
	
	
	// 没半小时获取话单列表
	@Scheduled(cron = "0 0/30 * * * ?")
	public void getTelRecord() {
		String baseUrl = Global.getConfig("tel.baseUrl");
		String getTelRecordUrl = baseUrl + ConstantsUtil.GET_CDR;

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(getTelRecordUrl);
		String requsetbody = "";
		
		long currentTime = System.currentTimeMillis();

		JSONObject json = new JSONObject();
		json.put("needPage", "y");
		json.put("pageSize", "1000");
		json.put("currentPage", 1);
		json.put("shopCode", "8001801");
		json.put("companyCode", "null");
		json.put("callTypeList", "[]");
		json.put("uniqueId", "null");
		json.put("startTimeBegin", DateUtils.getTimeBeforeHalfHour(currentTime));
		json.put("startTimeEnd",  currentTime);
		json.put("callerAnswerTimeBegin", "null");
		json.put("callerAnswerTimeEnd", "null");
		json.put("calleeAnswerTimeBegin", "null");
		json.put("calleeAnswerTimeEnd", "null");
		json.put("endTimeBegin", "null");
		json.put("endTimeEnd", "null");
		requsetbody = json.toString();
		
		System.out.println(DateUtils.getTimeBeforeHalfHour(currentTime));
		System.out.println(currentTime);
		
		BasicHttpEntity requestBody = new BasicHttpEntity();
		try {
			requestBody.setContent(new ByteArrayInputStream(requsetbody
					.getBytes("UTF-8")));
			requestBody.setContentLength(requsetbody.getBytes("UTF-8").length);
			httppost.addHeader("Content-Type", "application/json");
			httppost.addHeader("Authorization", ConstantsUtil.AUTHORIZATION);
			httppost.setEntity(requestBody);
			HttpResponse response;
			response = httpclient.execute(httppost);
			HttpEntity resEntity = (HttpEntity) response.getEntity();
			InputStreamReader reader = new InputStreamReader(
					resEntity.getContent(), "utf-8");
			char[] buff = new char[10240];
			int length = 0;
			while ((length = reader.read(buff)) != -1) {
				System.out.println(new String(buff, 0, length));
				String str = new String(buff, 0, length);

				try {
					JSONObject r = JSONObject.fromObject(str);

					if (r.get("resultCode").equals("0")) {
						JSONObject model = (JSONObject)r.get("model");
						JSONArray list = (JSONArray)model.get("list");
						
						for(int i=0;i<list.size();i++) {
							Map<String,String> m = list.getJSONObject(i);
							
							Tel tel = new Tel();
							tel.setCaller(m.get("caller"));
							tel.setCallee(m.get("callee"));
							
							SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
						    String d = format.format(m.get("startTime"));  
						    Date date= format.parse(d);  

							tel.setCallTime(date);
							tel.setTalkTime(String.valueOf(m.get("duration")));
							
							telService.save(tel);
						}
					}

				} catch (JSONException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (httpclient != null)
				httpclient.getConnectionManager().shutdown();
		}
	}
}
