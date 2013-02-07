package fr.istic.synthlab.controller.impl;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.abstraction.impl.Parameter;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPParameter;

public class CParameter extends Parameter implements ICParameter {

	private IPParameter pres;
	
	public CParameter(String name, double min, double max, double value) {
		super(name, min, max, value);
		this.pres = PACFactory.getPFactory().newParameter(this);
	}

	@Override
	public IPParameter getPresentation() {
		return pres;
	}

	@Override
	public void p2cSetValue(double value) {
		super.setValue(value);
	}
	
	@Override
	public void setValue(double value) {
		super.setValue(value);
		pres.c2pSetValue(getValue());
	}
	
	@Override
	public void connect(UnitInputPort input) {
		super.connect(input);
	}


}
