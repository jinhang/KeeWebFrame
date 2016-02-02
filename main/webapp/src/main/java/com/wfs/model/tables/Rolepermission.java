/**
 * This class is generated by jOOQ
 */
package com.wfs.model.tables;


import com.wfs.model.Ewp;
import com.wfs.model.Keys;
import com.wfs.model.tables.records.RolepermissionRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class Rolepermission extends TableImpl<RolepermissionRecord> {

	private static final long serialVersionUID = 1383769154;

	/**
	 * The reference instance of <code>ewp.rolepermission</code>
	 */
	public static final Rolepermission ROLEPERMISSION = new Rolepermission();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<RolepermissionRecord> getRecordType() {
		return RolepermissionRecord.class;
	}

	/**
	 * The column <code>ewp.rolepermission.rolepermissionid</code>.
	 */
	public final TableField<RolepermissionRecord, String> ROLEPERMISSIONID = createField("rolepermissionid", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

	/**
	 * The column <code>ewp.rolepermission.roleid</code>.
	 */
	public final TableField<RolepermissionRecord, String> ROLEID = createField("roleid", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.rolepermission.permissionid</code>.
	 */
	public final TableField<RolepermissionRecord, String> PERMISSIONID = createField("permissionid", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.rolepermission.type</code>.
	 */
	public final TableField<RolepermissionRecord, Integer> TYPE = createField("type", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>ewp.rolepermission</code> table reference
	 */
	public Rolepermission() {
		this("rolepermission", null);
	}

	/**
	 * Create an aliased <code>ewp.rolepermission</code> table reference
	 */
	public Rolepermission(String alias) {
		this(alias, ROLEPERMISSION);
	}

	private Rolepermission(String alias, Table<RolepermissionRecord> aliased) {
		this(alias, aliased, null);
	}

	private Rolepermission(String alias, Table<RolepermissionRecord> aliased, Field<?>[] parameters) {
		super(alias, Ewp.EWP, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<RolepermissionRecord> getPrimaryKey() {
		return Keys.KEY_ROLEPERMISSION_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<RolepermissionRecord>> getKeys() {
		return Arrays.<UniqueKey<RolepermissionRecord>>asList(Keys.KEY_ROLEPERMISSION_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Rolepermission as(String alias) {
		return new Rolepermission(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Rolepermission rename(String name) {
		return new Rolepermission(name, null);
	}
}
