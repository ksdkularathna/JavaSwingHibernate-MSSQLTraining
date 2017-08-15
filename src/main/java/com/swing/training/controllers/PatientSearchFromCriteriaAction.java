package com.swing.training.controllers;

import java.util.List;

import com.swing.training.dataproviders.PatientDataProvider;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.criteria.PatientSearchCriteriaDto;
import com.swing.training.enums.Actions;

public class PatientSearchFromCriteriaAction extends AbstractAction {

	public PatientSearchFromCriteriaAction(MainController controller,
			EventData data) {
		super(controller, data);
	}

	@Override
	void executeOpeartion() {

		PatientSearchCriteriaDto criteriaDto = (PatientSearchCriteriaDto) controller
				.getPatientMainView().getData(
						Actions.PATIENT_SEARCH_FROM_CRITERIA);

		if (criteriaDto != null) {
			List<PatientDto> dtos = PatientDataProvider.getInstance()
					.getPatientListFromSearchCriteria(criteriaDto);

			PatientDto dto=new PatientDto();
//			dto.getPrescriptionDtos().get(1).getPrescribedDrugs().get(0).getDrug().getDrugId();
			/* update the patient table */
			controller.getPatientMainView().refreshData(
					new EventData(Actions.PATIENT_LOAD_TABLE_DATA, dtos));
		}
	}
}
