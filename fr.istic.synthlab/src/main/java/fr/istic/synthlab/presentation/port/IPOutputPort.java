package fr.istic.synthlab.presentation.port;

import fr.istic.synthlab.controller.port.ICOutputPort;

public interface IPOutputPort {

	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICOutputPort getControl();

	public void c2pSetName();

	public void c2pClickAllowed();

	public void c2pClickNotAllowed();
}
