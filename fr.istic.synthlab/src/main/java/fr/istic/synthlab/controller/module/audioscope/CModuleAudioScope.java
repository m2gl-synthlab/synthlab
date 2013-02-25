package fr.istic.synthlab.controller.module.audioscope;

import fr.istic.synthlab.abstraction.module.audioscope.ModuleAudioScope;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.audioscope.IPModuleAudioScope;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

/**
 * Controller for the AudioScope module
 */
public class CModuleAudioScope extends ModuleAudioScope implements ICModuleAudioScope {

	private IPModuleAudioScope pres;
	private ISynthesizer cSynthesizer;

	public CModuleAudioScope(ISynthesizer cSynthesizer) {
		super(cSynthesizer);
		this.cSynthesizer = cSynthesizer;
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

	@Override
	public void setParameter(String key, Double value) {
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
