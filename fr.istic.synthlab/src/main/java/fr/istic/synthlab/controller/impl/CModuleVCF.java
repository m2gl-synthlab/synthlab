package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.ModuleVCF;
import fr.istic.synthlab.controller.ICModuleVCF;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModuleVCF;

public class CModuleVCF extends ModuleVCF implements ICModuleVCF {

	private IPModuleVCF pres;

	public CModuleVCF(ISynthesizer synth) {
		super(synth);
		this.pres = PACFactory.getPFactory().newVCF(this);
	}

	@Override
	public IPModuleVCF getPresentation() {
		return pres;
	}
	
	@Override
	public void p2cCutFrequencyChanged(double cutFrequency) {
		setCutFrequency(cutFrequency);
		pres.c2pSetCutFrequencyValue(getCutFrequency());
	}

	@Override
	public void p2cResonanceChanged(double resonance) {
		setResonance(resonance);
		pres.c2pSetResonanceValue(getResonance());
	}

	@Override
	public void p2cClosing() {
		for(IWire w : this.getWires()){
			w.disconnect();
		}
	}
}
