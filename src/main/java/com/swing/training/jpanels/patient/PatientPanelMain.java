package com.swing.training.jpanels.patient;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.swing.training.controllers.AbstractController;
import com.swing.training.controllers.MainController;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PatientDto;
import com.swing.training.enums.Actions;
import com.swing.training.tablemodels.PatientTableModel;
import com.swing.training.view.ViewInterface;

public class PatientPanelMain extends JPanel implements ViewInterface {

	private PatientRegestrationPanel patientRegestrationPanel;
	private PatientSearchPanel patientSearchPanel;
	private PatientTablePanel patientTablePanel;
	private AbstractController controller;

	public PatientPanelMain(AbstractController controller) {

		this.controller = controller;
		patientTablePanel = new PatientTablePanel(controller);
		patientRegestrationPanel = new PatientRegestrationPanel(controller);
		patientSearchPanel = new PatientSearchPanel(controller);
		patientRegestrationPanel.setMaximumSize(patientRegestrationPanel
				.getPreferredSize());
		controller.setPatientMainView(this);
		addSubPanels();
	}

	private void addSubPanels() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(patientRegestrationPanel);
		add(patientSearchPanel);
		add(patientTablePanel);
	}

	public void initialize() {

		patientTablePanel.initialize();
	}

	@Override
	public void refreshData(EventData eventData) {

		switch (eventData.getAction()) {
		case PATIENT_CLEAR_INPUTS:

			patientRegestrationPanel.clearInputFields();
			break;
		case PATIENT_LOAD_TABLE_DATA:

			patientTablePanel.getPatientTableModel().setPatientTableData(
					(List<PatientDto>) eventData.getObject());
			break;
		case PATIENT_LOAD_SELECTED_TABLE_DATA:

			patientRegestrationPanel.setPatientDtoToUi((PatientDto) eventData
					.getObject());
			break;
		case PATIENT_PRINT:

			patientRegestrationPanel.askToPrintOrNot();
			break;
		default:
			break;
		}
	}

	@Override
	public Object getData(Actions action) {

		switch (action) {
		case PATIENT_SAVE_ACTION:

			return patientRegestrationPanel.validateAndGetPatientDetails();

		case PATIENT_SEARCH_FROM_CRITERIA:

			return patientSearchPanel.validateAndGetPatientSearchCriteria();

		default:
			break;
		}
		return patientRegestrationPanel.validateAndGetPatientDetails();
	}
}
