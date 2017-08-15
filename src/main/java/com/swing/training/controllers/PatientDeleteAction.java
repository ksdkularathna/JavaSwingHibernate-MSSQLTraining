package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.PatientDataProvider;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PatientDto;
import com.swing.training.enums.Actions;
import com.swing.training.service.impl.PatientServiceImpl;

public class PatientDeleteAction extends AbstractAction {

	public PatientDeleteAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		PatientDto patientDto = new PatientDto();
		patientDto.setPatientId((Integer) data.getObject());
		((PatientServiceImpl) PatientDataProvider.getInstance())
				.delete(patientDto);

		/* clearing the input fields in the patient registration */
		controller.getPatientMainView().refreshData(
				new EventData(Actions.PATIENT_CLEAR_INPUTS, null));

		/* update the patient table */
		List<PatientDto> patientDtos = PatientDataProvider.getInstance()
				.getAllPatients();
		controller.getPatientMainView().refreshData(
				new EventData(Actions.PATIENT_LOAD_TABLE_DATA, patientDtos));
	}

}
