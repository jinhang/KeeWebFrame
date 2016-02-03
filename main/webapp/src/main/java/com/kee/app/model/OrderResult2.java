package com.kee.app.model;

public class OrderResult2 {
	
	private Double totalprice;
	
	private Double serverfee;
	
	private Double discountfee;
	
	private Double expresscharge;
	
	private String staffid;

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public Double getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(Double totalprice) {
		this.totalprice = totalprice;
	}

	public Double getServerfee() {
		return serverfee;
	}

	public void setServerfee(Double serverfee) {
		this.serverfee = serverfee;
	}

	public Double getDiscountfee() {
		return discountfee;
	}

	public void setDiscountfee(Double discountfee) {
		this.discountfee = discountfee;
	}

	public Double getExpresscharge() {
		return expresscharge;
	}

	public void setExpresscharge(Double expresscharge) {
		this.expresscharge = expresscharge;
	}

}
