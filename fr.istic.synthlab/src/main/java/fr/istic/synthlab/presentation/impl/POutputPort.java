package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.presentation.IPOutputPort;

public class POutputPort extends JPanel implements IPOutputPort {
	private ICOutputPort ctrl;

	public POutputPort(ICOutputPort control) {
		ctrl = control;
		this.setBorder(BorderFactory.createTitledBorder(getClass().getSimpleName()));
	}
	
	@Override
	public ICOutputPort getControl() {
		return ctrl;
	}

}
