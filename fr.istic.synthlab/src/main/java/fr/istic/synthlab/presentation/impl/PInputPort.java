package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.presentation.IPInputPort;

public class PInputPort extends JPanel implements IPInputPort {

	private ICInputPort ctrl;

	public PInputPort(ICInputPort control) {
		ctrl = control;
		this.setBorder(BorderFactory.createTitledBorder(getClass().getSimpleName()));
	}
	
	@Override
	public ICInputPort getControl() {
		return ctrl;
	}

}
