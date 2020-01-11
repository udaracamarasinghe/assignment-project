package com.uca.assignment.dbone.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
@Entity
@Table(name = "customer")
public class DBOneCustomer {

	@Id
	private String ic;

	private String cxName;

	private Integer cxAge;

	@Temporal(TemporalType.DATE)
	private Date cxDOB;

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public String getCxName() {
		return cxName;
	}

	public void setCxName(String cxName) {
		this.cxName = cxName;
	}

	public Integer getCxAge() {
		return cxAge;
	}

	public void setCxAge(Integer cxAge) {
		this.cxAge = cxAge;
	}

	public Date getCxDOB() {
		return cxDOB;
	}

	public void setCxDOB(Date cxDOB) {
		this.cxDOB = cxDOB;
	}

}
