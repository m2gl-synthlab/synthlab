package fr.istic.synthlab.controller.module.vcf;

import fr.istic.synthlab.abstraction.module.vcf.ModuleVCFA_HP;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.vcf.IPModuleVCF;

public class CModuleVCFA_HP extends ModuleVCFA_HP implements ICModuleVCF {

	private IPModuleVCF pres;

	public CModuleVCFA_HP() {
		super();
		this.pres = PACFactory.getPFactory().newVCF(this);
	}

	@Override
	public IPModuleVCF getPresentation() {
		return pres;
	}
	
	@Override
	public void p2cCutFrequencyChanged(int cutFrequency) {
		setCutFrequency(cutFrequency);
		pres.c2pSetCutFrequencyValue(getCutFrequency());
	}

	@Override
	public void p2cResonanceChanged(double resonance) {
		setResonance(resonance);
		pres.c2pSetResonanceValue(getResonance());
	}

	@Override
	public void p2cClosing() {
		for(IWire w : this.getWires()){
			w.disconnect();
		}
	}
	
	
	@Override
	public void setParameter(String key, Double value){
		if(key.equals("cutFrequency")){
			p2cCutFrequencyChanged(value.intValue());
		} else if (key.equals("resonance")){
			p2cResonanceChanged(value);
		}
	}
}