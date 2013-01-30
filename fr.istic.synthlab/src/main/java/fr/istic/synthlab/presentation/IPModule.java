package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICModule;

public interface IPModule {
	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICModule getControl();
}
