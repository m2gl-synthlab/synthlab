package fr.istic.synthlab.controller.port;

import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.presentation.port.IPOutputPort;

/**
 * Interface for an OutputPort controller
 */
public interface ICOutputPort extends ICPort, IOutputPort {
	
	/**
	 * Return the output port's presentation
	 * 
	 * @return presentation
	 */
	public IPOutputPort getPresentation();
}