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
@Table(name = "relation", schema = "ewp")
public class Relation implements Serializable {

	private static final long serialVersionUID = 168811442;

	private String relationid;
	private String userid;
	private String staffid;
	private String memo;
	private String followtype;
	private String uname;
	private String telphone;
	private String address;
	private String landline;

	public Relation() {}

	public Relation(Relation value) {
		this.relationid = value.relationid;
		this.userid = value.userid;
		this.staffid = value.staffid;
		this.memo = value.memo;
		this.followtype = value.followtype;
		this.uname = value.uname;
		this.telphone = value.telphone;
		this.address = value.address;
		this.landline = value.landline;
	}

	public Relation(
		String relationid,
		String userid,
		String staffid,
		String memo,
		String followtype,
		String uname,
		String telphone,
		String address,
		String landline
	) {
		this.relationid = relationid;
		this.userid = userid;
		this.staffid = staffid;
		this.memo = memo;
		this.followtype = followtype;
		this.uname = uname;
		this.telphone = telphone;
		this.address = address;
		this.landline = landline;
	}

	@Id
	@Column(name = "relationid", unique = true, nullable = false, length = 32)
	public String getRelationid() {
		return this.relationid;
	}

	public void setRelationid(String relationid) {
		this.relationid = relationid;
	}

	@Column(name = "userid", length = 32)
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Column(name = "staffid", length = 32)
	public String getStaffid() {
		return this.staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	@Column(name = "memo", length = 256)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "followtype", length = 64)
	public String getFollowtype() {
		return this.followtype;
	}

	public void setFollowtype(String followtype) {
		this.followtype = followtype;
	}

	@Column(name = "uname", length = 32)
	public String getUname() {
		return this.uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	@Column(name = "telphone", length = 32)
	public String getTelphone() {
		return this.telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	@Column(name = "address", length = 1024)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "landline", length = 64)
	public String getLandline() {
		return this.landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}
}
