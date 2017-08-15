package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.PrescriptionDataProvider;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.enums.Actions;
import com.swing.training.jpanels.prescription.PrescriptionPanelMain;

public class PrescriptionLoadTableDataAction extends AbstractAction {

	public PrescriptionLoadTableDataAction(MainController controller,
			EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		List<PrescriptionDto> prescriptionDtos = PrescriptionDataProvider
				.getInstance().getAllPrescriptions();
		controller.getPrescriptionMainView().refreshData(
				new EventData(Actions.PRESCRIPTION_LOAD_TABLE_DATA,
						prescriptionDtos));
	}
}
