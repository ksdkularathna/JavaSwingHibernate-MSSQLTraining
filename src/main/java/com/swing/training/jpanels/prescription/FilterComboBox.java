package com.swing.training.jpanels.prescription;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Class is used to filter the drop down list of the combo box according to the
 * user input
 * 
 * @author SDhananjaya
 * 
 */
public class FilterComboBox extends JComboBox {
	private List<String> array;
	private ItemListner itemListner;
	private JTextField patientName;

	public FilterComboBox(List<String> array, JTextField patientName) {

		super(array.toArray());
		this.array = array;
		this.patientName = patientName;
		this.setEditable(true);
		final JTextField textfield = (JTextField) this.getEditor()
				.getEditorComponent();
		textfield.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						comboFilter(textfield.getText());
					}
				});
			}
		});
		this.itemListner = new ItemListner();
		this.addItemListener(itemListner);
	}

	/**
	 * filter combo box according to the user input
	 * 
	 * @param enteredText
	 */
	public void comboFilter(String enteredText) {

		if (!this.isPopupVisible()) {
			this.showPopup();
		}

		List<String> filterArray = new ArrayList<String>();
		for (int i = 0; i < array.size(); i++) {
			if (array.get(i).toLowerCase().contains(enteredText.toLowerCase())) {
				filterArray.add(array.get(i));
			}
		}
		if (filterArray.size() > 0) {
			DefaultComboBoxModel model = (DefaultComboBoxModel) this.getModel();
			this.removeItemListener(itemListner);
			model.removeAllElements();
			for (String s : filterArray)
				model.addElement(s);

			this.addItemListener(itemListner);
			JTextField textfield = (JTextField) this.getEditor()
					.getEditorComponent();
			textfield.setText(enteredText);
		}
	}

	/**
	 * combo box item select listner
	 * 
	 * @author SDhananjaya
	 * 
	 */
	private class ItemListner implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {

			patientName.setText((String) e.getItem());
		}
	}
}
