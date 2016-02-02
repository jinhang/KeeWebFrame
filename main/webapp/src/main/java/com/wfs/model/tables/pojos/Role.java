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
@Table(name = "role", schema = "ewp")
public class Role implements Serializable {

	private static final long serialVersionUID = -822420941;

	private String  roleid;
	private String  name;
	private Integer type;
	private String  parentid;
	private String  clientid;
	private Integer status;

	public Role() {}

	public Role(Role value) {
		this.roleid = value.roleid;
		this.name = value.name;
		this.type = value.type;
		this.parentid = value.parentid;
		this.clientid = value.clientid;
		this.status = value.status;
	}

	public Role(
		String  roleid,
		String  name,
		Integer type,
		String  parentid,
		String  clientid,
		Integer status
	) {
		this.roleid = roleid;
		this.name = name;
		this.type = type;
		this.parentid = parentid;
		this.clientid = clientid;
		this.status = status;
	}

	@Id
	@Column(name = "roleid", unique = true, nullable = false, length = 32)
	public String getRoleid() {
		return this.roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	@Column(name = "name", length = 32)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "type", precision = 10)
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "parentid", length = 32)
	public String getParentid() {
		return this.parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	@Column(name = "clientid", length = 32)
	public String getClientid() {
		return this.clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	@Column(name = "status", precision = 10)
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}