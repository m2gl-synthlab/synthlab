package fr.istic.synthlab.factory.impl;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.factory.IPFactory;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPParameter;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.IPWire;
import fr.istic.synthlab.presentation.impl.PInputPort;
import fr.istic.synthlab.presentation.impl.PModule;
import fr.istic.synthlab.presentation.impl.POutputPort;
import fr.istic.synthlab.presentation.impl.PParameter;
import fr.istic.synthlab.presentation.impl.PSynthesizer;
import fr.istic.synthlab.presentation.impl.PWire;

public class PFactory implements IPFactory{

	private static final PFactory instance = new PFactory();

	private PFactory() {}

	public static PFactory getInstance() {
		return instance;
	}

	@Override
	public IPSynthesizer newSynthesizer(ICSynthesizer control) {
		return new PSynthesizer(control);
	}

	@Override
	public IPModule newModule(ICModule control) {
		return new PModule(control);
	}

	@Override
	public IPModule newVCO(ICModule control) {
		return new PModule(control); //TODO
	}

	@Override
	public IPModule newVCF(ICModule control) {
		return new PModule(control); //TODO
	}

	@Override
	public IPModule newVCA(ICModule control) {
		return new PModule(control); //TODO
	}
	
	@Override
	public IPWire newWire(ICWire control) {
		return new PWire(control); //TODO
	}
	
	@Override
	public IPOutputPort newOutputPort(ICOutputPort control) {
		return new POutputPort(control); //TODO
	}

	@Override
	public IPInputPort newInputPort(ICInputPort control) {
		return new PInputPort(control);
	}

	@Override
	public IPParameter newParameter(ICParameter control) {
		return new PParameter(control);
	}

}