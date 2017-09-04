package cn.com.do1.component.common.weixin.db.domain.send;

import java.io.Serializable;

import cn.com.do1.component.common.weixin.db.domain.WxMsgBase;

public class WxSendMsgVideo extends WxMsgBase implements Serializable{

	private String mediaId;
	private String title;
	private String description;
	
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toXmlString(){
		String xml = "<xml>";
		xml += "<ToUserName><![CDATA["+this.getToUserName()+"]]></ToUserName>";
		xml += "<FromUserName><![CDATA["+this.getFromUserName()+"]]></FromUserName>";
		xml += "<CreateTime>"+this.getCreateTime()+"</CreateTime>";
		xml += "<MsgType><![CDATA[video]]></MsgType>";
		xml += "<Video>";
		xml += "<MediaId><![CDATA["+this.getMediaId()+"]]></MediaId>";
		xml += "<Title><![CDATA["+this.getTitle()+"]]></Title>";
		xml += "<Description><![CDATA["+this.getDescription()+"]]></Description>";
		xml += "</Video>";
		xml += "</xml>";
		return xml;
	}
}
