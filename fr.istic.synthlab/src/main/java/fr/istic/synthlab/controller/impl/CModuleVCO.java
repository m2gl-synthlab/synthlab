package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModuleVCO;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPModuleVCO;

public class CModuleVCO extends ModuleVCO implements ICModuleVCO {

	private IPModuleVCO pres;

	public CModuleVCO(ISynthesizer synth) {
		super(synth);
		this.pres = PACFactory.getPFactory().newVCO(this);
		
		IParameter amplitude = this.getParameter(PARAM_AMPLITUDE);
		pres.addParameter(((ICParameter) amplitude).getPresentation());
		
		IParameter frequency = this.getParameter(PARAM_FREQUENCY);
		pres.addParameter(((ICParameter) frequency).getPresentation());
		
		IInputPort inputModulation = this.getInput(INPUT_MOD_FREQ);
		pres.addInputPort(((ICInputPort) inputModulation).getPresentation());
		
		IOutputPort outputSquare = this.getOutput(OUTPUT_SQUARE);
		pres.addOutputPort(((ICOutputPort) outputSquare).getPresentation());

		IOutputPort outputTriangle = this.getOutput(OUTPUT_TRIANGLE);
		pres.addOutputPort(((ICOutputPort) outputTriangle).getPresentation());
		
		IOutputPort outputSine = this.getOutput(OUTPUT_SINE);
		pres.addOutputPort(((ICOutputPort) outputSine).getPresentation());

		IOutputPort outputSawtooth = this.getOutput(OUTPUT_SAWTOOTH);
		pres.addOutputPort(((ICOutputPort) outputSawtooth).getPresentation());

	}

	@Override
	public IPModule getPresentation() {
		return pres;
	}

	@Override
	public void p2cDoSomething() {
		// TODO Auto-generated method stub
		
	}

}
