package com.swing.training.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.hibernate.Query;
import org.hibernate.Session;

import com.swing.training.dtos.DrugDosageDto;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.PrescribedDrugDto;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.dtos.criteria.PatientSearchCriteriaDto;
import com.swing.training.dtos.criteria.PrescriptionCriteriaSearchDto;
import com.swing.training.service.DrugService;
import com.swing.training.service.EmployeService;
import com.swing.training.service.PatientService;
import com.swing.training.service.PrescriptionService;
import com.swing.training.service.impl.DrugServiceImpl;
import com.swing.training.service.impl.EmployeServiceImpl;
import com.swing.training.service.impl.PatientServiceImpl;
import com.swing.training.service.impl.PrescriptionServiceImpl;

/**
 * Acts as a utility class for the application
 * 
 * @author SDhananjaya
 * 
 */
public class PhamarcyUtility {

	private static PhamarcyUtility phamarcyUtility;

	private PrescriptionService prescriptionService = new PrescriptionServiceImpl();
	private EmployeService employeService = new EmployeServiceImpl();
	private DrugService drugService = new DrugServiceImpl();
	private PatientService patientService = new PatientServiceImpl();
	private File propertFile;
	private static final String NULL_STRING = "";

	private PhamarcyUtility() {

		setupPropertyFiles();
	}

	public static PhamarcyUtility getInstance() {

		if (phamarcyUtility == null) {

			phamarcyUtility = new PhamarcyUtility();
		}
		return phamarcyUtility;
	}

	/**
	 * returns drug combox list
	 * 
	 * @return
	 */
	public String[] getDrugComboBoxList() {

		return drugService.getDrugComboxList();
	}

	/**
	 * returns drug employee list
	 * 
	 * @return
	 */
	public String[] getEmployeeComboBoxList() {

		return employeService.getEmployeComboList();
	}

	/**
	 * returns drug patient list
	 * 
	 * @return
	 */
	public List<String> getPatientComboBoxList() {

		return patientService.getPatientComboDropDownList();
	}

	/**
	 * setup the property file for the application
	 */
	public void setupPropertyFiles() {

		ClassLoader classLoader = getClass().getClassLoader();
		propertFile = new File(classLoader.getResource("app.properties")
				.getFile());
	}

	/**
	 * reads the property file and returns the properties
	 * 
	 * @return
	 */
	public Properties getProperties() {

		try {

			FileReader reader = new FileReader(propertFile);
			Properties properties = new Properties();
			properties.load(reader);
			reader.close();
			return properties;
		} catch (FileNotFoundException ex) {

			return null;
		} catch (IOException ex) {

			return null;
		}
	}

	/**
	 * utility method for getting the hibernate query for criteria search in
	 * patient normal search
	 * 
	 * @param criteria
	 * @return
	 */
	public String getHiberenateQueryFromCriteriaInNormalSearch(
			PatientSearchCriteriaDto criteria) {

		String hQuery = "SELECT p From PatientDto p";

		if (criteria.getName() != null || criteria.getGender() != null
				|| criteria.getDrugId() != 0) {

			hQuery += " where p.patientId > 0 ";
		}
		if (!criteria.getName().equals(NULL_STRING)) {

			hQuery += " AND p.name LIKE CONCAT('%',:name,'%')";
		}
		if (!criteria.getGender().equals(NULL_STRING)) {

			hQuery += " AND p.gender=:gender";
		}
		return hQuery;
	}

	/**
	 * utility method for setting the parameters in the query in patient normal
	 * search
	 * 
	 * @param query
	 * @param criteria
	 * @return
	 */
	public Query setQueryParametersInNormalSearch(Query query,
			PatientSearchCriteriaDto criteria) {

		if (!criteria.getName().equals(NULL_STRING)) {
			query.setParameter("name", criteria.getName());
		}
		if (!criteria.getGender().equals(NULL_STRING)) {
			query.setParameter("gender", criteria.getGender());
		}
		return query;
	}

	/**
	 * utility method for generating hibernate query in patient advance search
	 * 
	 * @param criteria
	 * @return
	 */
	public String getHiberenateQueryFromCriteriaInAdvanceSearch(
			PatientSearchCriteriaDto criteria) {

		String hQuery = NULL_STRING;

		if (!criteria.getName().equals(NULL_STRING)) {

			hQuery += " and prescription.patient.name LIKE CONCAT('%',:name,'%')";
		}
		if (criteria.getGender() != null) {

			hQuery += " and prescription.patient.gender=:gender";
		}
		return hQuery;
	}

