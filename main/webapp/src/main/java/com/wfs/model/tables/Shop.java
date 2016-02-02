/**
 * This class is generated by jOOQ
 */
package com.wfs.model.tables;


import com.wfs.model.Ewp;
import com.wfs.model.Keys;
import com.wfs.model.tables.records.ShopRecord;

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
public class Shop extends TableImpl<ShopRecord> {

	private static final long serialVersionUID = 1479870681;

	/**
	 * The reference instance of <code>ewp.shop</code>
	 */
	public static final Shop SHOP = new Shop();

	/**
	 * The class holding records for this type
	 */
	@Override
	public Class<ShopRecord> getRecordType() {
		return ShopRecord.class;
	}

	/**
	 * The column <code>ewp.shop.shopid</code>.
	 */
	public final TableField<ShopRecord, String> SHOPID = createField("shopid", org.jooq.impl.SQLDataType.VARCHAR.length(32).nullable(false), this, "");

	/**
	 * The column <code>ewp.shop.trafficnum</code>.
	 */
	public final TableField<ShopRecord, Integer> TRAFFICNUM = createField("trafficnum", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>ewp.shop.follownum</code>.
	 */
	public final TableField<ShopRecord, Integer> FOLLOWNUM = createField("follownum", org.jooq.impl.SQLDataType.INTEGER, this, "");

	/**
	 * The column <code>ewp.shop.deliveryscope</code>.
	 */
	public final TableField<ShopRecord, String> DELIVERYSCOPE = createField("deliveryscope", org.jooq.impl.SQLDataType.VARCHAR.length(1024), this, "");

	/**
	 * The column <code>ewp.shop.userid</code>.
	 */
	public final TableField<ShopRecord, String> USERID = createField("userid", org.jooq.impl.SQLDataType.VARCHAR.length(32), this, "");

	/**
	 * The column <code>ewp.shop.quote</code>.
	 */
	public final TableField<ShopRecord, String> QUOTE = createField("quote", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");

	/**
	 * The column <code>ewp.shop.features</code>.
	 */
	public final TableField<ShopRecord, String> FEATURES = createField("features", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");

	/**
	 * The column <code>ewp.shop.points1</code>.
	 */
	public final TableField<ShopRecord, String> POINTS1 = createField("points1", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.shop.points2</code>.
	 */
	public final TableField<ShopRecord, String> POINTS2 = createField("points2", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.shop.points3</code>.
	 */
	public final TableField<ShopRecord, String> POINTS3 = createField("points3", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.shop.points4</code>.
	 */
	public final TableField<ShopRecord, String> POINTS4 = createField("points4", org.jooq.impl.SQLDataType.VARCHAR.length(64), this, "");

	/**
	 * The column <code>ewp.shop.codeimage</code>.
	 */
	public final TableField<ShopRecord, String> CODEIMAGE = createField("codeimage", org.jooq.impl.SQLDataType.VARCHAR.length(256), this, "");

	/**
	 * Create a <code>ewp.shop</code> table reference
	 */
	public Shop() {
		this("shop", null);
	}

	/**
	 * Create an aliased <code>ewp.shop</code> table reference
	 */
	public Shop(String alias) {
		this(alias, SHOP);
	}

	private Shop(String alias, Table<ShopRecord> aliased) {
		this(alias, aliased, null);
	}

	private Shop(String alias, Table<ShopRecord> aliased, Field<?>[] parameters) {
		super(alias, Ewp.EWP, aliased, parameters, "");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public UniqueKey<ShopRecord> getPrimaryKey() {
		return Keys.KEY_SHOP_PRIMARY;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UniqueKey<ShopRecord>> getKeys() {
		return Arrays.<UniqueKey<ShopRecord>>asList(Keys.KEY_SHOP_PRIMARY);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Shop as(String alias) {
		return new Shop(alias, this);
	}

	/**
	 * Rename this table
	 */
	public Shop rename(String name) {
		return new Shop(name, null);
	}
}
