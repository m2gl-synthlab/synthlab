package fr.istic.synthlab.controller.impl;

import java.util.ArrayList;
import java.util.List;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.impl.ModuleVCF;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;

public class CModuleVCF extends ModuleVCF implements ICModule {

	private IPModule pres;

	public CModuleVCF(String name) {
		super(name);
		this.pres = PACFactory.getPFactory().newVCF(this);

		IParameter amplitude = this.getParameter(PARAM_AMPLITUDE);
		pres.addParameter(((ICParameter) amplitude).getPresentation());
		
		IParameter frequency = this.getParameter(PARAM_FREQUENCY);
		pres.addParameter(((ICParameter) frequency).getPresentation());


		IInputPort input = this.getInput(INPUT_IN);
		pres.addInputPort(((ICInputPort) input).getPresentation());
		
		IOutputPort output = this.getOutput(OUTPUT_OUT);
		pres.addOutputPort(((ICOutputPort) output).getPresentation());
	}

	@Override
	public IPModule getPresentation() {
		return pres;
	}
}
