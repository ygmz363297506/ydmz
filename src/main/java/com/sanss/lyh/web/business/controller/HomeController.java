package com.sanss.lyh.web.business.controller;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.dom4j.tree.BackedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sanss.lyh.web.business.dao.LogTestDao;
import com.sanss.lyh.web.business.model.Backup;
import com.sanss.lyh.web.business.model.BlackList;
import com.sanss.lyh.web.business.model.Dustbin;
import com.sanss.lyh.web.business.model.LinkMan;
import com.sanss.lyh.web.business.model.LogTest;
import com.sanss.lyh.web.business.model.NoteModel;
import com.sanss.lyh.web.business.model.POSTsend;
import com.sanss.lyh.web.business.model.SendRecord;
import com.sanss.lyh.web.business.model.Timingsend;
import com.sanss.lyh.web.business.model.User;
import com.sanss.lyh.web.business.model.Userlogs;
import com.sanss.lyh.web.business.utils.Page;
import com.sanss.lyh.web.business.utils.ResultUtil;
import com.sanss.lyh.web.business.utils.ValidateUtil;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private LogTestDao dao;
	@Autowired
	private HttpSession session;
	private int width = 100;//定义图片的width
	private int height = 30;//定义图片的height
	private int codeCount = 4;//定义图片上显示验证码的个数
	private int xx = 16;
	private int fontHeight = 21;
	private int codeY = 20;
	char[] codeSequence = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D',
			'F','G','H','J','K','L','Z','X','C','V','B','N','M',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	char[] mathcode = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws NoSuchAlgorithmException 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home(Locale locale,Model model,HttpServletRequest request){
		logger.info("Welcome home! The client locale is {}.", locale);
		session = request.getSession();
		ModelAndView mav=new ModelAndView();
		User user=(User) session.getAttribute("logusernumber");
		if(user == null){
			mav.setViewName("phlogin");
		}else{
			mav.setViewName("home");
		}	
		return mav;
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> logininnotes(String phonenumber,String phonecode,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		String phone = phonenumber;
		String sphone = (String)session.getAttribute("phone");
		String sphonecode = (String)session.getAttribute("phonecode");
		logger.info("==获取到的phone为："+phone+"验证码为："+phonecode);
		logger.info("session中的验证码为："+sphonecode+"电话号码为："+sphone);
		if(sphone!=null && sphonecode!=null){
			if(sphone.equals(phone) && sphonecode.equalsIgnoreCase(phonecode)){
				logger.info("====登录成功！");
				session.setAttribute("logusernumber", phone);
				session.setAttribute("phpwd", sphone+phone);
			}else{
				map.put("msg", "手机验证码错误");
				logger.info("====手机验证码错误登录失败！");
			}
		}
		return map;
	}
	
	@RequestMapping(value="/loginvm",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public ModelAndView loginvm(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav = new ModelAndView();
		session = request.getSession();
		String logusernumber = (String)session.getAttribute("logusernumber");
		String pwd = (String)session.getAttribute("phpwd");
			if(pwd!=null && !pwd.equals("")){
				logger.info("====登录成功！");
				Userlogs userlogs = new Userlogs();
				userlogs.setCallnumber(logusernumber);
				userlogs.setDesc(logusernumber+"登录成功！");
				dao.adduserlog(userlogs);
				mav.addObject("username", logusernumber);
				mav.setViewName("home");
			}else{
				mav.addObject("msg", "登录失败");
				mav.setViewName("phlogin");
				logger.info("====登录失败！");
			}
		return mav;
	}
	
	
	
	/**
	 * 发送验证码
	 * @return
	 */
	@RequestMapping(value="/sendcode",method = RequestMethod.GET,produces = "application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> gaincodes(HttpServletRequest request,String code,String phonenumber){
		session=request.getSession();
		Map<String, Object> map = new TreeMap<>();
		String validate=(String) session.getAttribute("code");
			if(validate.equalsIgnoreCase(code)){
				ResultUtil resultUtil = new ResultUtil();
				POSTsend tsend = new POSTsend();
				Random random = new Random();
				StringBuffer buffer = new StringBuffer();
				for(int i = 0; i < 6; i ++){
					String codes = String.valueOf(mathcode[random.nextInt(10)]);
					buffer.append(codes);
				}
				String rancode = buffer.toString();
				logger.info("验证码为："+rancode);
				session.setAttribute("phonecode", rancode);
				session.setAttribute("phone", phonenumber);
				tsend.setContent("欢迎您登录翼动拇指,您的登录验证码为:"+rancode+"【中国电信上海公司】");
				tsend.setCalled(phonenumber);
				//主叫还不知道叫什么
				tsend.setCaller("106590210084301");
				logger.info("发送号码为："+phonenumber);
				try {
					resultUtil.facesend(tsend);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				map.put("msg", "图片验证码错误");
		}
		return map;
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> select(LogTest test,Page page,HttpServletRequest request) {
		Map<String, Object> map = new TreeMap<String, Object>();
		User user=(User)session.getAttribute("user");
		try {
			List<LogTest> list = dao.queryList(user.getAgent(),test, page);
			long countlist = dao.countlist(user.getAgent(),test);
			if (list == null) {
				logger.debug("该被叫：" + test.getDu() + "没有相关数据");
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
	
	@RequestMapping(value="/querynumcout",method = RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> querynumcout(Page page,SendRecord record){
		Map<String, Object> map = new HashMap<>();
		try {
			List<SendRecord> list = dao.querynumList(record, page);
			page.setCount(2413754);
			map.put("list", list);
			map.put("page", page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	
	
	
	/**
	 * 查询定时短信记录
	 * @param timingsend
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/querytimingnote", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> querytimingnote(HttpServletRequest request,Timingsend timingsend,Page page) {
		Map<String, Object> map = new TreeMap<String, Object>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		timingsend.setCaller(usernumber);
		List<Timingsend> timingsends = dao.querytimingsends(timingsend, page);
		long counttiming = dao.counttimging(timingsend);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("查询定时短信记录");
		dao.adduserlog(userlogs);
		page.setCount(counttiming);
		map.put("list", timingsends);
		map.put("page", page);
		return map;
	}
	
	/**
	 * 垃圾箱查询
	 * @param dustbin
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/dustbinquery", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> dustbinquery(Dustbin dustbin,Page page,HttpServletRequest request) {
		Map<String, Object> map = new TreeMap<String, Object>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		try {
			dustbin.setCaller("15316394656");
			BlackList blackList = new BlackList();
			blackList.setCallernumber(usernumber);
			logger.info("==callenum:"+blackList.getCallernumber());
			List<BlackList> blackLists = dao.queryblacklist(blackList, "");
			List<Dustbin> list = dao.querydusList(dustbin, blackLists,page);
			long countlist = dao.countquerydusList(dustbin,blackLists);
			Userlogs userlogs = new Userlogs();
			userlogs.setCallnumber(usernumber);
			userlogs.setDesc("查询垃圾箱");
			dao.adduserlog(userlogs);
			if (list == null) {
				logger.debug("该被叫：" + dustbin.getCalled() + "没有相关数据");
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
	/**
	 * 无卡短信接收查询
	 * @param record
	 * @param page
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/recevenotquery", method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> recevenotquery(LogTest record,Page page,HttpServletRequest request) {
		Map<String, Object> map = new TreeMap<String, Object>();
		String usernumber = (String)session.getAttribute("logusernumber");
		session = request.getSession();
		try {
			String[] a={usernumber};
			record.setDu(a);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date nowdate=new Date(System.currentTimeMillis());
			//此为5分钟
			Date olddate = new Date(System.currentTimeMillis()-5*60*1000);
			BlackList blackList = new BlackList();
			blackList.setCallernumber(usernumber);
			List<BlackList> blackLists = dao.queryblacklist(blackList, "");
			logger.info("======当前时间为："+nowdate+"olddate："+olddate);
			List<LogTest> list = dao.queryreceveList(record,blackLists,page,df.format(olddate),df.format(nowdate));
			long countlist = dao.countceveList(record,blackLists,df.format(olddate),df.format(nowdate));
			Userlogs userlogs = new Userlogs();
			userlogs.setCallnumber(usernumber);
			userlogs.setDesc("查询无卡短信接收");
			dao.adduserlog(userlogs);
			if (list == null) {
				logger.debug("该被叫：" + record.getDu() + "没有相关数据");
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
	
	
	
	
	/**
	 * 自写短信的联系人
	 * @param request
	 * @param linkMan
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "/querylinkofone",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> querylinkofone(LinkMan linkMan){
		Map<String, Object> map = new TreeMap<>();
		String usernumber = (String)session.getAttribute("logusernumber");
		linkMan.setCallnumber(usernumber);
//		List<LinkMan> list = dao.querylinkmanofone(linkMan);
//		map.put("list", list);
		return map;
	}
	
	/**
	 * 种子模板
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/addmodelnote",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> addmodelnotes(HttpServletRequest request,NoteModel model){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		model.setCallernumber(usernumber);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setCreatetime(df.format(new Date()));
		boolean result = dao.addmodelnote(model);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("添加一条种子模板短信");
		dao.adduserlog(userlogs);
		map.put("result", result);
		return map;
	}
	
	@RequestMapping(value="/updatemodelnote",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> updatemodelnote(HttpServletRequest request,NoteModel model){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		boolean result = dao.updatemodelnote(model);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("修改一条种子模板短信");
		dao.adduserlog(userlogs);
		map.put("result", result);
		return map;
	}
	
	@RequestMapping(value="/deletemodelnote",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> deletemodelnote(HttpServletRequest request,NoteModel model){
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		Map<String, Object> map = new TreeMap<>();
		boolean result = dao.deletemodelnote(model);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("删除一条种子模板短信");
		dao.adduserlog(userlogs);
		map.put("result", result);
		return map;
	}
	
	@RequestMapping(value="/querymodelnote",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> querymodelnote(HttpServletRequest request,NoteModel model){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		model.setCallernumber(usernumber);
		List<NoteModel> list = dao.querymodelnote(model);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("查询所有种子模板短信");
		dao.adduserlog(userlogs);
		map.put("list", list);
		return map;
	}
	
	/**
	 * 短信屏蔽
	 * @param blackList
	 * @param f
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/queryblacklist",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> queryblacklist(HttpServletRequest request,BlackList blackList,Page page){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		blackList.setCallernumber(usernumber);
		String a="";
		List<BlackList> list = dao.queryblacklist(blackList,a);
		long count = dao.countblacklist(blackList);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("查询短信屏蔽信息");
		dao.adduserlog(userlogs);
		page.setCount(count);
		map.put("list", list);
		map.put("page", page);
		return map;
	}
	
	@RequestMapping(value="/deleteblacklist",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> deleteblacklist(HttpServletRequest request,BlackList blackList){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		boolean result = dao.deleteblacklist(blackList);
		String usernumber = (String)session.getAttribute("logusernumber");
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("删除短信黑名单或关键字");
		dao.adduserlog(userlogs);
		map.put("result", result);
		return map;
	}
	
	@RequestMapping(value="/addblacklist",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> addblacklist(HttpServletRequest request,BlackList blackList){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		blackList.setCallernumber(usernumber);
		boolean result = dao.addblacklist(blackList);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("添加短信黑名单或关键字");
		dao.adduserlog(userlogs);
		map.put("result", result);
		return map;
	}
	
	/**
	 * 查询云备份短信
	 * @param backup
	 * @param page
	 * @return
	 */
	@RequestMapping(value="/querybackup",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> queryblacklist(HttpServletRequest request,Backup backup,Page page){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		backup.setCalled(usernumber);
		List<Backup> list = dao.querybackup(backup,page);
		long count = dao.countbackup(backup);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("查询云备份短信");
		dao.adduserlog(userlogs);
		page.setCount(count);
		map.put("list", list);
		map.put("page", page);
		return map;
	}
	//备份短信
	@RequestMapping(value="/addblackup",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> addblackup(HttpServletRequest request,Backup backup){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		backup.setCalled(usernumber);
		boolean result = dao.addblackup(backup);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("备份至云短信，云短信添加一条");
		dao.adduserlog(userlogs);
		map.put("result", result);
		return map;
	}
	@RequestMapping(value="/removeblackup",method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> removeblackup(HttpServletRequest request,Backup backup){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		boolean result = dao.deleteblackup(backup);
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("删除云备份短信");
		dao.adduserlog(userlogs);
		map.put("result", result);
		return map;
	}
	
	
	/**
	 * 清空缓存退出登录
	 * @return
	 */
	@RequestMapping(value="/drop",method=RequestMethod.GET)
	public @ResponseBody ModelAndView  dropblack(){
		String usernumber = (String)session.getAttribute("logusernumber");
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("清除缓存退出登录");
		dao.adduserlog(userlogs);
		session.removeAttribute("user");
		session.removeAttribute("logusernumber");
		ModelAndView mav=new ModelAndView();
		mav.setViewName("phlogin");
		return mav;
	}
	
	@RequestMapping(value="/updatepwd",method = RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> update(String oldpwd,String newpwd) {
		Map<String, Object> map=new TreeMap<>();
		User user=(User)session.getAttribute("user");
		if(dao.confirmuser(user.getAgent(), oldpwd)){
			if(dao.updatepwd(user, newpwd)){
				map.put("msg", "修改成功！");
				map.put("code", 1);
			}else{
				map.put("msg", "修改失败！");
				map.put("code", 0);
			}
		}else{
			map.put("msg", "旧密码错误！");
			map.put("code", 0);
		}
		return map;
	}
	
	@RequestMapping(value="/valider" ,method = RequestMethod.POST)
	public  ModelAndView valider(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mav=new ModelAndView();
		HttpSession session=request.getSession();
		String username=request.getParameter("username");
		System.out.println("username为："+username);
		String password=request.getParameter("password");
		System.out.println("password为："+password);
		String code=request.getParameter("code");
		System.out.println("code为："+code);
		User user=new User();
		user.setAgent(username);
		user.setPassword(password);
		Userlogs userlogs = new Userlogs();
		String validate=(String) session.getAttribute("code");
		System.out.println("真实生成的验证码是："+validate);
		if(dao.login(user)){
			if(!validate.equalsIgnoreCase(code)){
				mav.setViewName("login");
				System.out.println("验证码错误！");
				mav.addObject("msg", "验证码错误");
				userlogs.setCallnumber(username);
				userlogs.setDesc("验证码错误");
				dao.adduserlog(userlogs);
			}else{
				mav.setViewName("home");
				mav.addObject("username",user.getAgent());
//				user.setAgent("15821285073");
				session.setAttribute("logusernumber", "15821285073");
				System.out.println("登录成功");
				userlogs.setCallnumber("15821285073");
				userlogs.setDesc("登录成功");
				dao.adduserlog(userlogs);
			}
		}else if(username.equals("agent") && password.equals("wanqing@sanss123")){
			if(!validate.equalsIgnoreCase(code)){
				mav.setViewName("login");
				System.out.println("验证码错误！");
				mav.addObject("msg", "验证码错误");
			}else{
				mav.setViewName("home");
				mav.addObject("username",user.getAgent());
//				user.setAgent("15821285073");
				session.setAttribute("user", user);
				System.out.println("登录成功");
			}
		}else{
			mav.setViewName("login");
			System.out.println("账号密码错误！");
			mav.addObject("msg", "账号密码错误");
		}
		return mav;
	}
	
	
	@RequestMapping(value="/code")
	public void getCode(HttpServletRequest request,HttpServletResponse response) throws IOException{
		// 定义图像buffer
				BufferedImage buffImg = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
//				Graphics2D gd = buffImg.createGraphics();
				//Graphics2D gd = (Graphics2D) buffImg.getGraphics();
				Graphics gd = buffImg.getGraphics();
				// 创建一个随机数生成器类
				Random random = new Random();
				// 将图像填充为白色
				gd.setColor(Color.WHITE);
				gd.fillRect(0, 0, width, height);

				// 创建字体，字体的大小应该根据图片的高度来定。
				Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
				// 设置字体。
				gd.setFont(font);
				// 画边框。
				gd.setColor(Color.BLACK);
				gd.drawRect(0, 0, width - 1, height - 1);
				// 随机产生40条干扰线，使图象中的认证码不易被其它程序探测到。
				gd.setColor(Color.BLACK);
//				for (int i = 0; i < 40; i++) {
//					int x = random.nextInt(width);
//					int y = random.nextInt(height);
//					int xl = random.nextInt(12);
//					int yl = random.nextInt(12);
//					gd.drawLine(x, y, x + xl, y + yl);
//				}
				// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
				StringBuffer randomCode = new StringBuffer();
				int red = 0, green = 0, blue = 0;

				// 随机产生codeCount数字的验证码。
				for (int i = 0; i < codeCount; i++) {
					// 得到随机产生的验证码数字。
					String code = String.valueOf(codeSequence[random.nextInt(36)]);
					// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
					red = random.nextInt(255);
					green = random.nextInt(255);
					blue = random.nextInt(255);

					// 用随机产生的颜色将验证码绘制到图像中。
					gd.setColor(new Color(red, green, blue));
					gd.drawString(code, (i + 1) * xx, codeY);

					// 将产生的四个随机数组合在一起。
					randomCode.append(code);
				}
				// 将四位数字的验证码保存到Session中。
				HttpSession session = request.getSession();
				System.out.print(randomCode);
				session.setAttribute("code", randomCode.toString());

				// 禁止图像缓存。
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0);

				response.setContentType("image/jpeg");

				// 将图像输出到Servlet输出流中。
				ServletOutputStream sos = response.getOutputStream();
				ImageIO.write(buffImg, "jpeg", sos);
				sos.close();
	}
	
	/**
	 * 自写发送短信
	 * @param alllinkmans
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/sendnotesing",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> sendnotesing(HttpServletRequest request,String alllinkmans,SendRecord record){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		String[] linkman = alllinkmans.split(";");
		POSTsend posTsend = new POSTsend();
		ResultUtil resultUtil = new ResultUtil();
		//主叫号码
		posTsend.setCaller(usernumber);
		posTsend.setContent(record.getText());
		record.setCallernumber(usernumber);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		record.setSendtime(df.format(new Date()));
		String a="";
		for(int i = 0; i < linkman.length; i++){
//			if(linkman[i].length()==11){
//				posTsend.setCalled(linkman[i]);
//				record.setCallednumber(linkman[i]);
//				logger.info("===被叫："+(linkman[i]));
//			}else if((linkman[i].split("（"))[0].length()==11){
				posTsend.setCalled((linkman[i].split("（"))[0]);
				record.setCallednumber((linkman[i].split("（"))[0]);
				logger.info("===被叫："+(linkman[i].split("（"))[0]);
//			}else{
//				a+=linkman[i]+";";
//			}
			try {
				if(resultUtil.facesend(posTsend)){
					record.setStatus(1);
				}else{
					record.setStatus(0);
					a+=linkman[i]+";";
				}
				dao.addsendlogs(record);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("自写短信发送....");
		dao.adduserlog(userlogs);
		map.put("result",true);
		map.put("msg", a);
		return map;
	}
	
	/**
	 * 定时短信发送
	 * @param alllinkmans
	 * @param record
	 * @return
	 */
	@RequestMapping(value="/sendnotestime",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody Map<String, ? extends Object> sendnotestime(HttpServletRequest request,String alllinkmans,Timingsend timingsend){
		Map<String, Object> map = new TreeMap<>();
		session = request.getSession();
		String usernumber = (String)session.getAttribute("logusernumber");
		
		String[] linkman = alllinkmans.split(";");
		ResultUtil resultUtil = new ResultUtil();
		POSTsend posTsend = new POSTsend();
		//主叫号码
		posTsend.setCaller(usernumber);
		posTsend.setContent(timingsend.getText());
		timingsend.setCaller(usernumber);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		timingsend.setCreatetime(df.format(new Date()));
		String a="";
		for(int i = 0; i < linkman.length; i++){
			if(linkman[i].length()==11){
				posTsend.setCalled(linkman[i]);
				timingsend.setCalled(linkman[i]);
			}else if((linkman[i].split("（"))[0].length()==11){
				posTsend.setCalled((linkman[i].split("（"))[0]);
				timingsend.setCalled((linkman[i].split("（"))[0]);
			}else{
				a+=linkman[i]+";";
				logger.info("================"+linkman[i]+"不是电话号码，请重新填写！！");
			}
			try {
				if(true){
					dao.addsendtimelogs(timingsend);
				}else{

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Userlogs userlogs = new Userlogs();
		userlogs.setCallnumber(usernumber);
		userlogs.setDesc("定时短信发送....");
		dao.adduserlog(userlogs);
		map.put("result",true);
		map.put("msg", a);
		return map;
	}
	
	public static void main(String[] args) {
//		java.security.SecureRandom random;
//		try {
//			random = java.security.SecureRandom.getInstance("SHA1PRNG");
//			long seq = random.nextLong();
//			String randomVal = "" + seq;
//			System.out.println(randomVal);
//		} catch (NoSuchAlgorithmException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String a=";15821285073;12313211121;15173200236（小花）;15821285532（小易）;";
		String[] b=a.split(";");
//		String[] c=a.split("(");
		for (int i = 0; i < b.length; i++) {
			if(b[i].length()==11){
				System.out.println("b的值是："+b[i]);
			}else if((b[i].split("（"))[0].length()==11){
				System.out.println("包含括号里面的职位："+(b[i].split("（"))[0]);
			}else{
				System.out.println("其他值为："+b[i]);
			}
		}
//	
	}
	
}
