package fr.istic.synthlab.factory.impl;


import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.IPort;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.Module;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.abstraction.impl.Synthetizer;
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
		ISynthesizer syn = new Synthetizer();
		return syn;
	}

	public IModule newModule(String name, IFactory factory) { 
		IModule module = new Module(name);
		return module;
	}
	
	public IModule newVCO(IFactory factory) {
		IModule module = factory.newModule(MODULE_VCO, factory);
		IPort out = factory.newOutputPort("out", factory);
		module.addPort(out);// TODO : add strategy and stuff to output something on this port
		return module;
	}

	public IModule newVCF(IFactory factory) {
		IModule module = newModule(MODULE_VCF, factory);
		IPort in = factory.newInputPort("in", factory);
		IPort out = factory.newOutputPort("out", factory);
		module.addPort(in);
		module.addPort(out);// TODO : add strategy and stuff to output something on this port
		return module;
	}

	public IModule newVCA(IFactory factory) {
		IModule module = newModule(MODULE_VCA, factory);
		IPort in = factory.newInputPort("in", factory);
		module.addPort(in);// TODO : add strategy and stuff to output something on the sound card
		return module;
	}

	

	public IParameter newParameter(String name, IFactory factory) {
		//IParameter parameter = new PA(name);
		//return port;
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