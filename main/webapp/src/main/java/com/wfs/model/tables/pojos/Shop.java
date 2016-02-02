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
@Table(name = "shop", schema = "ewp")
public class Shop implements Serializable {

	private static final long serialVersionUID = -1145126600;

	private String  shopid;
	private Integer trafficnum;
	private Integer follownum;
	private String  deliveryscope;
	private String  userid;
	private String  quote;
	private String  features;
	private String  points1;
	private String  points2;
	private String  points3;
	private String  points4;
	private String  codeimage;

	public Shop() {}

	public Shop(Shop value) {
		this.shopid = value.shopid;
		this.trafficnum = value.trafficnum;
		this.follownum = value.follownum;
		this.deliveryscope = value.deliveryscope;
		this.userid = value.userid;
		this.quote = value.quote;
		this.features = value.features;
		this.points1 = value.points1;
		this.points2 = value.points2;
		this.points3 = value.points3;
		this.points4 = value.points4;
		this.codeimage = value.codeimage;
	}

	public Shop(
		String  shopid,
		Integer trafficnum,
		Integer follownum,
		String  deliveryscope,
		String  userid,
		String  quote,
		String  features,
		String  points1,
		String  points2,
		String  points3,
		String  points4,
		String  codeimage
	) {
		this.shopid = shopid;
		this.trafficnum = trafficnum;
		this.follownum = follownum;
		this.deliveryscope = deliveryscope;
		this.userid = userid;
		this.quote = quote;
		this.features = features;
		this.points1 = points1;
		this.points2 = points2;
		this.points3 = points3;
		this.points4 = points4;
		this.codeimage = codeimage;
	}

	@Id
	@Column(name = "shopid", unique = true, nullable = false, length = 32)
	public String getShopid() {
		return this.shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	@Column(name = "trafficnum", precision = 10)
	public Integer getTrafficnum() {
		return this.trafficnum;
	}

	public void setTrafficnum(Integer trafficnum) {
		this.trafficnum = trafficnum;
	}

	@Column(name = "follownum", precision = 10)
	public Integer getFollownum() {
		return this.follownum;
	}

	public void setFollownum(Integer follownum) {
		this.follownum = follownum;
	}

	@Column(name = "deliveryscope", length = 1024)
	public String getDeliveryscope() {
		return this.deliveryscope;
	}

	public void setDeliveryscope(String deliveryscope) {
		this.deliveryscope = deliveryscope;
	}

	@Column(name = "userid", length = 32)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "quote", length = 256)
	public String getQuote() {
		return this.quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Column(name = "features", length = 256)
	public String getFeatures() {
		return this.features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	@Column(name = "points1", length = 64)
	public String getPoints1() {
		return this.points1;
	}

	public void setPoints1(String points1) {
		this.points1 = points1;
	}

	@Column(name = "points2", length = 64)
	public String getPoints2() {
		return this.points2;
	}

	public void setPoints2(String points2) {
		this.points2 = points2;
	}

	@Column(name = "points3", length = 64)
	public String getPoints3() {
		return this.points3;
	}

	public void setPoints3(String points3) {
		this.points3 = points3;
	}

	@Column(name = "points4", length = 64)
	public String getPoints4() {
		return this.points4;
	}

	public void setPoints4(String points4) {
		this.points4 = points4;
	}

	@Column(name = "codeimage", length = 256)
	public String getCodeimage() {
		return this.codeimage;
	}

	public void setCodeimage(String codeimage) {
		this.codeimage = codeimage;
	}
}
