package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.impl.ModuleOUT;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICParameter;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;

public class CModuleOUT extends ModuleOUT implements ICModule{

	private IPModule pres;

	public CModuleOUT(String name) {
		super(name);
		this.pres = PACFactory.getPFactory().newModule(this);
		
		IInputPort input = this.getInput(INPUT_IN);
		pres.addInputPort(((ICInputPort) input).getPresentation());
		
		IParameter gain = this.getParameter(PARAM_GAIN);
		pres.addParameter(((ICParameter) gain).getPresentation());
		
		IParameter switchOnOff = this.getParameter(PARAM_SWITCH_ON_OFF);
		pres.addParameter(((ICParameter) switchOnOff).getPresentation());
	}

	@Override
	public IPModule getPresentation() {
		return pres;
	}
	
}
