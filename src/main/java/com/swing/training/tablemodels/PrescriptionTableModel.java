package com.swing.training.tablemodels;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.swing.training.controllers.AbstractController;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.enums.Actions;

/**
 * acts as a table model for perscription data
 * 
 * @author SDhananjaya
 * 
 */
public class PrescriptionTableModel extends AbstractTableModel {

	private AbstractController controller;
	private String[] tableColumnNames = { "ID", "Patient", "Patient ID",
			"Patient Gender", "Doctor", "Date" };
	private List<PrescriptionDto> prescriptionTableData = new ArrayList<>();

	public PrescriptionTableModel(AbstractController controller) {

		this.controller = controller;
	}

	public void initialize() {

		loadTableData();
	}

	private void loadTableData() {

		controller.executeOpeartion(new EventData(
				Actions.PRESCRIPTION_LOAD_TABLE_DATA, null));
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

		return getPrescriptionTableData().size();
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

		PrescriptionDto dto = getPrescriptionTableData().get(row);
		switch (col) {
		case 0:
			return dto.getPrescriptionId();// dto.getPatientId();
		case 1:
			return dto.getPatient().getName();
		case 2:
			return dto.getPatient().getPatientId();
		case 3:
			return dto.getPatient().getGender();
		case 4:
			return dto.getDoctor().getName();
		case 5:
			return dto.getDate().toLocaleString();
		default:
			return "";
		}
	}

	/**
	 * returns the prescription id of selected row in the table
	 * 
	 * @param row
	 * @return
	 */
	public int getSelectedPrescriptionIdInTable(int row) {

		PrescriptionDto dto = prescriptionTableData.get(row);
		return dto.getPrescriptionId();
	}

	public List<PrescriptionDto> getPrescriptionTableData() {
		return prescriptionTableData;
	}

	public void setPrescriptionTableData(
			List<PrescriptionDto> prescriptionTableData) {
		this.prescriptionTableData = prescriptionTableData;
		fireTableDataChanged();
	}
}
