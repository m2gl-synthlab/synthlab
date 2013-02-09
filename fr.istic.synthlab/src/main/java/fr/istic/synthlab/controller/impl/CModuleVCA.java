package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.ModuleVCA;
import fr.istic.synthlab.controller.ICModuleVCA;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModuleVCA;

public class CModuleVCA extends ModuleVCA implements ICModuleVCA {
	
	private IPModuleVCA presentation;

	public CModuleVCA(ISynthesizer synth) {
		super(synth);
		this.presentation = PACFactory.getPFactory().newVCA(this);
	}

	@Override
	public IPModuleVCA getPresentation() {
		return presentation;
	}

	@Override
	public void p2cClosing() {
		for(IWire w : this.getWires()){
			w.disconnect();
		}
	}

	@Override
	public void p2cAttenuationValueChanged(double attenuationValue) {
		setAttenuationValue(attenuationValue);
		this.presentation.c2pSetAttenuationValue(getAttenuationValue());
	}

}