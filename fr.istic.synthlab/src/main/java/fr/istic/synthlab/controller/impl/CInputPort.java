package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.IPort;
import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPInputPort;

public class CInputPort extends InputPort implements ICInputPort {

	private IPInputPort pres;
	private IPort abs;
	
	public CInputPort(String name) {
		super(name);
		this.abs = PACFactory.getAFactory().newInputPort(name, PACFactory.getAFactory());
		this.pres = PACFactory.getPFactory().newInputPort(this);
	}
	
	@Override
	public IPInputPort getPresentation() {
		return pres;
	}

	
}
