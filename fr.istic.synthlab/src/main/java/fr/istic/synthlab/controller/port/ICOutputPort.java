package fr.istic.synthlab.controller.port;

import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.presentation.port.IPOutputPort;

public interface ICOutputPort extends IOutputPort {
	/**
	 * Return the output port's presentation
	 * 
	 * @return presentation
	 */
	public IPOutputPort getPresentation();

	void p2cConnect();

	public void p2cDisconnect();

	public void p2cCanConnect();
}