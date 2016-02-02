/**
 * This class is generated by jOOQ
 */
package com.wfs.model.tables.records;


import com.wfs.model.tables.Rolepermission;

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
@Table(name = "rolepermission", schema = "ewp")
public class RolepermissionRecord extends UpdatableRecordImpl<RolepermissionRecord> implements Record4<String, String, String, Integer> {

	private static final long serialVersionUID = 1337881675;

	/**
	 * Setter for <code>ewp.rolepermission.rolepermissionid</code>.
	 */
	public void setRolepermissionid(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ewp.rolepermission.rolepermissionid</code>.
	 */
	@Id
	@Column(name = "rolepermissionid", unique = true, nullable = false, length = 32)
	public String getRolepermissionid() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>ewp.rolepermission.roleid</code>.
	 */
	public void setRoleid(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ewp.rolepermission.roleid</code>.
	 */
	@Column(name = "roleid", length = 32)
	public String getRoleid() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>ewp.rolepermission.permissionid</code>.
	 */
	public void setPermissionid(String value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>ewp.rolepermission.permissionid</code>.
	 */
	@Column(name = "permissionid", length = 32)
	public String getPermissionid() {
		return (String) getValue(2);
	}

	/**
	 * Setter for <code>ewp.rolepermission.type</code>.
	 */
	public void setType(Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>ewp.rolepermission.type</code>.
	 */
	@Column(name = "type", precision = 10)
	public Integer getType() {
		return (Integer) getValue(3);
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
	public Row4<String, String, String, Integer> fieldsRow() {
		return (Row4) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row4<String, String, String, Integer> valuesRow() {
		return (Row4) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return Rolepermission.ROLEPERMISSION.ROLEPERMISSIONID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Rolepermission.ROLEPERMISSION.ROLEID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field3() {
		return Rolepermission.ROLEPERMISSION.PERMISSIONID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field4() {
		return Rolepermission.ROLEPERMISSION.TYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getRolepermissionid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getRoleid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value3() {
		return getPermissionid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value4() {
		return getType();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolepermissionRecord value1(String value) {
		setRolepermissionid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolepermissionRecord value2(String value) {
		setRoleid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolepermissionRecord value3(String value) {
		setPermissionid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolepermissionRecord value4(Integer value) {
		setType(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RolepermissionRecord values(String value1, String value2, String value3, Integer value4) {
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
	 * Create a detached RolepermissionRecord
	 */
	public RolepermissionRecord() {
		super(Rolepermission.ROLEPERMISSION);
	}

	/**
	 * Create a detached, initialised RolepermissionRecord
	 */
	public RolepermissionRecord(String rolepermissionid, String roleid, String permissionid, Integer type) {
		super(Rolepermission.ROLEPERMISSION);

		setValue(0, rolepermissionid);
		setValue(1, roleid);
		setValue(2, permissionid);
		setValue(3, type);
	}
}
