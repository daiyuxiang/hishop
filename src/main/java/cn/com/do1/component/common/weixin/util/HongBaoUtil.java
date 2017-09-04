package cn.com.do1.component.common.weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.UUID;

import javax.net.ssl.SSLContext;
import javax.security.cert.CertificateException;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.com.do1.component.common.weixin.db.domain.send.WxSendredpack;


public class HongBaoUtil {

	public static final int FAIL = 0;    //领取失败  
    public static final int SUCCESS = 1; //领取成功  
    public static final int LOCK = 2;    //已在余额表中锁定该用户的余额,防止领取的红包金额大于预算 
    
	/** 
     * 对请求参数名ASCII码从小到大排序后签名 
     * @param openid 
     * @param userId 
     * @return 
     */  
    public static void sign(SortedMap<String, String> params){  
        Set<Entry<String,String>> entrys=params.entrySet();    
        Iterator<Entry<String,String>> it=entrys.iterator();    
        String result = "";  
        while(it.hasNext())    
        {    
           Entry<String,String> entry=it.next();    
           result +=entry.getKey()+"="+entry.getValue()+"&";  
        }    
        result +="key="+WxapiConstants.KEY;  
        String sign = null;  
        try {  
            sign = MD5.MD5Encode(result).toUpperCase();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        params.put("sign", sign);  
    }  
      
    public static String getRequestXml(SortedMap<String,String> params){  
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml>");  
        Set es = params.entrySet();  
        Iterator it = es.iterator();  
        while(it.hasNext()) {  
            Map.Entry entry = (Map.Entry)it.next();  
            String k = (String)entry.getKey();  
            String v = (String)entry.getValue();  
            if ("nick_name".equalsIgnoreCase(k)||"send_name".equalsIgnoreCase(k)||"wishing".equalsIgnoreCase(k)||"act_name".equalsIgnoreCase(k)||"remark".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {  
                sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");  
            }else {  
                sb.append("<"+k+">"+v+"</"+k+">");  
            }  
        }  
        sb.append("</xml>");  
        return sb.toString();  
    }  
    /** 
     * 生成随机字符串 
     * @return 
     */  
    public static String createNonceStr() {  
                return UUID.randomUUID().toString().toUpperCase().replace("-", "");  
        }  
    /** 
     * 生成商户订单号 
     * @param mch_id  商户号 
     * @param userId  该用户的userID 
     * @return 
     */  
    public static String createBillNo(){  
        //组成： mch_id+yyyymmdd+10位一天内不能重复的数字  
        //10位一天内不能重复的数字实现方法如下:  
        //因为每个用户绑定了userId,他们的userId不同,加上随机生成的(10-length(userId))可保证这10位数字不一样  
        Date dt=new Date();  
        SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");  
        String nowTime= df.format(dt);  
        int length = 10 ;  
        return WxapiConstants.PARTNER_ID + nowTime + getRandomNum(length);  
    }  
    /** 
     * 生成特定位数的随机数字 
     * @param length 
     * @return 
     */  
    public static String getRandomNum(int length) {  
        String val = "";  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            val += String.valueOf(random.nextInt(10));  
        }  
        return val;  
    }  
      
    public static String post(String requestXML,InputStream instream) throws Exception{  
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");  
        try {  
            keyStore.load(instream, WxapiConstants.PARTNER_ID.toCharArray());  
        }  finally {  
            instream.close();  
        }  
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, WxapiConstants.PARTNER_ID.toCharArray()).build();  
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(  
                sslcontext,  
                new String[] { "TLSv1" },  
                null,  
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);  
        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();  
        String result = "";  
        try {  
  
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack");  
              
            StringEntity  reqEntity  = new StringEntity(requestXML,"UTF-8");  
            // 设置类型   
            reqEntity.setContentType("application/x-www-form-urlencoded");   
            httpPost.setEntity(reqEntity);  
            CloseableHttpResponse response = httpclient.execute(httpPost);  
            try {  
                HttpEntity entity = response.getEntity();  
                if (entity != null) {  
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent(),"UTF-8"));  
                    String text;  
                    while ((text = bufferedReader.readLine()) != null) {  
                        result +=text;  
                        System.out.println(text);  
                    }  
                }  
                EntityUtils.consume(entity);  
            } finally {  
                response.close();  
            }  
        } finally {  
            httpclient.close();  
        }  
        return result;  
    }  
    
    public static void main(String[] args) {
    	/*String billNo = HongBaoUtil.createBillNo("15675121345");  
    	WxSendredpack hongbao = new WxSendredpack();  
        //默认发送结果失败  
        SortedMap<String, String> sortMap = HongBaoUtil.createMap(billNo, openid ,amount);  
        HongBaoUtil.sign(sortMap);  
        String requestXML = HongBaoUtil.getRequestXml(sortMap);  
        SystemLogger.info("["+mobile+"--"+mobile+"]报名领取红包,领红包提交微信的请求:"+requestXML);  
        try {  
            HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();  
            InputStream instream = request.getSession().getServletContext().getResourceAsStream("/WEB-INF/weixinCert/apiclient_cert.p12");   
            String responseXML = HongBaoUtil.post(requestXML,instream);  
            Map<String,String> resultMap = XmlUtil.parseXml(responseXML);  
            String return_code = resultMap.get("return_code").toString();  
            if("SUCCESS".equals(return_code)){  
                hongbao.setResult(HongBaoUtil.SUCCESS);  
            }  
            hongbao.setRemark(responseXML);  
        } catch (KeyManagementException e) {  
            e.printStackTrace();  
        } catch (UnrecoverableKeyException e) {  
            e.printStackTrace();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
        } catch (CertificateException e) {  
            e.printStackTrace();  
        } catch (KeyStoreException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally{  
            //不管发送成功不成功,都要更新红包发送记录,失败的商户余额回滚,成功的标志记录为成功  
            //hongBaoMapper.updateByPrimaryKeySelective(hongbao);  
        }  */
        //如果微信发送失败抛出异常的话,会导致收集到的用户信息回滚,此处为了保留用户信息  
//        return hongbao.getResult().equals(HongBaoUtil.SUCCESS) ? true :false;  
	}
}  