package cn.com.do1.component.common.weixin.db.domain.send;

public class WxSendMsgNewsItem {

	private String title;
	private String description;
	private String picUrl;
	private String url;
	private String media_id;
	
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
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String toXmlString(){
		String xml = "<item>";
		xml += "<Title><![CDATA["+this.getTitle()+"]]></Title>";
		xml += "<Description><![CDATA["+this.getDescription()+"]]></Description>";
		xml += "<PicUrl><![CDATA["+this.getPicUrl()+"]]></PicUrl>";
		xml += "<Url><![CDATA["+this.getUrl()+"]]></Url>";
		xml += "</item>";
		return xml;
	}
	
	@Override
	public String toString(){
		return "WxSendMsgNewsItem[title="+this.getTitle()+",description="+this.getDescription()
				+",picUrl="+this.getPicUrl()+",url="+this.getUrl()+"]";
	}
	public String getMedia_id() {
		return media_id;
	}
	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}
}
