package fr.istic.synthlab.controller.module.vco;

import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.vco.IPModuleVCO;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

public class CModuleVCO extends ModuleVCO implements ICModuleVCO {

	private IPModuleVCO pres;
	private ISynthesizer cSynthesizer;

	public CModuleVCO(ISynthesizer cSynthesizer) {
		super(cSynthesizer);
		this.cSynthesizer = cSynthesizer;
		this.pres = PACFactory.getPFactory().newVCO(this);
		pres.c2pSetOctaveValue(getOctave());
		pres.c2pSetToneValue(getTone());
		pres.c2pSetFrequencyValue(getFrequency());
	}

	@Override
	public IPModuleVCO getPresentation() {
		return pres;
	}

	private void changeOctave(int octave) {
		setOctave(octave);
		pres.c2pSetOctaveValue(getOctave());
		pres.c2pSetFrequencyValue(getFrequency());
	}

	private void changeTone(double tone) {
		setTone(tone);
		pres.c2pSetToneValue(getTone());
		pres.c2pSetFrequencyValue(getFrequency());
	}

	@Override
	public void p2cOctaveChanged(int octave) {
		changeOctave(octave);
	}

	@Override
	public void p2cToneChanged(double tone) {
		changeTone(tone);
	}

	@Override
	public void p2cClosing() {
		for (IWire w : this.getWires()) {
			w.disconnect();
		}
	}

	@Override
	public void setParameter(String key, Double value) {
		if (key.equals("octave")) {
			changeOctave(value.intValue());
		} else if (key.equals("tone")) {
			changeTone(value);
		}

	}
	
	@Override
	public IPSynthesizer getSynthesizerPresentation() {
		return ((ICSynthesizer)cSynthesizer).getPresentation();
	}

	@Override
	public void p2cRemoveModule(ICModule module) {
		((ICSynthesizer) cSynthesizer).p2cRemoveModule(module);
	}
}
