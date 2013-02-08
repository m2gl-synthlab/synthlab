package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.impl.ModuleOUT;
import fr.istic.synthlab.controller.ICModuleOUT;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModuleOUT;

/**
 * Controller for the OUT module
 */
public class CModuleOUT extends ModuleOUT implements ICModuleOUT {

	private IPModuleOUT pres;


	public CModuleOUT(ISynthesizer synth) {
		super(synth);
		this.pres = PACFactory.getPFactory().newOUT(this);
	}

	@Override
	public void p2cMute() {
		setMute(!isMute());
	}

	@Override
	public void p2cGainChanged(double gain) {
		setAttenuation(gain);
		pres.c2pSetGainValue(getAttenuation());
	}

	@Override
	public IPModuleOUT getPresentation() {
		return this.pres;
	}
}
