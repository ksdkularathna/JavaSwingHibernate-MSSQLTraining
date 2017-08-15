package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.DrugDataProvider;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;
import com.swing.training.service.impl.DrugServiceImpl;

public class DrugSaveAction extends AbstractAction {

	public DrugSaveAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		DrugDto drugDto = (DrugDto) controller.getDrugMainView().getData(
				Actions.DRUG_SAVE_ACTION);

		if (drugDto != null) {
			((DrugServiceImpl) DrugDataProvider.getInstance()).add(drugDto);
		}
		/* updating the table data in the panel */
		List<DrugDto> drugDtos = DrugDataProvider.getInstance().getAllDrugs();
		controller.getDrugMainView().refreshData(
				new EventData(Actions.DRUG_LOAD_TABLE_DATA, drugDtos));
	}
}
