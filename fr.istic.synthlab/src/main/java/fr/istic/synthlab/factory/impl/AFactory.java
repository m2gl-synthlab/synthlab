package fr.istic.synthlab.factory.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.ModuleVCA;
import fr.istic.synthlab.abstraction.impl.ModuleVCF;
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

	public ISynthesizer newSynthesizer() {
		ISynthesizer syn = new Synthesizer();
		return syn;
	}

	public IModule newModule(String name) {
		if (name == MODULE_VCO) {
			return newVCO();
		}
		if (name == MODULE_VCA) {
			return newVCA();
		}
		if (name == MODULE_VCF) {
			return newVCF();
		}
		return null;
	}

	public IModule newVCO() {
		IModule module = new ModuleVCO();
		return module;
	}

	public IModule newVCF() {
		IModule module = new ModuleVCF();
		return module;
	}

	public IModule newVCA() {
		IModule module = new ModuleVCA();
		return module;
	}

	public IParameter newParameter(String name) {
		// IParameter parameter = new PA(name);
		// return port;
		return null;
	}

	public IInputPort newInputPort(String name) {
		IInputPort port = new InputPort(name);
		return port;
	}

	public IOutputPort newOutputPort(String name) {
		IOutputPort port = new OutputPort(name);
		return port;
	}

	public IWire newWire() {
		IWire wire = new Wire();
		return wire;
	}

}