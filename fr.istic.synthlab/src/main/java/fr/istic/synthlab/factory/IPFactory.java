package fr.istic.synthlab.factory;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.IPWire;

public interface IPFactory {

	IPSynthesizer newSynthesizer(ICSynthesizer control);
	
	IPModule newModule(ICModule control);
	IPModule newVCO(ICModule control);
	IPModule newVCF(ICModule control);
	IPModule newVCA(ICModule control);
	IPWire newWire(ICWire control);
	IPOutputPort newOutputPort(ICOutputPort control);
	IPInputPort newInputPort(ICInputPort cInputPort);
	
//	IPParameter newParameter(String name, IFactory factory);
//	IPPort newInputPort(String name, IFactory factory);
//	IPPort newOutputPort(String name, IFactory factory);
//	IPWire newWire(IFactory factory);
}
