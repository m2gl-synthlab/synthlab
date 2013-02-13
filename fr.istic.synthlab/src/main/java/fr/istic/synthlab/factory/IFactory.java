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

public interface IFactory {

	ISynthesizer newSynthesizer();
	
	IModuleVCO newVCO();
	IModuleVCA newVCA();
	IModule newVCFA_LP();
	IModule newVCFA_HP();
	IModuleOUT newOUT();
	IModuleEG newEG();
	IModuleAudioScope newAudioScope();
	IModuleREP newREP();
	IModule newMIX();

	IWire newWire();

	IInputPort newInputPort(IModule mod, String name, UnitInputPort input);
	IOutputPort newOutputPort(IModule mod, String name, UnitOutputPort output);


}
