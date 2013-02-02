package fr.istic.synthlab.presentation.impl;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.presentation.IPParameter;

public class PParameter extends JPanel implements IPParameter {

	private static final long serialVersionUID = 7359022086561577171L;
	
	private ICParameter ctrl;
	
	public PParameter(ICParameter control) {
		ctrl = control;
		
		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setSize(100, 100);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(getClass().getSimpleName()));
	}

	private void defineCallbacks() {
	}
	
	@Override
	public ICParameter getControl() {
		return ctrl;
	}

}
