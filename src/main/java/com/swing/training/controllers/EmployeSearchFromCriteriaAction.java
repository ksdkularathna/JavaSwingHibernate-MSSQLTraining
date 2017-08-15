package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.EmployeeDataProvider;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.criteria.EmployeSearchCriteriaDto;
import com.swing.training.enums.Actions;

public class EmployeSearchFromCriteriaAction extends AbstractAction {

	public EmployeSearchFromCriteriaAction(MainController controller,
			EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		EmployeSearchCriteriaDto criteria = (EmployeSearchCriteriaDto) controller
				.getEmployeeMainView().getData(
						Actions.EMPLOYE_SEARCH_FROM_CRITERIA);

		if (criteria != null) {

			List<EmployeDto> dtos = EmployeeDataProvider.getInstance()
					.getEmployeDtosFromCriteria(criteria);
			controller.getEmployeeMainView().refreshData(
					new EventData(Actions.EMPLOYE_LOAD_TABLE_DATA, dtos));
		}
	}
}
