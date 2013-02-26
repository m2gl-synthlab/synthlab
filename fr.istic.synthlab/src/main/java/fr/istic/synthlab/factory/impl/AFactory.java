package fr.istic.synthlab.factory.impl;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.module.audioscope.IModuleAudioScope;
import fr.istic.synthlab.abstraction.module.audioscope.ModuleAudioScope;
import fr.istic.synthlab.abstraction.module.eg.IModuleEG;
import fr.istic.synthlab.abstraction.module.eg.ModuleEG;
import fr.istic.synthlab.abstraction.module.mix.IModuleMIX;
import fr.istic.synthlab.abstraction.module.mix.ModuleMIX;
import fr.istic.synthlab.abstraction.module.noise.IModuleNOISE;
import fr.istic.synthlab.abstraction.module.noise.ModuleNOISE;
import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.abstraction.module.out.ModuleOUT;
import fr.istic.synthlab.abstraction.module.rec.IModuleREC;
import fr.istic.synthlab.abstraction.module.rec.ModuleREC;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.rep.ModuleREP;
import fr.istic.synthlab.abstraction.module.vca.IModuleVCA;
import fr.istic.synthlab.abstraction.module.vca.ModuleVCA;
import fr.istic.synthlab.abstraction.module.vcf.IModuleVCF;
import fr.istic.synthlab.abstraction.module.vcf.ModuleVCF_HP;
import fr.istic.synthlab.abstraction.module.vcf.ModuleVCF_LP;
import fr.istic.synthlab.abstraction.module.vco.IModuleVCO;
import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.port.InputPort;
import fr.istic.synthlab.abstraction.port.OutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.factory.IFactory;

public class AFactory implements IFactory {

	public static final String MODULE_VCO = "VCO";
	public static final String MODULE_VCA = "VCA";
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
		return new Synthesizer();
	}

	@Override
	public IModuleVCO newVCO(ISynthesizer synth) {
		IModuleVCO module = new ModuleVCO(synth);
		return module;
	}

	@Override
	public IModuleVCA newVCA(ISynthesizer synth) {
		IModuleVCA module = new ModuleVCA(synth);
		return module;
	}

	@Override
	public IModuleVCF newVCFA_LP(ISynthesizer synth) {
		IModuleVCF module = new ModuleVCF_LP(synth);
		return module;
	}

	@Override
	public IModuleVCF newVCFA_HP(ISynthesizer synth) {
		IModuleVCF module = new ModuleVCF_HP(synth);
		return module;
	}
	
	@Override
	public IModuleNOISE newNOISE(ISynthesizer synth) {
		IModuleNOISE module = new ModuleNOISE(synth);
		return module;
	}


	@Override
	public IModuleOUT newOUT(ISynthesizer synth) {
		IModuleOUT module = new ModuleOUT(synth);
		return module;
	}

	@Override
	public IModuleREC newREC(ISynthApp synth) {
		IModuleREC module = new ModuleREC(synth.getSynthesizer());
		return module;
	}

	@Override
	public IModuleEG newEG(ISynthesizer synth) {
		IModuleEG module = new ModuleEG(synth);
		return module;
	}

	@Override
	public IModuleAudioScope newAudioScope(ISynthesizer synth) {
		IModuleAudioScope module = new ModuleAudioScope(synth);
		return module;
	}

	@Override
	public IModuleREP newREP(ISynthesizer synth) {
		IModuleREP module = new ModuleREP(synth);
		return module;
	}

	@Override
	public IModule newMIX(ISynthesizer synth) {
		IModuleMIX module = new ModuleMIX(synth);
		return module;
	}

	@Override
	public IWire newWire(ISynthesizer synth) {
		IWire wire = new Wire(synth);
		return wire;
	}

	@Override
	public IInputPort newInputPort(ISynthesizer synth, IModule mod, String name, UnitInputPort input) {
		IInputPort port = new InputPort(name, input, mod);
		port.setName(name);
		return port;
	}

	@Override
	public IOutputPort newOutputPort(ISynthesizer synth, IModule mod, String name, UnitOutputPort output) {
		IOutputPort port = new OutputPort(name, output, mod);
		return port;
	}

}
