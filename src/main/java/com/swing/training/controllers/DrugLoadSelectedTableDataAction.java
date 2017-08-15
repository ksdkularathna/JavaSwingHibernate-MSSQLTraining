package com.swing.training.controllers;

import com.swing.training.dataproviders.DrugDataProvider;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;

public class DrugLoadSelectedTableDataAction extends AbstractAction {

	public DrugLoadSelectedTableDataAction(MainController controller,
			EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		DrugDto drugDto = DrugDataProvider.getInstance().getDrug(
				(int) data.getObject());
		controller.getDrugMainView().refreshData(
				new EventData(Actions.DRUG_LOAD_SELECTED_TABLE_DATA, drugDto));
	}

}
