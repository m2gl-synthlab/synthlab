package fr.istic.synthlab.factory.impl;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.IPort;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.controller.impl.CInputPort;
import fr.istic.synthlab.controller.impl.CModuleVCA;
import fr.istic.synthlab.controller.impl.CModuleVCF;
import fr.istic.synthlab.controller.impl.CModuleVCO;
import fr.istic.synthlab.controller.impl.COutputPort;
import fr.istic.synthlab.controller.impl.CSynthesizer;
import fr.istic.synthlab.controller.impl.CWire;
import fr.istic.synthlab.factory.IFactory;

public class CFactory implements IFactory {

	public static final String MODULE_VCO = "VCO";
	public static final String MODULE_VCA = "VCA";
	public static final String MODULE_VCF = "VCF";

	private static final CFactory instance = new CFactory();

	private CFactory() {
	}

	public static CFactory getInstance() {
		return instance;
	}

	public ISynthesizer newSynthesizer(IFactory factory) {
		ISynthesizer syn = new CSynthesizer();
		return syn;
	}

	public IModule newVCO(IFactory factory) {
		IModule module = new CModuleVCO(MODULE_VCO);
		return module;
	}

	public IModule newVCF(IFactory factory) {
		IModule module = new CModuleVCF(MODULE_VCF);
		return module;
	}

	public IModule newVCA(IFactory factory) {
		IModule module = new CModuleVCA(MODULE_VCA);
		return module;
	}

	public IParameter newParameter(String name, IFactory factory) {
		// TODO Auto-generated method stub
		return null;
	}

	public IPort newInputPort(String name, IFactory factory) {
		IPort port = new CInputPort(name);
		return port;
	}

	public IPort newOutputPort(String name, IFactory factory) {
		IPort port = new COutputPort(name);
		return port;
	}

	public IWire newWire(IFactory factory) {
		IWire wire = new CWire();
		return wire;
	}

}
