package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.presentation.IPModule;

public interface ICModule extends IModule{

	/**
	 * Return the module's presentation
	 * 
	 * @return presentation
	 */
	public IPModule  getPresentation();
}
