package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;

public class CModuleVCO extends ModuleVCO implements ICModule {

	private IPModule pres;

	public CModuleVCO(String name) {
		this.pres = PACFactory.getPFactory().newModule(this);
		



		IInputPort amplitude = this.getInput(INPUT_AMPLITUDE);
		pres.addInputPort(((ICInputPort) amplitude).getPresentation());

		IInputPort frequency = this.getInput(INPUT_FREQUENCY);
		pres.addInputPort(((ICInputPort) frequency).getPresentation());
		
		IInputPort width = this.getInput(INPUT_WIDTH);
		pres.addInputPort(((ICInputPort) width).getPresentation());

		
		IOutputPort output = this.getOutput(OUTPUT_OUT);
		pres.addOutputPort(((ICOutputPort) output).getPresentation());
		
	}

	@Override
	public IPModule getPresentation() {
		return pres;
	}

}
