package fr.istic.synthlab.controller.module.mix;

import fr.istic.synthlab.abstraction.module.mix.ModuleMIX;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.module.IPModule;
import fr.istic.synthlab.presentation.module.mix.IPModuleMIX;
import fr.istic.synthlab.presentation.module.rep.IPModuleREP;

public class CModuleMIX extends ModuleMIX implements ICModuleMIX {

	
	private IPModuleMIX pres;
	
	public CModuleMIX() {
		super();
		this.pres = PACFactory.getPFactory().newMIX(this);	
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
	public void setParameter(String key, Double value) {}

	

}
