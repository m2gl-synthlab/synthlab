package fr.istic.synthlab.factory;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICModuleEG;
import fr.istic.synthlab.controller.ICModuleOUT;
import fr.istic.synthlab.controller.ICModuleVCO;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPModuleEG;
import fr.istic.synthlab.presentation.IPModuleOUT;
import fr.istic.synthlab.presentation.IPModuleVCO;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPParameter;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.IPWire;

public interface IPFactory {

	// Synthesize
	IPSynthesizer newSynthesizer(ICSynthesizer control);

	// Modules
	IPModuleVCO newVCO(ICModuleVCO control);

	IPModule newVCF(ICModule control);

	IPModuleOUT newOUT(ICModuleOUT control);

	IPModuleEG newEG(ICModuleEG control);

	// Connectivity
	IPWire newWire(ICWire control);

	IPOutputPort newOutputPort(ICOutputPort control);

	IPInputPort newInputPort(ICInputPort cInputPort);

	// Deprecated
	@Deprecated
	IPParameter newParameter(ICParameter cParameter);

	@Deprecated
	IPParameter newSwitch(ICParameter cSwitch);

}
