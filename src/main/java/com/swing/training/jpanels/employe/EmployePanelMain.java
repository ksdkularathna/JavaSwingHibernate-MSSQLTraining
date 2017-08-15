package com.swing.training.jpanels.employe;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.swing.training.controllers.AbstractController;
import com.swing.training.controllers.MainController;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PatientDto;
import com.swing.training.enums.Actions;
import com.swing.training.tablemodels.EmployeTableModel;
import com.swing.training.view.ViewInterface;

public class EmployePanelMain extends JPanel implements ViewInterface {

	private EmployeRegestrationPanel employeRegestrationPanel;
	private EmployeSearchPanel employeSearchPanel;
	private EmployeTablePanel employeTablePanel;
	private AbstractController controller;

	public EmployePanelMain(AbstractController controller) {

		this.controller = controller;
		employeTablePanel = new EmployeTablePanel(controller);
		employeRegestrationPanel = new EmployeRegestrationPanel(controller);
		employeSearchPanel = new EmployeSearchPanel(controller);
		employeRegestrationPanel.setMaximumSize(employeRegestrationPanel
				.getPreferredSize());
		controller.setEmployeeMainView(this);
		addSubPanels();
	}

	public void initialize() {

		employeTablePanel.initialize();
	}

	private void addSubPanels() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(employeRegestrationPanel);
		add(employeSearchPanel);
		add(employeTablePanel);
	}

	@Override
	public void refreshData(EventData eventData) {

		switch (eventData.getAction()) {

		case EMPLOYE_CLEAR_INPUTS:

			employeRegestrationPanel.clearInputFields();
			break;
		case EMPLOYE_LOAD_TABLE_DATA:

			employeTablePanel.getEmployeTableModel().setEmployeeTableData(
					(List<EmployeDto>) eventData.getObject());
			break;
		case EMPLOYE_LOAD_SELECTED_TABLE_DATA:

			employeRegestrationPanel.setEmployeDtoToUi((EmployeDto) eventData
					.getObject());
		default:
			break;
		}
	}

	@Override
	public Object getData(Actions action) {

		switch (action) {
		case EMPLOYE_SAVE_ACTION:

			return employeRegestrationPanel.validateAndGetEmployeeDetails();

		case EMPLOYE_SEARCH_FROM_CRITERIA:

			return employeSearchPanel.getEmployeSearchCriteria();

		default:
			break;
		}
		return employeRegestrationPanel.validateAndGetEmployeeDetails();
	}
}
