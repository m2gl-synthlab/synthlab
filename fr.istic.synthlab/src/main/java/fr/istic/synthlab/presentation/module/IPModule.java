package fr.istic.synthlab.presentation.module;

import fr.istic.synthlab.controller.module.ICModule;

/**
 * Generic interface for module presentation
 */
public interface IPModule {
	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICModule getControl();

	public int getHeight();

	public int getWidth();

}
