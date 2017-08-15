package com.swing.training.dtos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "drug")
public class DrugDto {

	@Id
	@GeneratedValue
	private int drugId;
	private String name;
	private String type;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "drug")
	private List<PrescribedDrugDto> prescribedDrugs;

	public DrugDto() {
	}

	public DrugDto(int drugId, String drugName, String drugType) {

		this.drugId = drugId;
		this.name = drugName;
		this.type = drugType;
	}

	public int getDrugId() {
		return drugId;
	}

	public void setDrugId(int drugId) {
		this.drugId = drugId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<PrescribedDrugDto> getPrescribedDrugs() {
		return prescribedDrugs;
	}

	public void setPrescribedDrugs(List<PrescribedDrugDto> prescribedDrugs) {
		this.prescribedDrugs = prescribedDrugs;
	}
}
