package cn.com.do1.component.common.wxapi.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import cn.com.do1.component.common.weixin.util.WxapiConstants;
import cn.com.do1.dqdp.httphelper.HttpHelperV2;

import com.thoughtworks.xstream.core.BaseException;

/**
 * Created by zhanqi on 2015/3/13.
 */
public class WxClient {

    /**
     * access_token是公众号的全局唯一票据，
     * 公众号调用各接口时都需使用access_token
     *
     * @return
     * @throws Exception
     */
    public static JSONObject getAccessToken() throws Exception, BaseException {
        Map<String, Object> param = new HashMap<String, Object>();

        String url = WxapiConstants.getAccessTokenUrl();

        JSONObject json = new HttpHelperV2().getJSONFromHttp(url, param, 0);

        checkError(json);

        return json;
    }

    /**
     * jsapi_ticket是公众号用于调用微信JS接口的临时票据。
     *
     * @param accessToken
     * @return
     * @throws Exception
     */
    public static JSONObject getJsapiTicket(String accessToken) throws Exception, BaseException {
        Map<String, Object> param = new HashMap<String, Object>();

        String url = WxapiConstants.getJsapiTicketUrl(accessToken);

        JSONObject json = new HttpHelperV2().getJSONFromHttp(url, param, 0);

        checkError(json);

        return json;
    }

    /**
     * 网页登录授权
     *
     * @param code
     * @return
     * @throws Exception
     */
    public static JSONObject getOpenid(String code) throws Exception, BaseException {
        Map<String, Object> param = new HashMap<String, Object>();

        String url = WxapiConstants.getLoginAccessTokenUrl(code);

        JSONObject json = new HttpHelperV2().getJSONFromHttp(url, param, 0);

        checkError(json);

        return json;
    }

    /**
     * 获取用户信息
     *
     * @param accessToken
     * @param openid
     * @return
     * @throws Exception
     */
    public static JSONObject getUserInfo(String accessToken, String openid) throws Exception, BaseException {
        Map<String, Object> param = new HashMap<String, Object>();

        String url = WxapiConstants.getUserinfoUrl(accessToken, openid);

        JSONObject json = new HttpHelperV2().getJSONFromHttp(url, param, 0);

        checkError(json);

        return json;
    }

    /**
     * 获取媒体文件
     *
     * @param accessToken 接口访问凭证
     * @param mediaId     媒体文件id
     * @param savePath    文件在服务器上的存储路径
     */
    public static String downloadMedia(String accessToken, String mediaId, String savePath) throws Exception {

        if (mediaId == null) {
            throw new Exception("mediaId is null");
        }

        String filePath = null;
        String resPath = null;
        // 拼接请求地址
        try {
            URL url = new URL(WxapiConstants.getMediaDownloadUrl(accessToken, mediaId));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
            
            if (!savePath.endsWith("/")) {
                savePath += "/";
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            
            
            String path = "userfiles/online/" + formatter.format(new Date()) + "/";
            savePath = savePath + path;
            
            File dir = new File(savePath);
            if(!dir.exists()) {
                dir.mkdirs();
            }

            // 根据内容类型获取扩展名 Content-Type: image/jpeg
            String fileExt = conn.getHeaderField("Content-Type").replace("image/", ".");
            // 将mediaId作为文件名
            filePath = savePath + mediaId + fileExt;
            resPath = "/jiangnan/"+path+ mediaId + fileExt;
            
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
            fos.close();
            bis.close();

            conn.disconnect();

        } catch (Exception e) {
            throw new Exception("下载媒体文件失败：" + e.getMessage(), e);
        }

        return resPath;
    }

    /**
     * 微信接口返回码检查
     *
     * @param json
     */
    private static void checkError(JSONObject json) throws Exception {
        if (json.get("errcode") != null && json.getInt("errcode") != 0) {
            throw new Exception("微信接口异常：" + json.getString("errmsg"));
        }
    }
}
