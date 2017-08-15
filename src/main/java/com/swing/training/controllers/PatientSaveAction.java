package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.PatientDataProvider;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.enums.Actions;
import com.swing.training.service.impl.PatientServiceImpl;

public class PatientSaveAction extends AbstractAction {

	public PatientSaveAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		PatientDto patientDto = (PatientDto) controller.getPatientMainView()
				.getData(Actions.PATIENT_SAVE_ACTION);
		if (patientDto != null) {

			((PatientServiceImpl) PatientDataProvider.getInstance())
					.add(patientDto);

			/* executing printing in the panel */
			controller.getPatientMainView().refreshData(
					new EventData(Actions.PATIENT_PRINT, null));
			
			/* clearing the input fields in the patient registration */
			controller.getPatientMainView().refreshData(
					new EventData(Actions.PATIENT_CLEAR_INPUTS, null));

			/* update the patient table */
			List<PatientDto> patientDtos = PatientDataProvider.getInstance()
					.getAllPatients();
			controller.getPatientMainView()
					.refreshData(
							new EventData(Actions.PATIENT_LOAD_TABLE_DATA,
									patientDtos));
		}
	}
}
