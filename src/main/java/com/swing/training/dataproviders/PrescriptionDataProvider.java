package com.swing.training.dataproviders;

import com.swing.training.service.PrescriptionService;
import com.swing.training.service.impl.PrescriptionServiceImpl;

/**
 * class acts as a data provider for Prescription module
 * 
 * @author SDhananjaya
 * 
 */
public class PrescriptionDataProvider {

	private static PrescriptionService prescriptionService = null;

	private PrescriptionDataProvider() {
	}

	/**
	 * returns singleton instance for prescription service
	 * 
	 * @return PrescriptionService
	 */
	public static PrescriptionService getInstance() {

		if (prescriptionService == null) {

			prescriptionService = new PrescriptionServiceImpl();
		}
		return prescriptionService;
	}
}
