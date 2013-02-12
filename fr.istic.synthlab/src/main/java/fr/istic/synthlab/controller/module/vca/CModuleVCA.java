package fr.istic.synthlab.controller.module.vca;

import fr.istic.synthlab.abstraction.module.vca.ModuleVCA;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.vca.IPModuleVCA;

public class CModuleVCA extends ModuleVCA implements ICModuleVCA {
	
	private IPModuleVCA pres;

	public CModuleVCA(ISynthesizer synth) {
		super(synth);
		this.pres = PACFactory.getPFactory().newVCA(this);
		this.pres.c2pSetAttenuationValue(getAttenuation());
	}

	@Override
	public IPModuleVCA getPresentation() {
		return pres;
	}

	@Override
	public void p2cClosing() {
		for(IWire w : this.getWires()){
			w.disconnect();
		}
	}

	@Override
	public void p2cAttenuationValueChanged(double amplitude) {
		setAttenuation(amplitude);
		this.pres.c2pSetAttenuationValue(getAttenuation());
	}
	

	@Override
	public void setParameter(String key, Double value){
		if(key.equals("attenuation")){
			p2cAttenuationValueChanged(value);
		} 
	}


}