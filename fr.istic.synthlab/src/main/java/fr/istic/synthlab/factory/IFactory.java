package fr.istic.synthlab.factory;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;

public interface IFactory {

	ISynthesizer newSynthesizer();
	
//	IModule newModule(String name, IFactory factory);
	IModule newVCO();
	IModule newVCF();
	IModule newOUT();
	
	IParameter newParameter(double min, double max, double value);
	IInputPort newInputPort(String name);
	IOutputPort newOutputPort(String name);
	IWire newWire();

	IInputPort newInputPort(UnitInputPort input);
	IInputPort newInputPort(UnitInputPort input, int part);
	IOutputPort newOutputPort(UnitOutputPort output);
	IOutputPort newOutputPort(UnitOutputPort output, int part);
}
