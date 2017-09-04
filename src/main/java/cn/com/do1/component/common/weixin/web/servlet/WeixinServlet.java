package cn.com.do1.component.common.weixin.web.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.com.do1.component.common.common.utils.XMLTools;
import cn.com.do1.component.common.weixin.db.domain.receive.WxReceMsgEvent;
import cn.com.do1.component.common.weixin.db.domain.send.WxSendMsgTxt;
import cn.com.do1.component.common.weixin.util.WeixinAPI;
import cn.com.do1.component.common.weixin.util.WeixinUtil;

import com.huojuit.hishop.common.utils.SpringContextHolder;
import com.huojuit.hishop.modules.wx.service.WxUserinfoService;

/**
 * get方法配置接口地址，post方法接收消息
 * @author oyl
 *
 */
public class WeixinServlet extends HttpServlet{
	
	private static final Logger log = Logger.getLogger(WeixinServlet.class);
	private static final String DEFAULT_TOKEN = "wxgzh_hp";
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		System.out.println("#################配置微信接口地址");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String wxId = request.getParameter("wxId");
		//查询此微信账号的token
		String TOKEN = DEFAULT_TOKEN;
		System.out.println("==========signature:"+signature);
		System.out.println("==========timestamp:"+timestamp);
		System.out.println("==========nonce:"+nonce);
		System.out.println("==========wxId:"+wxId);
		System.out.println("==========TOKEN:"+DEFAULT_TOKEN);
		
