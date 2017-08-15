package com.swing.training.service;

import java.util.List;

/**
 * Generic Service interface for performing generic service requests like add
 * update and delete
 * 
 * @author SDhananjaya
 * 
 * @param <E>
 */
public interface GenericService<E> {

	/**
	 * add the entitiy
	 * 
	 * @param entity
	 */
	void add(E entity);

	/**
	 * update the entity
	 * 
	 * @param entity
	 */
	void update(E entity);

	/**
	 * delete the entity
	 * 
	 * @param entity
	 */
	void delete(E entity);
}
