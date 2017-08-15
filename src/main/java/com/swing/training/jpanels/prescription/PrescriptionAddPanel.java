package com.swing.training.jpanels.prescription;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.swing.training.controllers.AbstractController;
import com.swing.training.dtos.DrugDosageDto;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.enums.Actions;
import com.swing.training.listners.prescription.AddToPrescriptionActionListner;
import com.swing.training.listners.prescription.PrescriptionClearBtnActionListner;
import com.swing.training.listners.prescription.RemoveFromPrescriptionActionListner;
import com.swing.training.tablemodels.DrugDosageTableModel;
import com.swing.training.utility.PhamarcyUtility;

public class PrescriptionAddPanel extends JPanel implements Printable {

	private static final String HYPNE = "-";
	private static final String NULL_STRING = "";
	private static final String DRUG_NOT_NULL_MSG = "Please select the drug";
	private static final String PATIENT_NOT_NULL_MSG = "Please select the patient";
	private static final String DOSAGE_NOT_NULL_MSG = "Please enter the dosage";
	private static final String DOCTOR_NOT_NULL_MSG = "Please select the doctor";
	private static final String DRUG_DOSAGE_NOT_NULL_MSG = "Please add drugs to the prescription";
	private static final String PRESCRIPTION_ADDED = "Prescription added";
	private static final String ASK_TO_PRINT_MSG = "Do you want to print the information?";
	private static final String CONFIRM = "Confirm";

	private JLabel patientLabel, drugLabel, dosageLabel, drugAddedLabel,
			doctorLabel;
	private JTextField dosageText, prescriptionIdText, patientName;
	private JButton addToPrescriptionBtn, addNewBtn, clearBtn, removeBtn,
			saveBtn, removeFromPrescriptionBtn;
	private JComboBox patientCombo, drugCombo, doctorCombo;
	private JTable drugDosageTable;
	private GridBagConstraints gbConstraints = new GridBagConstraints();
	private AbstractController controller;
	private DrugDosageTableModel drugDosageTableModel;

	public PrescriptionAddPanel(AbstractController controller) {

		this.controller = controller;
		setLayout(new GridBagLayout());
		setPanelComponents();
		setActionListners();
	}

