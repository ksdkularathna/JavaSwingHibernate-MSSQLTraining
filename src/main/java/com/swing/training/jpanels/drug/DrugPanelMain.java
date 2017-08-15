package com.swing.training.jpanels.drug;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.swing.training.controllers.AbstractController;
import com.swing.training.controllers.MainController;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;
import com.swing.training.tablemodels.DrugTableModel;
import com.swing.training.view.ViewInterface;

public class DrugPanelMain extends JPanel implements ViewInterface {

	private DrugAddPanel drugRegestrationPanel;
	private DrugSearchPanel drugSearchPanel;
	private DrugTablePanel drugTablePanel;
	private AbstractController controller;

	public DrugPanelMain(AbstractController controller) {

		this.controller = controller;
		drugTablePanel = new DrugTablePanel(controller);
		drugSearchPanel = new DrugSearchPanel(controller);
		drugRegestrationPanel = new DrugAddPanel(controller);
		drugRegestrationPanel.setMaximumSize(drugRegestrationPanel
				.getPreferredSize());
		controller.setDrugMainPanel(this);
		addSubPanels();
	}

	public void initialize() {

		drugTablePanel.initialize();
	}

	private void addSubPanels() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(drugRegestrationPanel);
		add(drugSearchPanel);
		add(drugTablePanel);
	}

	@Override
	public void refreshData(EventData eventData) {

		switch (eventData.getAction()) {
		case DRUG_LOAD_TABLE_DATA:

			drugTablePanel.getDrugTableModel().setDrugTableData(
					(List<DrugDto>) eventData.getObject());
			drugRegestrationPanel.clearInputFields();
			break;

		case DRUG_LOAD_SELECTED_TABLE_DATA:

			drugRegestrationPanel.setDrugDtoToUi((DrugDto) eventData
					.getObject());
			break;
		default:
			break;
		}
	}

	@Override
	public Object getData(Actions action) {

		switch (action) {
		case DRUG_SAVE_ACTION:

			return drugRegestrationPanel.validateAndGetDrugDetails();

		case DRUG_SEARCH_FROM_CRITERIA:

			return drugSearchPanel.getDrugSearchCriteria();
		default:
			break;
		}
		return drugRegestrationPanel.validateAndGetDrugDetails();
	}
}
