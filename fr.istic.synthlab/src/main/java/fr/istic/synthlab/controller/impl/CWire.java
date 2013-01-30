package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.abstraction.impl.Wire;
import fr.istic.synthlab.controller.ICPort;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPWire;

public class CWire extends Wire implements ICWire {

	private IPWire pres;
	private IWire abs;
	
	public CWire() {
		this.abs = PACFactory.getAFactory().newWire(PACFactory.getAFactory());
		this.pres = PACFactory.getPFactory().newWire(this);
	}

	@Override
	public IPWire getPresentation() {
		return pres;
	}
	
	public void connect(ICPort port){
		if(port instanceof OutputPort){
			super.connect((OutputPort)port);
		} else {
			super.connect((InputPort)port);
		}
		pres.connect(port.getPresentation());
	}
}
