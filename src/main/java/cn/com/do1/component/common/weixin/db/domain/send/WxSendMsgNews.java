package cn.com.do1.component.common.weixin.db.domain.send;

import java.io.Serializable;
import java.util.List;

import cn.com.do1.component.common.weixin.db.domain.WxMsgBase;

public class WxSendMsgNews extends WxMsgBase implements Serializable{

	private Integer articleCount;
	private List<WxSendMsgNewsItem> items;
	
	public Integer getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	public List<WxSendMsgNewsItem> getItems() {
		return items;
	}
	public void setItems(List<WxSendMsgNewsItem> items) {
		this.items = items;
	}
	
	@Override
	public String toXmlString(){
		String xml = "<xml>";
		xml += "<ToUserName><![CDATA["+this.getToUserName()+"]]></ToUserName>";
		xml += "<FromUserName><![CDATA["+this.getFromUserName()+"]]></FromUserName>";
		xml += "<CreateTime>"+this.getCreateTime()+"</CreateTime>";
		xml += "<MsgType><![CDATA[news]]></MsgType>";
		xml += "<ArticleCount>"+this.getArticleCount()+"</ArticleCount>";
		xml += "<Articles>";
		for(int i=0;i<items.size();i++){
			xml += items.get(i).toXmlString();
		}
		xml += "</Articles>";
		xml += "</xml>";
		return xml;
	}
	
	@Override
	public String toString() {
		return "WxSendMsgNews [toUserName=" + super.getToUserName() + ", fromUserName="
				+ super.getFromUserName() + ", createTime=" + super.getCreateTime() + ", msgType="
				+ super.getMsgType() + ", articleCount="+this.getArticleCount()+" ,Articles="+this.getItems().toString()+"]";
	}
}
