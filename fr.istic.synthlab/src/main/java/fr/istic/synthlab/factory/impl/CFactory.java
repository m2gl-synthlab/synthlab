package fr.istic.synthlab.factory.impl;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.module.audioscope.IModuleAudioScope;
import fr.istic.synthlab.abstraction.module.eg.IModuleEG;
import fr.istic.synthlab.abstraction.module.mix.IModuleMIX;
import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.abstraction.module.rec.IModuleREC;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.vca.IModuleVCA;
import fr.istic.synthlab.abstraction.module.vco.IModuleVCO;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.audioscope.CModuleAudioScope;
import fr.istic.synthlab.controller.module.eg.CModuleEG;
import fr.istic.synthlab.controller.module.mix.CModuleMIX;
import fr.istic.synthlab.controller.module.out.CModuleOUT;
import fr.istic.synthlab.controller.module.rec.CModuleREC;
import fr.istic.synthlab.controller.module.rep.CModuleREP;
import fr.istic.synthlab.controller.module.vca.CModuleVCA;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF_HP;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP;
import fr.istic.synthlab.controller.module.vco.CModuleVCO;
import fr.istic.synthlab.controller.port.CInputPort;
import fr.istic.synthlab.controller.port.COutputPort;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.CWire;
import fr.istic.synthlab.factory.IFactory;

public class CFactory implements IFactory {

	private static final CFactory instance = new CFactory();

	private CFactory() {
	}

	public static CFactory getInstance() {
		return instance;
	}

	@Override
	public ISynthesizer newSynthesizer() {
		ISynthesizer cSynthesizer = new CSynthesizer();
		return cSynthesizer;
	}

	@Override
	public IModuleVCO newVCO(ISynthesizer cSynthesizer) {
		IModuleVCO module = new CModuleVCO(cSynthesizer);
		return module;
	}

	@Override
	public IModuleVCA newVCA(ISynthesizer cSynthesizer) {
		IModuleVCA module = new CModuleVCA(cSynthesizer);
		return module;
	}

	@Override
	public IModule newVCFA_LP(ISynthesizer cSynthesizer) {
		IModule module = new CModuleVCF_LP(cSynthesizer);
		return module;
	}

	@Override
	public IModule newVCFA_HP(ISynthesizer cSynthesizer) {
		IModule module = new CModuleVCF_HP(cSynthesizer);
		return module;
	}

	@Override
	public IModuleOUT newOUT(ISynthesizer cSynthesizer) {
		IModuleOUT module = new CModuleOUT(cSynthesizer);
		return module;
	}

	@Override
	public IModuleREC newREC(ISynthApp app) {
		IModuleREC module = new CModuleREC(app.getSynthesizer());
		return module;
	}

	@Override
	public IModuleREP newREP(ISynthesizer cSynthesizer) {
		IModuleREP module = new CModuleREP(cSynthesizer);
		return module;
	}

	@Override
	public IModule newMIX(ISynthesizer cSynthesizer) {
		IModuleMIX module = new CModuleMIX(cSynthesizer);
		return module;
	}

	@Override
	public IModuleEG newEG(ISynthesizer cSynthesizer) {
		IModuleEG module = new CModuleEG(cSynthesizer);
		return module;
	}

	@Override
	public IModuleAudioScope newAudioScope(ISynthesizer cSynthesizer) {
		IModuleAudioScope module = new CModuleAudioScope(cSynthesizer);
		return module;
	}

	@Override
	public IWire newWire(ISynthesizer cSynthesizer) {
		IWire wire = new CWire(cSynthesizer);
		return wire;
	}

	@Override
	public IInputPort newInputPort(ISynthesizer cSynthesizer, IModule mod, String name, UnitInputPort input) {
		IInputPort port = new CInputPort((ICSynthesizer) cSynthesizer, name, input, mod);
		return port;
	}

	@Override
	public IOutputPort newOutputPort(ISynthesizer cSynthesizer, IModule mod, String name, UnitOutputPort output) {
		IOutputPort port = new COutputPort((ICSynthesizer) cSynthesizer, name, output, mod);
		return port;
	}

}
