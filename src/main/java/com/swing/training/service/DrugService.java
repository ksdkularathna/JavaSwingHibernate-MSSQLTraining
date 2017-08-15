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

import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.criteria.DrugSearchCriteriaDto;

/**
 * Acts as an interface for the DrugServices
 * 
 * @author SDhananjaya
 * 
 */
public interface DrugService {

	/**
	 * add a drug
	 * 
	 * @param drugDto
	 */
	void addDrug(DrugDto drugDto);

	/**
	 * returns drug combo box list
	 * 
	 * @return
	 */
	String[] getDrugComboxList();

	/**
	 * returns all the drug entities
	 * 
	 * @return
	 */
	List<DrugDto> getAllDrugs();

	/**
	 * get a drug from id
	 * 
	 * @param drugId
	 * @return
	 */
	DrugDto getDrug(int drugId);

	/**
	 * returns drug list according to the crioteria
	 * 
	 * @param criteria
	 * @return
	 */
	List<DrugDto> getDrugsFromCriteria(DrugSearchCriteriaDto criteria);
}
