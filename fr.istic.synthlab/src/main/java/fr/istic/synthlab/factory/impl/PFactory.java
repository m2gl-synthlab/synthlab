package fr.istic.synthlab.factory.impl;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICModuleAudioScope;
import fr.istic.synthlab.controller.ICModuleEG;
import fr.istic.synthlab.controller.ICModuleOUT;
import fr.istic.synthlab.controller.ICModuleVCO;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.factory.IPFactory;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPModuleAudioScope;
import fr.istic.synthlab.presentation.IPModuleEG;
import fr.istic.synthlab.presentation.IPModuleOUT;
import fr.istic.synthlab.presentation.IPModuleVCO;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPParameter;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.IPWire;
import fr.istic.synthlab.presentation.impl.PInputPort;
import fr.istic.synthlab.presentation.impl.PModule;
import fr.istic.synthlab.presentation.impl.PModuleAudioScope;
import fr.istic.synthlab.presentation.impl.PModuleEG;
import fr.istic.synthlab.presentation.impl.PModuleOUT;
import fr.istic.synthlab.presentation.impl.PModuleVCO;
import fr.istic.synthlab.presentation.impl.POutputPort;
import fr.istic.synthlab.presentation.impl.PParameter;
import fr.istic.synthlab.presentation.impl.PSwitch;
import fr.istic.synthlab.presentation.impl.PSynthesizer;
import fr.istic.synthlab.presentation.impl.PWire;

public class PFactory implements IPFactory {

	private static final PFactory instance = new PFactory();

	private PFactory() {
	}

	public static PFactory getInstance() {
		return instance;
	}

	@Override
	public IPSynthesizer newSynthesizer(ICSynthesizer control) {
		return new PSynthesizer(control);
	}

	@Override
	public IPModuleVCO newVCO(ICModuleVCO control) {
		return new PModuleVCO(control);
	}

	@Override
	public IPModule newVCF(ICModule control) {
		return new PModule(control);// FIXME : Refactor this module
	}

	@Override
	public IPModuleOUT newOUT(ICModuleOUT control) {
		return new PModuleOUT(control);
	}
	
	@Override
	public IPModuleEG newEG(ICModuleEG control) {
		return new PModuleEG(control);
	}
	
	@Override
	public IPModuleAudioScope newAudioScope(ICModuleAudioScope control) {
		return new PModuleAudioScope(control);
	}


	
	
	@Override
	public IPWire newWire(ICWire control) {
		return new PWire(control); // TODO Ameliorer Presentation
	}

	@Override
	public IPOutputPort newOutputPort(ICOutputPort control) {
		return new POutputPort(control); // TODO Ameliorer Presentation
	}

	@Override
	public IPInputPort newInputPort(ICInputPort control) {
		return new PInputPort(control);
	}

	@Override
	public IPParameter newParameter(ICParameter control) {
		return new PParameter(control);
	}

	@Override
	public IPParameter newSwitch(ICParameter control) {
		return new PSwitch(control);
	}



}