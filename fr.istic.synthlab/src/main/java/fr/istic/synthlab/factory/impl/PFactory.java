package fr.istic.synthlab.factory.impl;

import fr.istic.synthlab.controller.module.audioscope.ICModuleAudioScope;
import fr.istic.synthlab.controller.module.eg.ICModuleEG;
import fr.istic.synthlab.controller.module.mix.ICModuleMIX;
import fr.istic.synthlab.controller.module.out.ICModuleOUT;
import fr.istic.synthlab.controller.module.rep.ICModuleREP;
import fr.istic.synthlab.controller.module.vca.ICModuleVCA;
import fr.istic.synthlab.controller.module.vcf.ICModuleVCF;
import fr.istic.synthlab.controller.module.vco.ICModuleVCO;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.factory.IPFactory;
import fr.istic.synthlab.presentation.module.audioscope.IPModuleAudioScope;
import fr.istic.synthlab.presentation.module.audioscope.PModuleAudioScope;
import fr.istic.synthlab.presentation.module.eg.IPModuleEG;
import fr.istic.synthlab.presentation.module.eg.PModuleEG;
import fr.istic.synthlab.presentation.module.mix.IPModuleMIX;
import fr.istic.synthlab.presentation.module.mix.PModuleMIX;
import fr.istic.synthlab.presentation.module.out.IPModuleOUT;
import fr.istic.synthlab.presentation.module.out.PModuleOUT;
import fr.istic.synthlab.presentation.module.rep.IPModuleREP;
import fr.istic.synthlab.presentation.module.rep.PModuleREP;
import fr.istic.synthlab.presentation.module.vca.IPModuleVCA;
import fr.istic.synthlab.presentation.module.vca.PModuleVCA;
import fr.istic.synthlab.presentation.module.vcf.IPModuleVCF;
import fr.istic.synthlab.presentation.module.vcf.PModuleVCF;
import fr.istic.synthlab.presentation.module.vco.IPModuleVCO;
import fr.istic.synthlab.presentation.module.vco.PModuleVCO;
import fr.istic.synthlab.presentation.port.IPInputPort;
import fr.istic.synthlab.presentation.port.IPOutputPort;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;
import fr.istic.synthlab.presentation.synthesizer.PSynthesizer;
import fr.istic.synthlab.presentation.wire.IPWire;
import fr.istic.synthlab.presentation.wire.PWire;

public class PFactory implements IPFactory {

	private static final PFactory instance = new PFactory();

	private PFactory() {
	}

	public static PFactory getInstance() {
		return instance;
	}

	@Override
	public IPSynthesizer newSynthesizer(ICSynthesizer cSynthesizer) {
		return new PSynthesizer(cSynthesizer);
	}

	@Override
	public IPModuleVCO newVCO(ICModuleVCO control) {
		return new PModuleVCO(control);
	}

	@Override
	public IPModuleVCA newVCA(ICModuleVCA control) {
		return new PModuleVCA(control);
	}

	@Override
	public IPModuleVCF newVCF(ICModuleVCF control) {
		return new PModuleVCF(control);
	}

	@Override
	public IPModuleOUT newOUT(ICModuleOUT control) {
		return new PModuleOUT(control);
	}

	@Override
	public IPModuleEG newEG(ICModuleEG control) {
		return new PModuleEG(control);
	}

	@Override
	public IPModuleAudioScope newAudioScope(ICModuleAudioScope control) {
		return new PModuleAudioScope(control);
	}

	@Override
	public IPModuleREP newREP(ICModuleREP control) {
		return new PModuleREP(control);
	}

	@Override
	public IPModuleMIX newMIX(ICModuleMIX control) {
		return new PModuleMIX(control);
	}

	@Override
	public IPWire newWire(ICWire control) {
		return new PWire(control);
	}

	@Override
	public IPOutputPort newOutputPort(ICOutputPort control) {
		return new POutputPort(control);
	}

	@Override
	public IPInputPort newInputPort(ICInputPort control) {
		return new PInputPort(control);
	}

}