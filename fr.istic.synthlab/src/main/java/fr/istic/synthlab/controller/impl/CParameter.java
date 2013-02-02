package fr.istic.synthlab.controller.impl;

import com.jsyn.swing.PortModelFactory;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.impl.Parameter;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPParameter;

public class CParameter extends Parameter implements ICParameter {

	private IPParameter pres;
	
	public CParameter(double min, double max, double value) {
		super(min, max, value);
		this.pres = PACFactory.getPFactory().newParameter(this);
	}

	@Override
	public IPParameter getPresentation() {
		return pres;
	}

	@Override
	public void p2cSetValue(double value) {
		this.setValue(value);
		pres.c2pSetValue(getValue());
	}
	
	@Override
	public void connect(IInputPort input) {
		super.connect(input);
		
		pres.c2pSetRangeModel(PortModelFactory.createLinearModel(input.getJSyn()));
	}

}
