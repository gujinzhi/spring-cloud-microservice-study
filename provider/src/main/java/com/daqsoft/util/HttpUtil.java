package com.daqsoft.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.daqsoft.config.ConstantWords;
import com.daqsoft.pojo.Info;
import org.apache.commons.lang.time.DateFormatUtils;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;


public class HttpUtil {
	final static String METHOD_GET = "GET";
	final static String METHOD_POST = "POST";
	 static int TIMED = 60000;
	private static Info captureHtml(String strURL , String method,Map<String,Object> map)  {
		Long i = System.currentTimeMillis();
		System.out.print("开始："+ DateFormatUtils.format(new Date(), "HH:mm:ss"));
		HttpURLConnection httpConn;
		InputStreamReader input = null;
		BufferedReader bufReader = null;
		Info info = new Info();
		try {
			URL url = new URL(strURL);

			httpConn = (HttpURLConnection) url.openConnection();
			httpConn.setReadTimeout(TIMED);
			httpConn.setRequestMethod(method);
			if(map != null){
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					if(!"body".equals(entry.getKey())){
						httpConn.setRequestProperty(entry.getKey(),entry.getValue().toString());
					}

				}
				if(map.get("body") != null){
					httpConn.setDoOutput(true);
					OutputStream os = httpConn.getOutputStream();
						//将请求体写入到conn的输出流中
					os.write(map.get("body").toString().getBytes());
					//记得调用输出流的flush方法
					os.flush();
					//关闭输出流
					os.close();
				}
			}


			input = new InputStreamReader(httpConn.getInputStream(), "utf-8");
			bufReader = new BufferedReader(input);
			String line = "";
			StringBuilder contentBuf = new StringBuilder();
			while ((line = bufReader.readLine()) != null) {
				contentBuf.append(line);
			}
			System.out.println(contentBuf.toString());
			info.setConnet(contentBuf.toString());
			Long ii = System.currentTimeMillis();
			System.out.println("结束："+((ii-i))/1000);
			return info;
		} catch (Exception e) {
			e.printStackTrace();
			info.setCode(ConstantWords.HTTP_ERROR_STATUS);
			info.setError(e.getMessage());
			Long ii = System.currentTimeMillis();
			System.out.println("结束："+((ii-i))/1000);
			// TODO: handle exception
			return info;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (bufReader != null) {
				try {
					bufReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static Info captureHtmlPost(String strURL)  {
		return captureHtml(strURL,METHOD_POST,null);
	}
	public static Info captureHtmlPost(String strURL,Map map)  {
		return captureHtml(strURL,METHOD_POST,map);
	}
	public static Info captureHtmlGet(String strURL)  {
		return captureHtml(strURL,METHOD_GET,null);
	}

	public static Info captureHtmlSSLPost(String requestUrl) {
		return httpsRequest(requestUrl,METHOD_POST);

	}
	public static Info captureHtmlSSLGet(String requestUrl) {
		return httpsRequest(requestUrl,METHOD_GET);

	}
	public static Info httpsRequest(String requestUrl,String requestMethod) {
		Info info = new Info();
		StringBuffer buffer = null;
		try {
			//创建SSLContext
			SSLContext sslContext = SSLContext.getInstance("SSL");
			TrustManager[] tm = {new MyX509TrustManager()};
			//初始化
			sslContext.init(null, tm, new java.security.SecureRandom());
			//获取SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			//设置当前实例使用的SSLSoctetFactory
			conn.setSSLSocketFactory(ssf);
			conn.setReadTimeout(TIMED);
			conn.connect();
//			//往服务器端写内容
//			if (null != outputStr) {
//				OutputStream os = conn.getOutputStream();
//				os.write(outputStr.getBytes("utf-8"));
//				os.close();
//			}

			//读取服务器端返回的内容
			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			buffer = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			info.setConnet(buffer.toString());
		} catch (Exception e) {
			info.setCode(ConstantWords.HTTP_ERROR_STATUS);
			info.setError(e.getMessage() + requestUrl);

			e.printStackTrace();
		}
		return info;
	}
	public static String generateWelcome(Map map,String template){
		for (Object s : map.keySet()) {
			template = template.replaceAll("\\$\\{".concat(s.toString()).concat("\\}")
					, map.get(s.toString()).toString());
		}
		return template;
	}
	
	public static void main(String[] args) {
		Map<String,Object> ma = new HashMap<String,Object>();
		try {
			ma.put("Content-Type","application/json;charset=UTF-8");
			ma.put("body","{\"inf\": 105,\"apikey\": \"cdzkdqTour109\",\"data\": \"5B0A34BDD6B0BB046CE5F2658CBA08F5EE516811CC2BAD3CF5FEFAE0E7BF62F6F640699056EA64BA7F1FC5FB47FFA9DCCD9231FB79C8FFA718E222A141F01CD2DB23BC91CC2DB3869D4B32841E45BECC\"}\n");
		System.out.println(	HttpUtil.captureHtmlPost("http://110.249.146.212:8088/CSDfeeAnalyse/apiInf/select",ma));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public static void setTIMED(int tIMED) {
		TIMED = tIMED;
	}

}
