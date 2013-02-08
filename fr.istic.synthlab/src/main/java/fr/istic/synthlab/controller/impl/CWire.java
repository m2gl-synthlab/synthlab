package fr.istic.synthlab.controller.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.impl.Wire;
import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPOutputPort;
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

	@Override
	public void connect(IInputPort port) {
		super.connect(port);
		IPInputPort pInputPort = ((ICInputPort) port).getPresentation();
//		((ICModule)((ICInputPort) port).getModule()).addWire(this);
		pres.c2pConnectIn(pInputPort);
	}

	@Override
	public void connect(IOutputPort port) {
		super.connect(port);
		IPOutputPort pOutputPort = ((ICOutputPort) port).getPresentation();
//		((ICModule)((ICOutputPort) port).getModule()).addWire(this);
		pres.c2pConnectOut(pOutputPort);
	}

	@Override
	public void disconnect() {
		System.out.println("CWire disconnect");
		ICSynthesizer synth = ((ICSynthesizer)this.getInput().getModule().getSynthesizer());
		synth.getPresentation().removeWire(pres);
		pres = null;
		super.disconnect();
	}
	
}
