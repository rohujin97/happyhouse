package com.ssafy.happyhouse.model;

public class WishListDto {

   private String userid;
   private String aptdong;
   private String address;
   private String dongname;
   
   public String getUserid() {
	   return userid;
   }
   public void setUserid(String userid) {
	   this.userid = userid;
   }
   public String getAptdong() {
	   return aptdong;
   }
   public void setAptdong(String aptdong) {
	   this.aptdong = aptdong;
   }
   public String getAddress() {
	   return address;
   }
   public void setAddress(String address) {
	   this.address = address;
   }
   public String getDongname() {
		return dongname;
   }
   public void setDongname(String dongname) {
		this.dongname = dongname;
   }
   
}
