package cn.com.do1.component.common.weixin.db.domain.send;

import java.io.Serializable;

import cn.com.do1.component.common.weixin.db.domain.WxMsgBase;

public class WxSendMsgMusic extends WxMsgBase implements Serializable{

	private String title;
	private String description;
	private String musicURL;
	private String hQMusicUrl;
	private String thumbMediaId;
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
	public String getMusicURL() {
		return musicURL;
	}
	public void setMusicURL(String musicURL) {
		this.musicURL = musicURL;
	}
	public String gethQMusicUrl() {
		return hQMusicUrl;
	}
	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	@Override
	public String toXmlString(){
		String xml = "<xml>";
		xml += "<ToUserName><![CDATA["+this.getToUserName()+"]]></ToUserName>";
		xml += "<FromUserName><![CDATA["+this.getFromUserName()+"]]></FromUserName>";
		xml += "<CreateTime>"+this.getCreateTime()+"</CreateTime>";
		xml += "<MsgType><![CDATA[music]]></MsgType>";
		xml += "<Music>";
		xml += "<Title><![CDATA["+this.getTitle()+"]]></Title>";
		xml += "<Description><![CDATA["+this.getDescription()+"]]></Description>";
		xml += "<MusicUrl><![CDATA["+this.getMusicURL()+"]]></MusicUrl>";
		xml += "<HQMusicUrl><![CDATA["+this.gethQMusicUrl()+"]]></HQMusicUrl>";
		xml += "<ThumbMediaId><![CDATA["+this.getThumbMediaId()+"]]></ThumbMediaId>";
		xml += "</Music>";
		xml += "</xml>";
		return xml;
	}
}
