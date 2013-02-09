package fr.istic.synthlab.abstraction.module;

import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;

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

}
