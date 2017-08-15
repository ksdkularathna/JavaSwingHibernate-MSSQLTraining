package com.swing.training.jpanels.employe;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.swing.training.controllers.AbstractController;
import com.swing.training.dtos.EventData;
import com.swing.training.dtos.criteria.EmployeSearchCriteriaDto;
import com.swing.training.enums.Actions;

public class EmployeSearchPanel extends JPanel {

	private static final String NULL_STRING = "";
	private static final String NAME_NOT_NULL_MSG = "Please enter the name";
	private static final String BDAY_NOT_NULL_MSG = "Please enter the birthday";
	private static final String BDAY_IS_WRONG_MSG = "Please check your birthday";
	private static final String BIRTH_YEAR_IS_WRONG = "Please check your birthyear";
	private static final String GENDER_NOT_NULL_MSG = "Please select the gender";
	private static final String MALE = "Male";

	private JLabel nameLabel;
	private JTextField nameText;
	private JButton searchBtn;

	private GridBagConstraints gbConstraints = new GridBagConstraints();
	private AbstractController controller;

	public EmployeSearchPanel(AbstractController controller2) {

		this.controller = controller2;
		setLayout(new GridBagLayout());
		setPanelComponents();
		addActionListners();
	}

	public void setPanelComponents() {

		gbConstraints.fill = GridBagConstraints.HORIZONTAL;
		gbConstraints.insets = new Insets(5, 5, 5, 5);

		setNameLabel(new JLabel("Name "));
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		gbConstraints.insets = new Insets(5, 10, 5, 5);
		add(getNameLabel(), gbConstraints);

		setNameText(new JTextField(10));
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 0;
		add(getNameText(), gbConstraints);

		setSearchBtn(new JButton("Search"));
		gbConstraints.insets = new Insets(5, 5, 5, 10);
		gbConstraints.gridx = 2;
		gbConstraints.gridy = 0;
		gbConstraints.weightx = 1;
		gbConstraints.anchor = GridBagConstraints.EAST;
		gbConstraints.fill = GridBagConstraints.NONE;
		add(getSearchBtn(), gbConstraints);
		// getSearchBtn().addActionListener(
		// new SearchBtnActionListner(this, tablePanel));
	}

	/**
	 * validate the input fields and returns search criteria object
	 * 
	 * @return
	 */
	public EmployeSearchCriteriaDto getEmployeSearchCriteria() {

		EmployeSearchCriteriaDto criteria = new EmployeSearchCriteriaDto();

		String name = nameText.getText().trim();
		criteria.setEmployeName(name);
		return criteria;
	}

	/**
	 * add action listners to the panel components
	 */
	private void addActionListners() {

		searchBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				controller.executeOpeartion(new EventData(
						Actions.EMPLOYE_SEARCH_FROM_CRITERIA, null));
			}
		});
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

	public JButton getSearchBtn() {
		return searchBtn;
	}

	public void setSearchBtn(JButton searchBtn) {
		this.searchBtn = searchBtn;
	}

	// public static void main(String[] args) {
	//
	// JFrame jFrame = new JFrame();
	//
	// EmployeSearchPanel mainPanel=new EmployeSearchPanel();
	// jFrame.setTitle("test");
	// // jFrame.setResizable(Boolean.FALSE);
	// jFrame.setSize(550, 550);
	// jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// jFrame.setVisible(true);
	// jFrame.add(mainPanel);
	// }
}
