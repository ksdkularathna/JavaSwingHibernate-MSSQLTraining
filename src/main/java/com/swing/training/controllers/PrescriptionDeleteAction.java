package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.PrescriptionDataProvider;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.enums.Actions;
import com.swing.training.service.impl.PrescriptionServiceImpl;

public class PrescriptionDeleteAction extends AbstractAction {

	public PrescriptionDeleteAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		int prescriptionId = (Integer) data.getObject();
		PrescriptionDto dto = new PrescriptionDto();
		dto.setPrescriptionId(prescriptionId);
		((PrescriptionServiceImpl) PrescriptionDataProvider.getInstance())
				.delete(dto);

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
