package com.swing.training.dtos.criteria;

import java.util.Date;

public class PrescriptionCriteriaSearchDto {

	private int patientId;
	private Date fromDate;
	private Date toDate;

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Date getFromData() {
		return fromDate;
	}

	public void setFromData(Date fromData) {
		this.fromDate = fromData;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
