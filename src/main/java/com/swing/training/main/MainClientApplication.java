package com.swing.training.main;

import javax.swing.JFrame;

import com.swing.training.controllers.AbstractController;
import com.swing.training.controllers.MainController;
import com.swing.training.jpanels.main.MainApplicationPanel;

/**
 * Class for running the main client application
 * 
 * @author SDhananjaya
 * 
 */
public class MainClientApplication {

	private JFrame jFrame;
	private static final String PROJECT_NAME = "Patient Registration";

	public static void main(String[] args) {

		new MainClientApplication().runClientApplication();
	}

	void runClientApplication() {

		AbstractController controller = new MainController();
		jFrame = new JFrame();
		jFrame.setTitle(PROJECT_NAME);
		jFrame.setSize(600, 680);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		MainApplicationPanel mainApplicationPanel = new MainApplicationPanel(
				controller, jFrame);
		mainApplicationPanel.initialize();
		jFrame.add(mainApplicationPanel);
		jFrame.setVisible(true);
		jFrame.setResizable(Boolean.FALSE);
	}
}
