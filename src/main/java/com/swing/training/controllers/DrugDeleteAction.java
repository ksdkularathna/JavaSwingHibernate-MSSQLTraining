package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.DrugDataProvider;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;
import com.swing.training.service.impl.DrugServiceImpl;

public class DrugDeleteAction extends AbstractAction {

	public DrugDeleteAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		DrugDto drugDto = new DrugDto();
		drugDto.setDrugId((int) data.getObject());
		((DrugServiceImpl) DrugDataProvider.getInstance()).delete(drugDto);

		/* updating the table data in the panel */
		List<DrugDto> drugDtos = DrugDataProvider.getInstance().getAllDrugs();
		controller.getDrugMainView().refreshData(
				new EventData(Actions.DRUG_LOAD_TABLE_DATA, drugDtos));
	}
}
