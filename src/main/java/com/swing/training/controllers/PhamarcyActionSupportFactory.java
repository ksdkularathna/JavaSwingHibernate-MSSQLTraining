package com.swing.training.controllers;

import com.swing.training.dtos.EventData;

public class PhamarcyActionSupportFactory {

	public static AbstractAction getAction(MainController controller,
			EventData data) {

		switch (data.getAction()) {
		case PRESCRIPTION_SAVE_ACTION:

			return new PrescriptionSaveAction(controller, data);

		case PRESCRIPTION_LOAD_TABLE_DATA:

			return new PrescriptionLoadTableDataAction(controller, data);

		case PATIENT_SAVE_ACTION:

			return new PatientSaveAction(controller, data);

		case PATIENT_LOAD_TABLE_DATA:

			return new PatientLoadTableData(controller, data);

		case EMPLOYE_SAVE_ACTION:

			return new EmployeeSaveAction(controller, data);

		case EMPLOYE_LOAD_TABLE_DATA:

			return new EmployeeLoadTableDataAction(controller, data);

		case DRUG_SAVE_ACTION:

			return new DrugSaveAction(controller, data);

		case DRUG_LOAD_TABLE_DATA:

			return new DrugLoadTableDataAction(controller, data);

		case PATIENT_LOAD_SELECTED_TABLE_DATA:

			return new PatientLoadSelectedTableDataAction(controller, data);

		case PATIENT_DELETE:

			return new PatientDeleteAction(controller, data);

		case EMPLOYE_LOAD_SELECTED_TABLE_DATA:

			return new EmployeLoadSelectedTableDataAction(controller, data);

		case EMPLOYE_DELETE:

			return new EmployeDeleteAction(controller, data);

		case DRUG_LOAD_SELECTED_TABLE_DATA:

			return new DrugLoadSelectedTableDataAction(controller, data);

		case DRUG_DELETE:

			return new DrugDeleteAction(controller, data);

		case PRESCRIPTION_SEARCH_FROM_CRITERIA:

			return new PrescriptionSearchFromCriteriaAction(controller, data);

		case PATIENT_SEARCH_FROM_CRITERIA:

			return new PatientSearchFromCriteriaAction(controller, data);

		case EMPLOYE_SEARCH_FROM_CRITERIA:

			return new EmployeSearchFromCriteriaAction(controller, data);

		case DRUG_SEARCH_FROM_CRITERIA:

			return new DrugSearchFromCriteriaAction(controller, data);

		case PRESCRIPTION_LOAD_SELECTED_TABLE_DATA:

			return new PrescriptionLoadSelectedTableDataAction(controller, data);

		case PRESCRIPTION_DELETE:

			return new PrescriptionDeleteAction(controller, data);

		default:
			return null;
		}
	}
}
