// *************************************************************************************************
//
// PROJECT : se.cambio.trainingsession3
// 
// ************************************************************************************************
//
// Copyright(C) 2016 Propozer(PRIVATE) LIMITED
// All rights reserved.
//
// THIS IS UNPUBLISHED PROPRIETARY SOURCE CODE OF
// Propozer(PRIVATE) LIMITED.
//
// This copy of the Source Code is intended for Propozer(PRIVATE) LIMITED 's internal use only
// and is
// intended for view by persons duly authorized by the management of Propozer(PRIVATE)
// LIMITED. No
// part of this file may be reproduced or distributed in any form or by any
// means without the written approval of the Management of Propozer(PRIVATE) LIMITED.
//
// *************************************************************************************************
//
// REVISIONS:
// Author : Sampath
// Date : Jul 23, 2017
// Since : version 1.0
//
package com.swing.training.listners.prescription;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import org.hibernate.type.descriptor.java.SerializableTypeDescriptor;

import com.swing.training.dtos.DrugDosageDto;
import com.swing.training.jpanels.prescription.PrescriptionAddPanel;
import com.swing.training.tablemodels.DrugDosageTableModel;

public class AddToPrescriptionActionListner implements ActionListener {

	private PrescriptionAddPanel prescriptionAddPanel;
	private DrugDosageTableModel dosageTableModel;

	public AddToPrescriptionActionListner(PrescriptionAddPanel prescriptionAddPanel,
			DrugDosageTableModel dosageTableModel) {

		this.prescriptionAddPanel = prescriptionAddPanel;
		this.dosageTableModel = dosageTableModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		List<DrugDosageDto> tableData = dosageTableModel.getDrugDosageTableData();

		DrugDosageDto newDrugDosage = prescriptionAddPanel.getEnteredDrugAndDosage();
		if (newDrugDosage != null) {

			tableData.add(newDrugDosage);
			dosageTableModel.setDrugDosageTableData(tableData);
			prescriptionAddPanel.clearDrugAndDosageFields();
		}
	}
}
