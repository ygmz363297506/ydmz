package com.sanss.lyh.web.business.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import com.sanss.lyh.web.business.model.POSTsend;

import net.sf.json.JSONObject;

public class ResultUtil {
//	private static final String INTERFACE_URL="http://10.4.9.115:8080/emsp_sgw6/basicsms/send.action";
	//公网地址
	private static final String INTERFACE_URL="http://101.95.49.96:8080/emsp_sgw5/basicsms/send.action";
	
//	private static final String INTERFACE_URL="http://172.16.68.226/emsp_sgw5/basicsms/send.action";
	
	public boolean facesend(POSTsend tsend) throws Exception {
		String code = null;
		code = sendSms(tsend,code);
		if(code.equals("200")||code=="200"){
			return true;
		}
		else{
			return false;
		}
	}


	private String sendSms(POSTsend posTsend,String code) {
		try {
			URL url=new URL(INTERFACE_URL);
			HttpURLConnection connection=(HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			 //application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据 application/json;charset=utf-8 -> json数据  
			connection.setRequestProperty("Content-Type",  
			        "application/x-www-form-urlencoded");  
//			connection.setRequestProperty("Content-Type",  
//			        "application/json; charset=UTF-8");  
//			connection.setRequestProperty("accept", "*/*");  
//			connection.setRequestProperty("user-agent",  
//			        "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)"); 
			connection.connect();
			
			//post请求
			DataOutputStream out=new DataOutputStream(connection.getOutputStream());
			String b="json="+URLEncoder.encode("{\"rt\": {\"app\": {\"ins\": \"8014973986465966975\", \"pid\": \"1880\"}, \"serv\": {\"ins\": \"0\", \"pid\": \"EMSP_ST_MSGBasicSend\"}},\"rsc\": {\"du\": [\""+posTsend.getCalled()+"\"],\"fu\": {\"ft\":\"2\"}, \"ou\": \""+posTsend.getCaller()+"\", \"sms\": {\"ct\": \""+posTsend.getContent()+"\", \"ec\": 9}},\"log\": {\"gs\": 0, \"dp\": 1, \"nf\": 0}}", "UTF-8");
			//测试																																																			{\"app\": {\"ins\": \"8014973986465966975\", \"pid\": \"1880\"}
//			String b="json="+URLEncoder.encode("{\"rt\": {\"app\": {\"ins\": \"8014973986465966975\", \"pid\": \"102000000000000207996\"}, \"serv\": {\"ins\": \"0\", \"pid\": \"EMSP_ST_MSGBasicSend\"}},\"rsc\": {\"du\": [\""+posTsend.getCalled()+"\"], \"ou\": \""+posTsend.getCaller()+"\", \"sms\": {\"ct\": \""+posTsend.getContent()+"\", \"ec\": 9}},\"log\": {\"gs\": 0, \"dp\": 1, \"nf\": 1}}", "UTF-8");
			JSONObject obj=new JSONObject();
			obj.put("appid", posTsend.getAppid());
			obj.put("token", posTsend.getToken());
			obj.put("caller", posTsend.getCaller());
			obj.put("called", posTsend.getCalled());
			obj.put("ins", posTsend.getIns());
			obj.put("nf", posTsend.getNf());
			obj.put("content", posTsend.getContent());
			System.out.println("b为："+b);
			out.writeBytes(b);
			out.flush();
			out.close();
			System.out.println("json为："+URLDecoder.decode("{\"rt\": {\"app\": {\"ins\": \"8014973986465966975\", \"pid\": \"1880\"}, \"serv\": {\"ins\": \"0\", \"pid\": \"EMSP_ST_MSGBasicSend\"}},\"rsc\": {\"du\": [\""+posTsend.getCalled()+"\"],\"fu\": {\"ft\":\"2\"}, \"ou\": \""+posTsend.getCaller()+"\", \"sms\": {\"ct\": \""+posTsend.getContent()+"\", \"ec\": 9}},\"log\": {\"gs\": 0, \"dp\": 1, \"nf\": 0}}", "UTF-8"));
			//读取响应  
			BufferedReader reader = new BufferedReader(new InputStreamReader(  
			        connection.getInputStream()));  
			String lines;  
			StringBuffer sb = new StringBuffer("");  
			while ((lines = reader.readLine()) != null) {  
			    lines = new String(lines.getBytes(), "utf-8");  
			    sb.append(lines);  
			}  
			code = new Integer(connection.getResponseCode()).toString();
			System.out.println("time:"+new Date()+"主叫："+posTsend.getCaller()+"被叫"+posTsend.getCalled()+"内容："+posTsend.getContent()+"返回结果："+code);
			reader.close();
			//断开连接
			connection.disconnect();
		} catch (MalformedURLException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
		return code;
	}
	
	 /**
     * POST方式发起http请求
     */
   /* @Test
    public void requestByPostMethod(){
        CloseableHttpClient httpClient = getHttpClient();
        try {
            HttpPost post = new HttpPost("http://localhost/....");          //这里用上本机的某个工程做测试
            //创建参数列表
            List<POSTsend> list = new ArrayList<POSTsend>();
            list.add(new BasicNameValuePair("j_username", "admin"));
            list.add(new BasicNameValuePair("j_password", "admin"));
            //url格式编码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list,"UTF-8");
            post.setEntity(uefEntity);
            System.out.println("POST 请求...." + post.getURI());
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try{
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("-------------------------------------------------------");
                    System.out.println(EntityUtils.toString(uefEntity));
                    System.out.println("-------------------------------------------------------");
                }
            } finally{
                httpResponse.close();
            }
             
        } catch( UnsupportedEncodingException e){
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
         
    }
     
    private CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }
     
    private void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }*/
	
	
	public static void main(String[] args) {
		try {
			String fileurl = "D:/test/重发.txt";
			
			File file = new File(fileurl);
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			int i = 0;
			int f = 0;
			ResultUtil resultUtil=new ResultUtil();
			POSTsend tsend=new POSTsend();
			tsend.setCalled("15316849620");
			tsend.setContent("翼动拇指153测试2");
			
			while((s = br.readLine()) != null) {
				tsend.setCaller(s);
				if(resultUtil.facesend(tsend)){
					System.out.println("成功一条");
					i++;
				}else{
					System.out.println("失败一条");
					f++;
				}
			}
			br.close();
			System.out.println("总共增加："+i+"，失败："+f);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
