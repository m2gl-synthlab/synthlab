package fr.istic.synthlab.presentation.port;

import fr.istic.synthlab.controller.port.ICOutputPort;

/**
 * Interface for an output port presentation
 */
public interface IPOutputPort extends IPPort {

	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICOutputPort getControl();

}
