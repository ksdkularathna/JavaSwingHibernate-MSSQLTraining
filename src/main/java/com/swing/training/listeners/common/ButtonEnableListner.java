package com.swing.training.listeners.common;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

public class ButtonEnableListner implements DocumentListener {

	private ButtonModel buttonModel;
	private List<Document> documents = new ArrayList<Document>();

	public ButtonEnableListner(ButtonModel buttonModel) {
		this.buttonModel = buttonModel;
	}

	public void addDocument(Document document) {
		document.addDocumentListener(this);
		this.documents.add(document);
		documentChanged();
	}

	/**
	 * go through all the documents and enable save button if any of the documents have a input value
	 */
	public void documentChanged() {
		boolean buttonEnabled = false;
		for (Document document : documents) {
			if (document.getLength() > 0) {
				buttonEnabled = true;
				break;
			}
		}
		buttonModel.setEnabled(buttonEnabled);
	}

	public void insertUpdate(DocumentEvent e) {
		documentChanged();
	}

	public void removeUpdate(DocumentEvent e) {
		documentChanged();
	}

	public void changedUpdate(DocumentEvent e) {
		documentChanged();
	}
}
