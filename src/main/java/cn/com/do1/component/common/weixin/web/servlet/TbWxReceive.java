package cn.com.do1.component.common.weixin.web.servlet;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import cn.com.do1.component.common.common.annotation.TitleAnnotation;
import cn.com.do1.component.common.common.utils.XMLTools;

public class TbWxReceive implements Serializable {

	private static final Map<String, String> msgType = new HashMap<String, String>();
	
	static {
		// 文本消息
		msgType.put("text", "1");
		// 图片消息
		msgType.put("image", "2");
		// 语音消息
		msgType.put("voice", "3");
		// 视频消息
		msgType.put("video", "4");
		// 音乐消息
		msgType.put("music", "5");
		// 事件消息
		msgType.put("event", "6");
	}
	
	private String id;
	@TitleAnnotation(titleName="消息ID")
	private String msgId;
	
	private String toUserName;
	
	private String fromUserName;
	private String type;
	@TitleAnnotation(titleName="消息类型")
	private String typeName;
	@TitleAnnotation(titleName="文本内容")
	private String content;
	@TitleAnnotation(titleName="图片URL")
	private String picUrl;
	@TitleAnnotation(titleName="媒体id")
	private String mediaId;
	@TitleAnnotation(titleName="语音格式")
	private String voiceFormat;
	@TitleAnnotation(titleName="视频缩略图的媒体id")
	private String thumbMediaId;
	@TitleAnnotation(titleName="创建时间")
	private Date create_time;
	@TitleAnnotation(titleName="接收方账号")
	private String wx_name;	
	@TitleAnnotation(titleName="发送方账号")
	private String nickname;
	private String startTime;
	private String endTime;
	
	private String sendName;
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

	public String getSendContent() {
		return sendContent;
	}

	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	private String receiveName;
	private String sendContent;
	

	private String sendTime;
	public TbWxReceive() {
		super();
	}
	
	public TbWxReceive(XMLTools tools) throws Exception {
		super();
		this.id = UUID.randomUUID().toString();
		this.msgId = tools.getSinglePropertie("MsgId");
		this.toUserName = tools.getSinglePropertie("ToUserName");
		this.fromUserName = tools.getSinglePropertie("FromUserName");
		String MsgType = tools.getSinglePropertie("MsgType");
		this.type = msgType.get(MsgType)!=null?msgType.get(MsgType):"0";
		if("event".equals(MsgType)){
			this.content = tools.getSinglePropertie("Event");
			if(tools.getSinglePropertie("EventKey")!=null){
				this.content += "|"+tools.getSinglePropertie("EventKey");
			}
		}else{
			this.content = tools.getSinglePropertie("Content");
		}
		this.picUrl = tools.getSinglePropertie("PicUrl");
		this.mediaId = tools.getSinglePropertie("MediaId");
		this.voiceFormat = tools.getSinglePropertie("Format");
		this.thumbMediaId = tools.getSinglePropertie("ThumbMediaId");
		String ct = tools.getSinglePropertie("CreateTime");
		this.create_time = new Date(Long.valueOf(ct)*1000);
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getVoiceFormat() {
		return voiceFormat;
	}
	public void setVoiceFormat(String voiceFormat) {
		this.voiceFormat = voiceFormat;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	public Date getCreateTime() {
		return create_time;
	}
	public void setCreateTime(Date create_time) {
		this.create_time = create_time;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public static Map<String, String> getMsgtype() {
		return msgType;
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


	
}
