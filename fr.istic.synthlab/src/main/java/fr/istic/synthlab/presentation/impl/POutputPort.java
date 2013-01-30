package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPWire;

public class POutputPort extends JPanel implements IPOutputPort {
	private ICOutputPort ctrl;

	public POutputPort(ICOutputPort control) {
		ctrl = control;

		this.setSize(100, 100);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(getClass().getSimpleName()));
	}
	
	@Override
	public ICOutputPort getControl() {
		return ctrl;
	}

	public void connect(IPWire wire){
		this.add((PWire) wire);
	}
}
