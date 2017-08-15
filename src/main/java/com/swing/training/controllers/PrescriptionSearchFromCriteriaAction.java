package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.PrescriptionDataProvider;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.dtos.criteria.PrescriptionCriteriaSearchDto;
import com.swing.training.enums.Actions;

public class PrescriptionSearchFromCriteriaAction extends AbstractAction {

	public PrescriptionSearchFromCriteriaAction(MainController controller,
			EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		PrescriptionCriteriaSearchDto criteria = (PrescriptionCriteriaSearchDto) controller
				.getPrescriptionMainView().getData(
						Actions.PRESCRIPTION_SEARCH_FROM_CRITERIA);
		if (criteria != null) {

			List<PrescriptionDto> dtos = PrescriptionDataProvider.getInstance()
					.getPrescriptionsFromCriteria(criteria);
			controller.getPrescriptionMainView().refreshData(
					new EventData(Actions.PRESCRIPTION_LOAD_TABLE_DATA, dtos));
		}
	}
}
