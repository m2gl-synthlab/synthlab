package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.impl.ModuleVCA;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModuleVCA;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPModuleVCA;

public class CModuleVCA extends ModuleVCA implements ICModuleVCA {
	
	private IPModuleVCA presentation;

	public CModuleVCA(ISynthesizer synth) {
		super(synth);
		this.presentation = PACFactory.getPFactory().newVCA(this);
		
		//PARAM_AMPLITUDE et OUTPUT_SQUARE = 0 par defaut
		IParameter amplitude = this.getParameter();
		presentation.addParameter(((ICParameter) amplitude).getPresentation());
				
		
	
		
		IInputPort inputAmplitudeModulation = this.getInputAM();
		presentation.addInputPort(((ICInputPort) inputAmplitudeModulation).getPresentation());
		
		IInputPort inputModule = this.getInput();
		presentation.addInputPort(((ICInputPort) inputModule).getPresentation());

		IOutputPort outputVCA = this.getOutput();
		presentation.addOutputPort(((ICOutputPort) outputVCA).getPresentation());
	}

	@Override
	public IPModule getPresentation() {
		return presentation;
	}

	@Override
	public void p2cDoSomething() {
		// TODO Auto-generated method stub
		
	}

}