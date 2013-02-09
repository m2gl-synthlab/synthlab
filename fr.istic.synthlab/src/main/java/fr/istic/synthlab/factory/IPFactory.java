package fr.istic.synthlab.factory;

import fr.istic.synthlab.controller.module.audioscope.ICModuleAudioScope;
import fr.istic.synthlab.controller.module.eg.ICModuleEG;
import fr.istic.synthlab.controller.module.out.ICModuleOUT;
import fr.istic.synthlab.controller.module.rep.ICModuleREP;
import fr.istic.synthlab.controller.module.vca.ICModuleVCA;
import fr.istic.synthlab.controller.module.vcf.ICModuleVCF;
import fr.istic.synthlab.controller.module.vco.ICModuleVCO;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.presentation.module.audioscope.IPModuleAudioScope;
import fr.istic.synthlab.presentation.module.eg.IPModuleEG;
import fr.istic.synthlab.presentation.module.out.IPModuleOUT;
import fr.istic.synthlab.presentation.module.rep.IPModuleREP;
import fr.istic.synthlab.presentation.module.vca.IPModuleVCA;
import fr.istic.synthlab.presentation.module.vcf.IPModuleVCF;
import fr.istic.synthlab.presentation.module.vco.IPModuleVCO;
import fr.istic.synthlab.presentation.port.IPInputPort;
import fr.istic.synthlab.presentation.port.IPOutputPort;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;
import fr.istic.synthlab.presentation.wire.IPWire;

public interface IPFactory {

	// Synthesize
	IPSynthesizer newSynthesizer(ICSynthesizer control);

	// Modules
	IPModuleVCO newVCO(ICModuleVCO control);
	IPModuleVCA newVCA(ICModuleVCA control);
	IPModuleVCF newVCF(ICModuleVCF control);
	IPModuleOUT newOUT(ICModuleOUT control);
	IPModuleEG newEG(ICModuleEG control);
	IPModuleAudioScope newAudioScope(ICModuleAudioScope control);
	IPModuleREP newREP(ICModuleREP control);

	// Connectivity
	IPWire newWire(ICWire control);
	IPOutputPort newOutputPort(ICOutputPort control);
	IPInputPort newInputPort(ICInputPort cInputPort);

}
