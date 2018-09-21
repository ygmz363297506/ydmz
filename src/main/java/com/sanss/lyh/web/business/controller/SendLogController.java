package com.sanss.lyh.web.business.controller;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sanss.lyh.web.business.dao.LogTestDao;
import com.sanss.lyh.web.business.dao.SendLogDao;
import com.sanss.lyh.web.business.model.LogTest;
import com.sanss.lyh.web.business.model.SendRecord;
import com.sanss.lyh.web.business.model.Userlogs;
import com.sanss.lyh.web.business.utils.Page;
/**
 * 发送记录
 * Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/sendlog")
public class SendLogController extends BasicController{
	// 日志定义
	private static final Logger logger = LoggerFactory.getLogger(SendLogController.class);
	
	@Autowired
	private SendLogDao dao;
	
	@Autowired
	private LogTestDao logdao;
	
	/**
	 * 查询发送记录
	 * @param record
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sendnotquery", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> sendnotquery(SendRecord record,Page page,HttpServletRequest request) {
		Map<String, Object> map = new TreeMap<String, Object>();
		// 查找登录手机号
		String usernumber = (String)getSessionAbbr(request, SESSION_NAME);
		try {
			record.setCallernumber(usernumber);
			List<SendRecord> list = dao.querysendList(record, page);
			long countlist = dao.countsendlist(record);
			Userlogs userlogs = new Userlogs();
			userlogs.setCallnumber(usernumber);
			userlogs.setDesc("查询发送记录");
			// 添加用户操作日志
			logdao.adduserlog(userlogs);
			if (list == null) {
				logger.debug("该被叫：" + record.getCallednumber() + "没有相关数据");
			}
			page.setCount(countlist);
			map.put("list", list);
			map.put("page", page);
		} catch (Exception e) {
			map.put("exception", true);
			logger.warn("ProductController-product-exception", e);
		}
		return map;
	}
		
		
}	
