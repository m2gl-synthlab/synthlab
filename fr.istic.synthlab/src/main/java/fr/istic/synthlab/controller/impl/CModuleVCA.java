package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.impl.ModuleVCA;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;

public class CModuleVCA extends ModuleVCA implements ICModule{

	private IPModule pres;

	public CModuleVCA(String name) {
		super();
		this.pres = PACFactory.getPFactory().newModule(this);
		
		IInputPort input = this.getInput(INPUT_IN);
		pres.addInputPort(((ICInputPort) input).getPresentation());
	}

	@Override
	public IPModule getPresentation() {
		return pres;
	}
	
}
