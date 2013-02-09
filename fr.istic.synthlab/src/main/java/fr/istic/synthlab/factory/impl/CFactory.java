package fr.istic.synthlab.factory.impl;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.module.audioscope.IModuleAudioScope;
import fr.istic.synthlab.abstraction.module.eg.IModuleEG;
import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.vca.IModuleVCA;
import fr.istic.synthlab.abstraction.module.vco.IModuleVCO;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.audioscope.CModuleAudioScope;
import fr.istic.synthlab.controller.module.eg.CModuleEG;
import fr.istic.synthlab.controller.module.out.CModuleOUT;
import fr.istic.synthlab.controller.module.rep.CModuleREP;
import fr.istic.synthlab.controller.module.vca.CModuleVCA;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF;
import fr.istic.synthlab.controller.module.vco.CModuleVCO;
import fr.istic.synthlab.controller.port.CInputPort;
import fr.istic.synthlab.controller.port.COutputPort;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.CWire;
import fr.istic.synthlab.factory.IFactory;

public class CFactory implements IFactory {

	public static final String MODULE_VCO = "VCO";
	public static final String MODULE_VCA = "VCA";
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
	public IModuleVCA newVCA(ISynthesizer synth) {
		IModuleVCA module = new CModuleVCA(synth);
		return module;
	}

	@Override
	public IModule newVCF(ISynthesizer synth) {
		IModule module = new CModuleVCF(synth);
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
