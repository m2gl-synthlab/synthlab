package fr.istic.synthlab.controller.wire;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
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
		try {
			super.connect(port);
		} catch (PortAlreadyInUseException | BadConnectionException e) {
			System.err.println(e.getMessage());
		}
		IPInputPort pInputPort = ((ICInputPort) port).getPresentation();
		pres.c2pConnectIn(pInputPort);
	}

	@Override
	public void connect(IOutputPort port) {
		try {
			super.connect(port);
		} catch (PortAlreadyInUseException | BadConnectionException e) {
			System.err.println(e.getMessage());
		}
		IPOutputPort pOutputPort = ((ICOutputPort) port).getPresentation();
		pres.c2pConnectOut(pOutputPort);
	}

	@Override
	public void disconnect() {
		ICSynthesizer synth = (ICSynthesizer) Synthesizer.getInstance();
		synth.getPresentation().removeWire(pres);
//		pres = null;
		super.disconnect();
	}

}
