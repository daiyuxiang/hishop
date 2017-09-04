package cn.com.do1.component.common.weixin.db.domain;

import java.io.Serializable;

import net.sf.json.JSONObject;

public class AccessToken implements Serializable{

	private String accessToken;	//获取到的凭证
	private String expiresIn;	//凭证有效时间，单位：秒(7200)
	
	
	public AccessToken() {
		super();
	}
	public AccessToken(JSONObject obj) {
		super();
		this.accessToken = obj.getString("access_token");
		this.expiresIn = obj.getString("expires_in");
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
