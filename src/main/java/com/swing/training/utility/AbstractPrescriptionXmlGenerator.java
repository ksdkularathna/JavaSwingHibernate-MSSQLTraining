package com.swing.training.utility;

/**
 * Abstract interface for prescription xml file generation
 * 
 * @author SDhananjaya
 * 
 */
public interface AbstractPrescriptionXmlGenerator {

	/**
	 * Object is converted to a xml file
	 * 
	 * @param object
	 */
	void generatePrescriptionXml(Object object);
}
