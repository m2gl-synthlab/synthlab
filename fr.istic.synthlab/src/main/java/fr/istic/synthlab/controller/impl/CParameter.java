package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.impl.Parameter;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPParameter;

public class CParameter extends Parameter implements ICParameter {

	private IPParameter pres;
	
	public CParameter(String name) {
		this.pres = PACFactory.getPFactory().newParameter(this);
	}

	
	
	@Override
	public IPParameter getPresentation() {
		return pres;
	}

}
