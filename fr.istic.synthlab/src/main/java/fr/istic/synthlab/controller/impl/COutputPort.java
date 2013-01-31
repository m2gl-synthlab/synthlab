package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPOutputPort;

public class COutputPort extends OutputPort implements ICOutputPort{

	private IPOutputPort pres;
	
	public COutputPort(String name) {
		super(name);
		this.pres = PACFactory.getPFactory().newOutputPort(this);
	}
	
	@Override
	public IPOutputPort getPresentation() {
		return pres;
	}

}
