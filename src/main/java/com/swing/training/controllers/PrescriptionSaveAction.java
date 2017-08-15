package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.PrescriptionDataProvider;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.enums.Actions;
import com.swing.training.utility.AbstractPrescriptionXmlGenerator;
import com.swing.training.utility.PharmacyAPrescriptionXmlGenerator;

public class PrescriptionSaveAction extends AbstractAction {

	public PrescriptionSaveAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		PrescriptionDto prescriptionDto = (PrescriptionDto) controller
				.getPrescriptionMainView().getData(
						Actions.PRESCRIPTION_SAVE_ACTION);

		if (prescriptionDto != null) {

			if (prescriptionDto.getPrescriptionId() == 0) {

				PrescriptionDataProvider.getInstance().addNewPrescription(
						prescriptionDto);
			} else {

				PrescriptionDataProvider.getInstance().editPrescription(
						prescriptionDto);
			}

			/* executing printing in the panel */
			controller.getPrescriptionMainView().refreshData(
					new EventData(Actions.PRESCRIPTION_PRINT, null));

			/* exporting as a xml */
//			AbstractPrescriptionXmlGenerator generator = new PharmacyAPrescriptionXmlGenerator();
//			generator.generatePrescriptionXml(prescriptionDto);

			/* clearing the input fields in presecription registration */
			controller.getPrescriptionMainView().refreshData(
					new EventData(Actions.PRESCRIPTION_CLEAR_INPUTS, null));

			/* updating the table in prescription */
			List<PrescriptionDto> prescriptionDtos = PrescriptionDataProvider
					.getInstance().getAllPrescriptions();
			controller.getPrescriptionMainView().refreshData(
					new EventData(Actions.PRESCRIPTION_LOAD_TABLE_DATA,
							prescriptionDtos));
		}
	}
}
