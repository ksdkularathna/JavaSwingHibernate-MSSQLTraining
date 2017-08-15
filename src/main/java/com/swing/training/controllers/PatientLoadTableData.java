package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.PatientDataProvider;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PatientDto;
import com.swing.training.enums.Actions;

public class PatientLoadTableData extends AbstractAction {

	public PatientLoadTableData(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		List<PatientDto> patientDtos = PatientDataProvider.getInstance()
				.getAllPatients();
		controller.getPatientMainView().refreshData(
				new EventData(Actions.PATIENT_LOAD_TABLE_DATA, patientDtos));

	}
}
