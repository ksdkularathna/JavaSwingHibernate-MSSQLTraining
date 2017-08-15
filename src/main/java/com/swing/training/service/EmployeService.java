// *************************************************************************************************
//
// PROJECT : se.cambio.trainingsession3
// 
// ************************************************************************************************
//
// Copyright(C) 2016 Propozer(PRIVATE) LIMITED
// All rights reserved.
//
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
// Propozer(PRIVATE) LIMITED.
//
// This copy of the Source Code is intended for Propozer(PRIVATE) LIMITED 's internal use only
// and is
// intended for view by persons duly authorized by the management of Propozer(PRIVATE)
// LIMITED. No
// part of this file may be reproduced or distributed in any form or by any
// means without the written approval of the Management of Propozer(PRIVATE) LIMITED.
//
// *************************************************************************************************
//
// REVISIONS:
// Author : Sampath
// Date : Jul 23, 2017
// Since : version 1.0
//
package com.swing.training.service;

import java.util.List;

import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.criteria.EmployeSearchCriteriaDto;

/**
 * Acts as an interface for EmployeServices
 * 
 * @author SDhananjaya
 * 
 */
public interface EmployeService {

	/**
	 * returns employees' combo list
	 * 
	 * @return
	 */
	String[] getEmployeComboList();

	/**
	 * get all the employee entities
	 * 
	 * @return
	 */
	List<EmployeDto> getAllEmployees();

	/**
	 * get employee from employee id
	 * 
	 * @param employeId
	 * @return
	 */
	EmployeDto getEmploye(int employeId);

	/**
	 * retunrs Employes according to the criteria
	 * 
	 * @param criteria
	 * @return
	 */
	List<EmployeDto> getEmployeDtosFromCriteria(
			EmployeSearchCriteriaDto criteria);
}
