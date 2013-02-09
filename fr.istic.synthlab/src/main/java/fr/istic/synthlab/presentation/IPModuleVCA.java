package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICModuleVCA;

public interface IPModuleVCA extends IPModule {
	
	/* (non-Javadoc)
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	public ICModuleVCA getControl();
	
	/**
	 * Called by the controller to inform the presentation of the new attenuation value
	 * 
	 * @param attenuation value
	 */
	public void c2pSetAttenuationValue(double attenuationValue);
}
