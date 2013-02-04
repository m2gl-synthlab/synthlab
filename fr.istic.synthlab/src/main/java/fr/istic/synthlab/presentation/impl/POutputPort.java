package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.presentation.IPOutputPort;

public class POutputPort extends JPanel implements IPOutputPort {
	
	private static final long serialVersionUID = 4664436294243269232L;
	
	private ICOutputPort ctrl;

	public POutputPort(ICOutputPort control) {
		ctrl = control;

		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setSize(100, 100);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(ctrl.getName()));
	}

	private void defineCallbacks() {
	}
	
	@Override
	public ICOutputPort getControl() {
		return ctrl;
	}
}
