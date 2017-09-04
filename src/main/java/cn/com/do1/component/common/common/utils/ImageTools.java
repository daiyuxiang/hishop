package cn.com.do1.component.common.common.utils;


import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/** @author bruce */
public class ImageTools {

    /**
     * 指定宽和高, 强制转换源图片到一个新的图片.
     *
     * @param originalImageFile 源图片在文件系统中的路径
     * @param targetImageFile   目标图片在文件系统中的路径
     * @param width             目标图片的宽
     * @param height            目标图片的高
     *
     * @throws Exception
     */
    public static void convertImage(String originalImageFile, String targetImageFile, int width, int height) throws Exception {
        ResizeOption option = new ResizeOption(ResizeOption.TYPE.ENFORCE);
        option.setHeight(height);
        option.setWidth(width);
        DefaultProcessor processor = new DefaultProcessor(originalImageFile);
        processor.resize(option);
        processor.saveAs(targetImageFile);
    }

    /**
     * 生成源图片的等比例小图, 小图的最大宽是maxWidth, 最大高是maxHeight
     *
     * @param originalImageFile 源图片在文件系统中的路径
     * @param targetImageFile   小图在文件系统中的路径
     * @param maxWidth          小图的最大宽
     * @param maxHeight         小图的最大高
     *
     * @throws Exception
     */
    public static void convertToConstantRatioImage(String originalImageFile, String targetImageFile, int maxWidth, int maxHeight) throws Exception {
        ResizeOption option = new ResizeOption(ResizeOption.TYPE.CONSTRAIN);
        option.setHeight(maxHeight);
        option.setWidth(maxWidth);
        DefaultProcessor processor = new DefaultProcessor(originalImageFile);
        processor.resize(option);
        processor.saveAs(targetImageFile);
    }
    
    /**
     * 生成源图片的等比例小图, 小图的最大宽是maxWidth
     * 不影响原图片的清晰度
     * @param inputFile         源图片文件
     * @param outputPicName     小图在文件系统中的路径
     * @param maxWidth          小图的最大宽
     * @throws IOException 
     * @throws Exception
     */
    public static void zoomPicture(String filePath, String outputPicName, double maxWidth) throws Exception {
    	File pic = new File(filePath);
    	double maxLimit = maxWidth;
    	double ratio = 1.0;
    	String name = pic.getName();
    	String imgType = "";
    	int index = name.lastIndexOf(".");
    	if (index >= 0) {
    		imgType =  name.substring(index + 1);
    	} 
		BufferedImage Bi = ImageIO.read(pic);
		if ((Bi.getWidth() > maxLimit)) {
			ratio = maxLimit / Bi.getWidth();
		}
		int widthdist = (int) Math.floor(Bi.getWidth() * ratio), heightdist = (int) Math.floor(Bi.getHeight() * ratio);
		BufferedImage tag = new BufferedImage(widthdist, heightdist, BufferedImage.TYPE_INT_RGB);
		tag.getGraphics().drawImage(Bi.getScaledInstance(widthdist, heightdist,BufferedImage.SCALE_SMOOTH), 0, 0, null);
		File littleFile = new File(outputPicName);
		ImageIO.write(tag, imgType, littleFile);
	}



    /**
     * 指定宽和高, 强制转换源图片到一个新的图片. 并用新的图片覆盖源图片.
     *
     * @param imageFile 图片在文件系统中的路径
     * @param width     新图片的宽
     * @param height    新图片的高
     *
     * @throws Exception
     */
    public static void replaceImage(String imageFile, int width, int height) throws Exception {
        convertImage(imageFile, imageFile, width, height);
    }

    /**
     * 生成源图片的等比例小图, 并用生成的小图覆盖源图片. 小图的最大宽是maxWidth, 最大高是maxHeight
     *
     * @param imageFile 图片在文件系统中的路径
     * @param maxWidth  小图的最大宽
     * @param maxHeight 小图的最大高
     *
     * @throws Exception
     */
    public static void replaceWithConstantRatioImage(String imageFile, int maxWidth, int maxHeight) throws Exception {
        convertToConstantRatioImage(imageFile, imageFile, maxWidth, maxHeight);
    }

