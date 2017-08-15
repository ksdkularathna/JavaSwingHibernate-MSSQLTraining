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

import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.criteria.DrugSearchCriteriaDto;
import com.swing.training.service.DrugService;

public class DrugServiceImpl extends GenericServiceImpl<DrugDto> implements
		DrugService {

	public static final String HYPNE = "-";
	public static final String NULL_STRING = "";

	@Override
	public String[] getDrugComboxList() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		List<DrugDto> drugDtos = session.createCriteria(DrugDto.class).list();
		String[] drugComboList = new String[drugDtos.size()];

		for (int i = 0; i < drugComboList.length; i++) {

			drugComboList[i] = drugDtos.get(i).getName() + HYPNE
					+ drugDtos.get(i).getDrugId();
		}
		transaction.commit();
		return drugComboList;
	}

	@Override
	public List<DrugDto> getAllDrugs() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		List<DrugDto> drugDtos = session.createCriteria(DrugDto.class).list();
		transaction.commit();
		return drugDtos;
	}

	@Override
	public DrugDto getDrug(int drugId) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		DrugDto drugDto = (DrugDto) session.get(DrugDto.class, drugId);
		transaction.commit();
		return drugDto;
	}

	@Override
	public List<DrugDto> getDrugsFromCriteria(DrugSearchCriteriaDto criteria) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		String hQuery = "from DrugDto";
		Query query;

		if (!criteria.getDrugName().equals(NULL_STRING)) {

			hQuery += " where name LIKE CONCAT('%',:name,'%')";
			query = session.createQuery(hQuery);
			query.setParameter("name", criteria.getDrugName());

			List<DrugDto> drugDtos = query.list();
			transaction.commit();
			return drugDtos;
		}

		query = session.createQuery(hQuery);
		List<DrugDto> drugDtos = query.list();
		transaction.commit();
		return drugDtos;
	}

	@Override
	public void addDrug(DrugDto drugDto) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		session.save(drugDto);
		transaction.commit();
	}
}
