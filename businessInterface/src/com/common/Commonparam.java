package com.common;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.json.JsonAllDateValueProcessor;
import com.json.JsonDateValueProcessor;
import com.json.BaseBean;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 提示信息配置
 * 
 * @author Acer
 * 
 */
public class Commonparam {

	// FILE_PATH:0，1图片，2音频，3视频
	public static final String[] FILE_PATH = new String[] { "other", "image",
			"audio", "video" };
	// FALE_SIZE:文件大小(0，1图片，2音频，3视频)
	public static final Long[] FALE_SIZE = new Long[] { 1024 * 1024 * 200L,
			1024 * 1024 * 20L, 1024 * 1024 * 50L, 1024 * 1024 * 50L };

	public static String error_param_msg = "参数错误!";

	public static SimpleDateFormat fmt = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat fmtshort = new SimpleDateFormat("yyyy-MM-dd");
	public static SimpleDateFormat fmtTime = new SimpleDateFormat("HH:mm:ss");

	public static Date String2ShortDate(String str) {
		Date time = null;
		try {
			time = fmtshort.parse(str);
		} catch (java.text.ParseException e) {

			e.printStackTrace();
		}
		return time;
	}

	public static String Date2ShortStr(Date date) {
		if (date != null)
			return fmtshort.format(date);
		else
			return "";
	}

