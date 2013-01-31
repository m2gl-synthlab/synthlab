package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICPort;

public interface IPPort {
	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICPort getControl();
	public void connect(IPWire wire);
}
