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
@Table(name = "staffpermission", schema = "ewp")
public class Staffpermission implements Serializable {

	private static final long serialVersionUID = -21504250;

	private String staffpermissionid;
	private String staffid;
	private String roleid;
	private String permissionid;

	public Staffpermission() {}

	public Staffpermission(Staffpermission value) {
		this.staffpermissionid = value.staffpermissionid;
		this.staffid = value.staffid;
		this.roleid = value.roleid;
		this.permissionid = value.permissionid;
	}

	public Staffpermission(
		String staffpermissionid,
		String staffid,
		String roleid,
		String permissionid
	) {
		this.staffpermissionid = staffpermissionid;
		this.staffid = staffid;
		this.roleid = roleid;
		this.permissionid = permissionid;
	}

	@Id
	@Column(name = "staffpermissionid", unique = true, nullable = false, length = 32)
	public String getStaffpermissionid() {
		return this.staffpermissionid;
	}

	public void setStaffpermissionid(String staffpermissionid) {
		this.staffpermissionid = staffpermissionid;
	}

	@Column(name = "staffid", length = 32)
	public String getStaffid() {
		return this.staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	@Column(name = "roleid", length = 32)
	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	@Column(name = "permissionid", length = 32)
	public String getPermissionid() {
		return this.permissionid;
	}

	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}
}