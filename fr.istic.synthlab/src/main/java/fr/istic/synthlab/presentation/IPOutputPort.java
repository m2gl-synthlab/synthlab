package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.controller.ICParameter;

public interface IPOutputPort {
	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICOutputPort getControl();
}
