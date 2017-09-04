package cn.com.do1.component.common.weixin.api;

import java.util.Map;

import cn.com.do1.component.common.weixin.util.WxapiConstants;

/**
 * 
 * @author louiseliu
 *
 */
public class Signature {

	public static String generateSign(Map<String, String> map){
    	Map<String, String> orderMap = MapUtil.order(map);
		
    	String result = MapUtil.mapJoin(orderMap,true,false);
        result += "&key=" + WxapiConstants.KEY;
        result = MD5.MD5Encode(result).toUpperCase();
       
        return result;
    }
}

