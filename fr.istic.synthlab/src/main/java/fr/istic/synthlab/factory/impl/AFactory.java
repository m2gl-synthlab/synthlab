package fr.istic.synthlab.factory.impl;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.module.audioscope.IModuleAudioScope;
import fr.istic.synthlab.abstraction.module.audioscope.ModuleAudioScope;
import fr.istic.synthlab.abstraction.module.eg.IModuleEG;
import fr.istic.synthlab.abstraction.module.eg.ModuleEG;
import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.abstraction.module.out.ModuleOUT;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.rep.ModuleREP;
import fr.istic.synthlab.abstraction.module.vca.IModuleVCA;
import fr.istic.synthlab.abstraction.module.vca.ModuleVCA;
import fr.istic.synthlab.abstraction.module.vcf.ModuleVCF;
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
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
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
		return Synthesizer.getInstance();
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
	public IModule newVCF(ISynthesizer synth) {
		IModule module = new ModuleVCF(synth);
		return module;
	}

	@Override
	public IModuleOUT newOUT(ISynthesizer synth) {
		IModuleOUT module = new ModuleOUT(synth);
		return module;
	}

	@Override
	public IModuleEG newEG(ICSynthesizer synth) {
		IModuleEG module = new ModuleEG(synth);
		return module;
	}

	@Override
	public IModuleAudioScope newAudioScope(ICSynthesizer synth) {
		IModuleAudioScope module = new ModuleAudioScope(synth);
		return module;
	}

	@Override
	public IModuleREP newREP(ICSynthesizer synth) {
		IModuleREP module = new ModuleREP(synth);
		return module;
	}

	@Override
	public IWire newWire() {
		IWire wire = new Wire();
		return wire;
	}

	@Override
	public IInputPort newInputPort(IModule mod, String name, UnitInputPort input) {
		IInputPort port = new InputPort(name, input, mod);
		port.setName(name);
		return port;
	}

	@Override
	public IOutputPort newOutputPort(IModule mod, String name, UnitOutputPort output) {
		IOutputPort port = new OutputPort(name, output, mod);
		return port;
	}

}
