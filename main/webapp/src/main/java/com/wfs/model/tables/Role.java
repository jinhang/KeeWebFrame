/**
 * This class is generated by jOOQ
 */
package com.wfs.model.tables;


import com.wfs.model.Ewp;
import com.wfs.model.Keys;
import com.wfs.model.tables.records.RoleRecord;

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
public class Role extends TableImpl<RoleRecord> {

	private static final long serialVersionUID = 1942009424;

	/**
	 * The reference instance of <code>ewp.role</code>
	 */
	public static final Role ROLE = new Role();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<RoleRecord> getRecordType() {
		return RoleRecord.class;
	}

	/**
	 * The column <code>ewp.role.roleid</code>.
	 */
	public final TableField<RoleRecord, String> ROLEID = createField("roleid", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

	/**
	 * The column <code>ewp.role.name</code>.
	 */
	public final TableField<RoleRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.role.type</code>.
	 */
	public final TableField<RoleRecord, Integer> TYPE = createField("type", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>ewp.role.parentid</code>.
	 */
	public final TableField<RoleRecord, String> PARENTID = createField("parentid", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.role.clientid</code>.
	 */
	public final TableField<RoleRecord, String> CLIENTID = createField("clientid", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.role.status</code>.
	 */
	public final TableField<RoleRecord, Integer> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * Create a <code>ewp.role</code> table reference
	 */
	public Role() {
		this("role", null);
	}

	/**
	 * Create an aliased <code>ewp.role</code> table reference
	 */
	public Role(String alias) {
		this(alias, ROLE);
	}

	private Role(String alias, Table<RoleRecord> aliased) {
		this(alias, aliased, null);
	}

	private Role(String alias, Table<RoleRecord> aliased, Field<?>[] parameters) {
		super(alias, Ewp.EWP, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<RoleRecord> getPrimaryKey() {
		return Keys.KEY_ROLE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<RoleRecord>> getKeys() {
		return Arrays.<UniqueKey<RoleRecord>>asList(Keys.KEY_ROLE_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Role as(String alias) {
		return new Role(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Role rename(String name) {
		return new Role(name, null);
	}
}