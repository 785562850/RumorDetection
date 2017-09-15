package com.yyjc.domain.po;

public class AccessToken {
		private String token;
		private String uid;
		private long expireIn;
		
		public AccessToken() {
		
		}
		public AccessToken(String token, String uid, long expireIn) {
		
			this.token = token;
			this.uid = uid;
			this.expireIn = expireIn;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getUid() {
			return uid;
		}
		public void setUid(String uid) {
			this.uid = uid;
		}
		public long getExpireIn() {
			return expireIn;
		}
		public void setExpireIn(long expireIn) {
			this.expireIn = expireIn;
		}
		@Override
		public String toString() {
			return "AccessToken [token=" + token + ", uid=" + uid + ", expireIn=" + expireIn + "]";
		}
		
}
