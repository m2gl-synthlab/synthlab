package fr.istic.synthlab.controller.module.rec;

import fr.istic.synthlab.abstraction.module.rec.ModuleREC;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.rec.IPModuleREC;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

/**
 * Controller for the REC module
 */
public class CModuleREC extends ModuleREC implements ICModuleREC {

	private IPModuleREC pres;
	private ISynthesizer cSynthesizer;

	public CModuleREC(ISynthesizer cSynthesizer) {
		super(cSynthesizer);
		this.cSynthesizer = cSynthesizer;
		this.pres = PACFactory.getPFactory().newREC(this);
		this.pres.c2pSetGainValue(getAttenuation());
	}

	@Override
	public IPModuleREC getPresentation() {
		return this.pres;
	}

	private void changeGain(double gain) {
		setAttenuation(gain);
		pres.c2pSetGainValue(getAttenuation());
	}

	@Override
	public void p2cRecord() {
		setRecording(!isRecording());
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
		stop();
	}

	@Override
	public void setParameter(String key, Double value) {
		if (key.equals("attenuation")) {
			changeGain(value);
		} else if (key.equals("isRecording")) {
			if (value != (DEFAULT_STATE_RECORDING ? 1.0 : 0.0)) {
				pres.c2pRecord(!DEFAULT_STATE_RECORDING);
			}
		}
	}

	@Override
	public IPSynthesizer getSynthesizerPresentation() {
		return ((ICSynthesizer) cSynthesizer).getPresentation();
	}

	@Override
	public void p2cRemoveModule(ICModule module) {
		((ICSynthesizer) cSynthesizer).p2cRemoveModule(module);
	}
}
