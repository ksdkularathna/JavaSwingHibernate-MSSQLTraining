package com.swing.training.dataproviders;

import com.swing.training.service.PatientService;
import com.swing.training.service.impl.PatientServiceImpl;

/**
 * class act as a data provider for patient module
 * 
 * @author SDhananjaya
 * 
 */
public class PatientDataProvider {

	private static PatientService patientService = null;

	private PatientDataProvider() {
	}

	/**
	 * returns singleton instance for drug service
	 * 
	 * @return PatientService
	 */
	public static PatientService getInstance() {

		if (patientService == null) {

			patientService = new PatientServiceImpl();
		}
		return patientService;
	}
}
