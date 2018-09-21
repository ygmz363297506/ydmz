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

import com.sanss.lyh.web.business.dao.LinkmanDao;
import com.sanss.lyh.web.business.dao.LogTestDao;
import com.sanss.lyh.web.business.model.LinkMan;
import com.sanss.lyh.web.business.model.Userlogs;
import com.sanss.lyh.web.business.utils.Page;
/**
 * 联系人管理
 * Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/linkman")
public class LinkmanController extends BasicController{
	private static final Logger logger = LoggerFactory.getLogger(LinkmanController.class);
	
	@Autowired
	private LinkmanDao dao;
	
	@Autowired
	private LogTestDao logdao;
	
	
	
	
	/**
	 * linkman查询
	 * @param request
	 * @param linkMan
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/querylinkman",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> querylinkman(HttpServletRequest request,LinkMan linkMan,Page page){
		Map<String, Object> map = new TreeMap<>();
		// 登录号码
		String userlognumber = (String)getSessionAbbr(request, SESSION_NAME);
		linkMan.setCallnumber(userlognumber);
		List<LinkMan> list = dao.querylinkman(linkMan,page);
		long countlinkman = dao.countlinkman(linkMan);
		page.setCount(countlinkman);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(userlognumber);
		userlogs.setDesc("查询联系人管理中联系人操作");
		logdao.adduserlog(userlogs);
		map.put("list", list);
		map.put("page", page);
		return map;
	}
	
	
	/**
	 * linkman增加
	 * @param linkMan
	 * @return
	 */
	@RequestMapping(value="/addlinkman",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> addlinkman(HttpServletRequest request,LinkMan linkMan){
		// 获取用户名
		String usernumber = (String)getSessionAbbr(request, SESSION_NAME);
		Map<String, Object> map = new TreeMap<>();
		linkMan.setCallnumber(usernumber);
		boolean result = dao.addlinkman(linkMan);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("添加一条联系人信息");
		logdao.adduserlog(userlogs);
		map.put("result", result);
		return map;
	}
	
	/**
	 * linkman删除
	 * @param request
	 * @param linkMan
	 * @return
	 */
	@RequestMapping(value="/removelinkman",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> removelinkman(HttpServletRequest request,LinkMan linkMan){
		// 获取用户名
		String usernumber = (String)getSessionAbbr(request, SESSION_NAME);
		Map<String, Object> map = new TreeMap<>();
		boolean result = dao.removelinkman(linkMan);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("删除一条联系人信息");
		logdao.adduserlog(userlogs);
		map.put("result", result);
		return map;
	}
	
	/**
	 * linkman修改
	 * @param request
	 * @param linkMan
	 * @return
	 */
	@RequestMapping(value="/updatelinkman",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> updatelinkman(HttpServletRequest request,LinkMan linkMan){
		// 获取用户名
		String usernumber = (String)getSessionAbbr(request, SESSION_NAME);
		Map<String, Object> map = new TreeMap<>();
		boolean result = dao.updatelinkman(linkMan);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("修改一条联系人信息");
		logdao.adduserlog(userlogs);
		map.put("result", result);
		return map;
	}
	
}
