package com.swing.training.tablemodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.swing.training.controllers.AbstractController;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;

/**
 * acts as a table model for drug data
 * 
 * @author SDhananjaya
 * 
 */
public class DrugTableModel extends AbstractTableModel {

	private String[] tableColumnNames = { "ID", "Name", "Type" };
	private List<DrugDto> drugTableData = new ArrayList<>();
	private AbstractController controller;

	public DrugTableModel(AbstractController controller) {

		this.controller = controller;
	}

	public void initialize() {

		loadTableData();
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

		DrugDto dto = getEmployeTableData().get(row);
		switch (col) {
		case 0:
			return dto.getDrugId();
		case 1:
			return dto.getName();
		case 2:
			return dto.getType();
		default:
			return "";
		}
	}

	/**
	 * returns the drug id of the selected row
	 * 
	 * @param row
	 * @return
	 */
	public int getSelectedDrugId(int row) {

		DrugDto drugDto = drugTableData.get(row);
		return drugDto.getDrugId();
	}

	public List<DrugDto> getEmployeTableData() {
		return drugTableData;
	}

	public void setDrugTableData(List<DrugDto> drugTableData) {
		this.drugTableData = drugTableData;
		fireTableDataChanged();
	}

	private void loadTableData() {

		controller.executeOpeartion(new EventData(Actions.DRUG_LOAD_TABLE_DATA,
				null));
	}
}
