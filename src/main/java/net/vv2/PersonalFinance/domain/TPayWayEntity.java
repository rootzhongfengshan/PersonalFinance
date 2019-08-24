package net.vv2.PersonalFinance.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author zhongfs
 * @email zhongfs@asiainfo.com
 * @date 2019-08-24 20:24:22
 */

public class TPayWayEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Long id;
	/**
	 * 
	 */
	private String payWay;
	/**
	 * 
	 */
	private String info;
	/**
	 * 
	 */
	private String bankid;
	/**
	 * 
	 */
	private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
