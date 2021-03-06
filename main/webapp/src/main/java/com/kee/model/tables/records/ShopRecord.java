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
import org.jooq.Record12;
import org.jooq.Row12;
import org.jooq.impl.UpdatableRecordImpl;

import cn.kee.model.tables.Shop;


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
@Table(name = "shop", schema = "ewp")
public class ShopRecord extends UpdatableRecordImpl<ShopRecord> implements Record12<String, Integer, Integer, String, String, String, String, String, String, String, String, String> {

	private static final long serialVersionUID = 1976948563;

	/**
	 * Setter for <code>ewp.shop.shopid</code>.
	 */
	public void setShopid(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ewp.shop.shopid</code>.
	 */
	@Id
	@Column(name = "shopid", unique = true, nullable = false, length = 32)
	public String getShopid() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>ewp.shop.trafficnum</code>.
	 */
	public void setTrafficnum(Integer value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ewp.shop.trafficnum</code>.
	 */
	@Column(name = "trafficnum", precision = 10)
	public Integer getTrafficnum() {
		return (Integer) getValue(1);
	}

	/**
	 * Setter for <code>ewp.shop.follownum</code>.
	 */
	public void setFollownum(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>ewp.shop.follownum</code>.
	 */
	@Column(name = "follownum", precision = 10)
	public Integer getFollownum() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>ewp.shop.deliveryscope</code>.
	 */
	public void setDeliveryscope(String value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>ewp.shop.deliveryscope</code>.
	 */
	@Column(name = "deliveryscope", length = 1024)
	public String getDeliveryscope() {
		return (String) getValue(3);
	}

	/**
	 * Setter for <code>ewp.shop.userid</code>.
	 */
	public void setUserid(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>ewp.shop.userid</code>.
	 */
	@Column(name = "userid", length = 32)
	public String getUserid() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>ewp.shop.quote</code>.
	 */
	public void setQuote(String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>ewp.shop.quote</code>.
	 */
	@Column(name = "quote", length = 256)
	public String getQuote() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>ewp.shop.features</code>.
	 */
	public void setFeatures(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>ewp.shop.features</code>.
	 */
	@Column(name = "features", length = 256)
	public String getFeatures() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>ewp.shop.points1</code>.
	 */
	public void setPoints1(String value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>ewp.shop.points1</code>.
	 */
	@Column(name = "points1", length = 64)
	public String getPoints1() {
		return (String) getValue(7);
	}

	/**
	 * Setter for <code>ewp.shop.points2</code>.
	 */
	public void setPoints2(String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>ewp.shop.points2</code>.
	 */
	@Column(name = "points2", length = 64)
	public String getPoints2() {
		return (String) getValue(8);
	}

	/**
	 * Setter for <code>ewp.shop.points3</code>.
	 */
	public void setPoints3(String value) {
		setValue(9, value);
	}

	/**
	 * Getter for <code>ewp.shop.points3</code>.
	 */
	@Column(name = "points3", length = 64)
	public String getPoints3() {
		return (String) getValue(9);
	}

	/**
	 * Setter for <code>ewp.shop.points4</code>.
	 */
	public void setPoints4(String value) {
		setValue(10, value);
	}

	/**
	 * Getter for <code>ewp.shop.points4</code>.
	 */
	@Column(name = "points4", length = 64)
	public String getPoints4() {
		return (String) getValue(10);
	}

	/**
	 * Setter for <code>ewp.shop.codeimage</code>.
	 */
	public void setCodeimage(String value) {
		setValue(11, value);
	}

	/**
	 * Getter for <code>ewp.shop.codeimage</code>.
	 */
	@Column(name = "codeimage", length = 256)
	public String getCodeimage() {
		return (String) getValue(11);
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
	// Record12 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row12<String, Integer, Integer, String, String, String, String, String, String, String, String, String> fieldsRow() {
		return (Row12) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row12<String, Integer, Integer, String, String, String, String, String, String, String, String, String> valuesRow() {
		return (Row12) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return Shop.SHOP.SHOPID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field2() {
		return Shop.SHOP.TRAFFICNUM;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return Shop.SHOP.FOLLOWNUM;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field4() {
		return Shop.SHOP.DELIVERYSCOPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return Shop.SHOP.USERID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field6() {
		return Shop.SHOP.QUOTE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return Shop.SHOP.FEATURES;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field8() {
		return Shop.SHOP.POINTS1;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field9() {
		return Shop.SHOP.POINTS2;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field10() {
		return Shop.SHOP.POINTS3;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field11() {
		return Shop.SHOP.POINTS4;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field12() {
		return Shop.SHOP.CODEIMAGE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getShopid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value2() {
		return getTrafficnum();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getFollownum();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value4() {
		return getDeliveryscope();
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
		return getQuote();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getFeatures();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value8() {
		return getPoints1();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value9() {
		return getPoints2();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value10() {
		return getPoints3();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value11() {
		return getPoints4();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value12() {
		return getCodeimage();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value1(String value) {
		setShopid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value2(Integer value) {
		setTrafficnum(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value3(Integer value) {
		setFollownum(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value4(String value) {
		setDeliveryscope(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value5(String value) {
		setUserid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value6(String value) {
		setQuote(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value7(String value) {
		setFeatures(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value8(String value) {
		setPoints1(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value9(String value) {
		setPoints2(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value10(String value) {
		setPoints3(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value11(String value) {
		setPoints4(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord value12(String value) {
		setCodeimage(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ShopRecord values(String value1, Integer value2, Integer value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, String value11, String value12) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		value10(value10);
		value11(value11);
		value12(value12);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached ShopRecord
	 */
	public ShopRecord() {
		super(Shop.SHOP);
	}

	/**
	 * Create a detached, initialised ShopRecord
	 */
	public ShopRecord(String shopid, Integer trafficnum, Integer follownum, String deliveryscope, String userid, String quote, String features, String points1, String points2, String points3, String points4, String codeimage) {
		super(Shop.SHOP);

		setValue(0, shopid);
		setValue(1, trafficnum);
		setValue(2, follownum);
		setValue(3, deliveryscope);
		setValue(4, userid);
		setValue(5, quote);
		setValue(6, features);
		setValue(7, points1);
		setValue(8, points2);
		setValue(9, points3);
		setValue(10, points4);
		setValue(11, codeimage);
	}
}
