package com.swing.training.controllers;

import com.swing.training.dataproviders.PrescriptionDataProvider;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.enums.Actions;

public class PrescriptionLoadSelectedTableDataAction extends AbstractAction {

	public PrescriptionLoadSelectedTableDataAction(MainController controller,
			EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		int prescriptionId = (Integer) data.getObject();

		PrescriptionDto prescriptionDto = PrescriptionDataProvider
				.getInstance().getPrescriptionFromId(prescriptionId);
		if (prescriptionDto != null) {

			controller.getPrescriptionMainView().refreshData(
					new EventData(
							Actions.PRESCRIPTION_LOAD_SELECTED_TABLE_DATA,
							prescriptionDto));
		}
	}
}
