package fr.istic.synthlab.controller.port;

import com.jsyn.ports.ConnectableOutput;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.OutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.port.IPOutputPort;

public class COutputPort extends OutputPort implements ICOutputPort {

	private IPOutputPort pres;

	public COutputPort(String name, ConnectableOutput output, IModule module) {
		super(name, output, module);
		this.pres = PACFactory.getPFactory().newOutputPort(this);
	}

	@Override
	public IPOutputPort getPresentation() {
		return pres;
	}

	// @Override
	// public void p2cConnect() {
	// ISynthesizer synth = Synthesizer.getInstance();
	//
	// // Get the current wire if any
	// IWire currentWire = synth.getCurrentWire();
	// if (currentWire == null) {
	// // Create it if null
	// synth.setCurrentWire(PACFactory.getFactory().newWire());
	// currentWire = synth.getCurrentWire();
	// }
	//
	// // Try to connect the wire to the port
	// try {
	// currentWire.connect(this);
	// } catch (PortAlreadyInUseException e1) {
	// // The port is already connected
	// // TODO : inform the presentation that the connexion is impossible
	// } catch (BadConnectionException e) {
	// // The wire can't connect to the port
	// // TODO : inform the presentation that the wire is connected to the same
	// type of port
	// }
	//
	// // If the wire is well connected, delete it from the synth
	// if (currentWire.isConnected()) {
	// synth.setCurrentWire(null);
	// }
	//
	// }
	//
	// @Override
	// public void p2cDisconnect() {
	// if (getWire() != null) {
	// getWire().disconnect();
	// }
	// }

	@Override
	public void p2cMouseHover() {
		if (getWire() == null) {
			if (CSynthesizer.getInstance().getCurrentWire() != null) {
				if (CSynthesizer.getInstance().getCurrentWire().getInput() != null) {
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
				// We check if there is already an Output port connected with it
				if (CSynthesizer.getInstance().getCurrentWire().getOutput() != null) {
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
