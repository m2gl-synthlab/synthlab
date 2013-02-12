package fr.istic.synthlab.abstraction.module;

import java.util.ArrayList;
import java.util.List;

import fr.istic.synthlab.abstraction.port.IPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;

/**
 * Abstract class for modules.
 */
public abstract class AModule implements IModule{

	private String name;
	private ISynthesizer synth;
	private List<IPort> ports;
	
	public AModule(String name, ISynthesizer synth) {
		this.name = name;
		this.synth = synth;
		ports = new ArrayList<IPort>();
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
	public boolean havePort(IPort port){
		if(ports.contains(port))
			return true;
		else
			return false;
	}
	
	@Override
	public void addPort(IPort port){
		ports.add(port);
	}
}
