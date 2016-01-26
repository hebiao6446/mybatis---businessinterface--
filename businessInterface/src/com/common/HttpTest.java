package com.common;


import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class HttpTest {
	private static final String user = "http://127.0.0.1:8099/businessInterface/user/";
	private static final String sign = "http://127.0.0.1:8099/businessInterface/sign/";
	private static final String things = "http://127.0.0.1:8099/businessInterface/things/";
	public static void main(String[] args) throws Exception {

		String paramStr = JSONObject.fromObject(HttpTest.updateSign()).toString();
		String key = Commonparam.Md5String(paramStr);
		String url = sign + "updateSign" + ".action";
		String post = "data=" + paramStr + "&key=" + key;
		System.out.println(paramStr); 
		System.out.println(key);
		System.out.println(url + "?" + post);
		String data = HttpGetMsg.postUrl(url, post);
		System.out.println(data);
	}
	private static Object userLogin() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("account", "zrk");
		param.put("pwd", "E10ADC3949BA59ABBE56E057F20F883E");
		return param;
	}
	private static Object findHistorySign() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "7");
		param.put("fontTime", "2012-12-12");
		param.put("backTime", "2014-10-12");
		param.put("page", 2);
		param.put("size", 10);
		return param;
	}
	private static Object insertAttendDescript() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "7");
		param.put("content", "不好意思，忘记签到了,不好意思，忘记签退了,不好意思，忘记签退了,不好意思，忘记签退了,不好意思，忘记签退了,不好意思，忘记签退了,不好意思，忘记签退了,不好意思，忘记签退了,不好意思，忘记签退了");
		return param;		
	}
	
	private static Object findHistoryAttendDescript() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "7");
		param.put("page", "1");
		param.put("size", "10");

		
		return param;		
	}
	
	
	private static Object updateAttendanceTime() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "8");
		param.put("type", "2");	
		param.put("workTime", "18:00:00");
		
		return param;		
	}
	
	
	private static Object updateSign() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "9");
		param.put("signType", "1");
		param.put("lat", "123.00000000");
		param.put("lng", "123.00000000");		
		param.put("address", "上海市徐汇区天钥桥路1178号");		
		param.put("type", "T");		
		return param;
	}
	
	private static Object findTodaySign() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "1");
		param.put("account", "zhao");
		param.put("pwd", "E10ADC3949BA59ABBE56E057F20F883E");
		return param;
	}

	private static Object findManagedSign() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "7");
		param.put("status", "1");
		param.put("dateTime", "2014-06-12");
		param.put("page", "1");
		param.put("size", "10");
		return param;
	}
	
	private static Object findAllThings() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "15");
		return param;
	}
	
	
	private static Object updateNewThings() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "15");// createUserId
		param.put("thingsId", "16");
		param.put("content", "修改那日");
		param.put("isEmergency", "F");// 是否加急
		param.put("periodType", "1");// 循环方式：0不循环1是每天2是每周 3是每月4是每年
		param.put("startTime", "2014-06-08");//
		param.put("endTime", "2015-12-12");//
		param.put("finishUserId", "13,14,15,,");//
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		HashMap<String, Object> map1 = new HashMap<String, Object>();
	     map1.put("type", "2");
	     map1.put("fileId", "5");
		map1.put("fileType", 1);// 1图片2音频3视频
		map1.put("filePath", "D:/texdct1.txt");
		HashMap<String, Object> map2 = new HashMap<String, Object>();
		map2.put("type", "2");
	    map2.put("fileId", "3");
		HashMap<String, Object> map3 = new HashMap<String, Object>();
	    map3.put("type", "2");
	    map3.put("fileId", "6");
		
		list.add(map1);
		list.add(map2);
		list.add(map3);
		param.put("resourse", list);
		return param;
	}
	
	
	private static Object finishThings() {
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("userId", "15");
		param.put("thingsId", "15");
		return param;
	}
	

}
