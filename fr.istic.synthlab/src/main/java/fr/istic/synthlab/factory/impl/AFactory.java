package fr.istic.synthlab.factory.impl;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.IPort;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.abstraction.impl.Synthesizer;
import fr.istic.synthlab.abstraction.impl.Wire;
import fr.istic.synthlab.factory.IFactory;

public class AFactory implements IFactory {

	public static final String MODULE_VCO = "VCO";
	public static final String MODULE_VCA = "VCA";
	public static final String MODULE_VCF = "VCF";

	private static final AFactory instance = new AFactory();

	private AFactory() {
	}

	public static AFactory getInstance() {
		return instance;
	}

	public ISynthesizer newSynthesizer(IFactory factory) {
		ISynthesizer syn = new Synthesizer();
		return syn;
	}

	public IModule newModule(String name, IFactory factory) {
		if (name == MODULE_VCO) {
			return newVCO(factory);
		}
		if (name == MODULE_VCA) {
			return newVCA(factory);
		}
		if (name == MODULE_VCF) {
			return newVCF(factory);
		}
		return null;
	}

	public IModule newVCO(IFactory factory) {
		IModule module = new ModuleVCO();
		// IPort out = factory.newOutputPort("out", factory);
		// module.addPort(out);// TODO : add strategy and stuff to output
		// something on this port
		return module;
	}

	public IModule newVCF(IFactory factory) {
		// TODO not implemented
		System.err.println(this.getClass().getSimpleName()
				+ ".newVCF() not implemented");
		// IModule module = new ModuleVCF(MODULE_VCF);
		// IPort in = factory.newInputPort("in", factory);
		// IPort out = factory.newOutputPort("out", factory);
		// module.addPort(in);
		// module.addPort(out);// TODO : add strategy and stuff to output
		// something on this port
		// return module;
		return null;
	}

	public IModule newVCA(IFactory factory) {
		// TODO not implemented
		System.err.println(this.getClass().getSimpleName()
				+ ".newVCA() not implemented");
		// IModule module = new ModuleVCA(MODULE_VCA);
		// IPort in = factory.newInputPort("in", factory);
		// module.addPort(in);// TODO : add strategy and stuff to output
		// something on the sound card
		// return module;
		return null;
	}

	public IParameter newParameter(String name, IFactory factory) {
		// IParameter parameter = new PA(name);
		// return port;
		return null;
	}

	public IPort newInputPort(String name, IFactory factory) {
		IPort port = new InputPort(name);
		return port;
	}

	public IPort newOutputPort(String name, IFactory factory) {
		IPort port = new OutputPort(name);
		return port;
	}

	public IWire newWire(IFactory factory) {
		IWire wire = new Wire();
		return wire;
	}

}