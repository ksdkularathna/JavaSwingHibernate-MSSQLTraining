package com.swing.training.dtos;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "prescribedDrug")
public class PrescribedDrugDto {

	@Id
	@GeneratedValue
	private int prescribedDrugId;
	private String dosage;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private PrescriptionDto prescription;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private DrugDto drug;

	public int getPrescribedDrugId() {
		return prescribedDrugId;
	}

	public void setPrescribedDrugId(int prescribedDrugId) {
		this.prescribedDrugId = prescribedDrugId;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public PrescriptionDto getPrescription() {
		return prescription;
	}

	public void setPrescription(PrescriptionDto prescription) {
		this.prescription = prescription;
	}

	public DrugDto getDrug() {
		return drug;
	}

	public void setDrug(DrugDto drug) {
		this.drug = drug;
	}
}
