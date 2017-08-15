package com.swing.training.jpanels.employe;

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
import com.swing.training.tablemodels.EmployeTableModel;
import com.swing.training.tablemodels.PatientTableModel;

public class EmployeTablePanel extends JPanel {

	private JTable table;
	private EmployeTableModel employeTableModel;
	private AbstractController controller;

	private GridBagConstraints gbConstraints = new GridBagConstraints();

	public EmployeTablePanel(AbstractController controller) {

		this.controller = controller;
		setLayout(new GridBagLayout());
		setPanelComponents();
		addActionListners();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;

		table = new JTable();
		employeTableModel = new EmployeTableModel(controller);
		table.setModel(employeTableModel);
		table.setPreferredScrollableViewportSize(new Dimension(540, 260));
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane, gbConstraints);
	}

	private void addActionListners() {

		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					@Override
					public void valueChanged(ListSelectionEvent e) {

						if (table.getSelectedRow() > -1) {

							int employeId = ((EmployeTableModel) table
									.getModel()).getSelectedEmployeId(table
									.getSelectedRow());
							controller.executeOpeartion(new EventData(
									Actions.EMPLOYE_LOAD_SELECTED_TABLE_DATA,
									employeId));
						}
					}
				});
	}

	public void initialize() {

		employeTableModel.initialize();
	}

	public EmployeTableModel getEmployeTableModel() {
		return employeTableModel;
	}

	public void setEmployeTableModel(EmployeTableModel employeTableModel) {
		this.employeTableModel = employeTableModel;
	}
}
