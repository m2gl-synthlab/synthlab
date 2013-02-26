package fr.istic.synthlab.controller.module.vca;

import fr.istic.synthlab.abstraction.module.vca.ModuleVCA;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.vca.IPModuleVCA;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

public class CModuleVCA extends ModuleVCA implements ICModuleVCA {

	private IPModuleVCA pres;
	private ISynthesizer cSynthesizer;

	public CModuleVCA(ISynthesizer cSynthesizer) {
		super(cSynthesizer);
		this.cSynthesizer = cSynthesizer;
		this.pres = PACFactory.getPFactory().newVCA(this);
		this.pres.c2pSetAttenuationValue(getAttenuation());
	}

	@Override
	public IPModuleVCA getPresentation() {
		return pres;
	}

	@Override
	public void p2cClosing() {
		for (IWire w : this.getWires()) {
			w.disconnect();
		}
	}

	private void changeAttenuation(double amplitude) {
		setAttenuation(amplitude);
		this.pres.c2pSetAttenuationValue(getAttenuation());
	}

	@Override
	public void p2cAttenuationValueChanged(double amplitude) {
		changeAttenuation(amplitude);
	}

	@Override
	public void setParameter(String key, Double value) {
		if (key.equals("attenuation")) {
			changeAttenuation(value);
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