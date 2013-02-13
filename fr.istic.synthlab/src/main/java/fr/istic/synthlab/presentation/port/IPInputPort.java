package fr.istic.synthlab.presentation.port;

import fr.istic.synthlab.controller.port.ICInputPort;

public interface IPInputPort extends IPPort {

	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICInputPort getControl();

}