	/**
	 * set query parameters in patient advance search
	 * 
	 * @param query
	 * @param criteria
	 * @return
	 */
	public Query setQueryParametersInAdvanceSearch(Query query,
			PatientSearchCriteriaDto criteria) {

		/* setting the default parameter */
		query.setParameter("drugId", criteria.getDrugId());
		if (!criteria.getName().equals(NULL_STRING)) {

			query.setParameter("name", criteria.getName());
		}
		if (criteria.getGender() != null) {

			query.setParameter("gender", criteria.getGender());
		}
		return query;
	}

	/**
	 * utility method for creating the hibernate query from prescription search
	 * criteria
	 * 
	 * @param criteria
	 * @return
	 */
	public String getHibernateQuery(PrescriptionCriteriaSearchDto criteria) {

		String hQuery = "FROM PrescriptionDto";
		if (criteria.getPatientId() != 0 || criteria.getToDate() != null
				|| criteria.getFromData() != null) {

			hQuery += " where prescriptionId>0";
		}
		if (criteria.getPatientId() != 0) {

			hQuery += " AND patient.patientId=:patientId";
		}

		if (criteria.getFromData() != null) {

			hQuery += " AND year(date) >= year(:fromDate) and month(date) >= month(:fromDate) and day(date) >= day(:fromDate) ";
		}
		if (criteria.getToDate() != null) {

			hQuery += " AND year(date) <= year(:toDate) and month(date) <= month(:toDate) and day(date) <= day(:toDate) ";
		}
		return hQuery;
	}

	/**
	 * Utility method for setting query parameters in prescription search
	 * 
	 * @param query
	 * @param criteria
	 * @return
	 */
	public Query setQueryParameters(Query query,
			PrescriptionCriteriaSearchDto criteria) {

		if (criteria.getPatientId() != 0) {

			query.setParameter("patientId", criteria.getPatientId());
		}
		if (criteria.getFromData() != null) {

			query.setParameter("fromDate", criteria.getFromData());
		}
		if (criteria.getToDate() != null) {

			query.setParameter("toDate", criteria.getToDate());
		}
		return query;
	}

	/**
	 * Utility method for converting DrugDosageDtos to PrescribedDrugDtos in a
	 * PrescriptionDto
	 * 
	 * @param dto
	 * @param session
	 * @return
	 */
	public List<PrescribedDrugDto> convertDrugDosageDtosToPrescribedDrugDtos(
			PrescriptionDto dto, Session session) {

		if (dto == null || session == null) {

			return null;
		}
		List<DrugDosageDto> dosageDtos = dto.getDrugDosageDtos();
		List<PrescribedDrugDto> prescribedDrugDtos = new ArrayList<>();
		for (DrugDosageDto drugDosageDto : dosageDtos) {

			PrescribedDrugDto prescribedDrugDto = new PrescribedDrugDto();
			prescribedDrugDto.setDosage(drugDosageDto.getDosage());
			prescribedDrugDto.setDrug((DrugDto) session.load(DrugDto.class,
					drugDosageDto.getId()));
			prescribedDrugDto.setPrescription(dto);
			prescribedDrugDtos.add(prescribedDrugDto);
		}
		return prescribedDrugDtos;
	}

	/**
	 * Utility method for converting PrescribedDrugDto list to a DrugDosageDto
	 * list
	 * 
	 * @param prescribedDrugDtos
	 * @return
	 */
	public List<DrugDosageDto> convertPrescribedDrugDtosToDrugDosageDtos(
			List<PrescribedDrugDto> prescribedDrugDtos) {

		if (prescribedDrugDtos == null) {

			return null;
		}
		List<DrugDosageDto> dosageDtos = new ArrayList<>();
		for (PrescribedDrugDto prescribedDrugDto : prescribedDrugDtos) {

			DrugDosageDto dosageDto = new DrugDosageDto();
			dosageDto.setDrugId(prescribedDrugDto.getDrug().getDrugId());
			dosageDto.setDosage(prescribedDrugDto.getDosage());
			dosageDto.setName(prescribedDrugDto.getDrug().getName());
			dosageDtos.add(dosageDto);
		}
		return dosageDtos;
	}
}
