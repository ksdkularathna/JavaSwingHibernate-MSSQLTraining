package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.DrugDataProvider;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;

public class DrugLoadTableDataAction extends AbstractAction {

	public DrugLoadTableDataAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		List<DrugDto> drugDtos = DrugDataProvider.getInstance().getAllDrugs();
		controller.getDrugMainView().refreshData(
				new EventData(Actions.DRUG_LOAD_TABLE_DATA, drugDtos));
	}

}
