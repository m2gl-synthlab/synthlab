package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICOutputPort;

public interface IPOutputPort {

	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICOutputPort getControl();

	public void c2pSetName();
}
