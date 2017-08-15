package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.EmployeeDataProvider;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;
import com.swing.training.service.impl.EmployeServiceImpl;

public class EmployeeSaveAction extends AbstractAction {

	public EmployeeSaveAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		EmployeDto employeDto = (EmployeDto) controller.getEmployeeMainView()
				.getData(Actions.EMPLOYE_SAVE_ACTION);
		if (employeDto != null) {

			((EmployeServiceImpl) EmployeeDataProvider.getInstance())
					.add(employeDto);
			/* clearinf user inputs in the panel */
			controller.getEmployeeMainView().refreshData(
					new EventData(Actions.EMPLOYE_CLEAR_INPUTS, null));

			/* refreshing the employe table */
			List<EmployeDto> employeDtos = EmployeeDataProvider.getInstance()
					.getAllEmployees();
			controller.getEmployeeMainView().refreshData(
					new EventData(Actions.EMPLOYE_LOAD_TABLE_DATA, employeDtos));
		}
	}
}
