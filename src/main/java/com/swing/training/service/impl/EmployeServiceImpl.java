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
package com.swing.training.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.criteria.EmployeSearchCriteriaDto;
import com.swing.training.service.EmployeService;

public class EmployeServiceImpl extends GenericServiceImpl<EmployeDto>
		implements EmployeService {

	public static final String HYPNE = "-";
	public static final String NULL_STRING = "";

	@Override
	public String[] getEmployeComboList() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		List<EmployeDto> employeDtos = session.createCriteria(EmployeDto.class)
				.list();
		String[] employeComboList = new String[employeDtos.size()];

		for (int i = 0; i < employeComboList.length; i++) {

			employeComboList[i] = employeDtos.get(i).getName() + HYPNE
					+ employeDtos.get(i).getEmployeId();
		}
		transaction.commit();
		return employeComboList;
	}

	@Override
	public List<EmployeDto> getAllEmployees() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<EmployeDto> employeDtos = session.createCriteria(EmployeDto.class)
				.list();
		transaction.commit();
		return employeDtos;
	}

	@Override
	public EmployeDto getEmploye(int employeId) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		EmployeDto employeDto = (EmployeDto) session.get(EmployeDto.class,
				employeId);
		transaction.commit();
		return employeDto;
	}

	@Override
	public List<EmployeDto> getEmployeDtosFromCriteria(
			EmployeSearchCriteriaDto criteria) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		String hQuery = "from EmployeDto";
		Query query;

		if (!criteria.getEmployeName().equals(NULL_STRING)) {

			hQuery += " where name LIKE CONCAT('%',:name,'%')";
			query = session.createQuery(hQuery);
			query.setParameter("name", criteria.getEmployeName());
			List<EmployeDto> dtos = query.list();
			transaction.commit();
			return dtos;
		}
		query = session.createQuery(hQuery);
		List<EmployeDto> dtos = query.list();
		transaction.commit();
		return dtos;
	}
}
