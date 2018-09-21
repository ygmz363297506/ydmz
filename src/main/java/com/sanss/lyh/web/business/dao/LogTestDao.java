package com.sanss.lyh.web.business.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.sanss.lyh.web.business.model.Backup;
import com.sanss.lyh.web.business.model.BlackList;
import com.sanss.lyh.web.business.model.Dustbin;
import com.sanss.lyh.web.business.model.LinkMan;
import com.sanss.lyh.web.business.model.LogTest;
import com.sanss.lyh.web.business.model.Mdnlist;
import com.sanss.lyh.web.business.model.NoteModel;
import com.sanss.lyh.web.business.model.POSTsend;
import com.sanss.lyh.web.business.model.SendRecord;
import com.sanss.lyh.web.business.model.Timingsend;
import com.sanss.lyh.web.business.model.User;
import com.sanss.lyh.web.business.model.Userlogs;
import com.sanss.lyh.web.business.utils.Page;
import com.sanss.lyh.web.business.utils.SQLUtil;


@Repository
public class LogTestDao
{
	final static Logger logger = LoggerFactory.getLogger(LogTestDao.class);
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	public List<LogTest> queryList(String agent,LogTest test,Page page) {
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select l.* from sms_log as l left JOIN mdn_list as s  ON s.mdn=l.du where 1=1 ");
			if(!agent.equals("agent")){
				sqlbuffer.append(" and l.text not like '%支付%' and l.text not like '%银行%' and s.agent=? ");
				parameter.add(agent);
				
			}
			if(test.getDu()!=null && test.getDu().length>0){
				sqlbuffer.append("and l.du=? ");
				parameter.add(test.getDu()[0]);
			}
			if(test.getText()!=null && !test.getText().equals("")){
				sqlbuffer.append("and l.text like ?");
				parameter.add("%"+test.getText()+"%");
			}
			sqlbuffer.append(" order by logtime desc  ");
			logger.info("查询sql："+sqlbuffer.toString());
			return jdbcTemplate.query(SQLUtil.mysqlPage(sqlbuffer, page), parameter.toArray(), new BeanPropertyRowMapper<LogTest>(LogTest.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public List<SendRecord> querysendList(SendRecord record,Page page) {
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select * from sms_send where callernumber=?  ");
			parameter.add(record.getCallernumber());
			if (record.getCallednumber()!=null && !"".equals(record.getCallednumber())) {
				sqlbuffer.append(" and callednumber=?");
				parameter.add(record.getCallednumber());
			}
			if(record.getText()!=null && !"".equals(record.getText())){
				sqlbuffer.append(" and text like ?");
				parameter.add("%"+record.getText()+"%");
			}
			sqlbuffer.append(" order by sendtime desc  ");
			logger.info("查询sql："+sqlbuffer.toString());
			return jdbcTemplate.query(SQLUtil.mysqlPage(sqlbuffer, page), parameter.toArray(), new BeanPropertyRowMapper<SendRecord>(SendRecord.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public long countsendlist(SendRecord record) {
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select count(*) from sms_send where callernumber=?  ");
			parameter.add(record.getCallernumber());
			if (record.getCallednumber()!=null && !"".equals(record.getCallednumber())) {
				sqlbuffer.append(" and callednumber=?");
				parameter.add(record.getCallednumber());
			}
			if(record.getText()!=null && !"".equals(record.getText())){
				sqlbuffer.append(" and text like ?");
				parameter.add("%"+record.getText()+"%");
			}
			sqlbuffer.append(" order by sendtime desc  ");
			logger.info("查询sql："+sqlbuffer.toString());
			long result=jdbcTemplate.queryForObject(sqlbuffer.toString(), parameter.toArray(),long.class);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}
	
	public List<Timingsend> querytimingsends(Timingsend timingsend,Page page){
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select * from sms_timing where caller=?");
			parameter.add(timingsend.getCaller());
			if(timingsend.getCalled()!=null && !"".equals(timingsend.getCalled())){
				sqlbuffer.append(" and called=? ");
				parameter.add(timingsend.getCalled());
			}
			if(timingsend.getText()!=null && !"".equals(timingsend.getText())){
				sqlbuffer.append(" and text like ? ");
				parameter.add("%"+timingsend.getText()+"%");
			}
			sqlbuffer.append(" order by sendtime desc");
			return jdbcTemplate.query(SQLUtil.mysqlPage(sqlbuffer, page), parameter.toArray(),new BeanPropertyRowMapper<Timingsend>(Timingsend.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public long counttimging(Timingsend timingsend) {
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select count(*) from sms_timing where caller=?");
			parameter.add(timingsend.getCaller());
			if(timingsend.getCalled()!=null && !"".equals(timingsend.getCalled())){
				sqlbuffer.append(" and called=? ");
				parameter.add(timingsend.getCalled());
			}
			if(timingsend.getText()!=null && !"".equals(timingsend.getText())){
				sqlbuffer.append(" and text like ? ");
				parameter.add("%"+timingsend.getText()+"%");
			}
			long result=jdbcTemplate.queryForObject(sqlbuffer.toString(), parameter.toArray(),long.class);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}
	
	public List<Dustbin> querydusList(Dustbin dustbin,List<BlackList> blackLists,Page page) {
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select ou as caller,du as called,text,logtime from sms_log where du=?  ");
			parameter.add(dustbin.getCaller());
			sqlbuffer.append(" and (");
			for (BlackList blackList1 : blackLists) {
				if(blackList1.getKeyword()!=null && !"".equals(blackList1.getKeyword())){
					sqlbuffer.append(" text like ? or");
					parameter.add("%"+blackList1.getKeyword()+"%");
				}
			}
			sqlbuffer.append(" text like '')");
			sqlbuffer.append(" or (");
			for (BlackList blackList1 : blackLists) {
				if(blackList1.getShieldnum()!=null && !"".equals(blackList1.getShieldnum())){
					sqlbuffer.append(" ou=? or");
					parameter.add(blackList1.getShieldnum());
				}
			}
			sqlbuffer.append(" ou='' ) ");
			sqlbuffer.append(" order by logtime desc  ");
			logger.info("查询垃圾箱sql："+sqlbuffer.toString());
			return jdbcTemplate.query(SQLUtil.mysqlPage(sqlbuffer, page), parameter.toArray(), new BeanPropertyRowMapper<Dustbin>(Dustbin.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	
	public long countquerydusList(Dustbin dustbin,List<BlackList> blackLists) {
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select count(*) from sms_log where du=?  ");
			parameter.add(dustbin.getCaller());
			sqlbuffer.append(" and (");
			for (BlackList blackList1 : blackLists) {
				if(blackList1.getKeyword()!=null && !"".equals(blackList1.getKeyword())){
					sqlbuffer.append(" text like ? or");
					parameter.add("%"+blackList1.getKeyword()+"%");
				}
			}
			sqlbuffer.append(" text like '')");
			sqlbuffer.append(" or (");
			for (BlackList blackList1 : blackLists) {
				if(blackList1.getShieldnum()!=null && !"".equals(blackList1.getShieldnum())){
					sqlbuffer.append(" ou=? or");
					parameter.add(blackList1.getShieldnum());
				}
			}
			sqlbuffer.append(" ou='') ");
			sqlbuffer.append(" order by logtime desc  ");
			logger.info("查询sql："+sqlbuffer.toString());
			return jdbcTemplate.queryForObject(sqlbuffer.toString(), parameter.toArray(), long.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}
	
	public List<LogTest> queryreceveList(LogTest record,List<BlackList> blackLists,Page page,String olddate,String nowdate) {
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select * from sms_log where du=?");
			parameter.add(record.getDu()[0]);
			if(record.getOu()!=null && !"".equals(record.getOu())){
				sqlbuffer.append(" and ou=? ");
				parameter.add(record.getOu());
			}
			if(record.getText() != null && !"".equals(record.getText())){
				sqlbuffer.append(" and text like ?");
				parameter.add("%"+record.getText()+"%");
			}
			sqlbuffer.append(" and (");
			for (BlackList blackList : blackLists) {
				if(blackList.getKeyword()!=null && !"".equals(blackList.getKeyword())){
					sqlbuffer.append(" text not like ? and");
					parameter.add("%"+blackList.getKeyword()+"%");
				}
			}
			sqlbuffer.append(" text not like '')");
			sqlbuffer.append(" and (");
			for (BlackList blackList : blackLists) {
				if(blackList.getShieldnum()!=null && !"".equals(blackList.getShieldnum())){
					sqlbuffer.append(" ou!=? and");
					parameter.add(blackList.getShieldnum());
				}
			}
			sqlbuffer.append(" ou!='') ");
			sqlbuffer.append(" and logtime BETWEEN ? and ? ");
			parameter.add(olddate);
			parameter.add(nowdate);
			sqlbuffer.append(" order by logtime desc  ");
			logger.info("查询接收短信sql："+sqlbuffer.toString());
			return jdbcTemplate.query(SQLUtil.mysqlPage(sqlbuffer, page), parameter.toArray(), new BeanPropertyRowMapper<LogTest>(LogTest.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
	public long countceveList(LogTest record,List<BlackList> blackLists,String olddate,String nowdate) {
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select count(*) from sms_log where du=?  ");
			parameter.add(record.getDu()[0]);
			if(record.getOu()!=null && !"".equals(record.getOu())){
				sqlbuffer.append(" and ou=? ");
				parameter.add(record.getOu());
			}
			if(record.getText() != null && !"".equals(record.getText())){
				sqlbuffer.append(" and text like ?");
				parameter.add("%"+record.getText()+"%");
			}
			sqlbuffer.append(" and (");
			for (BlackList blackList : blackLists) {
				if(blackList.getKeyword()!=null && !"".equals(blackList.getKeyword())){
					sqlbuffer.append(" text not like ? and");
					parameter.add("%"+blackList.getKeyword()+"%");
				}
			}
			sqlbuffer.append(" text not like '')");
			sqlbuffer.append(" and (");
			for (BlackList blackList : blackLists) {
				if(blackList.getShieldnum()!=null && !"".equals(blackList.getShieldnum())){
					sqlbuffer.append(" ou!=? and");
					parameter.add(blackList.getShieldnum());
				}
			}
			sqlbuffer.append(" ou!='') ");
			sqlbuffer.append(" and logtime BETWEEN ? and ? ");
			parameter.add(olddate);
			parameter.add(nowdate);
			sqlbuffer.append(" order by logtime desc  ");
			logger.info("查询sql："+sqlbuffer.toString());
			return jdbcTemplate.queryForObject(sqlbuffer.toString(), parameter.toArray(),long.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}
	
	
	
	public List<NoteModel> querymodelnote(NoteModel model){
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select * from sms_model where callernumber=?");
			parameter.add(model.getCallernumber());
			logger.info("SQL:"+sqlbuffer.toString());
				return jdbcTemplate.query(sqlbuffer.toString(), parameter.toArray(),new BeanPropertyRowMapper<NoteModel>(NoteModel.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<BlackList> queryblacklist(BlackList blackList,String s){
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select * from sms_blacklist where callernumber=?");
			parameter.add(blackList.getCallernumber());
			if(s!=null && !"".equals(s)){
				if(s.equals("keyword")){
					sqlbuffer.append(" and AND keyword is not null");
				}else if(s.equals("shieldnum")){
					sqlbuffer.append(" and AND shieldnum is not null");
				}
			}
			logger.info("SQL:"+sqlbuffer.toString());
			return jdbcTemplate.query(sqlbuffer.toString(), parameter.toArray(),new BeanPropertyRowMapper<BlackList>(BlackList.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public long countblacklist(BlackList blackList){
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select count(*) from sms_blacklist where callernumber =?");
			parameter.add(blackList.getCallernumber());
			long result = jdbcTemplate.queryForObject(sqlbuffer.toString(), parameter.toArray(),long.class);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public List<Backup> querybackup(Backup backup,Page page){
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select * from sms_backup where called=?");
			parameter.add(backup.getCalled());
			if(backup.getCaller()!=null && !"".equals(backup.getCaller())){
				sqlbuffer.append(" and caller =? ");
				parameter.add(backup.getCaller());
			}else if(backup.getText()!=null && !"".equals(backup.getText())){
				sqlbuffer.append(" and text like ?");
				parameter.add("%"+backup.getText()+"%");
			}
			logger.info("SQL:"+sqlbuffer.toString());
			return jdbcTemplate.query(SQLUtil.mysqlPage(sqlbuffer, page), parameter.toArray(),new BeanPropertyRowMapper<Backup>(Backup.class));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public long countbackup(Backup backup){
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select count(*) from sms_backup where called =?");
			parameter.add(backup.getCalled());
			if(backup.getCaller()!=null && !"".equals(backup.getCaller())){
				sqlbuffer.append(" and caller =? ");
				parameter.add(backup.getCaller());
			}else if(backup.getText()!=null && !"".equals(backup.getText())){
				sqlbuffer.append(" and text like ?");
				parameter.add("%"+backup.getText()+"%");
			}
			long result = jdbcTemplate.queryForObject(sqlbuffer.toString(), parameter.toArray(),long.class);
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	
	
	public boolean adddustbin(LogTest logTest,String keyword){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" insert into sms_dustbin(called,caller,text,logtime,keyword) values(?,?,?,?,?)");
		parameter.add(logTest.getDu()[0]);
		parameter.add(logTest.getOu());
		parameter.add(logTest.getText());
		parameter.add(logTest.getLogtime());
		parameter.add(keyword);
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){	
			result = true;
		}
		return result;
	}
	
	
	
	
	/**
	 * 添加用户操作日志
	 * @param userlogs
	 * @return
	 */
	public boolean adduserlog(Userlogs userlogs){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" insert into sms_userlogs(callnumber,`time`,`desc`) values(?,?,?)");
		parameter.add(userlogs.getCallnumber());
		parameter.add(userlogs.getTime());
		parameter.add(userlogs.getDesc());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	/**
	 * 添加发送记录
	 * @param userlogs
	 * @return
	 */
	public boolean addsendlogs(SendRecord sendRecord){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" insert into sms_send(callernumber,callednumber,text,sendtime,status) values(?,?,?,?,?)");
		parameter.add(sendRecord.getCallernumber());
		parameter.add(sendRecord.getCallednumber());
		parameter.add(sendRecord.getText());
		parameter.add(sendRecord.getSendtime());
		parameter.add(sendRecord.getStatus());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	/**
	 * 添加定时发送记录
	 * @param userlogs
	 * @return
	 */
	public boolean addsendtimelogs(Timingsend timingsend){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" insert into sms_timing(createtime,caller,called,text,sendtime) values(?,?,?,?,?)");
		parameter.add(timingsend.getCreatetime());
		parameter.add(timingsend.getCaller());
		parameter.add(timingsend.getCalled());
		parameter.add(timingsend.getText());
		parameter.add(timingsend.getSendtime());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	
	
	
	public boolean deletemodelnote(NoteModel model){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" delete from sms_model where id=?");
		parameter.add(model.getId());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	public boolean updatemodelnote(NoteModel model){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" update sms_model set callermodel=? where id=?");
		parameter.add(model.getCallermodel());
		parameter.add(model.getId());
		logger.info("修改sql:"+sqlbuffer.toString());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	public boolean addmodelnote(NoteModel model){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" insert into sms_model(callernumber,callermodel,createtime) values(?,?,?)");
		parameter.add(model.getCallernumber());
		parameter.add(model.getCallermodel());
		parameter.add(model.getCreatetime());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	public boolean addblacklist(BlackList blackList){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" insert into sms_blacklist(callernumber,shieldnum,keyword) values(?,?,?)");
		parameter.add(blackList.getCallernumber());
		parameter.add(blackList.getShieldnum());
		parameter.add(blackList.getKeyword());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	public boolean addblackup(Backup backup){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" insert into sms_backup(caller,called,text,time) values(?,?,?,?)");
		parameter.add(backup.getCaller());
		parameter.add(backup.getCalled());
		parameter.add(backup.getText());
		parameter.add(backup.getTime());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	public boolean deleteblackup(Backup backup){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" delete from sms_backup where id=?");
		parameter.add(backup.getId());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	public boolean deleteblacklist(BlackList blackList){
		boolean result = false;
		StringBuffer sqlbuffer = new StringBuffer();
		List<Object> parameter = new ArrayList<>();
		sqlbuffer.append(" delete from sms_blacklist where id=?");
		parameter.add(blackList.getId());
		int a = jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		if(a>0){
			result = true;
		}
		return result;
	}
	
	public long countlist(String agent,LogTest test){
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select count(*) from sms_log as l left JOIN mdn_list as s  ON s.mdn=l.du where 1=1 ");
			if(!agent.equals("agent")){
				sqlbuffer.append(" and l.text not like '%支付%' and l.text not like '%银行%' and s.agent=? ");
				parameter.add(agent);
			}
			if(test.getDu()!=null && test.getDu().length>0){
				sqlbuffer.append(" and l.du=? ");
				parameter.add(test.getDu()[0]);
			}
			if(test.getText()!=null && !test.getText().equals("")){
				sqlbuffer.append("and l.text like ?");
				parameter.add("%"+test.getText()+"%");
			}
			return jdbcTemplate.queryForObject(sqlbuffer.toString(), parameter.toArray(),long.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}
	
	
	
	public boolean confirmuser(String username,String password){
		boolean result=false;
		StringBuffer buffer=new StringBuffer();
		List<Object> list=new ArrayList<>();
		buffer.append(" select count(*) from mdn_user where agent=? and password=?");
		list.add(username);
		list.add(password);
		long i=jdbcTemplate.queryForObject(buffer.toString(), list.toArray(),long.class);
		if(i==1){
			result=true;
		}
		return result; 
	}
	public boolean updatepwd(User user,String password){
		StringBuffer buffer=new StringBuffer();
		List<Object> list=new ArrayList<>();
		buffer.append(" update mdn_user set password=? where agent=?");
		list.add(password);
		list.add(user.getAgent());
		return jdbcTemplate.update(buffer.toString(), list.toArray()) == 1;
	}
	
	public boolean login(User user){
		boolean result=false;
		StringBuffer buffer=new StringBuffer();
		List<Object> list=new ArrayList<>();
		buffer.append(" select count(*) from mdn_user where agent=? and password=?");
		list.add(user.getAgent());
		list.add(user.getPassword());
		long i=jdbcTemplate.queryForObject(buffer.toString(), list.toArray(),long.class);
		if(i==1){
			result=true;
		}
		return result;
	}
	
	public long countlikequery(String agent,LogTest test){
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select count(*) from sms_log as l left JOIN mdn_list as s  ON s.mdn=l.du where 1=1 ");
			if(!agent.equals("agent")){
				sqlbuffer.append(" and l.text not like '%支付%' and l.text not like '%银行%' and s.agent=? ");
				parameter.add(agent);
			}
			if(test.getText()!=null && !test.getText().equals("")){
				sqlbuffer.append("and text like ?");
				parameter.add("%"+test.getText()+"%");
			}
			return jdbcTemplate.queryForObject(sqlbuffer.toString(),parameter.toArray(),long.class);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	
//	
	public boolean suresend(String mdn){
		boolean result=false;
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select `switch` as switchs  from mdn_list where mdn=?");
			parameter.add(mdn);
			List<Mdnlist> list=jdbcTemplate.query(sqlbuffer.toString(), parameter.toArray(),new BeanPropertyRowMapper<Mdnlist>(Mdnlist.class));
			if(list!=null){
				Mdnlist mdnlist=list.get(0);
				if(mdnlist.getSwitchs().equals("1")){
					result=true;
				}
			}
			return result;
	}
	
	public POSTsend querysend(String du){
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select du as called,ou as caller,text as content from sms_log where du=? ");
			parameter.add(du);
			sqlbuffer.append(" order by logtime desc  ");
			List<POSTsend> list=jdbcTemplate.query(sqlbuffer.toString(), parameter.toArray(), new BeanPropertyRowMapper<POSTsend>(POSTsend.class));
			if(list!=null){
				return list.get(0);
			}
			return null;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
		
	}
	
	public int insertlog(LogTest logTest) {
		try {
			logger.info("..................ready to insert ");
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" insert sms_log(du,ou,text,logtime) values(?,?,?,?) ");
//			String du=logTest.getDu()[0];
			parameter.add(logTest.getDu()[0]);
			parameter.add(logTest.getOu());
			parameter.add(logTest.getText());
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			parameter.add(dateFormat.format(new Date()));
			logger.info("..................insert sql:"+sqlbuffer.toString()+"========parameter:"+parameter.toString());
			return jdbcTemplate.update(sqlbuffer.toString(),parameter.toArray());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return 0;
		}
	}
	public static void main(String[] args) {
		/*String[] a={};
		System.out.println(a);
		System.out.println("a"+a.length);
		String[] b={"2","3"};
		System.out.println("b:"+b.length);
		*/
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		Date newdate=new Date(System.currentTimeMillis());
		Date date = new Date(System.currentTimeMillis()-5*60*1000);
		System.out.println("现在的时间为："+dateFormat.format(newdate)+"前五分钟为："+dateFormat.format(date));
//		System.out.println(dao.suresend("15300483182"));
	}
	
	
	public List<SendRecord> querynumList(SendRecord record,Page page) {
		try {
			StringBuffer sqlbuffer = new StringBuffer();
			List<Object> parameter = new ArrayList<>();
			sqlbuffer.append(" select * from sms_send where 1=1 ");
			logger.info("查询sql："+sqlbuffer.toString());
			return jdbcTemplate.query(SQLUtil.mysqlPage(sqlbuffer, page), parameter.toArray(), new BeanPropertyRowMapper<SendRecord>(SendRecord.class));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}
