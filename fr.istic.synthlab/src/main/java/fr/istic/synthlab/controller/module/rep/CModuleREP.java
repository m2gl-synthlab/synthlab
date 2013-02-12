package fr.istic.synthlab.controller.module.rep;

import fr.istic.synthlab.abstraction.module.rep.ModuleREP;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.IPModule;
import fr.istic.synthlab.presentation.module.rep.IPModuleREP;

//TODO : need comments

public class CModuleREP  extends ModuleREP implements ICModuleREP {

	private IPModuleREP pres;
	
	public CModuleREP(ISynthesizer synth) {
		super(synth);
		this.pres = PACFactory.getPFactory().newREP(this);	
	}

	@Override
	public IPModule getPresentation() {
		return this.pres;
	}

	@Override
	public void p2cClosing() {
		for(IWire w : this.getWires()){
			w.disconnect();
		}		
	}
	
	@Override
	public void setParameter(String key, Double value){}


}
