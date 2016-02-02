/**
 * This class is generated by jOOQ
 */
package com.wfs.model.tables.records;


import com.wfs.model.tables.Businesslibrary;

import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
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
@Table(name = "businesslibrary", schema = "ewp")
public class BusinesslibraryRecord extends UpdatableRecordImpl<BusinesslibraryRecord> implements Record8<String, String, String, Timestamp, String, String, String, String> {

	private static final long serialVersionUID = 454796946;

	/**
	 * Setter for <code>ewp.businesslibrary.businesslibraryid</code>.
	 */
	public void setBusinesslibraryid(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ewp.businesslibrary.businesslibraryid</code>.
	 */
	@Id
	@Column(name = "businesslibraryid", unique = true, nullable = false, length = 32)
	public String getBusinesslibraryid() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>ewp.businesslibrary.orderid</code>.
	 */
	public void setOrderid(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ewp.businesslibrary.orderid</code>.
	 */
	@Column(name = "orderid", length = 32)
	public String getOrderid() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>ewp.businesslibrary.action</code>.
	 */
	public void setAction(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>ewp.businesslibrary.action</code>.
	 */
	@Column(name = "action", length = 512)
	public String getAction() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>ewp.businesslibrary.createtime</code>.
	 */
	public void setCreatetime(Timestamp value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>ewp.businesslibrary.createtime</code>.
	 */
	@Column(name = "createtime")
	public Timestamp getCreatetime() {
		return (Timestamp) getValue(3);
	}

	/**
	 * Setter for <code>ewp.businesslibrary.userid</code>.
	 */
	public void setUserid(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>ewp.businesslibrary.userid</code>.
	 */
	@Column(name = "userid", length = 32)
	public String getUserid() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>ewp.businesslibrary.staffid</code>.
	 */
	public void setStaffid(String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>ewp.businesslibrary.staffid</code>.
	 */
	@Column(name = "staffid", length = 32)
	public String getStaffid() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>ewp.businesslibrary.memo</code>.
	 */
	public void setMemo(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>ewp.businesslibrary.memo</code>.
	 */
	@Column(name = "memo", length = 256)
	public String getMemo() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>ewp.businesslibrary.code</code>.
	 */
	public void setCode(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>ewp.businesslibrary.code</code>.
	 */
	@Column(name = "code", length = 32)
	public String getCode() {
		return (String) getValue(7);
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
	// Record8 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row8<String, String, String, Timestamp, String, String, String, String> fieldsRow() {
		return (Row8) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row8<String, String, String, Timestamp, String, String, String, String> valuesRow() {
		return (Row8) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return Businesslibrary.BUSINESSLIBRARY.BUSINESSLIBRARYID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Businesslibrary.BUSINESSLIBRARY.ORDERID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Businesslibrary.BUSINESSLIBRARY.ACTION;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Timestamp> field4() {
		return Businesslibrary.BUSINESSLIBRARY.CREATETIME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return Businesslibrary.BUSINESSLIBRARY.USERID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field6() {
		return Businesslibrary.BUSINESSLIBRARY.STAFFID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return Businesslibrary.BUSINESSLIBRARY.MEMO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field8() {
		return Businesslibrary.BUSINESSLIBRARY.CODE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getBusinesslibraryid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getOrderid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getAction();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Timestamp value4() {
		return getCreatetime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value5() {
		return getUserid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value6() {
		return getStaffid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getMemo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value8() {
		return getCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinesslibraryRecord value1(String value) {
		setBusinesslibraryid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinesslibraryRecord value2(String value) {
		setOrderid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinesslibraryRecord value3(String value) {
		setAction(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinesslibraryRecord value4(Timestamp value) {
		setCreatetime(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinesslibraryRecord value5(String value) {
		setUserid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinesslibraryRecord value6(String value) {
		setStaffid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinesslibraryRecord value7(String value) {
		setMemo(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinesslibraryRecord value8(String value) {
		setCode(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BusinesslibraryRecord values(String value1, String value2, String value3, Timestamp value4, String value5, String value6, String value7, String value8) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached BusinesslibraryRecord
	 */
	public BusinesslibraryRecord() {
		super(Businesslibrary.BUSINESSLIBRARY);
	}

	/**
	 * Create a detached, initialised BusinesslibraryRecord
	 */
	public BusinesslibraryRecord(String businesslibraryid, String orderid, String action, Timestamp createtime, String userid, String staffid, String memo, String code) {
		super(Businesslibrary.BUSINESSLIBRARY);

		setValue(0, businesslibraryid);
		setValue(1, orderid);
		setValue(2, action);
		setValue(3, createtime);
		setValue(4, userid);
		setValue(5, staffid);
		setValue(6, memo);
		setValue(7, code);
	}
}
