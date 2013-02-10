package fr.istic.synthlab.controller.port;

import com.jsyn.ports.ConnectableInput;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.InputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.port.IPInputPort;

public class CInputPort extends InputPort implements ICInputPort {

	private IPInputPort pres;

	public CInputPort(String name, ConnectableInput input, IModule module) {
		super(name, input, module);
		this.pres = PACFactory.getPFactory().newInputPort(this);
	}

	@Override
	public IPInputPort getPresentation() {
		return pres;
	}

	@Override
	public void p2cConnect() {
		ISynthesizer synth = Synthesizer.getInstance();
		
		// Get the current wire if any
		IWire currentWire = synth.getCurrentWire();
		if (currentWire == null) {
			// Create it if null
			synth.setCurrentWire(PACFactory.getFactory().newWire());
			currentWire = synth.getCurrentWire();
		}

		// Try to connect the wire to the port
		try {
			currentWire.connect(this);
		} catch (PortAlreadyInUseException e1) {
			// The port is already connected
			// TODO : inform the presentation that the connexion is impossible
		} catch (BadConnectionException e) {
			// The wire can't connect to the port
			// TODO : inform the presentation that the wire is connected to the same type of port
		}

		// If the wire is well connected, delete it from the synth
		if (currentWire.isConnected()) {
			synth.setCurrentWire(null);
		}

	}

	@Override
	public void p2cDisconnect() {
		if (getWire() != null) {
			getWire().disconnect();
		}
	}

}
