package cn.com.do1.component.common.weixin.db.domain;

import java.io.Serializable;

import cn.com.do1.component.common.common.utils.XMLTools;

public class WxMsgBase  implements Serializable {
	
	private String toUserName;			//开发者微信号
	private String fromUserName;		//发送方帐号（一个OpenID）
	private String createTime;			//消息创建时间 （整型）
	private String msgType;				//text
	private String key;
	private String nickname;
	private String recordId;
	
	public WxMsgBase() {
		super();
	}
	public WxMsgBase(XMLTools dom) throws Exception {
		super();
		this.toUserName = dom.getSinglePropertie("ToUserName");
		this.fromUserName = dom.getSinglePropertie("FromUserName");
		this.createTime = dom.getSinglePropertie("CreateTime");
		this.msgType = dom.getSinglePropertie("MsgType");
	}
	public String toXmlString() {
		String xml = "<xml>";
		xml += "<ToUserName><![CDATA["+this.getToUserName()+"]]></ToUserName>";
		xml += "<FromUserName><![CDATA["+this.getFromUserName()+"]]></FromUserName>";
		xml += "<CreateTime>"+this.getCreateTime()+"</CreateTime>";
		xml += "<MsgType><![CDATA[text]]></MsgType>";
		xml += "</xml>";
		return xml;
	}
	
	@Override
	public String toString() {
		return "WxMsgBase [toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + "]";
	}
	public String getToUserName() {
		return toUserName;
	}
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	public String getFromUserName() {
		return fromUserName;
	}
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	
	
}
