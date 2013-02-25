package fr.istic.synthlab.factory;

import fr.istic.synthlab.controller.module.audioscope.ICModuleAudioScope;
import fr.istic.synthlab.controller.module.eg.ICModuleEG;
import fr.istic.synthlab.controller.module.mix.ICModuleMIX;
import fr.istic.synthlab.controller.module.noise.ICModuleNOISE;
import fr.istic.synthlab.controller.module.out.ICModuleOUT;
import fr.istic.synthlab.controller.module.rec.ICModuleREC;
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
import fr.istic.synthlab.presentation.module.mix.IPModuleMIX;
import fr.istic.synthlab.presentation.module.noise.IPModuleNOISE;
import fr.istic.synthlab.presentation.module.out.IPModuleOUT;
import fr.istic.synthlab.presentation.module.rec.IPModuleREC;
import fr.istic.synthlab.presentation.module.rep.IPModuleREP;
import fr.istic.synthlab.presentation.module.vca.IPModuleVCA;
import fr.istic.synthlab.presentation.module.vcf.IPModuleVCF;
import fr.istic.synthlab.presentation.module.vco.IPModuleVCO;
import fr.istic.synthlab.presentation.port.IPInputPort;
import fr.istic.synthlab.presentation.port.IPOutputPort;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;
import fr.istic.synthlab.presentation.wire.IPWire;

/**
 * Interface of the presentation factory
 */
public interface IPFactory {

	// Synthesizer
	/**
	 * Return a synthesizer presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPSynthesizer newSynthesizer(ICSynthesizer control);

	// Modules
	/**
	 * Return a VCO module presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPModuleVCO newVCO(ICModuleVCO control);

	/**
	 * Return a VCA module presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPModuleVCA newVCA(ICModuleVCA control);

	/**
	 * Return a VCF module presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPModuleVCF newVCF(ICModuleVCF control);

	/**
	 * Return a OUT module presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPModuleOUT newOUT(ICModuleOUT control);

	/**
	 * Return a REC module presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPModuleREC newREC(ICModuleREC control);

	/**
	 * Return a EG module presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPModuleEG newEG(ICModuleEG control);

	/**
	 * Return a NOISE module presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPModuleNOISE newNOISE(ICModuleNOISE control);

	/**
	 * Return a SCOP module presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPModuleAudioScope newAudioScope(ICModuleAudioScope control);

	/**
	 * Return a REP module presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPModuleREP newREP(ICModuleREP control);

	/**
	 * Return a MIX module presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPModuleMIX newMIX(ICModuleMIX control);

	// Connectivity
	/**
	 * Return a wire presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPWire newWire(ICWire control);

	/**
	 * Return an output port presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPOutputPort newOutputPort(ICOutputPort control);

	/**
	 * Return an input port presentation
	 * 
	 * @param control
	 * @return pres
	 */
	IPInputPort newInputPort(ICInputPort cInputPort);

}
