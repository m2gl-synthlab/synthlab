package fr.istic.synthlab.factory.impl;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.controller.impl.CInputPort;
import fr.istic.synthlab.controller.impl.CModuleOUT;
import fr.istic.synthlab.controller.impl.CModuleVCF;
import fr.istic.synthlab.controller.impl.CModuleVCO;
import fr.istic.synthlab.controller.impl.COutputPort;
import fr.istic.synthlab.controller.impl.CParameter;
import fr.istic.synthlab.controller.impl.CSynthesizer;
import fr.istic.synthlab.controller.impl.CWire;
import fr.istic.synthlab.factory.IFactory;

public class CFactory implements IFactory {

	public static final String MODULE_VCO = "VCO";
	public static final String MODULE_OUT = "OUT";
	public static final String MODULE_VCF = "VCF";

	private static final CFactory instance = new CFactory();

	private CFactory() {
	}

	public static CFactory getInstance() {
		return instance;
	}

	public ISynthesizer newSynthesizer() {
		ISynthesizer syn = new CSynthesizer();
		return syn;
	}

	public IModule newVCO() {
		IModule module = new CModuleVCO(MODULE_VCO);
		return module;
	}

	public IModule newVCF() {
		IModule module = new CModuleVCF(MODULE_VCF);
		return module;
	}

	public IModule newOUT() {
		IModule module = new CModuleOUT(MODULE_OUT);
		return module;
	}

	public IParameter newParameter(String name, double min, double max, double value) {
		IParameter param = new CParameter(name, min, max, value);
		return param;
	}

	public IInputPort newInputPort(String name) {
		IInputPort port = new CInputPort(name);
		return port;
	}

	public IOutputPort newOutputPort(String name) {
		IOutputPort port = new COutputPort(name);
		return port;
	}

	public IWire newWire() {
		IWire wire = new CWire();
		return wire;
	}

	@Override
	public IInputPort newInputPort(UnitInputPort input) {
		IInputPort port = new CInputPort(input);
		return port;
	}
	
	@Override
	public IInputPort newInputPort(UnitInputPort input, int part) {
		IInputPort port = new CInputPort(input, part);
		return port;
	}

	@Override
	public IOutputPort newOutputPort(UnitOutputPort output) {
		IOutputPort port = new COutputPort(output);
		return port;
	}
	
	@Override
	public IOutputPort newOutputPort(UnitOutputPort output, int part) {
		IOutputPort port = new COutputPort(output, part);
		return port;
	}

}
