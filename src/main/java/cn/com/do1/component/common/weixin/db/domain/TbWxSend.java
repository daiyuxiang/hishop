package cn.com.do1.component.common.weixin.db.domain;

import java.io.Serializable;
import java.util.Date;

import cn.com.do1.component.common.common.annotation.TitleAnnotation;

public class TbWxSend implements Serializable{

	private String id;
	@TitleAnnotation(titleName="消息ID")
	private String msg_id;

	private String toUserName;
	
	private String fromUserName;
	private Integer msgType;	//(1 文本 2图片 3语音 4视频  5音乐  6图文 )
	@TitleAnnotation(titleName="消息类型")
	private String msgTypeDesc;
	@TitleAnnotation(titleName="标题")
	private String title;		
	@TitleAnnotation(titleName="文本内容")
	private String content;
	@TitleAnnotation(titleName="消息描述")
	private String description;
	@TitleAnnotation(titleName="媒体id")
	private String mediaId;
	@TitleAnnotation(titleName="音乐链接")
	private String musicURL;
	@TitleAnnotation(titleName="高质量音乐链接")
	private String hqmusic_URL;
	@TitleAnnotation(titleName="音乐缩略图的媒体id")
	private String thumbMediaId;
	@TitleAnnotation(titleName="图文消息个数")
	private Integer articleCount;
	@TitleAnnotation(titleName="多条图文消息信息")
	private String articles;
	@TitleAnnotation(titleName="图片链接")
	private String picUrl;
	@TitleAnnotation(titleName="点击图文消息跳转链接")
	private String clickUrl;
	private Integer status;
	@TitleAnnotation(titleName="发送状态")
	private String statusDesc;
	@TitleAnnotation(titleName="发送时间")
	private Date send_time;
	
	private Date create_time;
	
	private String  ctime;

	private String recordId;
	
	private String color;
	private Integer send_type; //0普通交互 1主动推送
	@TitleAnnotation(titleName="发送方账号")
	private String wx_name;
	@TitleAnnotation(titleName="接收方账号")
	private String nickname;
	
	@TitleAnnotation(titleName="消息接收数")
	private String receive_count;
	
	@TitleAnnotation(titleName="消息发送数")
	private String send_count;
	
	@TitleAnnotation(titleName="消息接收人数")
	private String receiver_count;
	@TitleAnnotation(titleName="微信号")
	private String w_name;

	
	public String getW_name() {
		return w_name;
	}
	public void setW_name(String w_name) {
		this.w_name = w_name;
	}
	private String startTime;
	private String endTime;
	
	private String sendName;
	private String receiveName;
	private String receiveContent;
	private String sendContent;
	private String messageType;
	private String receiveTime;
	private String sendTime;
	private String wx_id;
	private String busi_id;
	private String type;
	private String userName;
	private String fromName;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsg_id() {
		return msg_id;
	}
	public void setMsg_id(String msg_id) {
		this.msg_id = msg_id;
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
	public Integer getMsgType() {
		return msgType;
	}
	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getMusicURL() {
		return musicURL;
	}
	public void setMusicURL(String musicURL) {
		this.musicURL = musicURL;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public Integer getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
	public String getArticles() {
		return articles;
	}
	public void setArticles(String articles) {
		this.articles = articles;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getClickUrl() {
		return clickUrl;
	}
	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	public String getHqmusic_URL() {
		return hqmusic_URL;
	}
	public void setHqmusic_URL(String hqmusic_URL) {
		this.hqmusic_URL = hqmusic_URL;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getSend_type() {
		return send_type;
	}
	public void setSend_type(Integer send_type) {
		this.send_type = send_type;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getMsgTypeDesc() {
		return msgTypeDesc;
	}
	public void setMsgTypeDesc(String msgTypeDesc) {
		this.msgTypeDesc = msgTypeDesc;
	}
	public String getWx_name() {
		return wx_name;
	}
	public void setWx_name(String wx_name) {
		this.wx_name = wx_name;
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
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getSendName() {
		return sendName;
	}
	public void setSendName(String sendName) {
		this.sendName = sendName;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public String getReceiveContent() {
		return receiveContent;
	}
	public void setReceiveContent(String receiveContent) {
		this.receiveContent = receiveContent;
	}
	public String getSendContent() {
		return sendContent;
	}
	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public String getReceive_count() {
		return receive_count;
	}
	public void setReceive_count(String receive_count) {
		this.receive_count = receive_count;
	}
	public String getReceiver_count() {
		return receiver_count;
	}
	public void setReceiver_count(String receiver_count) {
		this.receiver_count = receiver_count;
	}
	public String getSend_count() {
		return send_count;
	}
	public void setSend_count(String send_count) {
		this.send_count = send_count;
	}
	public String getWx_id() {
		return wx_id;
	}
	public void setWx_id(String wx_id) {
		this.wx_id = wx_id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getBusi_id() {
		return busi_id;
	}
	public void setBusi_id(String busi_id) {
		this.busi_id = busi_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	
	
}
