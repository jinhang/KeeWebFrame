/**
 * This class is generated by jOOQ
 */
package com.kee.model.tables.records;


import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import cn.kee.model.tables.Staffpermission;


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
public class StaffpermissionRecord extends UpdatableRecordImpl<StaffpermissionRecord> implements Record4<String, String, String, String> {

	private static final long serialVersionUID = -1931680788;

	/**
	 * Setter for <code>ewp.staffpermission.staffpermissionid</code>.
	 */
	public void setStaffpermissionid(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ewp.staffpermission.staffpermissionid</code>.
	 */
	@Id
	@Column(name = "staffpermissionid", unique = true, nullable = false, length = 32)
	public String getStaffpermissionid() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>ewp.staffpermission.staffid</code>.
	 */
	public void setStaffid(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ewp.staffpermission.staffid</code>.
	 */
	@Column(name = "staffid", length = 32)
	public String getStaffid() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>ewp.staffpermission.roleid</code>.
	 */
	public void setRoleid(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>ewp.staffpermission.roleid</code>.
	 */
	@Column(name = "roleid", length = 32)
	public String getRoleid() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>ewp.staffpermission.permissionid</code>.
	 */
	public void setPermissionid(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>ewp.staffpermission.permissionid</code>.
	 */
	@Column(name = "permissionid", length = 32)
	public String getPermissionid() {
		return (String) getValue(3);
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
	// Record4 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<String, String, String, String> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<String, String, String, String> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return Staffpermission.STAFFPERMISSION.STAFFPERMISSIONID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Staffpermission.STAFFPERMISSION.STAFFID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Staffpermission.STAFFPERMISSION.ROLEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return Staffpermission.STAFFPERMISSION.PERMISSIONID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getStaffpermissionid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getStaffid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getRoleid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getPermissionid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StaffpermissionRecord value1(String value) {
		setStaffpermissionid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StaffpermissionRecord value2(String value) {
		setStaffid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StaffpermissionRecord value3(String value) {
		setRoleid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StaffpermissionRecord value4(String value) {
		setPermissionid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StaffpermissionRecord values(String value1, String value2, String value3, String value4) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached StaffpermissionRecord
	 */
	public StaffpermissionRecord() {
		super(Staffpermission.STAFFPERMISSION);
	}

	/**
	 * Create a detached, initialised StaffpermissionRecord
	 */
	public StaffpermissionRecord(String staffpermissionid, String staffid, String roleid, String permissionid) {
		super(Staffpermission.STAFFPERMISSION);

		setValue(0, staffpermissionid);
		setValue(1, staffid);
		setValue(2, roleid);
		setValue(3, permissionid);
	}
}
