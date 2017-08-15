package com.swing.training.controllers;

import javax.xml.crypto.Data;

import com.swing.training.dataproviders.PatientDataProvider;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PatientDto;
import com.swing.training.enums.Actions;

public class PatientLoadSelectedTableDataAction extends AbstractAction {

	public PatientLoadSelectedTableDataAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		PatientDto patientDto = PatientDataProvider.getInstance().getPatient(
				(Integer) data.getObject());
		controller.getPatientMainView().refreshData(
				new EventData(Actions.PATIENT_LOAD_SELECTED_TABLE_DATA, patientDto));
	}
}
 