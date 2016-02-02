/**
 * This class is generated by jOOQ
 */
package com.wfs.model.tables.records;


import com.wfs.model.tables.Expresstemplate;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record13;
import org.jooq.Row13;
import org.jooq.impl.UpdatableRecordImpl;


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
@Table(name = "expresstemplate", schema = "ewp")
public class ExpresstemplateRecord extends UpdatableRecordImpl<ExpresstemplateRecord> implements Record13<String, String, String, String, String, Integer, String, String, String, Integer, Integer, String, String> {

	private static final long serialVersionUID = 386256263;

	/**
	 * Setter for <code>ewp.expresstemplate.id</code>.
	 */
	public void setId(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.id</code>.
	 */
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 32)
	public String getId() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.name</code>.
	 */
	public void setName(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.name</code>.
	 */
	@Column(name = "name", length = 32)
	public String getName() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.initastr</code>.
	 */
	public void setInitastr(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.initastr</code>.
	 */
	@Column(name = "initastr", length = 256)
	public String getInitastr() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.clientid</code>.
	 */
	public void setClientid(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.clientid</code>.
	 */
	@Column(name = "clientid", length = 32)
	public String getClientid() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.forwardcompanyid</code>.
	 */
	public void setForwardcompanyid(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.forwardcompanyid</code>.
	 */
	@Column(name = "forwardcompanyid", length = 32)
	public String getForwardcompanyid() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.isdefault</code>.
	 */
	public void setIsdefault(Integer value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.isdefault</code>.
	 */
	@Column(name = "isdefault", precision = 10)
	public Integer getIsdefault() {
		return (Integer) getValue(5);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.pagewidth</code>.
	 */
	public void setPagewidth(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.pagewidth</code>.
	 */
	@Column(name = "pagewidth", length = 32)
	public String getPagewidth() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.pageheight</code>.
	 */
	public void setPageheight(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.pageheight</code>.
	 */
	@Column(name = "pageheight", length = 32)
	public String getPageheight() {
		return (String) getValue(7);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.templatevalue</code>.
	 */
	public void setTemplatevalue(String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.templatevalue</code>.
	 */
	@Column(name = "templatevalue", length = 5000)
	public String getTemplatevalue() {
		return (String) getValue(8);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.needsendcode</code>.
	 */
	public void setNeedsendcode(Integer value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.needsendcode</code>.
	 */
	@Column(name = "needsendcode", precision = 10)
	public Integer getNeedsendcode() {
		return (Integer) getValue(9);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.isopen</code>.
	 */
	public void setIsopen(Integer value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.isopen</code>.
	 */
	@Column(name = "isopen", precision = 10)
	public Integer getIsopen() {
		return (Integer) getValue(10);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.templateImgUrl</code>.
	 */
	public void setTemplateimgurl(String value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.templateImgUrl</code>.
	 */
	@Column(name = "templateImgUrl", length = 255)
	public String getTemplateimgurl() {
		return (String) getValue(11);
	}

	/**
	 * Setter for <code>ewp.expresstemplate.printer</code>.
	 */
	public void setPrinter(String value) {
		setValue(12, value);
	}

	/**
	 * Getter for <code>ewp.expresstemplate.printer</code>.
	 */
	@Column(name = "printer", length = 255)
	public String getPrinter() {
		return (String) getValue(12);
	}

	// -------------------------------------------------------------------------
	// Primary key information
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Record1<String> key() {
		return (Record1) super.key();
	}

	// -------------------------------------------------------------------------
	// Record13 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row13<String, String, String, String, String, Integer, String, String, String, Integer, Integer, String, String> fieldsRow() {
		return (Row13) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row13<String, String, String, String, String, Integer, String, String, String, Integer, Integer, String, String> valuesRow() {
		return (Row13) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return Expresstemplate.EXPRESSTEMPLATE.ID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Expresstemplate.EXPRESSTEMPLATE.NAME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Expresstemplate.EXPRESSTEMPLATE.INITASTR;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return Expresstemplate.EXPRESSTEMPLATE.CLIENTID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return Expresstemplate.EXPRESSTEMPLATE.FORWARDCOMPANYID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field6() {
		return Expresstemplate.EXPRESSTEMPLATE.ISDEFAULT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return Expresstemplate.EXPRESSTEMPLATE.PAGEWIDTH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field8() {
		return Expresstemplate.EXPRESSTEMPLATE.PAGEHEIGHT;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field9() {
		return Expresstemplate.EXPRESSTEMPLATE.TEMPLATEVALUE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field10() {
		return Expresstemplate.EXPRESSTEMPLATE.NEEDSENDCODE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field11() {
		return Expresstemplate.EXPRESSTEMPLATE.ISOPEN;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field12() {
		return Expresstemplate.EXPRESSTEMPLATE.TEMPLATEIMGURL;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field13() {
		return Expresstemplate.EXPRESSTEMPLATE.PRINTER;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getName();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getInitastr();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getClientid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getForwardcompanyid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value6() {
		return getIsdefault();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getPagewidth();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value8() {
		return getPageheight();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value9() {
		return getTemplatevalue();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value10() {
		return getNeedsendcode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value11() {
		return getIsopen();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value12() {
		return getTemplateimgurl();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value13() {
		return getPrinter();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value1(String value) {
		setId(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value2(String value) {
		setName(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value3(String value) {
		setInitastr(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value4(String value) {
		setClientid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value5(String value) {
		setForwardcompanyid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value6(Integer value) {
		setIsdefault(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value7(String value) {
		setPagewidth(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value8(String value) {
		setPageheight(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value9(String value) {
		setTemplatevalue(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value10(Integer value) {
		setNeedsendcode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value11(Integer value) {
		setIsopen(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value12(String value) {
		setTemplateimgurl(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord value13(String value) {
		setPrinter(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ExpresstemplateRecord values(String value1, String value2, String value3, String value4, String value5, Integer value6, String value7, String value8, String value9, Integer value10, Integer value11, String value12, String value13) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		value10(value10);
		value11(value11);
		value12(value12);
		value13(value13);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ExpresstemplateRecord
	 */
	public ExpresstemplateRecord() {
		super(Expresstemplate.EXPRESSTEMPLATE);
	}

	/**
	 * Create a detached, initialised ExpresstemplateRecord
	 */
	public ExpresstemplateRecord(String id, String name, String initastr, String clientid, String forwardcompanyid, Integer isdefault, String pagewidth, String pageheight, String templatevalue, Integer needsendcode, Integer isopen, String templateimgurl, String printer) {
		super(Expresstemplate.EXPRESSTEMPLATE);

		setValue(0, id);
		setValue(1, name);
		setValue(2, initastr);
		setValue(3, clientid);
		setValue(4, forwardcompanyid);
		setValue(5, isdefault);
		setValue(6, pagewidth);
		setValue(7, pageheight);
		setValue(8, templatevalue);
		setValue(9, needsendcode);
		setValue(10, isopen);
		setValue(11, templateimgurl);
		setValue(12, printer);
	}
}
