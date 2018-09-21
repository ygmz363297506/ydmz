package com.sanss.lyh.web.business.model;

public class Backup {
		private int id;
		private String caller;
		private String called;
		private String text;
		private String time;
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCaller() {
			return caller;
		}
		public void setCaller(String caller) {
			this.caller = caller;
		}
		public String getCalled() {
			return called;
		}
		public void setCalled(String called) {
			this.called = called;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getTime() {
			return time;
		}
		public void setTime(String time) {
			this.time = time;
		}
		
		
}
