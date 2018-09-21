//package com.sanss.lyh.web.business.service;
//
//import java.util.Date;
//import java.util.List;
//import java.util.concurrent.ConcurrentHashMap;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.sanss.lyh.web.business.dao.DrawLogDao;
//import com.sanss.lyh.web.business.dao.PrizeDao;
//import com.sanss.lyh.web.business.dao.PrizePoolDao;
//import com.sanss.lyh.web.business.dao.UserDao;
//import com.sanss.lyh.web.business.model.DrawLog;
//import com.sanss.lyh.web.business.model.Prize;
//import com.sanss.lyh.web.business.model.PrizePool;
//import com.sanss.lyh.web.business.model.User;
//import com.sanss.lyh.web.frame.SystemConfig;
//
//
//
//@Service
//public class TaskService{
//	@Autowired
//	private UserDao userDao;
//	@Autowired
//	private PrizeDao prizeDao;
//	@Autowired
//	private DrawLogDao drawLogDao;
//	@Autowired
//	private PrizePoolDao prizePoolDao;
//	
//	public ConcurrentHashMap<Integer, Prize> getNowPrize() {
//		List<Prize> lp = prizeDao.findAll();
//		ConcurrentHashMap<Integer, Prize> result = new ConcurrentHashMap<Integer, Prize>();
//		for (Prize t : lp) {
//			result.put(t.getId(), t);
//		}
//		return result;
//	}
//
//	
//	public List<User> getAllUser() {
//		return userDao.findAll();
//	}
//
//	
//	public List<DrawLog> todayWon() {
//		return drawLogDao.findTodayWon(SystemConfig.DBdayFormat.format(new Date()));
//	}
//
//	
//	public List<PrizePool> prizePool() {
//		return prizePoolDao.findUsable();
//	}
//
//	
//	public List<PrizePool> prizePoolTypeCount() {
//		return prizePoolDao.findCountUsable();
//	}
//
//}
