package com.sanss.lyh.web.business.model;

public class BlackList {
		private int id;
		private String callernumber;
		private String shieldnum;
		private String keyword;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCallernumber() {
			return callernumber;
		}
		public void setCallernumber(String callernumber) {
			this.callernumber = callernumber;
		}
		public String getShieldnum() {
			return shieldnum;
		}
		public void setShieldnum(String shieldnum) {
			this.shieldnum = shieldnum;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		
		
}