		//验证消息的真实性
		boolean verify = WeixinUtil.verifySign(TOKEN, signature, timestamp, nonce);
		if(!verify){
			System.out.println("#################接收微信数据时验签不通过");
			return ;
		}
		//验签成功后返回echostr给微信即算配置成功
		try {
			System.out.println("#################微信接口地址配置成功,回复微信："+echostr);
			PrintWriter writer = response.getWriter();
	        writer.write(echostr);
	      
	        writer.flush();
	        return ;
		} catch (IOException e) {
			log.error("#######获得请求数据时出错", e);
			e.printStackTrace();
		}
    } 
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		System.out.println("#################接收微信信息");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		String wxId = request.getParameter("wxId");
		System.out.println("==========signature:"+signature);
		System.out.println("==========timestamp:"+timestamp);
		System.out.println("==========nonce:"+nonce);
		System.out.println("==========wxId:"+wxId);
		System.out.println("==========TOKEN:"+DEFAULT_TOKEN);
		//查询此微信账号的token
		String TOKEN = DEFAULT_TOKEN;
		//验证消息的真实性
		boolean verify = WeixinUtil.verifySign(TOKEN, signature, timestamp, nonce);
		if(!verify){
			System.out.println("#################接收微信数据时验签不通过");
			return ; 
		}
		 //读取请求XML
        BufferedInputStream bis = null;
        InputStream is = null;
        String reqXml = null;
        try {
            int length = request.getContentLength();
            byte[] buffer = new byte[length];
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/xml;charset=UTF-8");
            is = request.getInputStream();
            bis = new BufferedInputStream(is);
            bis.read(buffer);
            reqXml = new String(buffer);
        } catch (Exception e) {
            log.error("接收微信请求参数出错", e);
            return ;
        }
        log.error("################接受到的报文为"+reqXml);
        StringReader stringReader = new StringReader(reqXml);
        XMLTools tools = null;
		try {
			tools = new XMLTools(stringReader);
			//插入微信接受消息记录表
			TbWxReceive receive = new TbWxReceive(tools);
			//获得消息类型
			String type = tools.getSinglePropertie("MsgType");
			 if("voice".equals(type)){
				 String servicePath =  request.getSession().getServletContext().getRealPath("") + File.separator;
				 WeixinAPI.getMediaFile(wxId, receive.getMediaId(),servicePath,"amr");
			 }
			
			dealMessageType(type,tools,response,request);
		} catch (IOException e) {
			log.error("#######获得请求数据时出错", e);
			e.printStackTrace();
		} catch (Exception e) {
			log.error("#######获得请求数据时出错", e);
			e.printStackTrace();
		}
    }
	/**
	 * 根据消息类型做出相应处理
	 * @throws Exception 
	 */
	public void dealMessageType(String msgType,XMLTools dom,HttpServletResponse response,HttpServletRequest request) throws Exception{
		Object send = null;
		
		if("event".equals(msgType)){		//事件消息
			WxReceMsgEvent receEvent = new WxReceMsgEvent(dom);
			
//			send = dealMessageEvent(receEvent,dom);
			if("subscribe".equals(receEvent.getEvent())){//订阅
				if(StringUtils.isEmpty(receEvent.getFromUserName())){
					return;
				}
//				IWxapiService wxapiService = DqdpAppContext.getSpringContext().getBean(IWxapiService.class);
				try {
					System.out.println("========发送欢迎消息===");
					String content = " 终于等到您，"
							+ "\n 感谢您关注HI铺，"
							+ "\n 兴隆的生意从Hi铺开始！"
							+ "\n 我是小hi，会尽快回复您的留言！"
							+ "\n 如果您也是急性子可以拨打我们的电话：021-80344870"
							+ "\n 专属客服马上为您服务 "
							+ "\n 我们的服务时间"
							+ "\n 9:00-22:30";
			        
					WxUserinfoService taWxUserinfoService = SpringContextHolder.getBean(WxUserinfoService.class);
			        
					taWxUserinfoService.saveOrUpdateUser(receEvent.getFromUserName(),null);
					WxSendMsgTxt sendTxt = new WxSendMsgTxt(dom);
					sendTxt.setContent(content);
					send = sendTxt;
					WeixinUtil.sendMessage(send,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return;
			}else if("unsubscribe".equals(receEvent.getEvent())){//取消订阅
				if(StringUtils.isEmpty(receEvent.getFromUserName())){
					return;
				}
				try {
					WxUserinfoService taWxUserinfoService = SpringContextHolder.getBean(WxUserinfoService.class);
			        
					taWxUserinfoService.unsubscribe(receEvent.getFromUserName());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			}
			
		}else if("text".equals(msgType)){
			/*WxReceMsgTxt receEvent = new WxReceMsgTxt(dom);
			
//			send = dealMessageEvent(receEvent,dom);
			if("我要抢红包".equals(receEvent.getContent())){//领取红包
				if(StringUtils.isEmpty(receEvent.getFromUserName())){
					return;
				}
				try {
					TaUserMoneyService taUserMoneyService = SpringContextHolder.getBean(TaUserMoneyService.class);
					
					String content = taUserMoneyService.sendWxRedPark("1", receEvent.getFromUserName(), request.getRealPath("/"));
					
					WxSendMsgTxt sendTxt = new WxSendMsgTxt(dom);
					sendTxt.setContent(content);
					send = sendTxt;
					WeixinUtil.sendMessage(send,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return;
			}*/
			/*else if("2".equals(receEvent.getContent())){//奖品领取时间
				//8月25日游戏结束之后，将有客服联系，并发送中奖短信到你手机上，你获得的所有奖品，只限9月10日举行的【株洲汽博园】上线发布会当天凭中奖短信领取！不要关机哦！
				if(StringUtils.isEmpty(receEvent.getFromUserName())){
					return;
				}
				try {
					String content = "8月25日游戏结束之后，将有客服联系，并发送中奖短信到你手机上，你获得的所有奖品，只限9月10日举行的【株洲汽博园】上线发布会当天凭中奖短信领取！不要关机哦！";
					WxSendMsgTxt sendTxt = new WxSendMsgTxt(dom);
					sendTxt.setContent(content);
					send = sendTxt;
					WeixinUtil.sendMessage(send,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return;
			}else if("3".equals(receEvent.getContent())){//查看发布会时间
				if(StringUtils.isEmpty(receEvent.getFromUserName())){
					return;
				}
				try {
					String content = "发布会暂定9月10日的株洲汽车博览园形象店举行，当天有领导，有记者，炎帝广场有专车接送，有水果冷餐，有大奖、好高端的说呢！";
					WxSendMsgTxt sendTxt = new WxSendMsgTxt(dom);
					sendTxt.setContent(content);
					send = sendTxt;
					WeixinUtil.sendMessage(send,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return;
			}else if("4".equals(receEvent.getContent())){//咨询电话
				if(StringUtils.isEmpty(receEvent.getFromUserName())){
					return;
				}
				try {
					String content = "4009028998";
					WxSendMsgTxt sendTxt = new WxSendMsgTxt(dom);
					sendTxt.setContent(content);
					send = sendTxt;
					WeixinUtil.sendMessage(send,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return;
			}*/
		}
		
		
		return ;
	}

}
