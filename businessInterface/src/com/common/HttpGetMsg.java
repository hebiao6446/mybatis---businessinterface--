package com.common;

import java.awt.Image;
import java.awt.geom.AffineTransform;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
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
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class HttpGetMsg {
	public static void main(String[] args) throws Exception {
		 
		//登录
		/*HashMap param = new HashMap();
		param.put("account", "zrk");
		param.put("pwd", "E10ADC3949BA59ABBE56E057F20F883E");
		String paramStr = JSONObject.fromObject(param).toString();
		System.out.println("data="+paramStr+"&key="+Commonparam.Md5String(paramStr));
		String data=HttpGetMsg.postUrl("http://127.0.0.1:8099/businessInterface/user/userLogin.action",
		 "data="+paramStr+"&key="+Commonparam.Md5String(paramStr));
		*/
		
//		//用户已选模块
//		HashMap param = new HashMap();
//		param.put("userId", "12");
//		String paramStr = JSONObject.fromObject(param).toString();
//		String data=HttpGetMsg.postUrl("http://127.0.0.1:8080/businessInterface/user/findUserMainMenu.action",
//			"data="+paramStr+"&key="+Commonparam.Md5String(paramStr));
		
//		//用户所有模块列表
//	    HashMap param = new HashMap();
//		param.put("companyId", "2");
//		String paramStr = JSONObject.fromObject(param).toString();
//		String data=HttpGetMsg.postUrl("http://127.0.0.1:8080/businessInterface/user/findUserAllMenu.action",
//		"data="+paramStr+"&key="+Commonparam.Md5String(paramStr));
		
//		//用户所有模块列表
//	    HashMap param = new HashMap();
//		param.put("companyId", "2");
//		String paramStr = JSONObject.fromObject(param).toString();
//		String data=HttpGetMsg.postUrl("http://127.0.0.1:8080/businessInterface/user/findUserAllMenu.action",
//		"data="+paramStr+"&key="+Commonparam.Md5String(paramStr));
				
//		//用户联系人分组
//		HashMap param = new HashMap();
//		param.put("userId", "7");
//		String paramStr = JSONObject.fromObject(param).toString();
//		String data=HttpGetMsg.postUrl("http://127.0.0.1:8080/businessInterface/contact/findUserGroupList.action",
//		 "data="+paramStr+"&key="+Commonparam.Md5String(paramStr));
		
		
//		//产品销售列表，筛选，搜索
//		HashMap param = new HashMap();
//		param.put("userId", 7);
//		param.put("status", 2);
//		param.put("startTime", "");
//		param.put("endTime", "2014-06-08 16:56:38");
//		String paramStr = JSONObject.fromObject(param).toString();
//		String data=HttpGetMsg.postUrl("http://127.0.0.1:8099/businessInterface/product/findSellList.action",
//			"data="+paramStr+"&key="+Commonparam.Md5String(paramStr));

		
	
		//分组修改排序
		HashMap param = new HashMap();
		param.put("userId", 7);
		param.put("groupId1", 28);
		param.put("groupId2", 1);
		String paramStr = JSONObject.fromObject(param).toString();
		String data=HttpGetMsg.postUrl("http://127.0.0.1:8080/businessInterface/contact/updateGroupOrder.action",
			"data="+paramStr+"&key="+Commonparam.Md5String(paramStr));

		
		System.out.println(data);
	}

	
	
	
	public static boolean isNetOk(String ip) {
		boolean state = false;
		try {
			InetAddress ad = InetAddress.getByName(ip);

			try {
				state = ad.isReachable(5000);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}// 测试是否可以达到该地址

		} catch (UnknownHostException e) {

		}
		return state;
	}

	public static String postUrl(String remoteUrl, String param) {
		URL url = null;
		HttpURLConnection httpurlconnection = null;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(remoteUrl);

			httpurlconnection = (HttpURLConnection) url.openConnection();
			httpurlconnection.setDoOutput(true);
			httpurlconnection.setRequestMethod("POST");

			httpurlconnection.getOutputStream().write(param.getBytes("UTF-8"));
			httpurlconnection.getOutputStream().close();
			InputStream in = httpurlconnection.getInputStream();
			byte[] b = new byte[1024];
			int i = 0;
			while ((i = in.read(b)) != -1) {
				sb.append(new String(b, 0, i, "UTF-8"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			sb.append("");

		} finally {
			if (httpurlconnection != null)
				httpurlconnection.disconnect();

		}
		return sb.toString();
	}

}
