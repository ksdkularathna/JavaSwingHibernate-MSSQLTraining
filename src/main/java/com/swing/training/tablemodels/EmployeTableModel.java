package com.swing.training.tablemodels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.swing.training.controllers.AbstractController;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PatientDto;
import com.swing.training.enums.Actions;
import com.swing.training.service.PatientService;
import com.swing.training.service.impl.PatientServiceImpl;

/**
 * Acts as a table model for employee data
 * 
 * @author SDhananjaya
 * 
 */
public class EmployeTableModel extends AbstractTableModel {

	private String[] tableColumnNames = { "ID", "Name", "Address" };
	private List<EmployeDto> employeTableData = new ArrayList<>();
	private AbstractController controller;

	public EmployeTableModel(AbstractController controller) {

		this.controller = controller;
	}

	/**
	 * initialize the table model
	 */
	public void initialize() {

		loadTableData();
	}

	/**
	 * fetch the patient list from the DB to tha table
	 */
	public void loadTableData() {

		controller.executeOpeartion(new EventData(
				Actions.EMPLOYE_LOAD_TABLE_DATA, null));
	}

	/**
	 * returns the column count of the table
	 */
	public int getColumnCount() {

		return tableColumnNames.length;
	}

	/**
	 * returns the row count of the table
	 */
	public int getRowCount() {

		return getEmployeTableData().size();
	}

	/**
	 * returns the column name of the table
	 */
	public String getColumnName(int col) {

		return tableColumnNames[col];
	}

	/**
	 * returns the cell value of the selected cell in the table
	 */
	public Object getValueAt(int row, int col) {

		EmployeDto dto = getEmployeTableData().get(row);
		switch (col) {
		case 0:
			return dto.getEmployeId();
		case 1:
			return dto.getName();
		case 2:
			return dto.getAddress();
		default:
			return "";
		}
	}

	/**
	 * make the cell editable in particular row and col IDs
	 */
	public boolean isCellEditable(int row, int col) {

		if (col < 2) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * returnd the employee id of the selected row
	 * 
	 * @param row
	 * @return
	 */
	public int getSelectedEmployeId(int row) {

		EmployeDto employeDto = employeTableData.get(row);
		return employeDto.getEmployeId();
	}

	public List<EmployeDto> getEmployeTableData() {
		return employeTableData;
	}

	public void setEmployeeTableData(List<EmployeDto> patientTableData) {
		this.employeTableData = patientTableData;
		fireTableDataChanged();
	}
}
