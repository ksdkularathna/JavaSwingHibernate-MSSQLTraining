package com.swing.training.jpanels.patient;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.swing.training.controllers.AbstractController;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;
import com.swing.training.tablemodels.PatientTableModel;

public class PatientTablePanel extends JPanel {

	public static final String SPACE_STRING = " ", MONTHS_STRING = "months";
	public static final int MINIMUM_AGE = 3;

	private JTable table;
	private PatientTableModel patientTableModel;
	private AbstractController controller;

	private GridBagConstraints gbConstraints = new GridBagConstraints();

	public PatientTablePanel(AbstractController controller) {

		this.controller = controller;
		setLayout(new GridBagLayout());
		setPanelComponents();
		setActionListners();
	}

	public void initialize() {

		patientTableModel.initialize();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;

		table = new JTable();
		patientTableModel = new PatientTableModel(controller);
		table.setModel(patientTableModel);
		// table.setDefaultRenderer(Object.class, new AgeCellRenderer());
		getTable().setPreferredScrollableViewportSize(new Dimension(560, 120));
		JScrollPane scrollPane = new JScrollPane(getTable());
		add(scrollPane, gbConstraints);
	}

	private void setActionListners() {

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						if (table.getSelectedRow() > -1) {
							
							int patientId=((PatientTableModel)table.getModel()).getPatientIdOfSelectedRow(table.getSelectedRow());
							controller.executeOpeartion(new EventData(Actions.PATIENT_LOAD_SELECTED_TABLE_DATA,patientId));
						}
					}
				});
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public PatientTableModel getPatientTableModel() {
		return patientTableModel;
	}

	public void setPatientTableModel(PatientTableModel patientTableModel) {
		this.patientTableModel = patientTableModel;
	}
}
