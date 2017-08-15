package com.swing.training.jpanels.drug;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.swing.training.controllers.AbstractController;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.criteria.DrugSearchCriteriaDto;
import com.swing.training.enums.Actions;

public class DrugSearchPanel extends JPanel {

	private JLabel nameLabel;
	private JTextField drugText;
	private JButton searchBtn;

	private GridBagConstraints gbConstraints = new GridBagConstraints();
	private AbstractController controller;
	public DrugTablePanel drugTablePanel;

	public DrugSearchPanel(AbstractController controller2) {

		this.controller = controller2;
		setLayout(new GridBagLayout());
		setPanelComponents();
		setActionListners();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);

		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;

		setNameLabel(new JLabel("Name "));
		gbConstraints.insets = new Insets(5, 10, 5, 10);
		add(getNameLabel(), gbConstraints);

		setNameText(new JTextField(10));
		gbConstraints.gridx++;
		add(getNameText(), gbConstraints);

		setSearchBtn(new JButton("Search"));
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 1;
		gbConstraints.insets = new Insets(5, 5, 5, 13);
		gbConstraints.anchor = GridBagConstraints.EAST;
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.gridx++;
		add(getSearchBtn(), gbConstraints);
	}

	/**
	 * returns search criteria object
	 * 
	 * @return
	 */
	public DrugSearchCriteriaDto getDrugSearchCriteria() {

		DrugSearchCriteriaDto criteria = new DrugSearchCriteriaDto();
		String drugName = drugText.getText().trim();
		criteria.setDrugName(drugName);
		return criteria;
	}

	private void setActionListners() {

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				controller.executeOpeartion(new EventData(
						Actions.DRUG_SEARCH_FROM_CRITERIA, null));
			}
		});
	}

	public JLabel getNameLabel() {
		if (nameLabel == null) {
			nameLabel = new JLabel();
		}
		return nameLabel;
	}

	public void setNameLabel(JLabel nameLabel) {
		this.nameLabel = nameLabel;
	}

	public JTextField getNameText() {
		return drugText;
	}

	public void setNameText(JTextField nameText) {
		this.drugText = nameText;
	}

	public JButton getSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(JButton searchBtn) {
		this.searchBtn = searchBtn;
	}
}
