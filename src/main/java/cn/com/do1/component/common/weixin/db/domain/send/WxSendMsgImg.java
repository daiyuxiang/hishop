package cn.com.do1.component.common.weixin.db.domain.send;

import java.io.Serializable;

import cn.com.do1.component.common.weixin.db.domain.WxMsgBase;

public class WxSendMsgImg extends WxMsgBase implements Serializable{

	private String mediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	@Override
	public String toXmlString(){
		String xml = "<xml>";
		xml += "<ToUserName><![CDATA["+this.getToUserName()+"]]></ToUserName>";
		xml += "<FromUserName><![CDATA["+this.getFromUserName()+"]]></FromUserName>";
		xml += "<CreateTime>"+this.getCreateTime()+"</CreateTime>";
		xml += "<MsgType><![CDATA[image]]></MsgType>";
		//xml += "<Image>";
		xml += "<MediaId><![CDATA["+this.getMediaId()+"]]></MediaId>";
		//xml += "</Image>";
		xml += "</xml>";
		return xml;
	}
}