	private void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);

		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		gbConstraints.gridwidth = 1;
		patientLabel = new JLabel("Patient");
		add(patientLabel, gbConstraints);

		gbConstraints.gridx = 0;
		gbConstraints.gridy = 1;
		gbConstraints.gridwidth = 1;
		drugLabel = new JLabel("Drug");
		add(drugLabel, gbConstraints);

		gbConstraints.gridx = 1;
		gbConstraints.gridy = 0;
		gbConstraints.gridwidth = 2;
		patientName = new JTextField(10);
		patientCombo = new FilterComboBox(getPatientComboDropDownList(),
				patientName);
		patientCombo.setSelectedIndex(-1);
		add(patientCombo, gbConstraints);

		gbConstraints.gridx = 3;
		gbConstraints.gridy = 0;
		gbConstraints.gridwidth = 2;
		patientName.setText(NULL_STRING);
		patientName.setEditable(Boolean.FALSE);
		add(patientName, gbConstraints);

		gbConstraints.gridx = 1;
		gbConstraints.gridy = 1;
		gbConstraints.gridwidth = 2;
		drugCombo = new JComboBox(getDrugComboDropDownList());
		drugCombo.setSelectedIndex(-1);
		drugCombo.setSize(100, 5);
		add(drugCombo, gbConstraints);

		dosageLabel = new JLabel("Dosage");
		gbConstraints.gridx = 3;
		gbConstraints.gridy = 1;
		gbConstraints.gridwidth = 1;
		add(dosageLabel, gbConstraints);

		dosageText = new JTextField(15);
		gbConstraints.gridx = 4;
		gbConstraints.gridy = 1;
		gbConstraints.gridwidth = 1;
		add(dosageText, gbConstraints);

		addToPrescriptionBtn = new JButton("Add to prescription");
		gbConstraints.gridx = 5;
		gbConstraints.gridy = 1;
		gbConstraints.gridwidth = 1;
		add(addToPrescriptionBtn, gbConstraints);

		removeFromPrescriptionBtn = new JButton("Remove");
		gbConstraints.gridx = 5;
		gbConstraints.gridy = 2;
		gbConstraints.gridwidth = 1;
		add(removeFromPrescriptionBtn, gbConstraints);

		drugAddedLabel = new JLabel("Drug Added");
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 3;
		gbConstraints.gridwidth = 3;
		add(drugAddedLabel, gbConstraints);

		drugDosageTable = new JTable();
		drugDosageTableModel = new DrugDosageTableModel();
		drugDosageTable.setModel(drugDosageTableModel);
		drugDosageTable.setPreferredScrollableViewportSize(new Dimension(518,
				70));
		JScrollPane scrollPane = new JScrollPane(drugDosageTable);
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 4;
		gbConstraints.gridwidth = 6;
		add(scrollPane, gbConstraints);

		doctorLabel = new JLabel("Doctor");
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 1;
		add(doctorLabel, gbConstraints);

		gbConstraints.gridx = 1;
		gbConstraints.gridy = 5;
		gbConstraints.gridwidth = 2;
		doctorCombo = new JComboBox(getEmployeComboDropDownList());
		doctorCombo.setSelectedIndex(-1);
		add(doctorCombo, gbConstraints);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		clearBtn = new JButton("Clear");
		GridBagConstraints gbConstraintsClearBtn = new GridBagConstraints();
		gbConstraintsClearBtn.gridx = 0;
		gbConstraintsClearBtn.gridy = 0;
		gbConstraintsClearBtn.weightx = 1;
		gbConstraintsClearBtn.weighty = 1;
		gbConstraintsClearBtn.insets = new Insets(5, 5, 5, 5);
		gbConstraintsClearBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsClearBtn.fill = GridBagConstraints.NONE;
		buttonPanel.add(clearBtn, gbConstraintsClearBtn);

		removeBtn = new JButton("Remove");
		GridBagConstraints gbConstraintsRemoveBtn = new GridBagConstraints();
		gbConstraintsRemoveBtn.gridx = 1;
		gbConstraintsRemoveBtn.gridy = 0;
		gbConstraintsRemoveBtn.weightx = 0;
		gbConstraintsRemoveBtn.weighty = 1;
		gbConstraintsRemoveBtn.insets = new Insets(5, 5, 5, 5);
		gbConstraintsRemoveBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsRemoveBtn.fill = GridBagConstraints.NONE;
		removeBtn.setEnabled(Boolean.FALSE);
		buttonPanel.add(removeBtn, gbConstraintsRemoveBtn);

		saveBtn = new JButton("Save");
		GridBagConstraints gbConstraintsSaveBtn = new GridBagConstraints();
		gbConstraintsSaveBtn.gridx = 2;
		gbConstraintsSaveBtn.gridy = 0;
		gbConstraintsSaveBtn.weightx = 0;
		gbConstraintsSaveBtn.weighty = 1;
		gbConstraintsSaveBtn.insets = new Insets(5, 5, 5, 5);
		gbConstraintsSaveBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsSaveBtn.fill = GridBagConstraints.NONE;
		// buttonPanel.add(saveBtn, gbConstraintsSaveBtn);

		addNewBtn = new JButton("Add new");
		GridBagConstraints gbConstraintsAddNewBtn = new GridBagConstraints();
		gbConstraintsAddNewBtn.insets = new Insets(5, 5, 5, 2);
		gbConstraintsAddNewBtn.fill = GridBagConstraints.NONE;
		gbConstraintsAddNewBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsAddNewBtn.gridx = 3;
		gbConstraintsAddNewBtn.gridy = 0;
		gbConstraintsAddNewBtn.weightx = 0;
		gbConstraintsAddNewBtn.weighty = 1;
		buttonPanel.add(addNewBtn, gbConstraintsAddNewBtn);

		gbConstraints.gridx = 4;
		gbConstraints.gridy = 6;
		add(buttonPanel, gbConstraints);

		prescriptionIdText = new JTextField(10);
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 7;
		prescriptionIdText.setVisible(Boolean.FALSE);
		add(prescriptionIdText, gbConstraints);
	}

	public DrugDosageDto getEnteredDrugAndDosage() {

		if (drugCombo.getSelectedIndex() < 0) {

			JOptionPane.showMessageDialog(null, DRUG_NOT_NULL_MSG);
			return null;
		}
		if (dosageText.getText().trim().equals(NULL_STRING)) {

			JOptionPane.showMessageDialog(null, DOSAGE_NOT_NULL_MSG);
			return null;
		}
		DrugDosageDto dosageDto = new DrugDosageDto();
		String drugText = drugCombo.getSelectedItem().toString().trim();
		dosageDto.setDrugId(Integer.parseInt(drugText.substring(drugText
				.indexOf(HYPNE) + 1)));
		dosageDto.setName(drugText.substring(0, drugText.indexOf(HYPNE)));
		dosageDto.setDosage(dosageText.getText().trim());
		return dosageDto;
	}

	public PrescriptionDto getPrescriptionData() {

		PrescriptionDto prescriptionDto = new PrescriptionDto();
		if (patientName.equals(NULL_STRING)) {

			JOptionPane.showMessageDialog(null, PATIENT_NOT_NULL_MSG);
			return null;
		} else {

			String patientComboText = patientName.getText();
			int patientid = Integer.parseInt(patientComboText
					.substring(patientComboText.indexOf(HYPNE) + 1));
			prescriptionDto.setPatient(new PatientDto(patientid));
		}

		if (doctorCombo.getSelectedIndex() < 0) {

			JOptionPane.showMessageDialog(null, DOCTOR_NOT_NULL_MSG);
			return null;
		} else {

			String doctorComboText = doctorCombo.getSelectedItem().toString();
			int doctorId = Integer.parseInt(doctorComboText
					.substring(doctorComboText.indexOf(HYPNE) + 1));
			prescriptionDto.setDoctor(new EmployeDto(doctorId));
		}

		/* setting the drug details */

		List<DrugDosageDto> dosageDtos = ((DrugDosageTableModel) getDrugDosageTable()
				.getModel()).getDrugDosageTableData();
		if (dosageDtos.size() == 0) {

			JOptionPane.showMessageDialog(null, DRUG_DOSAGE_NOT_NULL_MSG);
			return null;
		}
		prescriptionDto.setDrugDosageDtos(dosageDtos);
		if (!prescriptionIdText.getText().equals(NULL_STRING)) {

			prescriptionDto.setPrescriptionId(Integer
					.parseInt(prescriptionIdText.getText()));
		}
		return prescriptionDto;
	}

	/**
	 * set prescription details into fields
	 * 
	 * @param dto
	 */
	public void setPrescriptionData(PrescriptionDto dto) {

		patientCombo.setSelectedItem(dto.getPatient().getName() + HYPNE
				+ dto.getPatient().getPatientId());
		doctorCombo.setSelectedItem(dto.getDoctor().getName() + HYPNE
				+ dto.getDoctor().getEmployeId());
		drugDosageTableModel.setDrugDosageTableData(dto.getDrugDosageDtos());
		prescriptionIdText.setText(Integer.toString(dto.getPrescriptionId()));
		removeBtn.setEnabled(Boolean.TRUE);
		addNewBtn.setEnabled(Boolean.TRUE);
	}

	/**
	 * returns patient combo drop down list
	 * 
	 * @return
	 */
	public List<String> getPatientComboDropDownList() {

		return PhamarcyUtility.getInstance().getPatientComboBoxList();
	}

	/**
	 * returns employee combo drop down list
	 * 
	 * @return
	 */
	public String[] getEmployeComboDropDownList() {

		return PhamarcyUtility.getInstance().getEmployeeComboBoxList();
	}

	/**
	 * returns drug combo drop down list
	 * 
	 * @return
	 */
	public String[] getDrugComboDropDownList() {

		return PhamarcyUtility.getInstance().getDrugComboBoxList();
	}

	/**
	 * adds all action listners to the panel
	 */
	private void setActionListners() {

		addToPrescriptionBtn
				.addActionListener(new AddToPrescriptionActionListner(this,
						(DrugDosageTableModel) drugDosageTable.getModel()));
		removeFromPrescriptionBtn
				.addActionListener(new RemoveFromPrescriptionActionListner(
						this, (DrugDosageTableModel) drugDosageTable.getModel()));
		clearBtn.addActionListener(new PrescriptionClearBtnActionListner(this));

		addNewBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				prescriptionIdText.setText(NULL_STRING);
				controller.executeOpeartion(new EventData(
						Actions.PRESCRIPTION_SAVE_ACTION, null));
			}
		});
		removeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!prescriptionIdText.getText().equals(NULL_STRING)) {

					int prescriptionId = Integer.parseInt(prescriptionIdText
							.getText());
					controller.executeOpeartion(new EventData(
							Actions.PRESCRIPTION_DELETE, prescriptionId));
				}
			}
		});
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!prescriptionIdText.getText().equals(NULL_STRING)) {

					int prescriptionId = Integer.parseInt(prescriptionIdText
							.getText());
					controller.executeOpeartion(new EventData(
							Actions.PRESCRIPTION_SAVE_ACTION, null));
				}
			}
		});
	}

	/**
	 * Clear all the inputs
	 */
	public void clearAllInputFields() {

		patientCombo.setSelectedIndex(-1);
		patientName.setText(NULL_STRING);
		drugCombo.setSelectedIndex(-1);
		dosageText.setText(NULL_STRING);
		((DrugDosageTableModel) drugDosageTable.getModel())
				.getDrugDosageTableData().clear();
		((DrugDosageTableModel) drugDosageTable.getModel())
				.fireTableDataChanged();
		doctorCombo.setSelectedIndex(-1);
		removeBtn.setEnabled(Boolean.FALSE);
	}

	/**
	 * clear only drug and dosage fields
	 */
	public void clearDrugAndDosageFields() {

		drugCombo.setSelectedIndex(-1);
		dosageText.setText(NULL_STRING);
	}

	/**
	 * print the JPanel
	 */
	private void printPanelInfo() {

		PrinterJob printJob = PrinterJob.getPrinterJob();
		printJob.setPrintable(this);
		if (printJob.printDialog()) {
			try {
				printJob.print();
			} catch (Exception ex) {
				throw new RuntimeException(ex);
			}
		}
	}

	/**
	 * printing method overridden
	 */
	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int index)
			throws PrinterException {

		Graphics2D g2 = (Graphics2D) graphics;
		if (index >= 1) {
			return Printable.NO_SUCH_PAGE;
		} else {

			this.printAll(g2);
			return Printable.PAGE_EXISTS;
		}
	}

	/**
	 * prompting window for printing
	 * 
	 * @return
	 */
	public void askToPrintOrNot() {

		int response = JOptionPane.showConfirmDialog(null, ASK_TO_PRINT_MSG,
				CONFIRM, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (response == JOptionPane.YES_OPTION) {

			printPanelInfo();
		}
	}

	public JTable getDrugDosageTable() {
		return drugDosageTable;
	}

	public void setDrugDosageTable(JTable drugDosageTable) {
		this.drugDosageTable = drugDosageTable;
	}
}
