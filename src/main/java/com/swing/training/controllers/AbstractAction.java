package com.swing.training.controllers;

import com.swing.training.dtos.EventData;

/**
 * class is used as a abstract class for data manipulation actions
 * @author SDhananjaya
 *
 */
public abstract class AbstractAction {

	protected MainController controller;
	protected EventData data;

	public AbstractAction(MainController controller, EventData data) {

		this.controller = controller;
		this.data = data;
	}

	/**
	 * execute the abstract action according to the Action in the EventData object
	 */
	abstract void executeOpeartion();
}