    /**
     * 字符串返回指定格式的新图片地址.
     * 如: imageName 是 ../image.gif, ext 是 m, 则返回的新图片路径字符串是../image_m.gif
     *
     * @param imageName
     * @param ext
     *
     * @return
     */
    public static String newImageName(String imageName, String ext) {
        int index = imageName.lastIndexOf(".");
        if (index >= 0) {
            return imageName.substring(0, index) + "_" + ext + imageName.substring(index);
        }
        return imageName + "_" + ext;
    }

    public static void main(String[] args) throws Exception {
        String filePath = "c:/1.jpg";
        ImageTools.convertToConstantRatioImage(filePath, ImageTools.newImageName(filePath, "l"), 290, 290);
        // ImageTools.convertToConstantRatioImage(filePath,ImageTools.newImageName(filePath,"m"),100,100);
        ImageTools.convertToConstantRatioImage(filePath, ImageTools.newImageName(filePath, "s"), 50, 50);
        //前台商品图片
        ImageTools.convertToConstantRatioImage(filePath, ImageTools.newImageName(filePath, "w"), 150, 150);
        //略缩图
        ImageTools.convertToConstantRatioImage(filePath, ImageTools.newImageName(filePath, "t"), 102, 140);
    }

    static class ResizeOption {
        enum TYPE {
            /** 强制转换 */
            ENFORCE,
            /** 按比例转换 */
            CONSTRAIN
        }

        ResizeOption(TYPE type) {
            this.type = type;
        }

        private int width;
        private int height;
        private TYPE type = TYPE.CONSTRAIN;

        public int getHeight() {
            return height;
        }

        public ResizeOption setHeight(int height) {
            this.height = height;
            return this;
        }

        public int getWidth() {
            return width;
        }

        public ResizeOption setWidth(int width) {
            this.width = width;
            return this;
        }

        public TYPE getType() {
            return type;
        }
    }

    static class DefaultProcessor {
        private BufferedImage bufImage;

        public DefaultProcessor(String filename) throws IOException {
            File pic = new File(filename);
            if (pic.isDirectory() || !pic.canRead()) {
                throw new IOException("指定的文件路径是目录或者文件当前不可读");
            }
            bufImage = ImageIO.read(pic);
            if (bufImage == null) {
                throw new UnsupportedOperationException("不支持的图片类型：" + this.getFileExtension(pic));
            }
        }

        public synchronized void resize(ResizeOption opt) {
            int ow = bufImage.getWidth();
            int oh = bufImage.getHeight();

            int tw = ow;
            int th = oh;

            if (opt.getType() == ResizeOption.TYPE.ENFORCE) {
                tw = opt.getWidth();
                th = opt.getHeight();
            } else if (opt.getType() == ResizeOption.TYPE.CONSTRAIN) {
                int maxWidth = opt.getWidth();
                int maxHeight = opt.getHeight();

                if (ow * maxHeight >= maxWidth * oh) {
                    if (ow > maxWidth) {
                        tw = maxWidth;
                        th = maxWidth * oh / ow;
                    }
                } else {
                    if (oh > maxHeight) {
                        th = maxHeight;
                        tw = maxHeight * ow / oh;
                    }
                }
            }
            if (ow != tw || oh != th) {
                BufferedImage tagImage = new BufferedImage(tw, th, bufImage.getType() == BufferedImage.TYPE_CUSTOM ? BufferedImage.TYPE_INT_RGB : bufImage.getType());
                tagImage.getGraphics().drawImage(bufImage.getScaledInstance(tw, th, java.awt.Image.SCALE_AREA_AVERAGING), 0, 0, null);
                bufImage = tagImage;
            }
        }

        public synchronized void saveAs(String filename) {
            File file = new File(filename);
            FileOutputStream out = null;
            try {
                String extension = getFileExtension(file);
                if ("gif".equalsIgnoreCase(extension)) {
                    out = new FileOutputStream(file);
                  //  GifEncoder encoder = new GifEncoder(this.bufImage, out);
                  //  encoder.encode();
                } else if ("jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension)) {
                    out = new FileOutputStream(file);
                    JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                    encoder.encode(this.bufImage);
                } else {
                    ImageIO.write(this.bufImage, extension, file);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (out != null) {
                    try {
                        out.flush();
                    } catch (IOException ignore) {
                    } finally {
                        try { out.close(); } catch (IOException ignore) { }
                    }
                }
            }
        }

        private String getFileExtension(File file) {
            String name = file.getName();
            int index = name.lastIndexOf(".");
            if (index >= 0) {
                return name.substring(index + 1);
            } else {
                return "";
            }
        }
    }
}
