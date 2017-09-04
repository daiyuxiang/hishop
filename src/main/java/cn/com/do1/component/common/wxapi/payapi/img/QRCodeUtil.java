package cn.com.do1.component.common.wxapi.payapi.img;

import org.apache.commons.lang.StringUtils;

import java.awt.Color;  
import java.awt.Graphics2D;  
import java.awt.Image;
import java.awt.image.BufferedImage;  
import java.io.ByteArrayOutputStream;
import java.io.File;  
import java.io.IOException;

import javax.imageio.ImageIO;  

import com.swetake.util.Qrcode;
  
public class QRCodeUtil {  
      
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param imgPath 图片路径 
     */  
    public static void encoderQRCode(String content, String imgPath) {  
        encoderQRCode(content, imgPath, "png", 7, null);  
    }  
      
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param imgPath 图片路径 
     * @param imgType 图片类型 
     */  
    public static void encoderQRCode(String content, String imgPath, String imgType) {  
        encoderQRCode(content, imgPath, imgType, 7, null);  
    }  
    
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param imgPath 图片路径 
     * @param imgType 图片类型 
     * @param logoPath logo图片路径
     */  
    public static void encoderQRCode(String content, String imgPath, String imgType, String logoPath) {  
        encoderQRCode(content, imgPath, imgType, 7, logoPath);  
    } 
  
    /** 
     * 生成二维码(QRCode)图片 
     * @param content 存储内容 
     * @param imgPath 图片路径 
     * @param imgType 图片类型 
     * @param size 二维码尺寸 
     */  
    public static BufferedImage encoderQRCode(String content, String imgPath, String imgType, int size, String logoPath) {   
    	
            BufferedImage bufImg = qRCodeCommon(content, size ,logoPath);  
            
            if(StringUtils.isNotBlank(imgPath)){
            	// 判断附件存储路径是否存在，如果不存在则自动创建目录
                File imgFile = new File(imgPath);  
                // 生成二维码QRCode图片  
                try {
    				ImageIO.write(bufImg, imgType, imgFile);
    			} catch (IOException e) {
    				e.printStackTrace();
    			}  
            }
            
            
            return bufImg;
    }   
      
    /** 
     * 生成二维码(QRCode)图片的公共方法 
     * @param content 存储内容 
     * @param imgType 图片类型 
     * @param size 二维码尺寸 
     * @return 
     */  
    public static BufferedImage qRCodeCommon(String content, int size, String logoPath) {  
        BufferedImage bufImg = null;  
        try {  
            Qrcode qrcodeHandler = new Qrcode();  
            // 设置二维码排错率，可选L(7%)、M(15%)、Q(25%)、H(30%)，排错率越高可存储的信息越少，但对二维码清晰度的要求越小  
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            qrcodeHandler.setQrcodeEncodeMode('B');  
            // 设置设置二维码尺寸，取值范围1-40，值越大尺寸越大，可存储的信息越大  
            qrcodeHandler.setQrcodeVersion(size);  
            // 获得内容的字节数组，设置编码格式  
            byte[] contentBytes = content.getBytes("utf-8");
            // 图片尺寸  
            int imgSize = 67 + 12 * (size - 1); 
            bufImg = new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
            // 设置背景颜色  
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, imgSize, imgSize);  
  
            // 设定图像颜色> BLACK  
            gs.setColor(Color.BLACK);  
            // 设置偏移量，不设置可能导致解析出错  
            int pixoff = 2;  
            // 输出内容> 二维码  
            if (contentBytes.length > 0 && contentBytes.length < 800) {  
                boolean[][] codeOut = qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {  
                throw new Exception("QRCode content bytes length = " + contentBytes.length + " not in [0, 800].");  
            }  
            if(logoPath != null && !"".equals(logoPath)){
            	//读取logo图片 
                Image logo = ImageIO.read(new File(logoPath));  
                //设置logo的大小,设置为二维码图片的20%,因为过大会盖掉二维码 
                int widthLogo = logo.getWidth(null)>bufImg.getWidth()*2/10?(bufImg.getWidth()*2/10):logo.getWidth(null),   
                    heightLogo = logo.getHeight(null)>bufImg.getHeight()*2/10?(bufImg.getHeight()*2/10):logo.getWidth(null);  
                // 计算图片放置位置    
                int x = (bufImg.getWidth() - widthLogo) / 2; 
                int y = (bufImg.getHeight() - heightLogo) / 2; 
                gs.drawImage(logo, x, y, widthLogo, heightLogo, null);  //添加二维码logo
            }
            
            gs.dispose();  
            bufImg.flush();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return bufImg;  
    }     
  
    public static void main(String[] args) {  

        String imgPath = "C:/Users/ThinkPad/Desktop/Michael_QRCode.png"; 
        String encoderContent = "weixin://wxpay/bizpayurl?pr=OnimXE2";  
        BufferedImage bufImg = QRCodeUtil.encoderQRCode(encoderContent, imgPath, "png", 7, null);  
        System.out.println("========已生成");  
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();  
        try {
			ImageIO.write(bufImg, "png", out);
		} catch (IOException e) {
			e.printStackTrace();
		} 
        byte[] b = out.toByteArray();  
        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();  
        String base64 = "data:image/png;base64," + encoder.encodeBuffer(b).trim();  
        System.out.println(base64);
          
    }  
}