package fr.istic.synthlab.factory.impl;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IModuleAudioScope;
import fr.istic.synthlab.abstraction.IModuleEG;
import fr.istic.synthlab.abstraction.IModuleOUT;
import fr.istic.synthlab.abstraction.IModuleREP;
import fr.istic.synthlab.abstraction.IModuleVCO;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.impl.CInputPort;
import fr.istic.synthlab.controller.impl.CModuleAudioScope;
import fr.istic.synthlab.controller.impl.CModuleEG;
import fr.istic.synthlab.controller.impl.CModuleOUT;
import fr.istic.synthlab.controller.impl.CModuleREP;
import fr.istic.synthlab.controller.impl.CModuleVCF;
import fr.istic.synthlab.controller.impl.CModuleVCO;
import fr.istic.synthlab.controller.impl.COutputPort;
import fr.istic.synthlab.controller.impl.CParameter;
import fr.istic.synthlab.controller.impl.CSwitch;
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

	@Override
	public ISynthesizer newSynthesizer() {
		return new CSynthesizer();
	}

	@Override
	public IModuleVCO newVCO(ISynthesizer synth) {
		IModuleVCO module = new CModuleVCO(synth);
		return module;
	}

	@Override
	public IModule newVCF(ISynthesizer synth) {
		IModule module = new CModuleVCF(MODULE_VCF); // TODO : refactor vcf
		module.setSynthesizer(synth);
		return module;
	}

	@Override
	public IModuleOUT newOUT(ISynthesizer synth) {
		IModuleOUT module = new CModuleOUT(synth);
		return module;
	}
	
	@Override
	public IModuleREP newREP(ICSynthesizer synth) {
		IModuleREP module = new CModuleREP(synth);
		return module;
	}

	@Override
	public IModuleEG newEG(ICSynthesizer synth) {
		IModuleEG module = new CModuleEG(synth);
		return module;
	}
	
	@Override
	public IModuleAudioScope newAudioScope(ICSynthesizer synth) {
		IModuleAudioScope module = new CModuleAudioScope(synth);
		return module;
	}
	

	@Override
	public IParameter newParameter(IModule mod, String name, double min, double max, double value) {
		IParameter param = new CParameter(name, min, max, value);
		return param;
	}

	@Override
	public IParameter newSwitch(IModule mod, String name, boolean value) {
		IParameter param = new CSwitch(name, value);
		return param;
	}

	@Override
	public IInputPort newInputPort(IModule mod, String name) {
		IInputPort port = new CInputPort(name);
		port.setModule(mod);
		return port;
	}

	@Override
	public IOutputPort newOutputPort(IModule mod, String name) {
		IOutputPort port = new COutputPort(name);
		port.setModule(mod);
		return port;
	}

	@Override
	public IWire newWire() {
		IWire wire = new CWire();
		return wire;
	}

	@Override
	public IInputPort newInputPort(IModule mod, String name, UnitInputPort input) {
		IInputPort port = new CInputPort(input, name);
		port.setModule(mod);
		port.setName(name);
		return port;
	}
	
	@Override
	public IInputPort newInputPort(IModule mod, String name, UnitInputPort input, int part) {
		IInputPort port = new CInputPort(input, part, name);
		port.setModule(mod);
		port.setName(name);
		return port;
	}

	@Override
	public IOutputPort newOutputPort(IModule mod, String name, UnitOutputPort output) {
		IOutputPort port = new COutputPort(output, name);
		port.setModule(mod);
		return port;
	}
	
	@Override
	public IOutputPort newOutputPort(IModule mod, String name, UnitOutputPort output, int part) {
		IOutputPort port = new COutputPort(output, part, name);
		port.setModule(mod);
		port.setName(name);
		return port;
	}

}
