package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.ModuleAudioScope;
import fr.istic.synthlab.controller.ICModuleAudioScope;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModuleAudioScope;

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
