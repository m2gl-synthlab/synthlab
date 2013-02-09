package fr.istic.synthlab.controller.wire;

import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.port.IPInputPort;
import fr.istic.synthlab.presentation.port.IPOutputPort;
import fr.istic.synthlab.presentation.wire.IPWire;

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