	public static Date StringtoDate(String str) {
		Date time = null;
		try {
			time = fmt.parse(str);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	public static String Date2Str(Date date) {
		if (date != null)
			return fmt.format(date);
		else
			return "";
	}

	public static String Date2Str() {
		return fmt.format(new Date());
	}

	public static BaseBean checkUseDate(String loginDate, int spaceMonth,
			int remainMinutes) throws ParseException {
		BaseBean bean = new BaseBean();
		if (loginDate == null || loginDate.length() == 0) {
			bean.setStatus(200);
			bean.setMsg("未曾登录");
			return bean;
		}
		Calendar cal0 = Calendar.getInstance();
		cal0.setTime(fmt.parse(loginDate));
		cal0.add(Calendar.MONTH, spaceMonth); // 得到�?4个月
		cal0.add(Calendar.MINUTE, remainMinutes); // 得到�?4个月
		long enddate = cal0.getTimeInMillis();

		Calendar cal = Calendar.getInstance();
		cal.setTime(fmt.parse(loginDate));
		cal.add(Calendar.MONTH, 23); // 得到�?3个月
		long date = cal.getTimeInMillis();

		Calendar cal1 = Calendar.getInstance();
		long now = cal1.getTimeInMillis();
		if (now >= date) {
			if (now >= enddate) {
				bean.setStatus(400);
				bean.setMsg("使用期限已经到期");
				System.out.println("使用期限已经到期");
			} else {
				int spacedate = (int) ((enddate - now) / (24 * 60 * 60 * 1000));
				if (spacedate > 0) {
					bean.setStatus(300);
					bean.setMsg("使用期限将到期,剩余" + spacedate + "天");
					System.out.println("使用期限将到期,剩余" + spacedate + "天");
				} else {
					int spacehour = (int) ((enddate - now) / (60 * 60 * 1000));
					if (spacehour > 0) {
						bean.setStatus(300);
						bean.setMsg("使用期限将到期,剩余" + (spacehour) + "小时");
						System.out.println("使用期限将到期,剩余" + (spacehour) + "小时");
					} else {
						int spacem = (int) ((enddate - now) / (60 * 1000));
						bean.setStatus(300);
						bean.setMsg("使用期限将到期,剩余" + (spacem) + "分钟");
						System.out.println("使用期限将到期,剩余" + (spacem) + "分钟");
					}
				}

			}
		} else {
			bean.setStatus(200);
			bean.setMsg("使用期限未到期");
		}
		System.out.println("now:" + fmt.format(new Date(now)) + "---------end:"
				+ fmt.format(new Date(enddate)));
		return bean;
	}

	/**
	 * 剩余分钟数
	 * 
	 * @param loginDate
	 * @param spaceMonth
	 * @param remainMinutes
	 * @return
	 * @throws ParseException
	 */
	public static int calRemainDate(String loginDate, int spaceMonth,
			int remainMinutes) throws ParseException {

		Calendar cal0 = Calendar.getInstance();
		cal0.setTime(fmt.parse(loginDate));
		cal0.add(Calendar.MONTH, spaceMonth); // 得到后24个月
		cal0.add(Calendar.MINUTE, remainMinutes); // 得到后24个月
		long enddate = cal0.getTimeInMillis();

		Calendar cal1 = Calendar.getInstance();
		long now = cal1.getTimeInMillis();

		int spacem = (int) ((enddate - now) / (60 * 1000));
		System.out.println("使用期限将到期,剩余" + (spacem) + "分钟");
		System.out.println("now:" + fmt.format(new Date(now)) + "---------end:"
				+ fmt.format(new Date(enddate)));
		if (spacem < 0)
			spacem = 0;
		return spacem;

	}

	/**
	 * 压缩图片
	 * 
	 * @param srcImageFile
	 *            原文件
	 * @param dstImageFileName
	 *            目标文件地址
	 * @throws Exception
	 */
	public static void makeSmallImage(File srcImageFile, String dstImageFileName)
			throws Exception {
		FileOutputStream fileOutputStream = null;
		JPEGImageEncoder encoder = null;
		BufferedImage tagImage = null;
		Image srcImage = null;
		try {
			srcImage = ImageIO.read(srcImageFile);
			int srcWidth = srcImage.getWidth(null);// 原图片宽度
			int srcHeight = srcImage.getHeight(null);// 原图片高度
			// int dstMaxSize = 120;//目标缩略图的最大宽度/高度，宽度与高度将按比例缩写
			// int dstWidth = 120; //srcWidth;//缩略图宽度
			// int dstHeight = 120; //srcHeight;//缩略图高度
			// float scale = 0;
			// 计算缩略图的宽和高
			// if(srcWidth>dstMaxSize){
			// dstWidth = dstMaxSize;
			// scale = (float)srcWidth/(float)dstMaxSize;
			// dstHeight = Math.round((float)srcHeight/scale);
			// }
			// srcHeight = dstHeight;
			// if(srcHeight>dstMaxSize){
			// dstHeight = dstMaxSize;
			// scale = (float)srcHeight/(float)dstMaxSize;
			// dstWidth = Math.round((float)dstWidth/scale);
			// }
			// 生成缩略图
			tagImage = new BufferedImage(srcWidth, srcHeight,
					BufferedImage.TYPE_INT_RGB);
			tagImage.getGraphics().drawImage(srcImage, 0, 0, srcWidth,
					srcHeight, null);
			fileOutputStream = new FileOutputStream(dstImageFileName);
			encoder = JPEGCodec.createJPEGEncoder(fileOutputStream);
			encoder.encode(tagImage);
			fileOutputStream.close();
			fileOutputStream = null;
		} finally {
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (Exception e) {
				}
				fileOutputStream = null;
			}
			encoder = null;
			tagImage = null;
			srcImage = null;
			System.gc();
		}
	}

	public static boolean deleteFile(String filepath) {
		if (filepath == null || filepath.trim().length() == 0) {
			return true;
		} else {
			File file = new File(filepath);
			if (file.isFile() && file.exists()) {
				return file.delete();
			}
		}
		return true;
	}

	public static String makeNewFileName(String imgSmallFileName) {
		String ext = imgSmallFileName.substring(
				imgSmallFileName.lastIndexOf("."), imgSmallFileName.length());
		return UUID.randomUUID().toString() + ext;
	}

	/**
	 * minute分钟以后
	 * 
	 * @param minute
	 * @return
	 */
	public static String spaceMinute(int minute) {
		Calendar cal0 = Calendar.getInstance();
		cal0.add(Calendar.MINUTE, minute);
		return Date2Str(cal0.getTime());
	}

	// eyJncm91cElkIjoiMSIsInVzZXJJZCI6IjEiLCJhY2NvdW50IjoiemhhbyIsInB3ZCI6IkUxMEFE
	// QzM5NDlCQTU5QUJCRTU2RTA1N0YyMEY4ODNFIiwibmFtZSI6IuWkmuWkmiJ9
	public static String Md5String(String paramStr) throws Exception {
		String str64 = new String(Base64.encode(paramStr.getBytes("utf-8")));
		return MD5Util.digest(str64);
	}

	public static JsonConfig getJsonConfig(String dateformat) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new JsonDateValueProcessor(dateformat));
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class,
				new JsonDateValueProcessor(dateformat));
		jsonConfig.registerJsonValueProcessor(java.lang.String.class,
				new JsonDateValueProcessor(null));
		return jsonConfig;
	}

	/**
	 * 过滤时间:java.util.Date.class, java.sql.Date.class,java.sql.Time.class
	 */
	public static JsonConfig getJsonConfig() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.sql.Date.class,
				new JsonAllDateValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.sql.Time.class,
				new JsonAllDateValueProcessor());
		jsonConfig.registerJsonValueProcessor(java.util.Date.class,
				new JsonAllDateValueProcessor());
		return jsonConfig;
	}

	public static SimpleDateFormat yearmonth = new SimpleDateFormat("/yyyy/MM/");

	public static String makeYearMonth(Date date) {
		String time = "/2013/12/";
		try {
			time = yearmonth.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	public static void makeFile(File file, String absolute, String filename)
			throws IOException {
		File absoluteFolder = new File(absolute);
		if (!absoluteFolder.exists()) {
			absoluteFolder.mkdirs();
		}
		FileOutputStream fos = null;
		FileInputStream fis = null;
		fos = new FileOutputStream(absolute + filename);
		fis = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = fis.read(buffer)) > 0) {
			fos.write(buffer, 0, len);
		}
		fos.close();
		fis.close();
	}

	public static String getFilePath() {
		
		return "E:/businessFile";
	}

}