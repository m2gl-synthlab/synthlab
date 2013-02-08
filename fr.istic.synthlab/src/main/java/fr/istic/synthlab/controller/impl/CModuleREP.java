package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.ModuleREP;
import fr.istic.synthlab.controller.ICModuleREP;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPModuleREP;

public class CModuleREP  extends ModuleREP implements ICModuleREP {

	private IPModuleREP pres;
	
	public CModuleREP(ISynthesizer synth) {
		super(synth);
		this.pres = PACFactory.getPFactory().newREP(this);	
	}

	@Override
	public IPModule getPresentation() {
		// TODO Auto-generated method stub
		return this.pres;
	}

	@Override
	public void p2cClosing() {
		for(IWire w : this.getWires()){
			w.disconnect();
		}		
	}

}
