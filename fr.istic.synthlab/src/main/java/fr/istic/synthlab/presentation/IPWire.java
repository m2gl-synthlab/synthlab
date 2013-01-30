package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICWire;


public interface IPWire {
	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICWire getControl();

	public void connect(IPPort presentation);
}
