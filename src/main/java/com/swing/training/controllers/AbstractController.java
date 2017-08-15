package com.swing.training.controllers;

import com.swing.training.dtos.EventData;
import com.swing.training.view.ViewInterface;

/**
 * class is used as a abstract controller for all the panels
 * 
 * @author SDhananjaya
 * 
 */
public interface AbstractController {

	/**
	 * abstract method to be implemented in extending controllers
	 * 
	 * @param eventData
	 */
	void executeOpeartion(EventData eventData);

	void setPrescriptionMainView(ViewInterface viewInterface);

	void setPatientMainView(ViewInterface viewInterface);

	void setEmployeeMainView(ViewInterface viewInterface);

	void setDrugMainPanel(ViewInterface viewInterface);
}
