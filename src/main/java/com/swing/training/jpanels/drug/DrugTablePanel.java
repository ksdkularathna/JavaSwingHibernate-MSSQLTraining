package com.swing.training.jpanels.drug;

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
import com.swing.training.tablemodels.DrugTableModel;
import com.swing.training.tablemodels.EmployeTableModel;
import com.swing.training.tablemodels.PatientTableModel;

public class DrugTablePanel extends JPanel {

	private JTable table;
	private DrugTableModel drugTableModel;
	private AbstractController controller;

	private GridBagConstraints gbConstraints = new GridBagConstraints();

	public DrugTablePanel(AbstractController controller) {

		this.controller = controller;
		setLayout(new GridBagLayout());
		setPanelComponents();
		addActionListners();
	}

	public void initialize() {

		drugTableModel.initialize();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;

		table = new JTable();
		drugTableModel = new DrugTableModel(controller);
		table.setModel(drugTableModel);
		getTable().setPreferredScrollableViewportSize(new Dimension(550, 320));
		JScrollPane scrollPane = new JScrollPane(getTable());
		add(scrollPane, gbConstraints);
	}

	private void addActionListners() {

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						if (table.getSelectedRow() > -1) {

							int drugId = ((DrugTableModel) table.getModel())
									.getSelectedDrugId(table.getSelectedRow());
							controller.executeOpeartion(new EventData(
									Actions.DRUG_LOAD_SELECTED_TABLE_DATA, drugId));
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

	public DrugTableModel getDrugTableModel() {
		return drugTableModel;
	}

	public void setDrugTableModel(DrugTableModel drugTableModel) {
		this.drugTableModel = drugTableModel;
	}

}
