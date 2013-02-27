package fr.istic.synthlab.controller.wire;

import java.awt.Color;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.port.IPInputPort;
import fr.istic.synthlab.presentation.port.IPOutputPort;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;
import fr.istic.synthlab.presentation.wire.IPWire;

public class CWire extends Wire implements ICWire {

	private IPWire pres;
	private ISynthesizer cSynthesizer;

	public CWire(ISynthesizer cSynthesizer) {
		super(cSynthesizer);
		this.cSynthesizer = cSynthesizer;
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
		((ICSynthesizer) cSynthesizer).getPresentation().removeWire(pres);
		super.disconnect();
	}

	@Override
	public Color getCurrentWireColor() {
		return ((ICSynthesizer) cSynthesizer).getCurrentWireColor();
	}

	@Override
	public IPSynthesizer getSynthesizerPresentation() {
		return ((ICSynthesizer) cSynthesizer).getPresentation();
	}

	@Override
	public void setCurrentWire() {
		cSynthesizer.setCurrentWire(this);
	}

}
