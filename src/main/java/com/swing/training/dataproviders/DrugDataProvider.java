package com.swing.training.dataproviders;

import com.swing.training.service.DrugService;
import com.swing.training.service.impl.DrugServiceImpl;

/**
 * Act as a data provider for rug module
 * 
 * @author SDhananjaya
 * 
 */
public class DrugDataProvider {

	private static DrugService drugService = null;

	private DrugDataProvider() {
	}

	/**
	 * returns singleton instance for drug service
	 * 
	 * @return DrugService
	 */
	public static DrugService getInstance() {

		if (drugService == null) {

			drugService = new DrugServiceImpl();
		}
		return drugService;
	}
}
