package fr.istic.synthlab.factory;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IModuleOUT;
import fr.istic.synthlab.abstraction.IModuleVCO;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;

public interface IFactory {

	ISynthesizer newSynthesizer();
	
	IModuleVCO newVCO(ISynthesizer synth);
	IModule newVCF(ISynthesizer synth);
	IModuleOUT newOUT(ISynthesizer synth);
	
	IParameter newParameter(IModule mod, String name, double min, double max, double value);
	IParameter newSwitch(IModule mod, String string, boolean value);
	IInputPort newInputPort(IModule mod, String name);
	IOutputPort newOutputPort(IModule mod, String name);
	IWire newWire();

	IInputPort newInputPort(IModule mod, String name, UnitInputPort input);
	IInputPort newInputPort(IModule mod, String name, UnitInputPort input, int part);
	IOutputPort newOutputPort(IModule mod, String name, UnitOutputPort output);
	IOutputPort newOutputPort(IModule mod, String name, UnitOutputPort output, int part);
}