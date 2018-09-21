package com.sanss.lyh.web.business.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import oracle.net.aso.g;

public class HttpUtility {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtility.class);

	/**
	 * 发送 post请求 不支持中文
	 * 
	 * @return
	 */
	public static String postss(String url, Map<String, String> params) {
		String result = null;
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httppost
			HttpPost httppost = new HttpPost(url);
			// 创建参数队列
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			for (Map.Entry<String, String> entry : params.entrySet()) {
				formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			System.out.println("formparams:"+formparams);
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			logger.debug("executing request " + httppost.getURI());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity entity = response.getEntity();
				System.out.println(response.getStatusLine());
				String responsebody = EntityUtils.toString(entity, "UTF-8");
				if (entity != null) {
					logger.debug("--------------------------------------");
					logger.debug("Response content: " + responsebody);
					logger.debug("--------------------------------------");
					result = responsebody;
				}
			} finally {
				response.close();
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

//    
//    /**
//     * 用json进行系统间的传输
//     */
    public static int post(Object obj, String url) {
        try {
        	logger.info("==================post接口请求开始！");
            org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
            logger.info("================第一步");
            PostMethod method = new PostMethod(url);
            logger.info("================第二步");
            String jsonStrng = JSON.toJSONString(obj);
            logger.info("================第三步");
            logger.info("post发送接口参数："+jsonStrng);
            org.apache.commons.httpclient.methods.RequestEntity se = new StringRequestEntity(jsonStrng, "application/json", "UTF-8");
            method.setRequestEntity(se);
            // 使用系统提供的默认的恢复策略
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            int t = httpClient.executeMethod(method);
            logger.info("========接口返回状态码为："+t);
            System.out.println("返回状态："+t);
            return t;
        } catch (Exception e) {
            logger.info("exception:"+e);
        }
        return 0;
    }
    
    public static int posttomap(HashMap<String, String> obj, String url) {
        try {
        	logger.info("==================post接口请求开始！");
            org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
            logger.info("================第一步");
            PostMethod method = new PostMethod(url);
            logger.info("================第二步");
            Gson gson = new Gson();
            String jsonStrng = gson.toJson(obj);
//            String jsonStrng = JSON.toJSONString(obj);
            logger.info("================第三步");
            logger.info("post发送接口参数："+jsonStrng);
            org.apache.commons.httpclient.methods.RequestEntity se = new StringRequestEntity(jsonStrng, "application/json", "UTF-8");
            method.setRequestEntity(se);
            // 使用系统提供的默认的恢复策略
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            int t = httpClient.executeMethod(method);
            logger.info("========接口返回状态码为："+t);
            System.out.println("返回状态："+t);
            return t;
        } catch (Exception e) {
            logger.info("exception:"+e);
        }
        return 0;
    }
    
    
//
//    /**
//     * 本地测试
//     * @throws IOException 
//     */
    public static void main(String[] args) throws IOException, ParseException {
        getUserDescription();
    }

    private static void getUserDescription() throws IOException, ParseException{
//    	String url = "http://101.95.50.40:8080/smslog/receive";
        Map<String, String> values = new HashMap();
        values.put("du", "18921285013");
        values.put("ou", "10086");
        values.put("text", "【尊敬的用户】您好，您以欠费3元，请续交话费以保持电话畅通，谢谢！");
//        System.out.println("value:"+values);
//        String jsonStrng = JSON.toJSONString(values);
//        System.out.println("json:"+jsonStrng);
//        int result = post(values, url);
//    	Gson gson = new Gson();
//    	HashMap<String, String> values = new HashMap();
//    	String urlPost = "http://10.4.18.146/tokenservice/syncGWSecret";
//		values.put("GWID", "GW001000002");
//	    values.put("GWSecret", "B0F25FA456A8603D58695C3C13E745AEF400D9D157827EEE");
//	    String paradata = JSON.toJSONString(values);
//	    System.out.println(httpURLConnectionPOST(urlPost, paradata));
//	    int i = posttomap()
//	    System.out.println(str);
//        System.out.println("rest:"+result);
        String url = "http://47.96.3.190:8080/shtelsms/iface/receive.htm";
//        String param = "du=18921285013&ou=10086&text=【尊敬的用户】您好，您以欠费3元，请续交话费以保持电话畅通，谢谢！";
        System.out.println(postss(url, values));
    }
    
    
    /**
     * 向指定URL发送GET方法的请求
     * 
     * @param url
     *            发送请求的URL
     * @param param
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    
    
    
    /**
	 * 接口调用 POST
	 */
	 public static String httpURLConnectionPOST(String urlPost,String paradata) {
		String ret = "";
		try {
			URL url = new URL(urlPost);
			System.out.println("url=" + url);

			// 将url 以 open方法返回的urlConnection 连接强转为HttpURLConnection连接
			// (标识一个url所引用的远程对象连接)
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();// 此时cnnection只是为一个连接对象,待连接中

			// 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
			connection.setDoOutput(true);

			// 设置连接输入流为true
			connection.setDoInput(true);

			// 设置请求方式为post
			connection.setRequestMethod("POST");

			// post请求缓存设为false
			connection.setUseCaches(false);

			// 设置该HttpURLConnection实例是否自动执行重定向
			connection.setInstanceFollowRedirects(true);

			// 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
			// application/x-javascript text/xml->xml数据
			// application/x-javascript->json对象
			// application/x-www-form-urlencoded->表单数据
			connection.setRequestProperty("Content-Type",
					"application/json");

			// 建立连接
			// (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
			connection.connect();

			// 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
			DataOutputStream dataout = new DataOutputStream(
					connection.getOutputStream());
			 // URLEncoder.encode()方法
																			// 为字符串进行编码
//			String parm = "storeId=" + URLEncoder.encode("32", "utf-8");
			// 将参数输出到连接
			dataout.writeBytes(paradata);

			// 输出完成后刷新并关闭流
			dataout.flush();
			dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

			System.out.println(connection.getResponseCode());
			// 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
			BufferedReader bf = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			StringBuilder sb = new StringBuilder(); // 用来存储响应数据

			// 循环读取流,若不到结尾处
			while ((line = bf.readLine()) != null) {
				sb.append(bf.readLine());
			}
			bf.close(); // 重要且易忽略步骤 (关闭流,切记!)
			connection.disconnect(); // 销毁连接
			ret = sb.toString();
			System.out.println(sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}

}