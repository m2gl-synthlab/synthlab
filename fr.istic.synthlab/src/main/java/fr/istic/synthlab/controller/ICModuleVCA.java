package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IModuleVCA;
import fr.istic.synthlab.presentation.IPModuleVCA;

public interface ICModuleVCA extends ICModule, IModuleVCA {
	
	/**
	 * Called by the presentation when the attenuation value changed
	 * 
	 * @param attenuation value
	 */
	public void p2cAttenuationValueChanged(double attenuationValue);

	public IPModuleVCA getPresentation();
	
}
