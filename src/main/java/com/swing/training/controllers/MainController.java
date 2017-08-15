package com.swing.training.controllers;

import com.swing.training.dtos.EventData;
import com.swing.training.view.ViewInterface;

public class MainController implements AbstractController {

	private ViewInterface prescriptionMainView;
	private ViewInterface patientMainView;
	private ViewInterface employeeMainView;
	private ViewInterface drugMainView;

	public void executeOpeartion(EventData eventData) {

		AbstractAction action = PhamarcyActionSupportFactory.getAction(this,
				eventData);
		action.executeOpeartion();
	}

	public ViewInterface getPrescriptionMainView() {

		return prescriptionMainView;
	}

	@Override
	public void setPrescriptionMainView(ViewInterface prescriptionMainView) {

		this.prescriptionMainView = prescriptionMainView;
	}

	@Override
	public void setPatientMainView(ViewInterface viewInterface) {

		this.patientMainView = viewInterface;
	}

	public ViewInterface getPatientMainView() {
		return patientMainView;
	}

	@Override
	public void setEmployeeMainView(ViewInterface viewInterface) {

		this.employeeMainView = viewInterface;
	}

	public ViewInterface getEmployeeMainView() {
		return employeeMainView;
	}

	@Override
	public void setDrugMainPanel(ViewInterface viewInterface) {

		this.drugMainView = viewInterface;
	}

	public ViewInterface getDrugMainView() {
		return drugMainView;
	}
}
