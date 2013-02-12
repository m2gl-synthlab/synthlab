package fr.istic.synthlab.abstraction.module;

import java.util.ArrayList;
import java.util.List;

import fr.istic.synthlab.abstraction.port.IPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;

/**
 * Abstract class for modules.
 */
public abstract class AModule implements IModule{

	private String name;
	private ISynthesizer synth;
	private List<IPort> ports;
	
	public AModule(String name, ISynthesizer synth) {
		if(CSynthesizer.getInstance().getModules().size() >0){
			for (IModule module : CSynthesizer.getInstance().getModules()) {
				if (module.getName().startsWith(name)) {
					this.name = name
							+ (Integer.parseInt(module.getName().substring(
									name.length())) + 1);
				} else {
					this.name = name + "1";
				}
			}
		} else {
			this.name = name + "1";
		}
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
