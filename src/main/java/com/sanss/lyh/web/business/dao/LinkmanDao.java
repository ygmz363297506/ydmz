package com.sanss.lyh.web.business.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sanss.lyh.web.business.model.LinkMan;
import com.sanss.lyh.web.business.utils.Page;
import com.sanss.lyh.web.business.utils.SQLUtil;

@Repository
public class LinkmanDao {
	
	// 日志定义
	final static Logger logger = LoggerFactory.getLogger(LinkmanDao.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public List<LinkMan> querylinkman(LinkMan linkMan,Page page){
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select * from sms_linkman where callnumber =?");
			parameter.add(linkMan.getCallnumber());
			if(linkMan.getGroup()!=null && !"".equals(linkMan.getGroup())){
				sqlbuffer.append(" and `group` like ?");
				parameter.add("%"+linkMan.getGroup()+"%");
			}if(linkMan.getContname()!=null && !"".equals(linkMan.getContname())){
				sqlbuffer.append(" and contname=?");
				parameter.add(linkMan.getContname());
			}
			logger.info("SQL:"+sqlbuffer.toString());
				return jdbcTemplate.query(SQLUtil.mysqlPage(sqlbuffer, page), parameter.toArray(),new BeanPropertyRowMapper<LinkMan>(LinkMan.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
//	public List<LinkMan> querylinkmanofone(LinkMan linkMan){
//		try {
//			StringBuffer sqlbuffer = new StringBuffer();
//			List<Object> parameter = new ArrayList<>();
//			sqlbuffer.append(" select * from sms_linkman where callnumber =?");
//			parameter.add(linkMan.getCallnumber());
//			logger.info("SQL:"+sqlbuffer.toString());
//				return jdbcTemplate.query(sqlbuffer.toString(), parameter.toArray(),new BeanPropertyRowMapper<LinkMan>(LinkMan.class));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
	
	
	public long countlinkman(LinkMan linkMan){
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select count(*) from sms_linkman where callnumber =?");
			parameter.add(linkMan.getCallnumber());
			if(linkMan.getGroup()!=null && !"".equals(linkMan.getGroup())){
				sqlbuffer.append(" and `group`=?");
				parameter.add(linkMan.getGroup());
			}if(linkMan.getContname()!=null && !"".equals(linkMan.getContname())){
				sqlbuffer.append(" and contname=?");
				parameter.add(linkMan.getContname());
			}
			long result = jdbcTemplate.queryForObject(sqlbuffer.toString(), parameter.toArray(),long.class);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public boolean removelinkman(LinkMan linkMan){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" delete from sms_linkman where callednumber=?");
		parameter.add(linkMan.getCallednumber());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	public boolean updatelinkman(LinkMan linkMan){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" update sms_linkman set contname=?,`group`=? where callednumber=?");
		parameter.add(linkMan.getContname());
		parameter.add(linkMan.getGroup());
		parameter.add(linkMan.getCallednumber());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	
	public boolean addlinkman(LinkMan linkMan){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" insert into sms_linkman(callnumber,contname,callednumber,`group`) values(?,?,?,?)");
		parameter.add(linkMan.getCallnumber());
		parameter.add(linkMan.getContname());
		parameter.add(linkMan.getCallednumber());
		parameter.add(linkMan.getGroup());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	
	
}
