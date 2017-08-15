/**
 * 
 */
package com.swing.training.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.nimbusds.openid.connect.sdk.claims.Gender;
import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.PrescribedDrugDto;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.dtos.criteria.PatientSearchCriteriaDto;
import com.swing.training.service.PatientService;
import com.swing.training.utility.PhamarcyUtility;
import com.swing.training.utility.PharmacyAPrescriptionXmlGenerator;

/**
 * @author SDhananjaya
 * 
 */
public class PatientServiceImpl extends GenericServiceImpl<PatientDto>
		implements PatientService {

	private static final String HYPNE = "-";
	private static final String NULL_STRING = "";

	@Override
	public PatientDto getPatient(int patientId) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		PatientDto patientDto = (PatientDto) session.get(PatientDto.class,
				patientId);
		transaction.commit();
		return patientDto;
	}

	@Override
	public List<PatientDto> getAllPatients() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		List<PatientDto> patientDtos = session.createCriteria(PatientDto.class)
				.list();
		transaction.commit();
		return patientDtos;
	}

	@Override
	public List<PatientDto> getPatientListFromSearchCriteria(
			PatientSearchCriteriaDto criteria) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		if (criteria.getDrugId() != 0) {
			// means an advance search

			List<PrescribedDrugDto> drugDtos = new ArrayList<>();
			String hQuery = "from PrescribedDrugDto where drug.drugId=:drugId";
			hQuery += PhamarcyUtility.getInstance()
					.getHiberenateQueryFromCriteriaInAdvanceSearch(criteria);
			Query query = session.createQuery(hQuery);
			query = PhamarcyUtility.getInstance()
					.setQueryParametersInAdvanceSearch(query, criteria);
			drugDtos = query.list();
			Set<PatientDto> dtos = new HashSet<>();
			/* getting the patient list through drugDto list */
			for (PrescribedDrugDto prescribedDrugDto : drugDtos) {

				dtos.add(prescribedDrugDto.getPrescription().getPatient());
			}
			transaction.commit();
			List<PatientDto> patientDtos = new ArrayList<PatientDto>(dtos);
			return patientDtos;
		}

		String hQuery = PhamarcyUtility.getInstance()
				.getHiberenateQueryFromCriteriaInNormalSearch(criteria);
		Query query = session.createQuery(hQuery);
		query = PhamarcyUtility.getInstance().setQueryParametersInNormalSearch(
				query, criteria);

		List<PatientDto> dtos = query.list();
		transaction.commit();
		return dtos;
	}

	@Override
	public List<String> getPatientComboDropDownList() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<PatientDto> patientDtos = session.createCriteria(PatientDto.class)
				.list();
		List<String> patientComboList = new ArrayList<>();

		for (PatientDto patientDto : patientDtos) {

			patientComboList.add(patientDto.getName() + HYPNE
					+ patientDto.getPatientId());
		}
		transaction.commit();
		return patientComboList;
	}
}
