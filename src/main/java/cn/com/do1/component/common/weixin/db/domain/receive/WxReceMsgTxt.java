package cn.com.do1.component.common.weixin.db.domain.receive;

import java.io.Serializable;

import org.jdom.Document;

import cn.com.do1.component.common.common.utils.XMLTools;
import cn.com.do1.component.common.weixin.db.domain.WxMsgBase;

public class WxReceMsgTxt extends WxMsgBase implements Serializable{

	private String content;				//文本消息内容
	private String msgId;				//消息id，64位整型
	
	public WxReceMsgTxt() {
		super();
	}
	public WxReceMsgTxt(XMLTools dom) throws Exception {
		super(dom);
		this.content = dom.getSinglePropertie("Content");
		this.msgId = dom.getSinglePropertie("MsgId");
	}
	@Override
	public String toString() {
		return "WxMsgTxt [toUserName=" + super.getToUserName() + ", fromUserName="
				+ super.getFromUserName() + ", createTime=" + super.getCreateTime() + ", msgType="
				+ super.getMsgType() + ", content=" + content + ", msgId=" + msgId + "]";
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
}
