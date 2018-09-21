package com.sanss.lyh.web.business.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Userlogs {
		private int id;
		private String callnumber;
		private String time;
		private String desc;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCallnumber() {
			return callnumber;
		}
		public void setCallnumber(String callnumber) {
			this.callnumber = callnumber;
		}
		public String getTime() {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(new Date());
		}
		public void setTime(String time) {
			this.time = time;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		
		
}
