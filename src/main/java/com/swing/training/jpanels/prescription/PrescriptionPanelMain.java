package com.swing.training.jpanels.prescription;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.swing.training.controllers.AbstractController;
import com.swing.training.controllers.MainController;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.enums.Actions;
import com.swing.training.tablemodels.PrescriptionTableModel;
import com.swing.training.view.ViewInterface;

public class PrescriptionPanelMain extends JPanel implements ViewInterface {

	private static PrescriptionAddPanel prescriptionAddPanel;
	private static PrescriptionSearchPanel prescriptionSearchPanel;
	private static PrescriptionTablePanel prescriptionTablePanel;
	private AbstractController controller;

	public PrescriptionPanelMain(AbstractController controller) {

		this.controller = controller;
		prescriptionTablePanel = new PrescriptionTablePanel(controller);
		prescriptionAddPanel = new PrescriptionAddPanel(controller);
		prescriptionSearchPanel = new PrescriptionSearchPanel(controller);

		prescriptionAddPanel.setMaximumSize(prescriptionAddPanel
				.getPreferredSize());
		controller.setPrescriptionMainView(this);
		addSubPanel();
	}

	public void initialize() {

		prescriptionTablePanel.initialize();
	}

	private void addSubPanel() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(prescriptionAddPanel);
		add(prescriptionSearchPanel);
		add(prescriptionTablePanel);
	}

	private void clearPrescriptionAddInputs() {

		prescriptionAddPanel.clearAllInputFields();
	}

	@Override
	public void refreshData(EventData eventData) {

		switch (eventData.getAction()) {
		case PRESCRIPTION_CLEAR_INPUTS:

			clearPrescriptionAddInputs();
			break;

		case PRESCRIPTION_LOAD_TABLE_DATA:

			List<PrescriptionDto> prescriptionDtos = (List<PrescriptionDto>) eventData
					.getObject();
			prescriptionTablePanel.getPrescriptionTableModel()
					.setPrescriptionTableData(prescriptionDtos);
			break;

		case PRESCRIPTION_LOAD_SELECTED_TABLE_DATA:

			PrescriptionDto dto = (PrescriptionDto) eventData.getObject();
			prescriptionAddPanel.setPrescriptionData(dto);
			break;

		case PRESCRIPTION_PRINT:

			prescriptionAddPanel.askToPrintOrNot();
			break;

		default:
			break;
		}
	}

	@Override
	public Object getData(Actions action) {

		switch (action) {
		case PRESCRIPTION_SEARCH_FROM_CRITERIA:

			return prescriptionSearchPanel.getPrescriptionSearchCriteria();
		case PRESCRIPTION_SAVE_ACTION:

			return prescriptionAddPanel.getPrescriptionData();
		default:
			break;
		}
		return prescriptionAddPanel.getPrescriptionData();
	}
}
