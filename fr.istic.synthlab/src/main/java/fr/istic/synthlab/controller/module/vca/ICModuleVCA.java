package fr.istic.synthlab.controller.module.vca;

import fr.istic.synthlab.abstraction.module.vca.IModuleVCA;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.module.vca.IPModuleVCA;

public interface ICModuleVCA extends ICModule, IModuleVCA {
	
	/**
	 * Called by the presentation when the attenuation value changed
	 * 
	 * @param attenuation value
	 */
	public void p2cAttenuationValueChanged(double attenuationValue);

	public IPModuleVCA getPresentation();
	
}
