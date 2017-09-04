package cn.com.do1.component.common.weixin.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

public class WxImageUpload {
	public static String formUpload(String urlStr, Map<String, String> textMap, 
            Map<String, String> fileMap) { 
        String res = ""; 
        HttpURLConnection conn = null; 
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符 
        try { 
            URL url = new URL(urlStr); 
            conn = (HttpURLConnection) url.openConnection(); 
            conn.setConnectTimeout(5000); 
            conn.setReadTimeout(30000); 
            conn.setDoOutput(true); 
            conn.setDoInput(true); 
            conn.setUseCaches(false); 
            conn.setRequestMethod("POST"); 
            conn.setRequestProperty("Connection", "Keep-Alive"); 
            conn 
                    .setRequestProperty("User-Agent", 
                            "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)"); 
            conn.setRequestProperty("Content-Type", 
                    "multipart/form-data; boundary=" + BOUNDARY); 
   
            OutputStream out = new DataOutputStream(conn.getOutputStream()); 
            // text 
            if (textMap != null) { 
                StringBuffer strBuf = new StringBuffer(); 
                Iterator iter = textMap.entrySet().iterator(); 
                while (iter.hasNext()) { 
                    Map.Entry entry = (Map.Entry) iter.next(); 
                    String inputName = (String) entry.getKey(); 
                    String inputValue = (String) entry.getValue(); 
                    if (inputValue == null) { 
                        continue; 
                    } 
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append( 
                            "\r\n"); 
                    strBuf.append("Content-Disposition: form-data; name=\"" 
                            + inputName + "\"\r\n\r\n"); 
                    strBuf.append(inputValue); 
                } 
                out.write(strBuf.toString().getBytes()); 
            } 
   
            // file 
            if (fileMap != null) { 
                Iterator iter = fileMap.entrySet().iterator(); 
                while (iter.hasNext()) { 
                    Map.Entry entry = (Map.Entry) iter.next(); 
                    String inputName = (String) entry.getKey(); 
                    String inputValue = (String) entry.getValue(); 
                    if (inputValue == null) { 
                        continue; 
                    } 
                    File file = new File(inputValue); 
                    String filename = file.getName(); 
                    String contentType = new MimetypesFileTypeMap() 
                            .getContentType(file); 
                    if (filename.endsWith(".png")) { 
                        contentType = "image/png"; 
                    } 
                    if (contentType == null || contentType.equals("")) { 
                        contentType = "application/octet-stream"; 
                    } 
   
                    StringBuffer strBuf = new StringBuffer(); 
                    strBuf.append("\r\n").append("--").append(BOUNDARY).append( 
                            "\r\n"); 
                    strBuf.append("Content-Disposition: form-data; name=\"" 
                            + inputName + "\"; filename=\"" + filename 
                            + "\"\r\n"); 
                    strBuf.append("Content-Type:" + contentType + "\r\n\r\n"); 
   
                    out.write(strBuf.toString().getBytes()); 
   
                    DataInputStream in = new DataInputStream( 
                            new FileInputStream(file)); 
                    int bytes = 0; 
                    byte[] bufferOut = new byte[1024]; 
                    while ((bytes = in.read(bufferOut)) != -1) { 
                        out.write(bufferOut, 0, bytes); 
                    } 
                    in.close(); 
                } 
            } 
   
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes(); 
            out.write(endData); 
            out.flush(); 
            out.close(); 
   
            // 读取返回数据 
            StringBuffer strBuf = new StringBuffer(); 
            BufferedReader reader = new BufferedReader(new InputStreamReader( 
                    conn.getInputStream())); 
            String line = null; 
            while ((line = reader.readLine()) != null) { 
                strBuf.append(line).append("\n"); 
            } 
            res = strBuf.toString(); 
            reader.close(); 
            reader = null; 
        } catch (Exception e) { 
            System.out.println("发送POST请求出错。" + urlStr); 
            e.printStackTrace(); 
        } finally { 
            if (conn != null) { 
                conn.disconnect(); 
                conn = null; 
            } 
        } 
        return res; 
    } 
    
    public static String postMedia(String url,String type,String filePath){
        
        Map<String, String> textMap = new HashMap<String, String>(); 
           
        textMap.put("type", type); 
   
        Map<String, String> fileMap = new HashMap<String, String>(); 
           
        fileMap.put("media", filePath); 
           
        String ret = formUpload(url, textMap, fileMap);
        
        return ret;
    }
    
    
    
    public static void main(String args[]) throws IOException{
        //System.out.println(URLPostContent("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxf7eada91d02985a3&secret=c73e56b6f16c69f0f2107fbb144197e5", null));
        
        String filepath="D:\\apache-tomcat-6.0.35-gzwb\\webapps\\bdp-newmedia\\uploadpic\\ff2b66e7-2ae0-4442-b481-ccf837482fb7.jpg"; 
        String access_token="sTBXK9tWDM0AuzYq4v5I6lrJr6nfKytLp8wUFqOxeXxfNnaZyMmPe2TztAocm_7IHjtbem-g7LdiGVZrpHGM4wxXeeXSz-kSUxBT8bQb9Ac";
        String urlStr = "http://api.weixin.qq.com/cgi-bin/material/add_material?access_token="+access_token; 
           
        String ret =  postMedia(urlStr,"image",filepath);
           
        System.out.println(ret); 
        
        
    }
}
