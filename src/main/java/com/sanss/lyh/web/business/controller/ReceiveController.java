package com.sanss.lyh.web.business.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sanss.lyh.web.business.dao.LogTestDao;
import com.sanss.lyh.web.business.model.LogTest;
import com.sanss.lyh.web.business.model.POSTsend;
import com.sanss.lyh.web.business.model.RecLog;
import com.sanss.lyh.web.business.utils.HttpUtility;
import com.sanss.lyh.web.business.utils.ResultUtil;

import net.sf.json.JSONObject;




/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/receive")
public class ReceiveController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReceiveController.class);
	@Autowired
	private HttpSession session;
	@Autowired
	private LogTestDao dao;
	private String url = "http://172.16.68.226/shtelsms/iface/receive.htm";
	
	Gson gson=new Gson();
	
	@RequestMapping(value="/text", method=RequestMethod.POST,produces = "application/json; charset=utf-8")
	@ResponseBody
	public String insert(@RequestBody LogTest logTest){
        JSONObject json=JSONObject.fromObject(logTest);
        String str=json.toString();
//        Strin
        logger.info("xxxxxxxxxxxxxxxxxxxxx"+str);
		Map<String, Object> map=new HashMap<String, Object>();
		try {	
			if(null==logTest.getDu() || logTest.getDu().length==0){
				logger.info("字段du为空");
				map.put("result", "字段du为空");
			}else if("".equals(logTest.getOu()) || null==logTest.getOu() || logTest.getOu().trim().length()==0){
				logger.info("字段ou为空");
				map.put("result", "字段ou为空");
			}else if("".equals(logTest.getText()) || null==logTest.getText() || logTest.getText().trim().length()==0){
				logger.info("字段text为空");
				map.put("result", "字段text为空");
			}else{
				logger.info("rec suc.............ready to insert.........");
				int i=dao.insertlog(logTest);
				logger.info("insert finish .............result is........."+ i);
				//发送to smslog
				logger.info("============开始转发至smslog");
				HashMap<String, String> values = new HashMap();
				values.put("du", logTest.getDu()[0]);
			    values.put("ou", logTest.getOu());
			    values.put("text", logTest.getText());
			    // 短信下发
			    String result = HttpUtility.postss(url, values);
			    logger.info("短信下发返回："+result);
//				if(HttpUtility.posttomap(values, url)==200){
//					logger.info("smslog接口发送================>：成功");
//				}else{
//					logger.info("smslog接口发送================>：失败");
//				}
				if(i==1){
					logger.info("数据添加成功！");
					map.put("result", "1");
					//入库后发送短信到号码
//					if(dao.suresend(logTest.getDu()[0])){
//					ResultUtil util=new  ResultUtil();
//					POSTsend poTsend=new POSTsend();
//					poTsend.setCalled(logTest.getDu()[0]);
//					poTsend.setCaller(logTest.getOu());
//					poTsend.setContent(logTest.getText());
//					if(util.facesend(poTsend)){
//						logger.info("============发送成功！");
//					}else{
//						logger.info("============发送失败！");
//					}
//					}else{
//						logger.info(logTest.getDu()[0]+"===========switch为0不能发送");
//					}
				}else{
					logger.info("数据添加失败！");
					map.put("result", "0");
				}
			// 
				}
		} catch (Exception e) {
			logger.info("getList Exception:"+e);
		}	
		return gson.toJson(map);
	}
}
