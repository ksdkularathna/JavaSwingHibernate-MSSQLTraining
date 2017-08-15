package com.swing.training.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.bouncycastle.asn1.esf.ESFAttributes;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.swing.training.dtos.DrugDosageDto;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.PrescribedDrugDto;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.dtos.criteria.PrescriptionCriteriaSearchDto;
import com.swing.training.service.PrescriptionService;
import com.swing.training.utility.PhamarcyUtility;

public class PrescriptionServiceImpl extends
		GenericServiceImpl<PrescriptionDto> implements PrescriptionService {

	@Override
	public void addNewPrescription(PrescriptionDto prescriptionDto) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		EmployeDto employeDto = (EmployeDto) session.load(EmployeDto.class,
				prescriptionDto.getDoctor().getEmployeId());
		PatientDto patientDto = (PatientDto) session.load(PatientDto.class,
				prescriptionDto.getPatient().getPatientId());

		prescriptionDto.setDoctor(employeDto);
		prescriptionDto.setPatient(patientDto);
		prescriptionDto.setDoctorName(employeDto.getName());
		prescriptionDto.setPatientName(patientDto.getName());

		List<PrescribedDrugDto> prescribedDrugDtos = PhamarcyUtility
				.getInstance().convertDrugDosageDtosToPrescribedDrugDtos(
						prescriptionDto, session);
		prescriptionDto.setPrescribedDrugs(prescribedDrugDtos);
		prescriptionDto.setDate(new Date());
		session.save(prescriptionDto);
		transaction.commit();
	}

	@Override
	public void editPrescription(PrescriptionDto prescriptionDto) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		PrescriptionDto existintPrescriptionDto = (PrescriptionDto) session
				.load(PrescriptionDto.class,
						prescriptionDto.getPrescriptionId());

		EmployeDto employeDto = (EmployeDto) session.load(EmployeDto.class,
				prescriptionDto.getDoctor().getEmployeId());
		PatientDto patientDto = (PatientDto) session.load(PatientDto.class,
				prescriptionDto.getPatient().getPatientId());

		existintPrescriptionDto.setDoctor(employeDto);
		existintPrescriptionDto.setPatient(patientDto);
		existintPrescriptionDto.setDoctorName(employeDto.getName());
		existintPrescriptionDto.setPatientName(patientDto.getName());

		existintPrescriptionDto.setDrugDosageDtos(prescriptionDto
				.getDrugDosageDtos());

		existintPrescriptionDto.getPrescribedDrugs().clear();
		List<PrescribedDrugDto> prescribedDrugDtos = PhamarcyUtility
				.getInstance().convertDrugDosageDtosToPrescribedDrugDtos(
						existintPrescriptionDto, session);

		existintPrescriptionDto.setPrescribedDrugs(prescribedDrugDtos);
		session.update(existintPrescriptionDto);
		transaction.commit();
	}

	@Override
	public List<PrescriptionDto> getAllPrescriptions() {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<PrescriptionDto> prescriptionDtos = session.createCriteria(
				PrescriptionDto.class).list();
		transaction.commit();
		return prescriptionDtos;
	}

	@Override
	public PrescriptionDto getPrescriptionFromId(int prescriptionId) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		if (prescriptionId == 0) {

			return null;
		}
		PrescriptionDto prescriptionDto = (PrescriptionDto) session.get(
				PrescriptionDto.class, prescriptionId);

		if (prescriptionDto == null) {

			return null;
		}
		List<DrugDosageDto> dosageDtos = PhamarcyUtility.getInstance()
				.convertPrescribedDrugDtosToDrugDosageDtos(
						prescriptionDto.getPrescribedDrugs());
		prescriptionDto.setDrugDosageDtos(dosageDtos);
		transaction.commit();
		return prescriptionDto;
	}

	@Override
	public List<PrescriptionDto> getPrescriptionsFromCriteria(
			PrescriptionCriteriaSearchDto criteria) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		String hibernateQuery = PhamarcyUtility.getInstance()
				.getHibernateQuery(criteria);
		Query query = session.createQuery(hibernateQuery);
		query = PhamarcyUtility.getInstance().setQueryParameters(query,
				criteria);

		List<PrescriptionDto> prescriptionDtos = query.list();

		transaction.commit();
		return prescriptionDtos;
	}
}
