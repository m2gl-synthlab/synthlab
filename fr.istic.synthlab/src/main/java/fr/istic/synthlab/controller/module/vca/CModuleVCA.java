package fr.istic.synthlab.controller.module.vca;

import fr.istic.synthlab.abstraction.module.vca.ModuleVCA;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.vca.IPModuleVCA;

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
	public void p2cAttenuationValueChanged(double amplitude) {
		setBaseAmplitudeValue(amplitude);
		this.presentation.c2pSetAttenuationValue(getBaseAmplitudeValue());
	}

}