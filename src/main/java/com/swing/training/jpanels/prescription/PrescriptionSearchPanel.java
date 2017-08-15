package com.swing.training.jpanels.prescription;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.swing.training.controllers.AbstractController;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.criteria.PrescriptionCriteriaSearchDto;
import com.swing.training.enums.Actions;
import com.swing.training.utility.DateLabelFormatter;
import com.swing.training.view.ViewInterface;

public class PrescriptionSearchPanel extends JPanel implements ViewInterface {

	private static final String NULL_STRING = "";
	private static final String PATIENT_ID_INCORRECT = "Please check the patient ID";
	private JLabel patientNameLabel, prescriptionTimeLabel, fromLabel, toLabel;
	private JTextField patientNameText;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl fromDatePicker;
	private JDatePickerImpl toDatePicker;
	private JButton searchBtn;
	private AbstractController controller;

	private GridBagConstraints gbConstraints = new GridBagConstraints();

	public PrescriptionSearchPanel(AbstractController controller) {

		this.controller = controller;
		setLayout(new GridBagLayout());
		setPanelComponents();
		addActionListeners();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);

		patientNameLabel = new JLabel("Patient ID");
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		add(patientNameLabel, gbConstraints);

		patientNameText = new JTextField(8);
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 0;
		add(patientNameText, gbConstraints);

		fromLabel = new JLabel("From");
		gbConstraints.gridx = 2;
		gbConstraints.gridy = 0;
		add(fromLabel, gbConstraints);

		datePanel = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
		fromDatePicker = new JDatePickerImpl(datePanel,
				new DateLabelFormatter());
		((UtilDateModel) fromDatePicker.getModel()).setSelected(Boolean.FALSE);
		gbConstraints.gridx = 3;
		gbConstraints.gridy = 0;
		add(fromDatePicker, gbConstraints);

		toLabel = new JLabel("To");
		gbConstraints.gridx = 2;
		gbConstraints.gridy = 1;
		add(toLabel, gbConstraints);

		datePanel = new JDatePanelImpl(new UtilDateModel(), getDateProperties());
		toDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		((UtilDateModel) toDatePicker.getModel()).setSelected(Boolean.FALSE);
		gbConstraints.gridx = 3;
		gbConstraints.gridy = 1;
		add(toDatePicker, gbConstraints);

		searchBtn = new JButton("Search");
		gbConstraints.gridx = 4;
		gbConstraints.gridy = 1;
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 1;
		gbConstraints.insets = new Insets(5, 5, 5, 13);
		gbConstraints.anchor = GridBagConstraints.NORTHEAST;
		add(searchBtn, gbConstraints);
	}

	@Override
	public void refreshData(EventData eventData) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getData(Actions action) {
		// TODO Auto-generated method stub
		return null;
	}

	public PrescriptionCriteriaSearchDto getPrescriptionSearchCriteria() {

		PrescriptionCriteriaSearchDto criteria = new PrescriptionCriteriaSearchDto();
		String patientId = patientNameText.getText().trim();
		if (patientId.equals(NULL_STRING)) {

			criteria.setPatientId(0);
		} else if (!patientId.matches("[0-9]+")) {

			JOptionPane.showMessageDialog(null, PATIENT_ID_INCORRECT);
			return null;
		} else {

			criteria.setPatientId(Integer.parseInt(patientId));
		}
		Date fromDate = ((UtilDateModel) fromDatePicker.getModel()).getValue();
		Date toDate = ((UtilDateModel) toDatePicker.getModel()).getValue();
		criteria.setFromData(fromDate);
		criteria.setToDate(toDate);
		return criteria;
	}

	private void addActionListeners() {

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				controller.executeOpeartion(new EventData(
						Actions.PRESCRIPTION_SEARCH_FROM_CRITERIA,null));
			}
		});
	}

	private Properties getDateProperties() {

		Properties properties = new Properties();
		properties.put("text.today", "Today");
		properties.put("text.month", "Month");
		properties.put("text.year", "Year");
		return properties;
	}
}
