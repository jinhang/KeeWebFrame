/**
 * This class is generated by jOOQ
 */
package com.wfs.model.tables.records;


import com.wfs.model.tables.Evaluation;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row9;
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
@Table(name = "evaluation", schema = "ewp")
public class EvaluationRecord extends UpdatableRecordImpl<EvaluationRecord> implements Record9<String, String, Integer, Integer, String, String, String, Integer, String> {

	private static final long serialVersionUID = 1698479888;

	/**
	 * Setter for <code>ewp.evaluation.evaluationid</code>.
	 */
	public void setEvaluationid(String value) {
		setValue(0, value);
	}

	/**
	 * Getter for <code>ewp.evaluation.evaluationid</code>.
	 */
	@Id
	@Column(name = "evaluationid", unique = true, nullable = false, length = 32)
	public String getEvaluationid() {
		return (String) getValue(0);
	}

	/**
	 * Setter for <code>ewp.evaluation.orderid</code>.
	 */
	public void setOrderid(String value) {
		setValue(1, value);
	}

	/**
	 * Getter for <code>ewp.evaluation.orderid</code>.
	 */
	@Column(name = "orderid", length = 32)
	public String getOrderid() {
		return (String) getValue(1);
	}

	/**
	 * Setter for <code>ewp.evaluation.etime</code>.
	 */
	public void setEtime(Integer value) {
		setValue(2, value);
	}

	/**
	 * Getter for <code>ewp.evaluation.etime</code>.
	 */
	@Column(name = "etime", precision = 10)
	public Integer getEtime() {
		return (Integer) getValue(2);
	}

	/**
	 * Setter for <code>ewp.evaluation.eattitude</code>.
	 */
	public void setEattitude(Integer value) {
		setValue(3, value);
	}

	/**
	 * Getter for <code>ewp.evaluation.eattitude</code>.
	 */
	@Column(name = "eattitude", precision = 10)
	public Integer getEattitude() {
		return (Integer) getValue(3);
	}

	/**
	 * Setter for <code>ewp.evaluation.userid</code>.
	 */
	public void setUserid(String value) {
		setValue(4, value);
	}

	/**
	 * Getter for <code>ewp.evaluation.userid</code>.
	 */
	@Column(name = "userid", length = 32)
	public String getUserid() {
		return (String) getValue(4);
	}

	/**
	 * Setter for <code>ewp.evaluation.staffid</code>.
	 */
	public void setStaffid(String value) {
		setValue(5, value);
	}

	/**
	 * Getter for <code>ewp.evaluation.staffid</code>.
	 */
	@Column(name = "staffid", length = 32)
	public String getStaffid() {
		return (String) getValue(5);
	}

	/**
	 * Setter for <code>ewp.evaluation.memo</code>.
	 */
	public void setMemo(String value) {
		setValue(6, value);
	}

	/**
	 * Getter for <code>ewp.evaluation.memo</code>.
	 */
	@Column(name = "memo", length = 256)
	public String getMemo() {
		return (String) getValue(6);
	}

	/**
	 * Setter for <code>ewp.evaluation.isshow</code>.
	 */
	public void setIsshow(Integer value) {
		setValue(7, value);
	}

	/**
	 * Getter for <code>ewp.evaluation.isshow</code>.
	 */
	@Column(name = "isshow", precision = 10)
	public Integer getIsshow() {
		return (Integer) getValue(7);
	}

	/**
	 * Setter for <code>ewp.evaluation.evatype</code>.
	 */
	public void setEvatype(String value) {
		setValue(8, value);
	}

	/**
	 * Getter for <code>ewp.evaluation.evatype</code>.
	 */
	@Column(name = "evatype", length = 32)
	public String getEvatype() {
		return (String) getValue(8);
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
	// Record9 type implementation
	// -------------------------------------------------------------------------

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row9<String, String, Integer, Integer, String, String, String, Integer, String> fieldsRow() {
		return (Row9) super.fieldsRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Row9<String, String, Integer, Integer, String, String, String, Integer, String> valuesRow() {
		return (Row9) super.valuesRow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field1() {
		return Evaluation.EVALUATION.EVALUATIONID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field2() {
		return Evaluation.EVALUATION.ORDERID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field3() {
		return Evaluation.EVALUATION.ETIME;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field4() {
		return Evaluation.EVALUATION.EATTITUDE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field5() {
		return Evaluation.EVALUATION.USERID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field6() {
		return Evaluation.EVALUATION.STAFFID;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field7() {
		return Evaluation.EVALUATION.MEMO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<Integer> field8() {
		return Evaluation.EVALUATION.ISSHOW;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Field<String> field9() {
		return Evaluation.EVALUATION.EVATYPE;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value1() {
		return getEvaluationid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value2() {
		return getOrderid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value3() {
		return getEtime();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value4() {
		return getEattitude();
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
		return getStaffid();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value7() {
		return getMemo();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer value8() {
		return getIsshow();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String value9() {
		return getEvatype();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluationRecord value1(String value) {
		setEvaluationid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluationRecord value2(String value) {
		setOrderid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluationRecord value3(Integer value) {
		setEtime(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluationRecord value4(Integer value) {
		setEattitude(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluationRecord value5(String value) {
		setUserid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluationRecord value6(String value) {
		setStaffid(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluationRecord value7(String value) {
		setMemo(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluationRecord value8(Integer value) {
		setIsshow(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluationRecord value9(String value) {
		setEvatype(value);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public EvaluationRecord values(String value1, String value2, Integer value3, Integer value4, String value5, String value6, String value7, Integer value8, String value9) {
		value1(value1);
		value2(value2);
		value3(value3);
		value4(value4);
		value5(value5);
		value6(value6);
		value7(value7);
		value8(value8);
		value9(value9);
		return this;
	}

	// -------------------------------------------------------------------------
	// Constructors
	// -------------------------------------------------------------------------

	/**
	 * Create a detached EvaluationRecord
	 */
	public EvaluationRecord() {
		super(Evaluation.EVALUATION);
	}

	/**
	 * Create a detached, initialised EvaluationRecord
	 */
	public EvaluationRecord(String evaluationid, String orderid, Integer etime, Integer eattitude, String userid, String staffid, String memo, Integer isshow, String evatype) {
		super(Evaluation.EVALUATION);

		setValue(0, evaluationid);
		setValue(1, orderid);
		setValue(2, etime);
		setValue(3, eattitude);
		setValue(4, userid);
		setValue(5, staffid);
		setValue(6, memo);
		setValue(7, isshow);
		setValue(8, evatype);
	}
}
