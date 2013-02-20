package fr.istic.synthlab.controller.module.noise;

import fr.istic.synthlab.abstraction.module.noise.ModuleNOISE;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.noise.IPModuleNOISE;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

/**
 * Controller for the NOISE module
 */
public class CModuleNOISE extends ModuleNOISE implements ICModuleNOISE {

	private IPModuleNOISE pres;
	private ISynthesizer cSynthesizer;

	public CModuleNOISE(ISynthesizer cSynthesizer) {
		super(cSynthesizer);
		this.cSynthesizer = cSynthesizer;
		this.pres = PACFactory.getPFactory().newNOISE(this);
		this.pres.c2pSetGainValue(getAttenuation());
	}

	@Override
	public IPModuleNOISE getPresentation() {
		return this.pres;
	}

	private void changeGain(double gain) {
		setAttenuation(gain);
		pres.c2pSetGainValue(getAttenuation());
	}

	@Override
	public void p2cGainChanged(double gain) {
		changeGain(gain);
	}

	@Override
	public void p2cClosing() {
		for (IWire w : this.getWires()) {
			w.disconnect();
		}
	}

	@Override
	public void setParameter(String key, Double value) {
		if (key.equals("attenuation")) {
			changeGain(value);
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
