package fr.istic.synthlab.abstraction.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;

/**
 * Abstract class for modules.
 */
public abstract class AModule implements IModule{

	private String name;
	private ISynthesizer synth;
	
	public AModule(String name, ISynthesizer synth) {
		this.name = name;
		this.synth = synth;
	}
	
	@Override
	public void setSynthesizer(ISynthesizer synth) {
		this.synth = synth;
	}	
	
	@Override
	public ISynthesizer getSynthesizer() {
		return synth;
	}

	@Override
	public String getName() {
		return name;
	}


	@Override
	public IInputPort getInput(int identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IOutputPort getOutput(int identifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IParameter getParameter(int identifier) {
		// TODO Auto-generated method stub
		return null;
	}

}
