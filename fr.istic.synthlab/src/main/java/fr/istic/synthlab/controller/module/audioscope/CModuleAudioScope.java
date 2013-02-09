package fr.istic.synthlab.controller.module.audioscope;

import fr.istic.synthlab.abstraction.module.audioscope.ModuleAudioScope;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.audioscope.IPModuleAudioScope;

/**
 * Controller for the AudioScope module
 */
public class CModuleAudioScope extends ModuleAudioScope implements ICModuleAudioScope {

	private IPModuleAudioScope pres;

	public CModuleAudioScope(ISynthesizer synth) {
		super(synth);
		this.pres = PACFactory.getPFactory().newAudioScope(this);
	}

	@Override
	public IPModuleAudioScope getPresentation() {
		return this.pres;
	}

	@Override
	public void p2cClosing() {
		for (IWire w : this.getWires()) {
			w.disconnect();
		}
	}


}
