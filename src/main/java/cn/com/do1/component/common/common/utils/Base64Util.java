package cn.com.do1.component.common.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {

	 /** */  
    /** 
     * BASE64解密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String decryptBASE64(String key){  
    	try {
    		byte[] output = (new BASE64Decoder()).decodeBuffer(key);  
            String outputStr = new String(output);  
            return outputStr;  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }  
  
    /** */  
    /** 
     * BASE64加密 
     *  
     * @param key 
     * @return 
     * @throws Exception 
     */  
    public static String encryptBASE64(String inputStr) {  
    	try {
    		byte[] inputData = inputStr.getBytes();  
            return (new BASE64Encoder()).encodeBuffer(inputData);  
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }  
    
    public static void main(String[] args) throws Exception{
    	String s = Base64Util.decryptBASE64("eyJzdGFyRHQiOiIyMDEzLTExLTEwIDE2OjE3OjQ2IiwiZW5kRHQiOiIyMDEzLTEyLTEwIDE2OjE3OjQ2IiwicGFnZU51bSI6IjEiLCJwYWdlU3VtIjoiMTAifQ");
    	System.out.println(s);
	}
}
