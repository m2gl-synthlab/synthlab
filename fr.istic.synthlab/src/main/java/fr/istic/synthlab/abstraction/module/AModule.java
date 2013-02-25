package fr.istic.synthlab.abstraction.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.istic.synthlab.abstraction.port.IPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;

/**
 * Abstract class for modules.
 */
public abstract class AModule implements IModule {

	private String name;
	private List<IPort> ports;
	private HashMap<String, Double> parameters;

	public AModule(ISynthesizer synth, String name) {
		// genère le numero de serie des modules
		int nbModules = 0;
		if (synth.getModules().size() > 0) {
			for (IModule module : synth.getModules()) {
				// on recupere le numero de serie du module
				String numberModule[] = module.getName().split("- ");
				// Selon le modele du module
				if (module.getName().startsWith(name)) {
					// On retient le numero de la dernière instance
					nbModules = Integer.parseInt(numberModule[1]);
				}
			}
		}
		this.name = name + " - " + ++nbModules;
		ports = new ArrayList<IPort>();
		parameters = new HashMap<String, Double>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean containsPort(IPort port) {
		if (ports.contains(port))
			return true;
		else
			return false;
	}

	@Override
	public void addPort(IPort port) {
		ports.add(port);
	}

	@Override
	public Double getParameter(String key) {
		if (parameters.get(key) == null) {
			return 0.;
		} else
			return parameters.get(key);
	}

	@Override
	public HashMap<String, Double> getParameters() {
		return parameters;
	}

	@Override
	public IPort getPortByName(String portName) {
		for (IPort port : ports) {
			if (port.getName().equals(portName)) {
				return port;
			}
		}
		return null;
	}

}
