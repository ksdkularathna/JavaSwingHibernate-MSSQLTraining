package com.swing.training.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "prescription")
public class PrescriptionDto implements Serializable {

	@Id
	@GeneratedValue
	private int prescriptionId;
	private Date date;
	private String doctorName;
	private String patientName;

	@Transient
	private List<DrugDosageDto> drugDosageDtos;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private PatientDto patient;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private EmployeDto doctor;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "prescription")
	private List<PrescribedDrugDto> prescribedDrugs;

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}

	public EmployeDto getDoctor() {
		return doctor;
	}

	public void setDoctor(EmployeDto doctor) {
		this.doctor = doctor;
	}

	public List<PrescribedDrugDto> getPrescribedDrugs() {
		return prescribedDrugs;
	}

	public void setPrescribedDrugs(List<PrescribedDrugDto> prescribedDrugs) {
		this.prescribedDrugs = prescribedDrugs;
	}

	public List<DrugDosageDto> getDrugDosageDtos() {
		return drugDosageDtos;
	}

	public void setDrugDosageDtos(List<DrugDosageDto> drugDosageDtos) {
		this.drugDosageDtos = drugDosageDtos;
	}
}