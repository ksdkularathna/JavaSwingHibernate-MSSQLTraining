package com.swing.training.tablemodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.swing.training.dtos.DrugDosageDto;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.PrescribedDrugDto;

/**
 * acts as a table model for drug dosage data
 * 
 * @author SDhananjaya
 * 
 */
public class DrugDosageTableModel extends AbstractTableModel {

	private String[] tableColumnNames = { "Drug ID", "Name", "Dosage" };
	private List<DrugDosageDto> drugDosageTableData = new ArrayList<>();

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

		return getDrugDosageTableData().size();
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

		DrugDosageDto dto = getDrugDosageTableData().get(row);
		switch (col) {
		case 0:
			return dto.getId();
		case 1:
			return dto.getName();
		case 2:
			return dto.getDosage();
		default:
			return "";
		}
	}

	/**
	 * returns data list of the table
	 * 
	 * @return
	 */
	public List<DrugDosageDto> getDrugDosageTableData() {
		return drugDosageTableData;
	}

	/**
	 * set data list of the table
	 * 
	 * @param drugDosageTableData
	 */
	public void setDrugDosageTableData(List<DrugDosageDto> drugDosageTableData) {
		this.drugDosageTableData = drugDosageTableData;
		fireTableDataChanged();
	}

	/**
	 * returns the DrugDosage object of the selected row in the table
	 * 
	 * @param row
	 * @return
	 */
	public DrugDosageDto getSelectedRowData(int row) {

		return getDrugDosageTableData().get(row);
	}

}
