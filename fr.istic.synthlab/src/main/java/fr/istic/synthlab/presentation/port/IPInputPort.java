package fr.istic.synthlab.presentation.port;

import fr.istic.synthlab.controller.port.ICInputPort;

/**
 * Interface of an input port presentation
 */
public interface IPInputPort extends IPPort {

	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICInputPort getControl();

}
