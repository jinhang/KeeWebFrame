/**
 * This class is generated by jOOQ
 */
package cn.kee.model.tables;


import com.kee.model.tables.records.RelationRecord;

import cn.kee.model.Ewp;
import cn.kee.model.Keys;

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
public class Relation extends TableImpl<RelationRecord> {

	private static final long serialVersionUID = 1016775662;

	/**
	 * The reference instance of <code>ewp.relation</code>
	 */
	public static final Relation RELATION = new Relation();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<RelationRecord> getRecordType() {
		return RelationRecord.class;
	}

	/**
	 * The column <code>ewp.relation.relationid</code>.
	 */
	public final TableField<RelationRecord, String> RELATIONID = createField("relationid", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

	/**
	 * The column <code>ewp.relation.userid</code>.
	 */
	public final TableField<RelationRecord, String> USERID = createField("userid", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.relation.staffid</code>.
	 */
	public final TableField<RelationRecord, String> STAFFID = createField("staffid", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.relation.memo</code>.
	 */
	public final TableField<RelationRecord, String> MEMO = createField("memo", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");

	/**
	 * The column <code>ewp.relation.followtype</code>.
	 */
	public final TableField<RelationRecord, String> FOLLOWTYPE = createField("followtype", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.relation.uname</code>.
	 */
	public final TableField<RelationRecord, String> UNAME = createField("uname", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.relation.telphone</code>.
	 */
	public final TableField<RelationRecord, String> TELPHONE = createField("telphone", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.relation.address</code>.
	 */
	public final TableField<RelationRecord, String> ADDRESS = createField("address", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

	/**
	 * The column <code>ewp.relation.landline</code>.
	 */
	public final TableField<RelationRecord, String> LANDLINE = createField("landline", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * Create a <code>ewp.relation</code> table reference
	 */
	public Relation() {
		this("relation", null);
	}

	/**
	 * Create an aliased <code>ewp.relation</code> table reference
	 */
	public Relation(String alias) {
		this(alias, RELATION);
	}

	private Relation(String alias, Table<RelationRecord> aliased) {
		this(alias, aliased, null);
	}

	private Relation(String alias, Table<RelationRecord> aliased, Field<?>[] parameters) {
		super(alias, Ewp.EWP, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<RelationRecord> getPrimaryKey() {
		return Keys.KEY_RELATION_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<RelationRecord>> getKeys() {
		return Arrays.<UniqueKey<RelationRecord>>asList(Keys.KEY_RELATION_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Relation as(String alias) {
		return new Relation(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Relation rename(String name) {
		return new Relation(name, null);
	}
}
