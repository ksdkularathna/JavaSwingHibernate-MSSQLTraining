package com.swing.training.service.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.swing.training.service.GenericService;

public abstract class GenericServiceImpl<E> implements GenericService<E> {

	private Session session;
	private Transaction transaction;

	@Override
	public void add(E entity) {

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		session.saveOrUpdate(entity);
		transaction.commit();
	}

	@Override
	public void update(E entity) {

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		session.update(entity);
		transaction.commit();
	}

	@Override
	public void delete(E entity) {

		session = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		session.delete(entity);
		transaction.commit();
	}
}
