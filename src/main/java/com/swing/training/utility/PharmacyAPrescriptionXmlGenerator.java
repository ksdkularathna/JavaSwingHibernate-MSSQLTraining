package com.swing.training.utility;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import com.swing.training.dtos.PrescriptionDto;

/**
 * class acts as prescription xml generator for pharmacy A
 * @author SDhananjaya
 *
 */
public class PharmacyAPrescriptionXmlGenerator implements
		AbstractPrescriptionXmlGenerator {

	@Override
	public void generatePrescriptionXml(Object object) {

		PrescriptionDto dto = (PrescriptionDto) object;
		try {

			File file = new File("E:\\file.xml");
			JAXBContext jaxbContext = JAXBContext
					.newInstance(PrescriptionDto.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			JAXBElement<PrescriptionDto> rootElement = new JAXBElement<PrescriptionDto>(new QName("dto"), PrescriptionDto.class, dto);
			jaxbMarshaller.marshal(dto, file);
			jaxbMarshaller.marshal(dto, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
