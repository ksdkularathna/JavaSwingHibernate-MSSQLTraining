package com.swing.training.jpanels.employe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.Document;

import com.swing.training.controllers.AbstractController;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.PatientDto;
import com.swing.training.enums.Actions;
import com.swing.training.listeners.common.ButtonEnableListner;

public class EmployeRegestrationPanel extends JPanel {

	private static final String NULL_STRING = "";
	private static final String NAME_NOT_NULL_MSG = "Please enter the name";
	private static final String BDAY_NOT_NULL_MSG = "Please enter the birthday";
	private static final String BDAY_IS_WRONG_MSG = "Please check your birthday";
	private static final String ADDRESS_NOT_NULL_MSG = "Please enter the address";
	private static final String GENDER_NOT_NULL_MSG = "Please select the gender";
	private static final String MALE = "Male";
	private static final int MIN_ADDRESS_CHARACTOR_COUNT = 10;

	private JLabel nameLabel, birthDayLabel, genderLabel, addressLabel,
			nicLabel;
	private JTextField nameText, employeIdText, nicText;
	private JTextArea addressText;
	private JButton clearBtn, saveBtn, removeBtn, addNewBtn;
	private EmployeTablePanel employeTablePanel;
	private AbstractController controller;

	private GridBagConstraints gbConstraints = new GridBagConstraints();

	public EmployeRegestrationPanel(AbstractController controller) {

		this.controller = controller;
		setLayout(new GridBagLayout());
		setPanelComponents();
		disableSaveBtn();
		addActionListners();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);

		setNameLabel(new JLabel("Name *"));
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		add(getNameLabel(), gbConstraints);

		setAddressLabel(new JLabel("Address "));
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 1;
		add(getAddressLabel(), gbConstraints);

		setNameText(new JTextField(45));
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 0;
		gbConstraints.gridwidth = 2;
		add(getNameText(), gbConstraints);

		setAddressText(new JTextArea(5, 20));
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 1;
		gbConstraints.gridwidth = 2;
		add(getAddressText(), gbConstraints);

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		setClearBtn(new JButton("Clear"));
		GridBagConstraints gbConstraintsCancelBtn = new GridBagConstraints();
		gbConstraintsCancelBtn.gridx = 0;
		gbConstraintsCancelBtn.gridy = 0;
		gbConstraintsCancelBtn.weightx = 1;
		gbConstraintsCancelBtn.weighty = 1;
		gbConstraintsCancelBtn.insets = new Insets(5, 5, 5, 5);
		gbConstraintsCancelBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsCancelBtn.fill = GridBagConstraints.NONE;
		buttonPanel.add(getClearBtn(), gbConstraintsCancelBtn);

		removeBtn = new JButton("Remove");
		GridBagConstraints gbConstraintsRemoveBtn = new GridBagConstraints();
		gbConstraintsRemoveBtn.insets = new Insets(5, 5, 5, 0);
		gbConstraintsRemoveBtn.fill = GridBagConstraints.NONE;
		gbConstraintsRemoveBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsRemoveBtn.gridx = 1;
		gbConstraintsRemoveBtn.gridy = 0;
		gbConstraintsRemoveBtn.weightx = 0;
		gbConstraintsRemoveBtn.weighty = 1;
		removeBtn.setEnabled(Boolean.FALSE);
		buttonPanel.add(removeBtn, gbConstraintsRemoveBtn);

		setSaveBtn(new JButton("Save"));
		GridBagConstraints gbConstraintsSaveBtn = new GridBagConstraints();
		gbConstraintsSaveBtn.insets = new Insets(5, 5, 5, 0);
		gbConstraintsSaveBtn.fill = GridBagConstraints.NONE;
		gbConstraintsSaveBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsSaveBtn.gridx = 2;
		gbConstraintsSaveBtn.gridy = 0;
		gbConstraintsSaveBtn.weightx = 0;
		gbConstraintsSaveBtn.weighty = 1;
		buttonPanel.add(getSaveBtn(), gbConstraintsSaveBtn);

		addNewBtn = new JButton("Add new");
		GridBagConstraints gbConstraintsAddNewBtn = new GridBagConstraints();
		gbConstraintsAddNewBtn.insets = new Insets(5, 5, 5, 0);
		gbConstraintsAddNewBtn.fill = GridBagConstraints.NONE;
		gbConstraintsAddNewBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsAddNewBtn.gridx = 3;
		gbConstraintsAddNewBtn.gridy = 0;
		gbConstraintsAddNewBtn.weightx = 0;
		gbConstraintsAddNewBtn.weighty = 1;
		buttonPanel.add(addNewBtn, gbConstraintsAddNewBtn);

		gbConstraints.anchor = GridBagConstraints.EAST;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 6;
		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.gridwidth = 3;
		gbConstraints.gridheight = 1;

