package cn.com.do1.component.common.weixin.db.domain.receive;


import java.io.Serializable;

import org.jdom.Document;

import cn.com.do1.component.common.common.utils.XMLTools;
import cn.com.do1.component.common.weixin.db.domain.WxMsgBase;

/**
 * 微信发送的事件消息，使用FromUserName + CreateTime 排重
 * @author oyl
 *
 */
public class WxReceMsgEvent extends WxMsgBase implements Serializable{

	private String event;			//事件类型，subscribe(订阅)、unsubscribe(取消订阅)、scan、LOCATION、CLICK
	//（subscribe）事件KEY值，qrscene_为前缀，后面为二维码的参数值
	//(scan)事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
	private String eventKey;		
	private String ticket;			//二维码的ticket，可用来换取二维码图片
	private String latitude;		//地理位置纬度
	private String longitude;		//地理位置经度
	private String precision;		//地理位置精度
	
	public WxReceMsgEvent(){
		super();
	}
	public WxReceMsgEvent(XMLTools dom) throws Exception {
		super(dom);
		this.event = dom.getSinglePropertie("Event");
		this.eventKey = dom.getSinglePropertie("EventKey");
		this.ticket = dom.getSinglePropertie("Ticket");
		this.latitude = dom.getSinglePropertie("Latitude");
		this.longitude = dom.getSinglePropertie("Longitude");
		this.precision = dom.getSinglePropertie("Precision");
	}
	
	@Override
	public String toString() {
		return "WxReceMsgEvent [event=" + event + ", eventKey=" + eventKey
				+ ", ticket=" + ticket + ", latitude=" + latitude
				+ ", longitude=" + longitude + ", precision=" + precision
				+ ", toString()=" + super.toString() + "]";
	}
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEventKey() {
		return eventKey;
	}
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getPrecision() {
		return precision;
	}
	public void setPrecision(String precision) {
		this.precision = precision;
	}
	
}
