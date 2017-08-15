package com.swing.training.dataproviders;

import com.swing.training.service.EmployeService;
import com.swing.training.service.impl.EmployeServiceImpl;

/**
 * class act as a data provider for employee module
 * 
 * @author SDhananjaya
 * 
 */
public class EmployeeDataProvider {

	private static EmployeService employeService = null;

	private EmployeeDataProvider() {
	}

	/**
	 * returns singleton drug service instance
	 * 
	 * @return EmployeService
	 */
	public static EmployeService getInstance() {

		if (employeService == null) {

			employeService = new EmployeServiceImpl();
		}
		return employeService;
	}
}
