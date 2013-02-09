package fr.istic.synthlab.controller.module.out;

import fr.istic.synthlab.abstraction.module.out.ModuleOUT;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.out.IPModuleOUT;

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
	public IPModuleOUT getPresentation() {
		return this.pres;
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
	public void p2cClosing() {
		for(IWire w : this.getWires()){
			w.disconnect();
		}
	}

}
