package com.swing.training.view;

import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;

/**
 * Generic view interface for JPanels
 * 
 * @author SDhananjaya
 * 
 */
public interface ViewInterface {

	/**
	 * abstract method for refreshing the data in the panel
	 * 
	 * @param eventData
	 */
	public void refreshData(EventData eventData);

	/**
	 * abstract method for getting the data from the panel
	 * 
	 * @param action
	 * @return
	 */
	public Object getData(Actions action);
}
