package cn.com.do1.component.common.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * FileUpload 用来保存优惠卷图片在本地的目录,并返回相对路径名
 * 
 * @author yeshenghai
 * @version 1.0 2007/07/10
 */
public class FileUpload {
	private transient final static Logger log = LoggerFactory.getLogger(FileUpload.class);
	private static int BUFFER_SIZE = 16 * 1024;
	private static List<String> photoSize = Collections.synchronizedList(new ArrayList<String>());
	static {
		photoSize.add("280x280");
		photoSize.add("100x100");
		photoSize.add("166x163");
		photoSize.add("50x50");
		photoSize.add("110x110");
		photoSize.add("200x19");
		photoSize.add("150x19");
		photoSize.add("100x19");
		photoSize.add("190x1000");
	}

	/**
	 * 文件上传(不修改文件名)
	 * 
	 * @param file
	 * @param filePath
	 *            文件存放目录（相对）
	 * @param fileName
	 *            文件名称
	 * 
	 * @return distName 返回保存路径
	 * @throws Exception
	 */
	public static String uploadFile(File file, String filePath, String fileName) throws Exception {
		if (file == null || StringUtils.isBlank(filePath) || StringUtils.isBlank(fileName)) {
			throw new Exception("文件、文件路径和文件名不能为空！");
		}
		File folder = new File(ConstConfig.TOMCAT_CONTEXT_PATH + filePath);
		// 如果父目录不存在, 当然没必要检测它的子文件了
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String distName = filePath + File.separator + fileName;
		File dst = new File(ConstConfig.TOMCAT_CONTEXT_PATH + distName);
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			int bytesReader = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesReader = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
				out.write(buffer, 0, bytesReader);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException ignore) {
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException ignore) {
				}
			}
		}
		return distName;
	}

	/**
	 * 文件上传(文件名由UUID重新命名)
	 * 
	 * @param file
	 * @param filePath
	 *            文件存放目录（相对）
	 * @param fileName
	 *            文件名称
	 * 
	 * @return distName 返回保存路径
	 * @throws Exception
	 */
	public static String uploadFileUUID(File file, String filePath, String fileName) throws Exception {
		if (file == null || StringUtils.isBlank(filePath) || StringUtils.isBlank(fileName)) {
			throw new Exception("文件、文件路径和文件名不能为空！");
		}
		File folder = new File(ConstConfig.TOMCAT_CONTEXT_PATH + filePath);
		// 如果父目录不存在, 当然没必要检测它的子文件了
		if (!folder.exists()) {
			folder.mkdirs();
		}
		String distName = UUID.randomUUID().toString();
		distName = filePath + File.separator + distName + fileName.substring(fileName.indexOf("."), fileName.length());
		File dst = new File(ConstConfig.TOMCAT_CONTEXT_PATH + distName);
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);
			int bytesReader = 0;
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((bytesReader = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
				out.write(buffer, 0, bytesReader);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException ignore) {
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException ignore) {
				}
			}
		}
		return distName;
	}

	public static void deleteFile(String filePath) {
		if (AssertUtil.isEmpty(filePath)) {
			return;
		}
		filePath = ConstConfig.TOMCAT_CONTEXT_PATH + filePath;
		deleteSingleFile(filePath);
		for (String size : photoSize) {
			deleteSingleFile(ImageTools.newImageName(filePath, size));
		}
	}

	public static void deleteFile(String filePath, List<String> suffix) {
		deleteFile(filePath);
		for (String size : suffix) {
			deleteSingleFile(ImageTools.newImageName(filePath, size));
		}
	}

	public static String getImagePath(String filePath, String imageType) {
		String imagePath = null;
		if (filePath != null && filePath.length() > 0) {
			int length = filePath.indexOf(".");
			if (length != -1) {
				imagePath = filePath.substring(0, length) + imageType + filePath.substring(length);
			}
		}
		return imagePath;
	}

	public static void deleteSingleFile(String realFilePath) {
		File file = new File(realFilePath);
		if (file.exists() && !file.isDirectory()) {
			boolean result = file.delete();
			if (!result) {
				log.error("删除文件：" + realFilePath + " 失败！");
			}
		}
	}

	/**
	 * 根据参数fileType(文件类型)判断是否是图片格式的文件
	 * 
	 * @return
	 */
	public static boolean isImagesFile(String fileType) {
		if (AssertUtil.isEmpty(fileType))
			return false;
		String[] fileTypes = { "jpg", "png", "gif", "bmp", "tif", "jpeg" };
		for (int i = 0; i < fileTypes.length; i++) {
			if (fileType.equals(fileTypes[i]))
				return true;
		}
		return false;
	}

	/**
	 * 根据参数fileType(文件类型)判断是否是自定义的文件格式
	 * 
	 * @param fileType
	 *            ：上传的文件类型
	 * @param fileTypes
	 *            ：目标类型字符串，用","隔开，如"jpg,png,gif"。
	 * @return
	 */
	public static boolean isFileType(String fileType, String fileTypes) {
		if (AssertUtil.isEmpty(fileType))
			return false;
		if (AssertUtil.isEmpty(fileTypes))
			return false;
		String[] fileTypeStr = fileTypes.split(",");
		for (int i = 0; i < fileTypeStr.length; i++) {
			if (fileType.equals(fileTypeStr[i]))
				return true;
		}
		return false;
	}
}
