package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.abstraction.impl.Wire;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPWire;

public class CWire extends Wire implements ICWire {

	private IPWire pres;
	
	public CWire() {
		this.pres = PACFactory.getPFactory().newWire(this);
	}

	@Override
	public IPWire getPresentation() {
		return pres;
	}

//	public void connect(ICPort port){
//		if(port instanceof OutputPort){
//			super.connect((OutputPort)port);
//		} else {
//			super.connect((InputPort)port);
//			
//		}
//		((JPanel)port.getPresentation()).getX();
//		((JPanel)port.getPresentation()).getY();
//		
//		port.getPresentation().connect(pres);
//	}
	
	@Override
	public void connect(InputPort port) {
		super.connect(port);
		// TODO : Inform view
	}
	
	@Override
	public void connect(OutputPort port) {
		super.connect(port);
		// TODO : Inform view
	}
	
}
