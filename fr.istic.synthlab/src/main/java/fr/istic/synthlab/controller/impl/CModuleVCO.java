package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.controller.ICModuleVCO;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModuleVCO;

public class CModuleVCO extends ModuleVCO implements ICModuleVCO {

	private IPModuleVCO pres;

	public CModuleVCO(ISynthesizer synth) {
		super(synth);
		this.pres = PACFactory.getPFactory().newVCO(this);

	}

	@Override
	public IPModuleVCO getPresentation() {
		return pres;
	}
	@Override
	public void p2cOctaveChanged(int octave) {
		setOctave(octave);
		pres.c2pSetOctaveValue(getOctave());
	}

	@Override
	public void p2cToneChanged(double tone) {
		setTone(tone);
		pres.c2pSetToneValue(getTone());
	}

	@Override
	public void p2cClosing() {
		for(IWire w : this.getWires()){
			w.disconnect();
		}
	}
}
