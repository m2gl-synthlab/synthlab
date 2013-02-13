package fr.istic.synthlab.presentation.port;

import fr.istic.synthlab.controller.port.ICOutputPort;

public interface IPOutputPort extends IPPort {

	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICOutputPort getControl();

}
