package fr.istic.synthlab.factory;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.IPort;
import fr.istic.synthlab.abstraction.ISynthetizer;
import fr.istic.synthlab.abstraction.IWire;

public interface IFactory {

	ISynthetizer newSynthesizer(IFactory factory);
	
	IModule newModule(String name, IFactory factory);
	IModule newVCO(IFactory factory);
	IModule newVCF(IFactory factory);
	IModule newVCA(IFactory factory);
	
	IParameter newParameter(String name, IFactory factory);
	IPort newInputPort(String name, IFactory factory);
	IPort newOutputPort(String name, IFactory factory);
	IWire newWire(IFactory factory);
}