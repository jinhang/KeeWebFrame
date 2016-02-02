/**
 * This class is generated by jOOQ
 */
package com.wfs.model.tables;


import com.wfs.model.Ewp;
import com.wfs.model.Keys;
import com.wfs.model.tables.records.UserRecord;

import java.sql.Timestamp;
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
public class User extends TableImpl<UserRecord> {

	private static final long serialVersionUID = 429962015;

	/**
	 * The reference instance of <code>ewp.user</code>
	 */
	public static final User USER = new User();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<UserRecord> getRecordType() {
		return UserRecord.class;
	}

	/**
	 * The column <code>ewp.user.userid</code>.
	 */
	public final TableField<UserRecord, String> USERID = createField("userid", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

	/**
	 * The column <code>ewp.user.username</code>.
	 */
	public final TableField<UserRecord, String> USERNAME = createField("username", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.user.password</code>.
	 */
	public final TableField<UserRecord, String> PASSWORD = createField("password", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.user.nick</code>.
	 */
	public final TableField<UserRecord, String> NICK = createField("nick", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.user.type</code>.
	 */
	public final TableField<UserRecord, Integer> TYPE = createField("type", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>ewp.user.telphone</code>.
	 */
	public final TableField<UserRecord, String> TELPHONE = createField("telphone", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.user.name</code>.
	 */
	public final TableField<UserRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.user.image</code>.
	 */
	public final TableField<UserRecord, String> IMAGE = createField("image", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");

	/**
	 * The column <code>ewp.user.securitycode</code>.
	 */
	public final TableField<UserRecord, String> SECURITYCODE = createField("securitycode", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.user.registertime</code>.
	 */
	public final TableField<UserRecord, Timestamp> REGISTERTIME = createField("registertime", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>ewp.user.isidentified</code>.
	 */
	public final TableField<UserRecord, Integer> ISIDENTIFIED = createField("isidentified", org.jooq.impl.SQLDataType.INTEGER.defaulted(true), this, "");

	/**
	 * The column <code>ewp.user.age</code>.
	 */
	public final TableField<UserRecord, Integer> AGE = createField("age", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>ewp.user.sex</code>.
	 */
	public final TableField<UserRecord, String> SEX = createField("sex", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.user.temptel</code>.
	 */
	public final TableField<UserRecord, String> TEMPTEL = createField("temptel", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.user.channelid</code>.
	 */
	public final TableField<UserRecord, String> CHANNELID = createField("channelid", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.user.stationcode</code>.
	 */
	public final TableField<UserRecord, String> STATIONCODE = createField("stationcode", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.user.staffcode</code>.
	 */
	public final TableField<UserRecord, String> STAFFCODE = createField("staffcode", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.user.dtype</code>.
	 */
	public final TableField<UserRecord, Integer> DTYPE = createField("dtype", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>ewp.user.dserial</code>.
	 */
	public final TableField<UserRecord, String> DSERIAL = createField("dserial", org.jooq.impl.SQLDataType.VARCHAR.length(128), this, "");

	/**
	 * Create a <code>ewp.user</code> table reference
	 */
	public User() {
		this("user", null);
	}

	/**
	 * Create an aliased <code>ewp.user</code> table reference
	 */
	public User(String alias) {
		this(alias, USER);
	}

	private User(String alias, Table<UserRecord> aliased) {
		this(alias, aliased, null);
	}

	private User(String alias, Table<UserRecord> aliased, Field<?>[] parameters) {
		super(alias, Ewp.EWP, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<UserRecord> getPrimaryKey() {
		return Keys.KEY_USER_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<UserRecord>> getKeys() {
		return Arrays.<UniqueKey<UserRecord>>asList(Keys.KEY_USER_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User as(String alias) {
		return new User(alias, this);
	}

	/**
	 * Rename this table
	 */
	public User rename(String name) {
		return new User(name, null);
	}
}