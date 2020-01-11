package com.uca.assignment.dtos;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author Udara Amarasinghe
 *
 */
public class CustomerDto {

	@NotNull
	@NotEmpty
	private String ic;

	private String cxName;

	private Integer cxAge;

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
