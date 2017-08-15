package com.swing.training.service;

import java.util.List;

import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.dtos.criteria.PrescriptionCriteriaSearchDto;

/**
 * Acts as an interface for prescription services
 * 
 * @author SDhananjaya
 * 
 */
public interface PrescriptionService {

	/**
	 * adds a new prescription entry to the database
	 * 
	 * @param prescriptionDto
	 */
	void addNewPrescription(PrescriptionDto prescriptionDto);

	/**
	 * edit the prescription
	 * 
	 * @param prescriptionDto
	 */
	void editPrescription(PrescriptionDto prescriptionDto);

	/**
	 * returns all the prescription list
	 * 
	 * @return
	 */
	List<PrescriptionDto> getAllPrescriptions();

	/**
	 * returns the prescription details for given prescription id
	 * 
	 * @param prescriptionId
	 * @return
	 */
	PrescriptionDto getPrescriptionFromId(int prescriptionId);

	/**
	 * returns prescription list according to the criteria
	 * 
	 * @param criteria
	 * @return
	 */
	List<PrescriptionDto> getPrescriptionsFromCriteria(
			PrescriptionCriteriaSearchDto criteria);
}
