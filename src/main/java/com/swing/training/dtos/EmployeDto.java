package com.swing.training.dtos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employe")
public class EmployeDto {

	@Id
	@GeneratedValue
	private int employeId;
	
	private String name;
	private String address;
	

	public EmployeDto() {
	}

	public EmployeDto(int id) {

		this.employeId = id;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "doctor")
	private List<PrescriptionDto> prescriptionDtos;

	public int getEmployeId() {
		return employeId;
	}

	public void setEmployeId(int employeId) {
		this.employeId = employeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
