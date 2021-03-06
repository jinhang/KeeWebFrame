/**
 * This class is generated by jOOQ
 */
package com.kee.model.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


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
@Table(name = "logisticsdata", schema = "ewp")
public class Logisticsdata implements Serializable {

	private static final long serialVersionUID = -1978766639;

	private String    logisticsdataid;
	private String    num;
	private String    sendcode;
	private String    scantype;
	private String    scanname;
	private String    stationcode;
	private String    scantime;
	private String    staffcode;
	private String    lnstation;
	private String    weight;
	private String    pacakgenum;
	private String    batchnum;
	private String    pronum;
	private String    memo;
	private String    image;
	private Timestamp createtime;
	private Integer   state;
	private String    dsnum;

	public Logisticsdata() {}

	public Logisticsdata(Logisticsdata value) {
		this.logisticsdataid = value.logisticsdataid;
		this.num = value.num;
		this.sendcode = value.sendcode;
		this.scantype = value.scantype;
		this.scanname = value.scanname;
		this.stationcode = value.stationcode;
		this.scantime = value.scantime;
		this.staffcode = value.staffcode;
		this.lnstation = value.lnstation;
		this.weight = value.weight;
		this.pacakgenum = value.pacakgenum;
		this.batchnum = value.batchnum;
		this.pronum = value.pronum;
		this.memo = value.memo;
		this.image = value.image;
		this.createtime = value.createtime;
		this.state = value.state;
		this.dsnum = value.dsnum;
	}

	public Logisticsdata(
		String    logisticsdataid,
		String    num,
		String    sendcode,
		String    scantype,
		String    scanname,
		String    stationcode,
		String    scantime,
		String    staffcode,
		String    lnstation,
		String    weight,
		String    pacakgenum,
		String    batchnum,
		String    pronum,
		String    memo,
		String    image,
		Timestamp createtime,
		Integer   state,
		String    dsnum
	) {
		this.logisticsdataid = logisticsdataid;
		this.num = num;
		this.sendcode = sendcode;
		this.scantype = scantype;
		this.scanname = scanname;
		this.stationcode = stationcode;
		this.scantime = scantime;
		this.staffcode = staffcode;
		this.lnstation = lnstation;
		this.weight = weight;
		this.pacakgenum = pacakgenum;
		this.batchnum = batchnum;
		this.pronum = pronum;
		this.memo = memo;
		this.image = image;
		this.createtime = createtime;
		this.state = state;
		this.dsnum = dsnum;
	}

	@Id
	@Column(name = "logisticsdataid", unique = true, nullable = false, length = 32)
	public String getLogisticsdataid() {
		return this.logisticsdataid;
	}

	public void setLogisticsdataid(String logisticsdataid) {
		this.logisticsdataid = logisticsdataid;
	}

	@Column(name = "num", length = 32)
	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Column(name = "sendcode", length = 32)
	public String getSendcode() {
		return this.sendcode;
	}

	public void setSendcode(String sendcode) {
		this.sendcode = sendcode;
	}

	@Column(name = "scantype", length = 32)
	public String getScantype() {
		return this.scantype;
	}

	public void setScantype(String scantype) {
		this.scantype = scantype;
	}

	@Column(name = "scanname", length = 32)
	public String getScanname() {
		return this.scanname;
	}

	public void setScanname(String scanname) {
		this.scanname = scanname;
	}

	@Column(name = "stationcode", length = 32)
	public String getStationcode() {
		return this.stationcode;
	}

	public void setStationcode(String stationcode) {
		this.stationcode = stationcode;
	}

	@Column(name = "scantime", length = 64)
	public String getScantime() {
		return this.scantime;
	}

	public void setScantime(String scantime) {
		this.scantime = scantime;
	}

	@Column(name = "staffcode", length = 32)
	public String getStaffcode() {
		return this.staffcode;
	}

	public void setStaffcode(String staffcode) {
		this.staffcode = staffcode;
	}

	@Column(name = "lnstation", length = 32)
	public String getLnstation() {
		return this.lnstation;
	}

	public void setLnstation(String lnstation) {
		this.lnstation = lnstation;
	}

	@Column(name = "weight", length = 32)
	public String getWeight() {
		return this.weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	@Column(name = "pacakgenum", length = 32)
	public String getPacakgenum() {
		return this.pacakgenum;
	}

	public void setPacakgenum(String pacakgenum) {
		this.pacakgenum = pacakgenum;
	}

	@Column(name = "batchnum", length = 64)
	public String getBatchnum() {
		return this.batchnum;
	}

	public void setBatchnum(String batchnum) {
		this.batchnum = batchnum;
	}

	@Column(name = "pronum", length = 32)
	public String getPronum() {
		return this.pronum;
	}

	public void setPronum(String pronum) {
		this.pronum = pronum;
	}

	@Column(name = "memo", length = 32)
	public String getMemo() {
		return this.memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Column(name = "image", length = 32)
	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Column(name = "createtime")
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "state", precision = 10)
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Column(name = "dsnum", length = 32)
	public String getDsnum() {
		return this.dsnum;
	}

	public void setDsnum(String dsnum) {
		this.dsnum = dsnum;
	}
}
