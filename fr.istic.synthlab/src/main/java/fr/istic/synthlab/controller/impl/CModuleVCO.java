package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;

public class CModuleVCO extends ModuleVCO implements ICModule {

	private IPModule pres;

	public CModuleVCO(String name) {
		super(name);
		this.pres = PACFactory.getPFactory().newModule(this);
		
		IParameter amplitude = this.getParameter(INPUT_AMPLITUDE);
		pres.addParameter(((ICParameter) amplitude).getPresentation());
		
		IParameter frequency = this.getParameter(INPUT_FREQUENCY);
		pres.addParameter(((ICParameter) frequency).getPresentation());
		
		IOutputPort output = this.getOutput(OUTPUT_OUT);
		pres.addOutputPort(((ICOutputPort) output).getPresentation());


	}

	@Override
	public IPModule getPresentation() {
		return pres;
	}

}
