package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.EmployeeDataProvider;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;

public class EmployeeLoadTableDataAction extends AbstractAction {

	public EmployeeLoadTableDataAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		List<EmployeDto> employeDtos = EmployeeDataProvider.getInstance()
				.getAllEmployees();
		controller.getEmployeeMainView().refreshData(
				new EventData(Actions.EMPLOYE_LOAD_TABLE_DATA, employeDtos));
	}

}
