package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.EmployeeDataProvider;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;
import com.swing.training.service.impl.EmployeServiceImpl;

public class EmployeDeleteAction extends AbstractAction {

	public EmployeDeleteAction(MainController controller, EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		EmployeDto employeDto = new EmployeDto();
		employeDto.setEmployeId((int) data.getObject());
		((EmployeServiceImpl) EmployeeDataProvider.getInstance())
				.delete(employeDto);

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
