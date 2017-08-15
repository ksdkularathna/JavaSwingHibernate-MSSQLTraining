package com.swing.training.jpanels.drug;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit.BoldAction;

import com.swing.training.controllers.AbstractController;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EventData;
import com.swing.training.enums.Actions;
import com.swing.training.listeners.common.ButtonEnableListner;
import com.swing.training.utility.PhamarcyUtility;

public class DrugAddPanel extends JPanel {

	private static final String NULL_STRING = "";
	private static final String NAME_NOT_NULL_MSG = "Please enter the drug name";
	private static final String DRUG_TYPE_NOT_NULL_MSG = "Please enter the drug type";

	private JLabel nameLabel, typeLabel;
	private JTextField nameText, drugIdText;
	private JButton clearBtn, saveBtn, removeBtn, addNewBtn;
	private JComboBox drugTypeCombo;
	private DrugTablePanel drugTablePanel;
	private AbstractController controller;

	private GridBagConstraints gbConstraints = new GridBagConstraints();

	public DrugAddPanel(AbstractController controller) {

		this.controller = controller;
		setLayout(new GridBagLayout());
		setPanelComponents();
		disableSaveBtn();
		addActionListners();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);

		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;

		setNameLabel(new JLabel("Name *"));
		add(getNameLabel(), gbConstraints);

		typeLabel = new JLabel("Type :");
		gbConstraints.gridy = 1;
		add(typeLabel, gbConstraints);

		setNameText(new JTextField(46));
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 0;
		gbConstraints.gridwidth = 2;
		add(getNameText(), gbConstraints);

		drugTypeCombo = new JComboBox(getDrugTypesComboList());
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 1;
		gbConstraints.gridwidth = 1;
		add(drugTypeCombo, gbConstraints);

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
		gbConstraintsRemoveBtn.gridx = 1;
		gbConstraintsRemoveBtn.gridy = 0;
		gbConstraintsRemoveBtn.weightx = 0;
		gbConstraintsRemoveBtn.weighty = 1;
		gbConstraintsRemoveBtn.insets = new Insets(5, 5, 5, 5);
		gbConstraintsRemoveBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsRemoveBtn.fill = GridBagConstraints.NONE;
		removeBtn.setEnabled(Boolean.FALSE);
		buttonPanel.add(removeBtn, gbConstraintsRemoveBtn);

		setSaveBtn(new JButton("Save"));
		GridBagConstraints gbConstraintsSaveBtn = new GridBagConstraints();
		gbConstraintsSaveBtn.insets = new Insets(5, 5, 5, 5);
		gbConstraintsSaveBtn.fill = GridBagConstraints.NONE;
		gbConstraintsSaveBtn.anchor = GridBagConstraints.NORTHEAST;
		gbConstraintsSaveBtn.gridx = 2;
		gbConstraintsSaveBtn.gridy = 0;
		gbConstraintsSaveBtn.weightx = 0;
		gbConstraintsSaveBtn.weighty = 1;
		buttonPanel.add(getSaveBtn(), gbConstraintsSaveBtn);

		addNewBtn = new JButton("Add new");
		GridBagConstraints gbConstraintsAddNewBtn = new GridBagConstraints();
		gbConstraintsAddNewBtn.insets = new Insets(5, 5, 5, 5);
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

		drugIdText = new JTextField(5);
		gbConstraints.gridy++;
		drugIdText.setVisible(Boolean.FALSE);
		add(drugIdText, gbConstraints);
	}

	/**
	 * method for setting save button disable listener
	 */
	public void disableSaveBtn() {

		final ButtonModel saveBtnModel = getSaveBtn().getModel();
		Document nameDocument = getNameText().getDocument();

		ButtonEnableListner buttonEnablement = new ButtonEnableListner(
				saveBtnModel);
		buttonEnablement.addDocument(nameDocument);
	}

	/**
	 * returns validated patient details
	 * 
	 * @return
	 */
	public DrugDto validateAndGetDrugDetails() {

		DrugDto drugDto = new DrugDto();

		if (getNameText().getText().trim().equals(NULL_STRING)) {

			JOptionPane.showMessageDialog(null, NAME_NOT_NULL_MSG);
			getNameText().requestFocus();
			return null;
		} else {

			drugDto.setName(getNameText().getText().trim());
		}

		String drugType = drugTypeCombo.getSelectedItem().toString();
		if (!drugType.equals(NULL_STRING)) {

			drugDto.setType(drugType);
		} else {

			JOptionPane.showMessageDialog(null, DRUG_TYPE_NOT_NULL_MSG);
			drugTypeCombo.requestFocus(Boolean.TRUE);
			return null;
		}
		String drugId = drugIdText.getText();
		if (!drugId.equals(NULL_STRING)) {

			drugDto.setDrugId(Integer.parseInt(drugId));
		}
		return drugDto;
	}

	/**
	 * set the input PatientDto details to input fields
	 * 
	 * @param dto
	 */
	public void setDrugDtoToUi(DrugDto dto) {

		getNameText().setText(dto.getName());
		drugTypeCombo.setSelectedItem(dto.getType());
		drugIdText.setText(Integer.toString(dto.getDrugId()));
		removeBtn.setEnabled(Boolean.TRUE);
	}

	/**
	 * clear the input fields
	 */
	public void clearInputFields() {

		getNameText().setText(NULL_STRING);
		drugTypeCombo.setSelectedIndex(-1);
		drugIdText.setText(NULL_STRING);
		removeBtn.setEnabled(Boolean.FALSE);
	}

	/**
	 * add action listners to the components
	 */
	private void addActionListners() {

		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				controller.executeOpeartion(new EventData(
						Actions.DRUG_SAVE_ACTION, null));
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

				int drugId = Integer.parseInt(drugIdText.getText());
				controller.executeOpeartion(new EventData(Actions.DRUG_DELETE,
						drugId));
			}
		});
		addNewBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				drugIdText.setText(NULL_STRING);
				controller.executeOpeartion(new EventData(
						Actions.DRUG_SAVE_ACTION, null));
			}
		});
	}

	/**
	 * returns the drug type combo list from the property file
	 * 
	 * @return
	 */
	private String[] getDrugTypesComboList() {

		String propertyValue = PhamarcyUtility.getInstance().getProperties()
				.getProperty("drugTypes");
		return propertyValue.split(",");
	}

	public JLabel getNameLabel() {
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JTextField getNameText() {
		return nameText;
	}

	public void setNameText(JTextField nameText) {
		this.nameText = nameText;
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
}
