package cn.com.do1.component.common.weixin.api;

import org.apache.log4j.Logger;

/**
 * 
 * @author louiseliu
 *
 */
public class PayUtils {
	private static final Logger logger = Logger.getLogger(PayUtils.class);
	 
	
	
	public static String generatePaySuccessReplyXML(){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<xml>")
					.append("<return_code><![CDATA[SUCCESS]]></return_code>")
					//.append("<return_msg><![CDATA[OK]]></return_msg>") //如非空，为错误原因：签名失败
					.append("</xml>");
		return stringBuffer.toString();
	}

}
