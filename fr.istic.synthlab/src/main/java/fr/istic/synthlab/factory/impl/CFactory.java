package fr.istic.synthlab.factory.impl;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.module.audioscope.IModuleAudioScope;
import fr.istic.synthlab.abstraction.module.eg.IModuleEG;
import fr.istic.synthlab.abstraction.module.mix.IModuleMIX;
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
import fr.istic.synthlab.controller.module.mix.CModuleMIX;
import fr.istic.synthlab.controller.module.out.CModuleOUT;
import fr.istic.synthlab.controller.module.rep.CModuleREP;
import fr.istic.synthlab.controller.module.vca.CModuleVCA;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF_HP;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP;
import fr.istic.synthlab.controller.module.vco.CModuleVCO;
import fr.istic.synthlab.controller.port.CInputPort;
import fr.istic.synthlab.controller.port.COutputPort;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
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
		return new CSynthesizer();
	}

	@Override
	public IModuleVCO newVCO() {
		IModuleVCO module = new CModuleVCO();
		return module;
	}

	@Override
	public IModuleVCA newVCA() {
		IModuleVCA module = new CModuleVCA();
		return module;
	}

	@Override
	public IModule newVCFA_LP() {
		IModule module = new CModuleVCF_LP();
		return module;
	}

	@Override
	public IModule newVCFA_HP() {
		IModule module = new CModuleVCF_HP();
		return module;
	}

	@Override
	public IModuleOUT newOUT() {
		IModuleOUT module = new CModuleOUT();
		return module;
	}

	@Override
	public IModuleREP newREP() {
		IModuleREP module = new CModuleREP();
		return module;
	}

	@Override
	public IModule newMIX() {
		IModuleMIX module = new CModuleMIX();
		return module;
	}

	@Override
	public IModuleEG newEG() {
		IModuleEG module = new CModuleEG();
		return module;
	}

	@Override
	public IModuleAudioScope newAudioScope() {
		IModuleAudioScope module = new CModuleAudioScope();
		return module;
	}

	@Override
	public IWire newWire() {
		IWire wire = new CWire();
		return wire;
	}

	@Override
	public IInputPort newInputPort(IModule mod, String name, UnitInputPort input) {
		IInputPort port = new CInputPort(name, input, mod);
		return port;
	}

	@Override
	public IOutputPort newOutputPort(IModule mod, String name, UnitOutputPort output) {
		IOutputPort port = new COutputPort(name, output, mod);
		return port;
	}

}
