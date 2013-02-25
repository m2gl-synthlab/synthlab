package fr.istic.synthlab.factory;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.module.audioscope.IModuleAudioScope;
import fr.istic.synthlab.abstraction.module.eg.IModuleEG;
import fr.istic.synthlab.abstraction.module.noise.IModuleNOISE;
import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.abstraction.module.rec.IModuleREC;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.vca.IModuleVCA;
import fr.istic.synthlab.abstraction.module.vcf.IModuleVCF;
import fr.istic.synthlab.abstraction.module.vco.IModuleVCO;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;

/**
 * Interface of the factory
 */
public interface IFactory {

	/**
	 * Return a synthesizer
	 * 
	 * @return pres
	 */
	ISynthesizer newSynthesizer();

	/**
	 * Return a VCO
	 * 
	 * @param synth
	 * @return VCO Module
	 */
	IModuleVCO newVCO(ISynthesizer synth);

	/**
	 * Return a VCA
	 * 
	 * @param synth
	 * @return VCA Module
	 */
	IModuleVCA newVCA(ISynthesizer synth);

	/**
	 * Return a VCFA_LP
	 * 
	 * @param synth
	 * @return VCFA_LP Module
	 */
	IModuleVCF newVCFA_LP(ISynthesizer synth);

	/**
	 * Return a VCFA_HP
	 * 
	 * @param synth
	 * @return VCFA_HP Module
	 */
	IModuleVCF newVCFA_HP(ISynthesizer synth);

	/**
	 * Return a NOISE
	 * 
	 * @param synth
	 * @return NOISE Module
	 */
	IModuleNOISE newNOISE(ISynthesizer synthesizer);

	/**
	 * Return an OUT
	 * 
	 * @param synth
	 * @return OUT Module
	 */
	IModuleOUT newOUT(ISynthesizer synth);

	/**
	 * Return a REC
	 * 
	 * @param app
	 * @return REC Module
	 */
	IModuleREC newREC(ISynthApp app);

	/**
	 * Return an EG
	 * 
	 * @param synth
	 * @return EG Module
	 */
	IModuleEG newEG(ISynthesizer synth);

	/**
	 * Return an AudioScope
	 * 
	 * @param synth
	 * @return AudioScope Module
	 */
	IModuleAudioScope newAudioScope(ISynthesizer synth);

	/**
	 * Return a REP
	 * 
	 * @param synth
	 * @return REP Module
	 */
	IModuleREP newREP(ISynthesizer synth);

	/**
	 * Return a MIX
	 * 
	 * @param synth
	 * @return MIX Module
	 */
	IModule newMIX(ISynthesizer synth);

	/**
	 * Return a Wire
	 * 
	 * @param synth
	 * @return Wire
	 */
	IWire newWire(ISynthesizer synth);

	/**
	 * @param synth
	 * @param mod
	 * @param name
	 * @param input
	 * @return
	 */
	IInputPort newInputPort(ISynthesizer synth, IModule mod, String name, UnitInputPort input);

	/**
	 * @param synth
	 * @param mod
	 * @param name
	 * @param output
	 * @return
	 */
	IOutputPort newOutputPort(ISynthesizer synth, IModule mod, String name, UnitOutputPort output);

}
