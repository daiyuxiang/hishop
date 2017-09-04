/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.huojuit.hishop.modules.shop.web.front;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huojuit.hishop.common.config.Global;
import com.huojuit.hishop.common.utils.ConstantsUtil;
import com.huojuit.hishop.common.utils.MessageBean;
import com.huojuit.hishop.common.web.BaseController;
import com.huojuit.hishop.modules.shop.entity.FindShop;
import com.huojuit.hishop.modules.shop.entity.Tel;
import com.huojuit.hishop.modules.shop.utils.ShopConstants;
import com.huojuit.hishop.modules.sys.entity.Area;
import com.huojuit.hishop.modules.sys.service.AreaService;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * 电话Controller
 * 
 * @author daiyuxiang
 * @version 2017-06-24
 */
@Controller
@RequestMapping(value = "${frontPath}/wx/tel")
public class TelFrontController extends BaseController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private AreaService areaService;

	@RequestMapping(value = "bindAx")
	@ResponseBody
	public MessageBean<String> bindAx(Tel tel, Model model) {
		MessageBean<String> messageBean = new MessageBean<String>();

		try {
			String baseUrl = Global.getConfig("tel.baseUrl");

			String getNumUrl = baseUrl + ConstantsUtil.GET_VTNB_NUM;

			String bindUrl = baseUrl + ConstantsUtil.BIND_AX;

			// 得到虚拟号码
			Map<String, String> numMap = getNum(getNumUrl);

			// 先取消绑定
			if ("0".equals(numMap.get("bindType"))) {
				unbindNum(bindUrl, numMap);
				numMap.put("bindType", "0");
			}

			// 绑定虚拟号码
			bindNum(bindUrl, numMap, tel.getCaller());

			messageBean.setStatus(1);
			logger.info("virtualNumber is======"+numMap.get("virtualNumber"));

			messageBean.setData(numMap.get("virtualNumber"));

		} catch (Exception e) {
			logger.debug(e.getStackTrace().toString());
			messageBean.setStatus(2);
		}

		return messageBean;
	}

	@RequestMapping(value = "tel")
	public String tel(FindShop findShop, Model model) {
		// 查找区域
		Area city = new Area();
		city.setId(ShopConstants.CITY_ID);
		List<Area> areaList = areaService.findAreaByParentId(city);

		model.addAttribute("areaList", areaList);
		model.addAttribute("findShop", findShop);

		return "modules/shop/front/themes/weixin/findShopForm";
	}

	private Map<String, String> getNum(String getNumUrl) {
		Map<String, String> result = new HashMap<String, String>();

		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(getNumUrl);
		String requsetbody = "";

		JSONObject json = new JSONObject();
		json.put("needPage", "n");
		json.put("pageSize", "100");
		json.put("currentPage", "1");
		json.put("shopCode", "80001801");
		json.put("bindType", "null");
		requsetbody = json.toString();

		BasicHttpEntity requestBody = new BasicHttpEntity();
		try {
			requestBody.setContent(new ByteArrayInputStream(requsetbody.getBytes("UTF-8")));
			requestBody.setContentLength(requsetbody.getBytes("UTF-8").length);
			httppost.addHeader("Content-Type", "application/json");
			httppost.addHeader("Authorization", ConstantsUtil.AUTHORIZATION);
			httppost.setEntity(requestBody);
			HttpResponse response;
			response = httpclient.execute(httppost);
			HttpEntity resEntity = (HttpEntity) response.getEntity();
			InputStreamReader reader = new InputStreamReader(resEntity.getContent(), "utf-8");
			char[] buff = new char[10240];
			int length = 0;

			while ((length = reader.read(buff)) != -1) {
				String str = new String(buff, 0, length);

				try {
					JSONObject r = JSONObject.fromObject(str);

					if (r.get("resultCode").equals("0")) {
						JSONObject model = (JSONObject) r.get("model");
						JSONArray list = (JSONArray) model.get("list");

						int index = 0;
						for (int i = 0; i < list.size(); i++) {
							Map m = list.getJSONObject(i);
							if ("na".equals(m.get("bindType"))) {
								index = i;
							}
						}

						Map temp = list.getJSONObject(index);

						result.put("virtualNumber", (String) temp.get("xNumber"));
						if ("na".equals(temp.get("bindType"))) {
							result.put("bindType", "1");
						} else {
							result.put("bindType", "0");
						}

					}

				} catch (JSONException e) {
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

		return result;
	}

	private void bindNum(String bindUrl, Map<String, String> numMap, String caller) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(bindUrl);
		String requsetbody = "";

		JSONObject json = new JSONObject();
		json.put("virtualNumber", numMap.get("virtualNumber"));
		json.put("bindNumber", caller);
		json.put("bindType", numMap.get("bindType"));
		json.put("extendParam", "{}");
		requsetbody = json.toString();

		BasicHttpEntity requestBody = new BasicHttpEntity();
		try {
			requestBody.setContent(new ByteArrayInputStream(requsetbody.getBytes("UTF-8")));
			requestBody.setContentLength(requsetbody.getBytes("UTF-8").length);
			httppost.addHeader("Content-Type", "application/json");
			httppost.addHeader("Authorization", ConstantsUtil.AUTHORIZATION);
			httppost.setEntity(requestBody);
			HttpResponse response;
			response = httpclient.execute(httppost);
			HttpEntity resEntity = (HttpEntity) response.getEntity();
			InputStreamReader reader = new InputStreamReader(resEntity.getContent(), "utf-8");
			char[] buff = new char[1024];
			int length = 0;
			while ((length = reader.read(buff)) != -1) {
				String str = new String(buff, 0, length);

				try {
					JSONObject r = JSONObject.fromObject(str);

					if (r.get("resultCode").equals("0")) {
						logger.info("======bind success======");
					}

				} catch (JSONException e) {
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

	private void unbindNum(String bindUrl, Map<String, String> numMap) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(bindUrl);
		String requsetbody = "";

		JSONObject json = new JSONObject();
		json.put("virtualNumber", numMap.get("virtualNumber"));
		json.put("bindNumber", "");
		json.put("bindType", numMap.get("bindType"));
		json.put("extendParam", "{}");
		requsetbody = json.toString();

		BasicHttpEntity requestBody = new BasicHttpEntity();
		try {
			requestBody.setContent(new ByteArrayInputStream(requsetbody.getBytes("UTF-8")));
			requestBody.setContentLength(requsetbody.getBytes("UTF-8").length);
			httppost.addHeader("Content-Type", "application/json");
			httppost.addHeader("Authorization", ConstantsUtil.AUTHORIZATION);
			httppost.setEntity(requestBody);
			HttpResponse response;
			response = httpclient.execute(httppost);
			HttpEntity resEntity = (HttpEntity) response.getEntity();
			InputStreamReader reader = new InputStreamReader(resEntity.getContent(), "utf-8");
			char[] buff = new char[1024];
			int length = 0;
			while ((length = reader.read(buff)) != -1) {
				String str = new String(buff, 0, length);

				try {
					JSONObject r = JSONObject.fromObject(str);

					if (r.get("resultCode").equals("0")) {
						logger.info("======cancel bind success======");
					}
				} catch (JSONException e) {
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