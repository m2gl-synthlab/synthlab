package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.presentation.IPInputPort;

public class PInputPort extends JPanel implements IPInputPort {

	private static final long serialVersionUID = -3189854166979295463L;
	
	private ICInputPort ctrl;

	public PInputPort(ICInputPort control) {
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
	public ICInputPort getControl() {
		return ctrl;
	}
}
