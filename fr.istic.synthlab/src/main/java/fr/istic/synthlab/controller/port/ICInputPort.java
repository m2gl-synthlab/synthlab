package fr.istic.synthlab.controller.port;

import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.presentation.port.IPInputPort;

/**
 * Interface for an InputPort controller
 */
public interface ICInputPort extends ICPort, IInputPort {

	/**
	 * Return the input port's presentation
	 * 
	 * @return presentation
	 */
	public IPInputPort getPresentation();

}
