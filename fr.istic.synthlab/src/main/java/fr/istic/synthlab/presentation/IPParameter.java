package fr.istic.synthlab.presentation;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.controller.ICParameter;


public interface IPParameter {

	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICParameter getControl();

	public void connect(IInputPort input);
}
