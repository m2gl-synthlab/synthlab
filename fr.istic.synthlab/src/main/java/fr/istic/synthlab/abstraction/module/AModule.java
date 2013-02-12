package fr.istic.synthlab.abstraction.module;

import java.util.ArrayList;
import java.util.HashMap;
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
	private HashMap<String, Double> parameters;
	
	public AModule(String name, ISynthesizer synth) {
		int nbModules = 0;
		if(CSynthesizer.getInstance().getModules().size() >0){
			for (IModule module : CSynthesizer.getInstance().getModules()) {
				if (module.getName().startsWith(name)) {
					nbModules++;
				}
			}
		}
		this.name = name + " - " + ++nbModules;
		this.synth = synth;
		ports = new ArrayList<IPort>();
		parameters = new HashMap<String, Double>();
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
	
	@Override
	public void setParameter(String key, Double value){
		parameters.put(key, value);
	}
	
	@Override
	public Double getParameter(String key){
		if (parameters.get(key) == null){
			return 0.;
		} else 
			return parameters.get(key);
	}
	
	@Override
	public HashMap<String, Double> getParameters(){
		return parameters;
	}
}
