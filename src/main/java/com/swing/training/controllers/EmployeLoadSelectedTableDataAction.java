package com.swing.training.controllers;

import com.swing.training.dataproviders.EmployeeDataProvider;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;

public class EmployeLoadSelectedTableDataAction extends AbstractAction {

	public EmployeLoadSelectedTableDataAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		EmployeDto employeDto = EmployeeDataProvider.getInstance().getEmploye(
				(Integer) data.getObject());
		controller.getEmployeeMainView().refreshData(
				new EventData(Actions.EMPLOYE_LOAD_SELECTED_TABLE_DATA, employeDto));
	}

}
