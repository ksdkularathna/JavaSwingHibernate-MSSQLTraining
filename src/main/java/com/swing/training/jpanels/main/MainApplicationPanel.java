package com.swing.training.jpanels.main;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

import com.swing.training.controllers.AbstractController;
import com.swing.training.controllers.MainController;
import com.swing.training.dtos.DrugDto;
import com.swing.training.dtos.EmployeDto;
import com.swing.training.dtos.PatientDto;
import com.swing.training.dtos.PrescribedDrugDto;
import com.swing.training.dtos.PrescriptionDto;
import com.swing.training.jpanels.drug.DrugPanelMain;
import com.swing.training.jpanels.employe.EmployePanelMain;
import com.swing.training.jpanels.patient.PatientPanelMain;
import com.swing.training.jpanels.prescription.PrescriptionPanelMain;
import com.swing.training.service.impl.HibernateUtil;

public class MainApplicationPanel extends JPanel {

	private JTabbedPane tabPane;
	private JFrame jFrame;
	private PatientPanelMain patientPanelMain;
	private EmployePanelMain employePanelMain;
	private DrugPanelMain drugPanelMain;
	private PrescriptionPanelMain prescriptionPanelMain;
	public AbstractController controller;

	public MainApplicationPanel(AbstractController controller, JFrame jFrame) {

		this.controller = controller;
		this.jFrame = jFrame;
		setPanelComponents();
	}

	private void setPanelComponents() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		createSubPanels();
		addTabPane();
		setCloseButton();
	}

	private void createSubPanels() {

		patientPanelMain = new PatientPanelMain(controller);
		employePanelMain = new EmployePanelMain(controller);
		drugPanelMain = new DrugPanelMain(controller);
		prescriptionPanelMain = new PrescriptionPanelMain(controller);
	}

	public void initialize() {

		prescriptionPanelMain.initialize();
		patientPanelMain.initialize();
		employePanelMain.initialize();
		drugPanelMain.initialize();
	}

	private void setCloseButton() {

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jFrame.dispose();
			}
		});
		GridBagConstraints gbConstraintsCloseBtn = new GridBagConstraints();
		gbConstraintsCloseBtn.gridx = 0;
		gbConstraintsCloseBtn.gridy = 0;
		gbConstraintsCloseBtn.weightx = 1;
		gbConstraintsCloseBtn.weighty = 1;
		gbConstraintsCloseBtn.insets = new Insets(5, 5, 5, 12);
		gbConstraintsCloseBtn.anchor = GridBagConstraints.NORTHEAST;
		buttonPanel.add(closeBtn, gbConstraintsCloseBtn);
		add(buttonPanel);
	}

	private void addTabPane() {

		tabPane = new JTabbedPane();
		tabPane.add("Prescription", prescriptionPanelMain);
		tabPane.add("Patient", patientPanelMain);
		tabPane.add("Employe", employePanelMain);
		tabPane.add("Drug", drugPanelMain);
		add(tabPane, BorderLayout.CENTER);
	}
}
