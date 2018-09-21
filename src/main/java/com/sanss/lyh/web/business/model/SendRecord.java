package com.sanss.lyh.web.business.model;

public class SendRecord {
		private int id;
		private String callernumber;
		private String callednumber;
		private String text;
		private String sendtime;
		private int status;
		
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
		public String getCallednumber() {
			return callednumber;
		}
		public void setCallednumber(String callednumber) {
			this.callednumber = callednumber;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getSendtime() {
			return sendtime;
		}
		public void setSendtime(String sendtime) {
			this.sendtime = sendtime;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		
		
}
