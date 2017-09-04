package cn.com.do1.component.common.weixin.db.domain.send;

import java.io.Serializable;

import org.jdom.Document;
import org.jdom.Element;

import cn.com.do1.component.common.common.utils.XMLTools;
import cn.com.do1.component.common.weixin.db.domain.WxMsgBase;

public class WxSendMsgTxt extends WxMsgBase implements Serializable{

	private String content;

	public String getContent() {
		return content;
	}

	public WxSendMsgTxt() {
		super();
	}

	public WxSendMsgTxt(XMLTools dom) throws Exception {
		super(dom);
		String from = super.getFromUserName();
		String to = super.getToUserName();
		super.setFromUserName(to);
		super.setToUserName(from);
		super.setCreateTime((System.currentTimeMillis() / 1000)+"");
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toXmlString(){
		String xml = "<xml>";
		xml += "<ToUserName><![CDATA["+this.getToUserName()+"]]></ToUserName>";
		xml += "<FromUserName><![CDATA["+this.getFromUserName()+"]]></FromUserName>";
		xml += "<CreateTime>"+this.getCreateTime()+"</CreateTime>";
		xml += "<MsgType><![CDATA[text]]></MsgType>";
		xml += "<Content><![CDATA["+this.getContent()+"]]></Content>";
		xml += "</xml>";
		return xml;
	}
}
