package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPInputPort;

public class CInputPort extends InputPort implements ICInputPort {

	private IPInputPort pres;
	
	public CInputPort(String name) {
		super(name);
		this.pres = PACFactory.getPFactory().newInputPort(this);
	}
	
	@Override
	public IPInputPort getPresentation() {
		return pres;
	}

	
}