		add(buttonPanel, gbConstraints);

		employeIdText = new JTextField(5);
		gbConstraints.gridy++;
		employeIdText.setVisible(Boolean.FALSE);
		add(employeIdText, gbConstraints);
	}

	public void disableSaveBtn() {

		final ButtonModel saveBtnModel = getSaveBtn().getModel();
		Document nameDocument = getNameText().getDocument();
		Document addressDocument = getAddressText().getDocument();

		ButtonEnableListner buttonEnablement = new ButtonEnableListner(
				saveBtnModel);
		buttonEnablement.addDocument(nameDocument);
		buttonEnablement.addDocument(addressDocument);
	}

	/**
	 * returns validated patient details
	 * 
	 * @return
	 */
	public EmployeDto validateAndGetEmployeeDetails() {

		EmployeDto employeDto = new EmployeDto();

		if (getNameText().getText().trim().equals(NULL_STRING)) {

			JOptionPane.showMessageDialog(null, NAME_NOT_NULL_MSG);
			getNameText().requestFocus();
			return null;
		} else {

			employeDto.setName(getNameText().getText().trim());
		}

		String address = getAddressText().getText().trim();
		if (address.equals(NULL_STRING)) {

			JOptionPane.showMessageDialog(null, ADDRESS_NOT_NULL_MSG);
			getAddressText().requestFocus();
			return null;
		} else if (address.length() < MIN_ADDRESS_CHARACTOR_COUNT) {

			int dialogButton = JOptionPane.YES_NO_OPTION;
			int dialogResult = JOptionPane
					.showConfirmDialog(
							null,
							"Address is less than 10 charactors. Would you like continue?",
							"Warning", dialogButton);
			if (dialogResult == JOptionPane.YES_OPTION) {

				employeDto.setAddress(address);
			} else {

				getAddressText().requestFocus();
				return null;
			}
		} else {

			employeDto.setAddress(address);
		}

		String patientId = employeIdText.getText();
		if (!patientId.equals(NULL_STRING)) {

			employeDto.setEmployeId(Integer.parseInt(employeIdText.getText()));
		}

		return employeDto;
	}

	/**
	 * add action listeners to the panel
	 */
	public void addActionListners() {

		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				controller.executeOpeartion(new EventData(
						Actions.EMPLOYE_SAVE_ACTION, null));
			}
		});
		clearBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				clearInputFields();
			}
		});
		removeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int employeId = Integer.parseInt(employeIdText.getText());
				controller.executeOpeartion(new EventData(
						Actions.EMPLOYE_DELETE, employeId));
			}
		});
		addNewBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				employeIdText.setText(NULL_STRING);
				controller.executeOpeartion(new EventData(
						Actions.EMPLOYE_SAVE_ACTION, null));
			}
		});
	}

	/**
	 * set the input PatientDto details to input fields
	 * 
	 * @param dto
	 */
	public void setEmployeDtoToUi(EmployeDto dto) {

		getNameText().setText(dto.getName());
		getAddressText().setText(dto.getAddress());
		employeIdText.setText(Integer.toString(dto.getEmployeId()));
		removeBtn.setEnabled(Boolean.TRUE);
	}

	/**
	 * clear the input fields
	 */
	public void clearInputFields() {

		getNameText().setText(NULL_STRING);
		getAddressText().setText(NULL_STRING);
		employeIdText.setText(NULL_STRING);
		removeBtn.setEnabled(Boolean.FALSE);
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JLabel getBirthDayLabel() {
		return birthDayLabel;
	}

	public void setBirthDayLabel(JLabel birthDayLabel) {
		this.birthDayLabel = birthDayLabel;
	}

	public JLabel getGenderLabel() {
		return genderLabel;
	}

	public void setGenderLabel(JLabel genderLabel) {
		this.genderLabel = genderLabel;
	}

	public JLabel getAddressLabel() {
		return addressLabel;
	}

	public void setAddressLabel(JLabel addressLabel) {
		this.addressLabel = addressLabel;
	}

	public JTextField getNameText() {
		return nameText;
	}

	public void setNameText(JTextField nameText) {
		this.nameText = nameText;
	}

	public JTextArea getAddressText() {
		return addressText;
	}

	public void setAddressText(JTextArea addressText) {
		this.addressText = addressText;
	}

	public JButton getClearBtn() {
		return clearBtn;
	}

	public void setClearBtn(JButton clearBtn) {
		this.clearBtn = clearBtn;
	}

	public JButton getSaveBtn() {
		return saveBtn;
	}

	public void setSaveBtn(JButton saveBtn) {
		this.saveBtn = saveBtn;
	}

	// public TablePanel getTablePanel() {
	// return tablePanel;
	// }
	//
	// public void setTablePanel(TablePanel tablePanel) {
	// this.tablePanel = tablePanel;
	// }
}
