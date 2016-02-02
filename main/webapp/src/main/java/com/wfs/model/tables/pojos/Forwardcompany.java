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
@Table(name = "forwardcompany", schema = "ewp")
public class Forwardcompany implements Serializable {

	private static final long serialVersionUID = -1277625597;

	private String  id;
	private String  name;
	private Integer type;
	private String  memo;
	private String  code;
	private Integer sortno;
	private String  templateimgurl;
	private String  pagewidth;
	private String  pageheight;
	private String  templatevalue;
	private String  shortname;

	public Forwardcompany() {}

	public Forwardcompany(Forwardcompany value) {
		this.id = value.id;
		this.name = value.name;
		this.type = value.type;
		this.memo = value.memo;
		this.code = value.code;
		this.sortno = value.sortno;
		this.templateimgurl = value.templateimgurl;
		this.pagewidth = value.pagewidth;
		this.pageheight = value.pageheight;
		this.templatevalue = value.templatevalue;
		this.shortname = value.shortname;
	}

	public Forwardcompany(
		String  id,
		String  name,
		Integer type,
		String  memo,
		String  code,
		Integer sortno,
		String  templateimgurl,
		String  pagewidth,
		String  pageheight,
		String  templatevalue,
		String  shortname
	) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.memo = memo;
		this.code = code;
		this.sortno = sortno;
		this.templateimgurl = templateimgurl;
		this.pagewidth = pagewidth;
		this.pageheight = pageheight;
		this.templatevalue = templatevalue;
		this.shortname = shortname;
	}

	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Column(name = "memo", length = 32)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "code", length = 32)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "sortno", precision = 10)
	public Integer getSortno() {
		return this.sortno;
	}

	public void setSortno(Integer sortno) {
		this.sortno = sortno;
	}

	@Column(name = "templateimgurl", length = 255)
	public String getTemplateimgurl() {
		return this.templateimgurl;
	}

	public void setTemplateimgurl(String templateimgurl) {
		this.templateimgurl = templateimgurl;
	}

	@Column(name = "pagewidth", length = 32)
	public String getPagewidth() {
		return this.pagewidth;
	}

	public void setPagewidth(String pagewidth) {
		this.pagewidth = pagewidth;
	}

	@Column(name = "pageheight", length = 32)
	public String getPageheight() {
		return this.pageheight;
	}

	public void setPageheight(String pageheight) {
		this.pageheight = pageheight;
	}

	@Column(name = "templatevalue", length = 5000)
	public String getTemplatevalue() {
		return this.templatevalue;
	}

	public void setTemplatevalue(String templatevalue) {
		this.templatevalue = templatevalue;
	}

	@Column(name = "shortname", length = 32)
	public String getShortname() {
		return this.shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
}