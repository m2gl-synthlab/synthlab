package fr.istic.synthlab.controller.module.vco;

import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.vco.IPModuleVCO;

public class CModuleVCO extends ModuleVCO implements ICModuleVCO {

	private IPModuleVCO pres;

	public CModuleVCO(ISynthesizer synth) {
		super(synth);
		this.pres = PACFactory.getPFactory().newVCO(this);
		pres.c2pSetOctaveValue(getOctave());
		pres.c2pSetToneValue(getTone());
		pres.c2pSetFrequencyValue(getFrequency());
	}

	@Override
	public IPModuleVCO getPresentation() {
		return pres;
	}
	
	@Override
	public void p2cOctaveChanged(int octave) {
		setOctave(octave);
		pres.c2pSetOctaveValue(getOctave());
		pres.c2pSetFrequencyValue(getFrequency());
	}

	@Override
	public void p2cToneChanged(double tone) {
		setTone(tone);
		pres.c2pSetToneValue(getTone());
		pres.c2pSetFrequencyValue(getFrequency());
	}

	@Override
	public void p2cClosing() {
		for(IWire w : this.getWires()){
			w.disconnect();
		}
	}
}