package se.cambio.assignment3.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.swing.training.dtos.DrugDosageDto;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.dtos.criteria.PrescriptionCriteriaSearchDto;
import com.swing.training.service.DrugService;
import com.swing.training.service.PrescriptionService;
import com.swing.training.service.impl.DrugServiceImpl;
import com.swing.training.service.impl.PrescriptionServiceImpl;

public class PrescriptionServiceImplTest {

	private static PrescriptionService prescriptionService;

	@BeforeClass
	public static void beforeMethod() {

		prescriptionService = new PrescriptionServiceImpl();
		DrugService drugService = new DrugServiceImpl();

		((DrugServiceImpl) drugService)
				.add(new DrugDto(0, "Penadol", "Tablet"));
		((DrugServiceImpl) drugService).add(new DrugDto(0, "Cetrizine",
				"Tablet"));
	}

	@Test
	public void testAddNewPrescription() {

		PrescriptionDto prescriptionDto = new PrescriptionDto();

		EmployeDto employeDto = new EmployeDto(31);
		PatientDto patientDto = new PatientDto(31);

		List<DrugDosageDto> dosageDtos = new ArrayList<>();
		DrugDosageDto dosageDto1 = new DrugDosageDto();
		dosageDto1.setDrugId(25);
		dosageDto1.setDosage("dosage 1");
		DrugDosageDto dosageDto2 = new DrugDosageDto();
		dosageDto2.setDrugId(26);
		dosageDto2.setDosage("dosage 2");
		dosageDtos.add(dosageDto1);
		dosageDtos.add(dosageDto2);

		prescriptionDto.setDoctor(employeDto);
		prescriptionDto.setPatient(patientDto);
		prescriptionDto.setDrugDosageDtos(dosageDtos);

		prescriptionService.addNewPrescription(prescriptionDto);

		List<PrescriptionDto> dtos = prescriptionService.getAllPrescriptions();

		assertTrue(dtos.contains(prescriptionDto));
	}

	@Test
	public void testGetPrescriptionsFromCriteria() {

		PrescriptionCriteriaSearchDto criteria = new PrescriptionCriteriaSearchDto();
		criteria.setFromData(new Date(2017 - 1900, 7, 1));
		criteria.setToDate(new Date());
		List<PrescriptionDto> dtos = prescriptionService
				.getPrescriptionsFromCriteria(criteria);

		assertEquals(null, dtos);
	}
}
