package fr.istic.synthlab.controller.port;

import com.jsyn.ports.ConnectableOutput;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.OutputPort;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.port.IPOutputPort;

public class COutputPort extends OutputPort implements ICOutputPort {

	private IPOutputPort pres;
	private ICSynthesizer cSynthesizer;

	public COutputPort(ICSynthesizer cSynthesizer, String name, ConnectableOutput output, IModule module) {
		super(name, output, module);
		this.cSynthesizer = cSynthesizer;
		this.pres = PACFactory.getPFactory().newOutputPort(this);
	}

	@Override
	public IPOutputPort getPresentation() {
		return pres;
	}

	@Override
	public void p2cMouseHover() {
		if (getWire() == null) {
			if (cSynthesizer.getCurrentWire() != null) {
				if (cSynthesizer.getCurrentWire().getInput() != null) {
					pres.c2pConnectionAllowed();
				} else {
					pres.c2pConnectionNotAllowed();
				}
			} else {
				pres.c2pConnectionAllowed();
			}
		} else {
			if (cSynthesizer.getCurrentWire() == null) {
				pres.c2pConnectionAllowed();
			} else {
				pres.c2pConnectionNotAllowed();
			}
		}
	}

	@Override
	public void p2cMouseClicked() {

		// If there is no wire already connected
		if (getWire() == null) {
			IWire currentWire = cSynthesizer.getCurrentWire();

			// If there is no current wire in the synthesizer
			if (currentWire == null) {
				// We create a new one
				cSynthesizer.setCurrentWire(PACFactory.getFactory().newWire(cSynthesizer));
				currentWire = cSynthesizer.getCurrentWire();
			}
			// If there is an existing wire in the synthesizer
			else {
				// We check if there is already an Output port connected with it
				if (cSynthesizer.getCurrentWire().getOutput() != null) {
					pres.c2pConnectionAttemptFailed();
					return;
				}
			}

			// Finally, we attempt to connect
			try {
				currentWire.connect(this);
			} catch (PortAlreadyInUseException e1) {
				// The port is already connected
				pres.c2pConnectionAttemptFailed();
			} catch (BadConnectionException e) {
				// The wire can't connect to the port
				pres.c2pConnectionAttemptFailed();
			}

			// If the wire is well connected, delete it from the synth
			if (currentWire.isConnected()) {
				cSynthesizer.setCurrentWire(null);
			}

		}
		// If a wire is already connected, we disconnect it
		else {
			if (cSynthesizer.getCurrentWire() == null) {
				if (getWire() != null) {
					getWire().disconnect();
				}
			}
		}
	}

}
