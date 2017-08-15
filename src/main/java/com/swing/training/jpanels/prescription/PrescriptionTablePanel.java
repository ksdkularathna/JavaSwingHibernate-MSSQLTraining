package com.swing.training.jpanels.prescription;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.swing.training.controllers.AbstractController;
import com.swing.training.controllers.MainController;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;
import com.swing.training.tablemodels.PrescriptionTableModel;
import com.swing.training.view.ViewInterface;

public class PrescriptionTablePanel extends JPanel {

	private JTable table;
	private PrescriptionTableModel prescriptionTableModel;
	private AbstractController controller;

	private GridBagConstraints gbConstraints = new GridBagConstraints();

	public PrescriptionTablePanel(AbstractController controller) {

		this.controller = controller;
		setLayout(new GridBagLayout());
		setPanelComponents();
		addActionListners();
	}

	public void initialize() {

		prescriptionTableModel.initialize();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;

		table = new JTable();
		this.prescriptionTableModel = new PrescriptionTableModel(controller);
		table.setModel(prescriptionTableModel);
		getTable().setPreferredScrollableViewportSize(new Dimension(560, 150));
		JScrollPane scrollPane = new JScrollPane(getTable());
		add(scrollPane, gbConstraints);
	}

	private void addActionListners() {

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						if (table.getSelectedRow() > -1) {

							int prescriptionId = ((PrescriptionTableModel) table
									.getModel())
									.getSelectedPrescriptionIdInTable(table
											.getSelectedRow());
							controller
									.executeOpeartion(new EventData(
											Actions.PRESCRIPTION_LOAD_SELECTED_TABLE_DATA,
											prescriptionId));
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

	public PrescriptionTableModel getPrescriptionTableModel() {
		return prescriptionTableModel;
	}

	public void setPrescriptionTableModel(
			PrescriptionTableModel prescriptionTableModel) {
		this.prescriptionTableModel = prescriptionTableModel;
	}
}
