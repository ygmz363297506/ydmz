package com.sanss.lyh.web.business.model;

public class RecLog {
		private int id;
		//被叫
		private String du;
		//主叫
		private String ou;
		private String text;
		private String createTime;
		
		public final int getId() {
			return id;
		}
		public final void setId(int id) {
			this.id = id;
		}
		public final String getDu() {
			return du;
		}
		public final void setDu(String du) {
			this.du = du;
		}
		public final String getOu() {
			return ou;
		}
		public final void setOu(String ou) {
			this.ou = ou;
		}
		public final String getText() {
			return text;
		}
		public final void setText(String text) {
			this.text = text;
		}
		public final String getCreateTime() {
			return createTime;
		}
		public final void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		
}
