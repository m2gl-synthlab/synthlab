package fr.istic.synthlab.controller.module;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.presentation.module.IPModule;

/**
 * Generic interface for module controllers
 */
public interface ICModule extends IModule {

	/**
	 * Return the module's presentation
	 * 
	 * @return presentation
	 */
	public IPModule getPresentation();

	/**
	 * Called when the presentation is closing
	 */
	public void p2cClosing();
}
