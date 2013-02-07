package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICInputPort;


public interface IPInputPort{
	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICInputPort getControl();
	
	public void c2pSetName();
}
