package fr.istic.synthlab.controller.impl;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.abstraction.impl.Switch;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPParameter;

public class CSwitch extends Switch implements ICParameter {

	private IPParameter pres;
	
	public CSwitch(String name, boolean value) {
		super(name, value);
		this.pres = PACFactory.getPFactory().newSwitch(this);
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
