package fr.istic.synthlab.factory;

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
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;

public interface IFactory {

	ISynthesizer newSynthesizer();
	
	IModuleVCO newVCO(ISynthesizer synth);
	IModuleVCA newVCA(ISynthesizer synth);
	IModule newVCF(ISynthesizer synth);
	IModuleOUT newOUT(ISynthesizer synth);
	IModuleEG newEG(ICSynthesizer synth);
	IModuleAudioScope newAudioScope(ICSynthesizer synth);
	IModuleREP newREP(ICSynthesizer synth);

	IInputPort newInputPort(IModule mod, String name);
	IOutputPort newOutputPort(IModule mod, String name);
	IWire newWire();

	IInputPort newInputPort(IModule mod, String name, UnitInputPort input);
	IInputPort newInputPort(IModule mod, String name, UnitInputPort input, int part);
	IOutputPort newOutputPort(IModule mod, String name, UnitOutputPort output);
	IOutputPort newOutputPort(IModule mod, String name, UnitOutputPort output, int part);

}
