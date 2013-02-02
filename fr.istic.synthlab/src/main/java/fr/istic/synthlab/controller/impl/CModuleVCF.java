package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.impl.ModuleVCF;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;

public class CModuleVCF extends ModuleVCF implements ICModule {

	private IPModule pres;

	public CModuleVCF(String name) {
		this.pres = PACFactory.getPFactory().newModule(this);

		IInputPort input = this.getInput(INPUT_IN);
		pres.addInputPort(((ICInputPort) input).getPresentation());

		IInputPort amplitude = this.getInput(INPUT_AMPLITUDE);
		pres.addInputPort(((ICInputPort) amplitude).getPresentation());
		
		IInputPort frequency = this.getInput(INPUT_FREQUENCY);
		pres.addInputPort(((ICInputPort) frequency).getPresentation());


		IOutputPort output = this.getOutput(OUTPUT_OUT);
		pres.addOutputPort(((ICOutputPort) output).getPresentation());
	}

	@Override
	public IPModule getPresentation() {
		return pres;
	}

}
