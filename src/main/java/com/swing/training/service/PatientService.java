/**
 * 
 */
package com.swing.training.service;

import java.util.HashMap;
import java.util.List;

import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.TrnxResponse;
import com.swing.training.dtos.criteria.PatientSearchCriteriaDto;

/**
 * Acts as an interface for patient services
 * 
 * @author SDhananjaya
 * 
 */
public interface PatientService {

	/**
	 * get patient details according to the id
	 * 
	 * @param patientId
	 * @return
	 */
	PatientDto getPatient(int patientId);

	/**
	 * Get all the patient list
	 * 
	 * @return List<PatientDto>
	 */
	List<PatientDto> getAllPatients();

	/**
	 * returns the patient list according to the search criteria
	 * 
	 * @return
	 */
	List<PatientDto> getPatientListFromSearchCriteria(
			PatientSearchCriteriaDto criteriaDto);

	/**
	 * get patient combo box drop down list
	 * 
	 * @return
	 */
	List<String> getPatientComboDropDownList();
}
