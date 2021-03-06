/**
 * This class is generated by jOOQ
 */
package cn.kee.model.tables;


import com.kee.model.tables.records.LogisticsdataRecord;

import cn.kee.model.Ewp;
import cn.kee.model.Keys;

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
public class Logisticsdata extends TableImpl<LogisticsdataRecord> {

	private static final long serialVersionUID = 465474878;

	/**
	 * The reference instance of <code>ewp.logisticsdata</code>
	 */
	public static final Logisticsdata LOGISTICSDATA = new Logisticsdata();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<LogisticsdataRecord> getRecordType() {
		return LogisticsdataRecord.class;
	}

	/**
	 * The column <code>ewp.logisticsdata.logisticsdataid</code>.
	 */
	public final TableField<LogisticsdataRecord, String> LOGISTICSDATAID = createField("logisticsdataid", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

	/**
	 * The column <code>ewp.logisticsdata.num</code>.
	 */
	public final TableField<LogisticsdataRecord, String> NUM = createField("num", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.sendcode</code>.
	 */
	public final TableField<LogisticsdataRecord, String> SENDCODE = createField("sendcode", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.scantype</code>.
	 */
	public final TableField<LogisticsdataRecord, String> SCANTYPE = createField("scantype", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.scanname</code>.
	 */
	public final TableField<LogisticsdataRecord, String> SCANNAME = createField("scanname", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.stationcode</code>.
	 */
	public final TableField<LogisticsdataRecord, String> STATIONCODE = createField("stationcode", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.scantime</code>.
	 */
	public final TableField<LogisticsdataRecord, String> SCANTIME = createField("scantime", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.logisticsdata.staffcode</code>.
	 */
	public final TableField<LogisticsdataRecord, String> STAFFCODE = createField("staffcode", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.lnstation</code>.
	 */
	public final TableField<LogisticsdataRecord, String> LNSTATION = createField("lnstation", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.weight</code>.
	 */
	public final TableField<LogisticsdataRecord, String> WEIGHT = createField("weight", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.pacakgenum</code>.
	 */
	public final TableField<LogisticsdataRecord, String> PACAKGENUM = createField("pacakgenum", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.batchnum</code>.
	 */
	public final TableField<LogisticsdataRecord, String> BATCHNUM = createField("batchnum", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.logisticsdata.pronum</code>.
	 */
	public final TableField<LogisticsdataRecord, String> PRONUM = createField("pronum", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.memo</code>.
	 */
	public final TableField<LogisticsdataRecord, String> MEMO = createField("memo", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.image</code>.
	 */
	public final TableField<LogisticsdataRecord, String> IMAGE = createField("image", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.logisticsdata.createtime</code>.
	 */
	public final TableField<LogisticsdataRecord, Timestamp> CREATETIME = createField("createtime", org.jooq.impl.SQLDataType.TIMESTAMP, this, "");

	/**
	 * The column <code>ewp.logisticsdata.state</code>.
	 */
	public final TableField<LogisticsdataRecord, Integer> STATE = createField("state", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>ewp.logisticsdata.dsnum</code>.
	 */
	public final TableField<LogisticsdataRecord, String> DSNUM = createField("dsnum", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * Create a <code>ewp.logisticsdata</code> table reference
	 */
	public Logisticsdata() {
		this("logisticsdata", null);
	}

	/**
	 * Create an aliased <code>ewp.logisticsdata</code> table reference
	 */
	public Logisticsdata(String alias) {
		this(alias, LOGISTICSDATA);
	}

	private Logisticsdata(String alias, Table<LogisticsdataRecord> aliased) {
		this(alias, aliased, null);
	}

	private Logisticsdata(String alias, Table<LogisticsdataRecord> aliased, Field<?>[] parameters) {
		super(alias, Ewp.EWP, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<LogisticsdataRecord> getPrimaryKey() {
		return Keys.KEY_LOGISTICSDATA_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<LogisticsdataRecord>> getKeys() {
		return Arrays.<UniqueKey<LogisticsdataRecord>>asList(Keys.KEY_LOGISTICSDATA_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Logisticsdata as(String alias) {
		return new Logisticsdata(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Logisticsdata rename(String name) {
		return new Logisticsdata(name, null);
	}
}
