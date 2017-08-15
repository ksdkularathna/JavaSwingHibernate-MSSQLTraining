package com.swing.training.dtos;

import com.swing.training.enums.Actions;

public class EventData {

	private Actions action;
	private Object object;

	public EventData() {
	}

	public EventData(Actions action, Object object) {

		this.action = action;
		this.object = object;
	}

	public Actions getAction() {
		return action;
	}

	public void setAction(Actions action) {
		this.action = action;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
