package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;

public class CModuleVCO extends ModuleVCO implements ICModule{

	private IPModule pres;

	public CModuleVCO(String name) {
		this.pres = PACFactory.getPFactory().newModule(this);
	}

	@Override
	public IPModule getPresentation() {
		return pres;
	}
	

}
