package fr.istic.synthlab.controller.module.out;

import fr.istic.synthlab.abstraction.module.out.ModuleOUT;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.out.IPModuleOUT;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

/**
 * Controller for the OUT module
 */
public class CModuleOUT extends ModuleOUT implements ICModuleOUT {

	private IPModuleOUT pres;
	private ISynthesizer cSynthesizer;

	public CModuleOUT(ISynthesizer cSynthesizer) {
		super(cSynthesizer);
		this.cSynthesizer = cSynthesizer;
		this.pres = PACFactory.getPFactory().newOUT(this);
		this.pres.c2pSetGainValue(getAttenuation());
	}

	@Override
	public IPModuleOUT getPresentation() {
		return this.pres;
	}

	private void changeGain(double gain) {
		setAttenuation(gain);
		pres.c2pSetGainValue(getAttenuation());
	}

	@Override
	public void p2cMute() {
		setMute(!isMute());
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
		} else if (key.equals("mute")) {
			if (value != (DEFAULT_STATE_MUTE ? 1.0 : 0.0)) {
				pres.c2pMute(!DEFAULT_STATE_MUTE);
			}
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
