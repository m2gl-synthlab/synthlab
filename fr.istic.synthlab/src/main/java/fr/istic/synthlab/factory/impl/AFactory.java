package fr.istic.synthlab.factory.impl;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.ModuleOUT;
import fr.istic.synthlab.abstraction.impl.ModuleVCF;
import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.abstraction.impl.Parameter;
import fr.istic.synthlab.abstraction.impl.Switch;
import fr.istic.synthlab.abstraction.impl.Synthesizer;
import fr.istic.synthlab.abstraction.impl.Wire;
import fr.istic.synthlab.factory.IFactory;

public class AFactory implements IFactory {

	public static final String MODULE_VCO = "VCO";
	public static final String MODULE_OUT = "OUT";
	public static final String MODULE_VCF = "VCF";

	private static final AFactory instance = new AFactory();

	private AFactory() {
	}

	public static AFactory getInstance() {
		return instance;
	}

	@Override
	public ISynthesizer newSynthesizer() {
		ISynthesizer syn = new Synthesizer();
		return syn;
	}

	public IModule newModule(ISynthesizer synth, String name) {
		if (name == MODULE_VCO) {
			return newVCO(synth);
		}
		if (name == MODULE_OUT) {
			return newOUT(synth);
		}
		if (name == MODULE_VCF) {
			return newVCF(synth);
		}
		return null;
	}

	@Override
	public IModule newVCO(ISynthesizer synth) {
		IModule module = new ModuleVCO(MODULE_VCO);
		module.setSynthesizer(synth);
		return module;
	}

	@Override
	public IModule newVCF(ISynthesizer synth) {
		IModule module = new ModuleVCF(MODULE_VCF);
		module.setSynthesizer(synth);
		return module;
	}

	@Override
	public IModule newOUT(ISynthesizer synth) {
		IModule module = new ModuleOUT(MODULE_OUT);
		module.setSynthesizer(synth);
		return module;
	}

	@Override
	public IParameter newParameter(IModule mod, String name, double min, double max, double val) {
		IParameter parameter = new Parameter(name, min, max, val);
		return parameter;
	}

	@Override
	public IParameter newSwitch(IModule mod, String name, boolean value) {
		IParameter parameter = new Switch(name, value);
		return parameter;
	}

	@Override
	public IInputPort newInputPort(IModule mod, String name) {
		IInputPort port = new InputPort(name);
		port.setModule(mod);
		return port;
	}

	@Override
	public IOutputPort newOutputPort(IModule mod, String name) {
		IOutputPort port = new OutputPort(name);
		port.setModule(mod);
		return port;
	}

	@Override
	public IWire newWire() {
		IWire wire = new Wire();
		return wire;
	}

	@Override
	public IInputPort newInputPort(IModule mod, String name, UnitInputPort input) {
		IInputPort port = new InputPort(input);
		port.setModule(mod);
		port.setName(name);
		return port;
	}
	
	@Override
	public IInputPort newInputPort(IModule mod, String name, UnitInputPort input, int part) {
		IInputPort port = new InputPort(input, part);
		port.setModule(mod);
		port.setName(name);
		return port;
	}

	@Override
	public IOutputPort newOutputPort(IModule mod, String name, UnitOutputPort output) {
		IOutputPort port = new OutputPort(output);
		port.setModule(mod);
		port.setName(name);
		return port;
	}
	
	public IOutputPort newOutputPort(IModule mod, String name, UnitOutputPort output, int part) {
		IOutputPort port = new OutputPort(output, part);
		port.setModule(mod);
		port.setName(name);
		return port;
	}

}
