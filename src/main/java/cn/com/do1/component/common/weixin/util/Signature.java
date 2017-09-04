package cn.com.do1.component.common.weixin.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import cn.com.do1.component.common.wxapi.payapi.PayResData;

/**
 * User: rizenguo
 * Date: 2014/10/29
 * Time: 15:23
 */
public class Signature {

    private static Logger logger = LoggerFactory.getLogger(Signature.class);

    /**
     * 签名算法
     *
     * @param o 要参与签名的数据对象
     * @return 签名
     * @throws IllegalAccessException
     */
    public static String getSign(Object o) throws IllegalAccessException {
        ArrayList<String> list = new ArrayList<String>();
        Class cls = o.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.get(o) != null && f.get(o) != "") {
                list.add(f.getName() + "=" + f.get(o) + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        result += "key=" + WxapiConstants.KEY;
        logger.info("Sign Before MD5:" + result);
        result = MD5.MD5Encode(result).toUpperCase();
        logger.info("Sign Result:" + result);
        return result;
    }

    public static String getSign(Map<String, Object> map) {

        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() != "") {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
                System.out.println("........."+entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        String result = sb.toString();
        
        result += "key=" + WxapiConstants.KEY;
        System.out.println("Sign Before MD5:" + result);
        result = MD5.MD5Encode(result).toUpperCase();
        System.out.println("Sign Result:" + result);
        return result;
    }

    /**
     * 从API返回的XML数据里面重新计算一次签名
     *
     * @param responseString API返回的XML数据
     * @return 新鲜出炉的签名
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static String getSignFromResponseString(String responseString) throws IOException, SAXException, ParserConfigurationException {
        Map<String, Object> map = Util.getMapFromXML(responseString);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign", "");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        return Signature.getSign(map);
    }

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     *
     * @param responseString API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    public static boolean checkIsSignValidFromResponseString(String responseString) throws ParserConfigurationException, IOException, SAXException {

        Map<String, Object> map = Util.getMapFromXML(responseString);
        logger.info(map.toString());

        String signFromAPIResponse = map.get("sign").toString();
        if ( signFromAPIResponse == null || signFromAPIResponse.trim().isEmpty()) {
            logger.info("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            System.out.println("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            return false;
        }
        logger.info("服务器回包里面的签名是:" + signFromAPIResponse);
        System.out.println("服务器回包里面的签名是:" + signFromAPIResponse);
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign", "");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = Signature.getSign(map);

        if (!signForAPIResponse.equals(signFromAPIResponse)) {
            //签名验不过，表示这个API返回的数据有可能已经被篡改了
            logger.info("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
            System.out.println("API返回的数据签名验证不通过，有可能被第三方篡改!!!");
            return false;
        }
        logger.info("恭喜，API返回的数据签名验证通过!!!");
        return true;
    }
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, Exception {
		String resXml = "<xml><return_code><![CDATA[SUCCESS]]></return_code>"+
			"<return_msg><![CDATA[OK]]></return_msg>"+
			"<appid><![CDATA[wxe8a44999b888cd60]]></appid>"+
			"<mch_id><![CDATA[1454840402]]></mch_id>"+
			"<device_info><![CDATA[WEB]]></device_info>"+
			"<nonce_str><![CDATA[rNZLWL5f0JgNgpTO]]></nonce_str>"+
			"<sign><![CDATA[253BAD4963F03947648C75EFF4474796]]></sign>"+
			"<result_code><![CDATA[SUCCESS]]></result_code>"+
			"<prepay_id><![CDATA[wx2017040609491262b2fc7e290761031722]]></prepay_id>"+
			"<trade_type><![CDATA[JSAPI]]></trade_type>"+
			"</xml>";
		
//		String resXml = GetWxOrderno.getPayNo(WxapiConstants.getUnifiedorderUrl(), reqXml);
        System.out.println("========resXml"+resXml);
        if (!Signature.checkIsSignValidFromResponseString(resXml)){
            throw new Exception("返回数据签名失败");
        }
        resXml = resXml.replace("<xml>", "<?xml version=\"1.0\" encoding=\"UTF8\"?><xml>");
        
        System.out.println("========resXml end ===========");
        PayResData resData = (PayResData) Util.getObjectFromXML(resXml, PayResData.class);
        
	}
}
