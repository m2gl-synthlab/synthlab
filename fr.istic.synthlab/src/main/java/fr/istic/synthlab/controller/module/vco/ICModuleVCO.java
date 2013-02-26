package fr.istic.synthlab.controller.module.vco;

import fr.istic.synthlab.abstraction.module.vco.IModuleVCO;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.module.vco.IPModuleVCO;

public interface ICModuleVCO extends ICModule, IModuleVCO {

	/**
	 * Called by the presentation when the octave param change
	 * 
	 * @param octave
	 */
	public void p2cOctaveChanged(int octave);

	/**
	 * Called by the presentation when the tone param changed
	 * 
	 * @param tone
	 */
	public void p2cToneChanged(double tone);

	public IPModuleVCO getPresentation();

}
