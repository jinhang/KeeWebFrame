/**
 * This class is generated by jOOQ
 */
package com.wfs.model.tables;


import com.wfs.model.Ewp;
import com.wfs.model.Keys;
import com.wfs.model.tables.records.ExpresstemplateRecord;

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
public class Expresstemplate extends TableImpl<ExpresstemplateRecord> {

	private static final long serialVersionUID = 213748516;

	/**
	 * The reference instance of <code>ewp.expresstemplate</code>
	 */
	public static final Expresstemplate EXPRESSTEMPLATE = new Expresstemplate();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<ExpresstemplateRecord> getRecordType() {
		return ExpresstemplateRecord.class;
	}

	/**
	 * The column <code>ewp.expresstemplate.id</code>.
	 */
	public final TableField<ExpresstemplateRecord, String> ID = createField("id", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

	/**
	 * The column <code>ewp.expresstemplate.name</code>.
	 */
	public final TableField<ExpresstemplateRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.expresstemplate.initastr</code>.
	 */
	public final TableField<ExpresstemplateRecord, String> INITASTR = createField("initastr", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");

	/**
	 * The column <code>ewp.expresstemplate.clientid</code>.
	 */
	public final TableField<ExpresstemplateRecord, String> CLIENTID = createField("clientid", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.expresstemplate.forwardcompanyid</code>.
	 */
	public final TableField<ExpresstemplateRecord, String> FORWARDCOMPANYID = createField("forwardcompanyid", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.expresstemplate.isdefault</code>.
	 */
	public final TableField<ExpresstemplateRecord, Integer> ISDEFAULT = createField("isdefault", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>ewp.expresstemplate.pagewidth</code>.
	 */
	public final TableField<ExpresstemplateRecord, String> PAGEWIDTH = createField("pagewidth", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.expresstemplate.pageheight</code>.
	 */
	public final TableField<ExpresstemplateRecord, String> PAGEHEIGHT = createField("pageheight", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.expresstemplate.templatevalue</code>.
	 */
	public final TableField<ExpresstemplateRecord, String> TEMPLATEVALUE = createField("templatevalue", org.jooq.impl.SQLDataType.VARCHAR.length(5000), this, "");

	/**
	 * The column <code>ewp.expresstemplate.needsendcode</code>.
	 */
	public final TableField<ExpresstemplateRecord, Integer> NEEDSENDCODE = createField("needsendcode", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>ewp.expresstemplate.isopen</code>.
	 */
	public final TableField<ExpresstemplateRecord, Integer> ISOPEN = createField("isopen", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>ewp.expresstemplate.templateImgUrl</code>.
	 */
	public final TableField<ExpresstemplateRecord, String> TEMPLATEIMGURL = createField("templateImgUrl", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * The column <code>ewp.expresstemplate.printer</code>.
	 */
	public final TableField<ExpresstemplateRecord, String> PRINTER = createField("printer", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

	/**
	 * Create a <code>ewp.expresstemplate</code> table reference
	 */
	public Expresstemplate() {
		this("expresstemplate", null);
	}

	/**
	 * Create an aliased <code>ewp.expresstemplate</code> table reference
	 */
	public Expresstemplate(String alias) {
		this(alias, EXPRESSTEMPLATE);
	}

	private Expresstemplate(String alias, Table<ExpresstemplateRecord> aliased) {
		this(alias, aliased, null);
	}

	private Expresstemplate(String alias, Table<ExpresstemplateRecord> aliased, Field<?>[] parameters) {
		super(alias, Ewp.EWP, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<ExpresstemplateRecord> getPrimaryKey() {
		return Keys.KEY_EXPRESSTEMPLATE_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<ExpresstemplateRecord>> getKeys() {
		return Arrays.<UniqueKey<ExpresstemplateRecord>>asList(Keys.KEY_EXPRESSTEMPLATE_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Expresstemplate as(String alias) {
		return new Expresstemplate(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Expresstemplate rename(String name) {
		return new Expresstemplate(name, null);
	}
}
