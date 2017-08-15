package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.DrugDataProvider;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.criteria.DrugSearchCriteriaDto;
import com.swing.training.enums.Actions;

public class DrugSearchFromCriteriaAction extends AbstractAction {

	public DrugSearchFromCriteriaAction(MainController controller,
			EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		DrugSearchCriteriaDto criteria = (DrugSearchCriteriaDto) controller
				.getDrugMainView().getData(Actions.DRUG_SEARCH_FROM_CRITERIA);
		if (criteria != null) {

			List<DrugDto> drugDtos = DrugDataProvider.getInstance()
					.getDrugsFromCriteria(criteria);
			controller.getDrugMainView().refreshData(
					new EventData(Actions.DRUG_LOAD_TABLE_DATA, drugDtos));
		}
	}
}
