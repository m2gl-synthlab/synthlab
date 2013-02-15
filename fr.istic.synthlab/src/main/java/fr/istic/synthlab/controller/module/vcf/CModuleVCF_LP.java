package fr.istic.synthlab.controller.module.vcf;

import fr.istic.synthlab.abstraction.module.vcf.ModuleVCF_LP;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.vcf.IPModuleVCF;

public class CModuleVCF_LP extends ModuleVCF_LP implements ICModuleVCF {

	private IPModuleVCF pres;

	public CModuleVCF_LP() {
		super();
		this.pres = PACFactory.getPFactory().newVCF(this);
	}

	@Override
	public IPModuleVCF getPresentation() {
		return pres;
	}

	private void changeCutFrequency(int cutFrequency) {
		setCutFrequency(cutFrequency);
		pres.c2pSetCutFrequencyValue(getCutFrequency());
	}

	private void changeResonance(double resonance) {
		setResonance(resonance);
		pres.c2pSetResonanceValue(getResonance());
	}

	@Override
	public void p2cCutFrequencyChanged(int cutFrequency) {
		changeCutFrequency(cutFrequency);
	}

	@Override
	public void p2cResonanceChanged(double resonance) {
		changeResonance(resonance);
	}

	@Override
	public void p2cClosing() {
		for (IWire w : this.getWires()) {
			w.disconnect();
		}
	}

	@Override
	public void setParameter(String key, Double value) {
		if (key.equals("cutFrequency")) {
			changeCutFrequency(value.intValue());
		} else if (key.equals("resonance")) {
			changeResonance(value);
		}
	}
}
