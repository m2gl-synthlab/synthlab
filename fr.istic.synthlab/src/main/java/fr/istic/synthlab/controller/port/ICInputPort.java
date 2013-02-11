package fr.istic.synthlab.controller.port;

import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.presentation.port.IPInputPort;

public interface ICInputPort extends IInputPort {
	/**
	 * Return the input port's presentation
	 * 
	 * @return presentation
	 */
	public IPInputPort getPresentation();

	public void p2cConnect();

	public IWire getWire();

	public void p2cDisconnect();

	public void p2cCanConnect();
}
