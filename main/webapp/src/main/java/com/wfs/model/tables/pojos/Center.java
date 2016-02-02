/**
 * This class is generated by jOOQ
 */
package com.wfs.model.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * This class is generated by jOOQ.
 */
@Generated(
	value = {
		"http://www.jooq.org",
		"jOOQ version:3.6.2"
	},
	comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
@Entity
@Table(name = "center", schema = "ewp")
public class Center implements Serializable {

	private static final long serialVersionUID = 179751250;

	private String  centerid;
	private Double  account;
	private Double  coins;
	private String  level;
	private String  userid;
	private Integer integral;
	private String  etime;
	private String  eattitude;
	private String  percentage;
	private String  countone;
	private String  counttwo;
	private String  countthree;
	private String  countfour;
	private String  countfive;
	private String  apercentage;
	private String  acountone;
	private String  acounttwo;
	private String  acountthree;
	private String  acountfour;
	private String  acountfive;

	public Center() {}

	public Center(Center value) {
		this.centerid = value.centerid;
		this.account = value.account;
		this.coins = value.coins;
		this.level = value.level;
		this.userid = value.userid;
		this.integral = value.integral;
		this.etime = value.etime;
		this.eattitude = value.eattitude;
		this.percentage = value.percentage;
		this.countone = value.countone;
		this.counttwo = value.counttwo;
		this.countthree = value.countthree;
		this.countfour = value.countfour;
		this.countfive = value.countfive;
		this.apercentage = value.apercentage;
		this.acountone = value.acountone;
		this.acounttwo = value.acounttwo;
		this.acountthree = value.acountthree;
		this.acountfour = value.acountfour;
		this.acountfive = value.acountfive;
	}

	public Center(
		String  centerid,
		Double  account,
		Double  coins,
		String  level,
		String  userid,
		Integer integral,
		String  etime,
		String  eattitude,
		String  percentage,
		String  countone,
		String  counttwo,
		String  countthree,
		String  countfour,
		String  countfive,
		String  apercentage,
		String  acountone,
		String  acounttwo,
		String  acountthree,
		String  acountfour,
		String  acountfive
	) {
		this.centerid = centerid;
		this.account = account;
		this.coins = coins;
		this.level = level;
		this.userid = userid;
		this.integral = integral;
		this.etime = etime;
		this.eattitude = eattitude;
		this.percentage = percentage;
		this.countone = countone;
		this.counttwo = counttwo;
		this.countthree = countthree;
		this.countfour = countfour;
		this.countfive = countfive;
		this.apercentage = apercentage;
		this.acountone = acountone;
		this.acounttwo = acounttwo;
		this.acountthree = acountthree;
		this.acountfour = acountfour;
		this.acountfive = acountfive;
	}

	@Id
	@Column(name = "centerid", unique = true, nullable = false, length = 32)
	public String getCenterid() {
		return this.centerid;
	}

	public void setCenterid(String centerid) {
		this.centerid = centerid;
	}

	@Column(name = "account", precision = 12)
	public Double getAccount() {
		return this.account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	@Column(name = "coins", precision = 12)
	public Double getCoins() {
		return this.coins;
	}

	public void setCoins(Double coins) {
		this.coins = coins;
	}

	@Column(name = "level", length = 32)
	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "userid", length = 32)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "integral", precision = 10)
	public Integer getIntegral() {
		return this.integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	@Column(name = "etime", length = 32)
	public String getEtime() {
		return this.etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

	@Column(name = "eattitude", length = 32)
	public String getEattitude() {
		return this.eattitude;
	}

	public void setEattitude(String eattitude) {
		this.eattitude = eattitude;
	}

	@Column(name = "percentage", length = 32)
	public String getPercentage() {
		return this.percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	@Column(name = "countone", length = 32)
	public String getCountone() {
		return this.countone;
	}

	public void setCountone(String countone) {
		this.countone = countone;
	}

	@Column(name = "counttwo", length = 32)
	public String getCounttwo() {
		return this.counttwo;
	}

	public void setCounttwo(String counttwo) {
		this.counttwo = counttwo;
	}

	@Column(name = "countthree", length = 32)
	public String getCountthree() {
		return this.countthree;
	}

	public void setCountthree(String countthree) {
		this.countthree = countthree;
	}

	@Column(name = "countfour", length = 32)
	public String getCountfour() {
		return this.countfour;
	}

	public void setCountfour(String countfour) {
		this.countfour = countfour;
	}

	@Column(name = "countfive", length = 32)
	public String getCountfive() {
		return this.countfive;
	}

	public void setCountfive(String countfive) {
		this.countfive = countfive;
	}

	@Column(name = "apercentage", length = 32)
	public String getApercentage() {
		return this.apercentage;
	}

	public void setApercentage(String apercentage) {
		this.apercentage = apercentage;
	}

	@Column(name = "acountone", length = 32)
	public String getAcountone() {
		return this.acountone;
	}

	public void setAcountone(String acountone) {
		this.acountone = acountone;
	}

	@Column(name = "acounttwo", length = 32)
	public String getAcounttwo() {
		return this.acounttwo;
	}

	public void setAcounttwo(String acounttwo) {
		this.acounttwo = acounttwo;
	}

	@Column(name = "acountthree", length = 32)
	public String getAcountthree() {
		return this.acountthree;
	}

	public void setAcountthree(String acountthree) {
		this.acountthree = acountthree;
	}

	@Column(name = "acountfour", length = 32)
	public String getAcountfour() {
		return this.acountfour;
	}

	public void setAcountfour(String acountfour) {
		this.acountfour = acountfour;
	}

	@Column(name = "acountfive", length = 32)
	public String getAcountfive() {
		return this.acountfive;
	}

	public void setAcountfive(String acountfive) {
		this.acountfive = acountfive;
	}
}