package fr.istic.synthlab.controller.port;

import com.jsyn.ports.ConnectableInput;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.InputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
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
	public void p2cMouseHover() {
		if (getWire() == null) {
			if (CSynthesizer.getInstance().getCurrentWire() != null) {
				if (CSynthesizer.getInstance().getCurrentWire().getOutput() != null) {
					pres.c2pConnectionAllowed();
				} else {
					pres.c2pConnectionNotAllowed();
				}
			} else {
				pres.c2pConnectionAllowed();
			}
		} else {
			if (CSynthesizer.getInstance().getCurrentWire() == null) {
				pres.c2pConnectionAllowed();
			} else {
				pres.c2pConnectionNotAllowed();
			}
		}
	}

	@Override
	public void p2cMouseClicked() {
		ISynthesizer synth = Synthesizer.getInstance();

		// If there is no wire already connected
		if (getWire() == null) {
			IWire currentWire = synth.getCurrentWire();

			// If there is no current wire in the synthesizer
			if (currentWire == null) {
				// We create a new one
				synth.setCurrentWire(PACFactory.getFactory().newWire());
				currentWire = synth.getCurrentWire();
			}
			// If there is an existing wire in the synthesizer
			else {
				// We check if there is already an Input port connected with it
				if (CSynthesizer.getInstance().getCurrentWire().getInput() != null) {
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
				synth.setCurrentWire(null);
			}

		}
		// If a wire is already connected, we disconnect it
		else {
			if (CSynthesizer.getInstance().getCurrentWire() == null) {
				if (getWire() != null) {
					getWire().disconnect();
				}
			}
		}
	}

}
