package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IModuleVCO;
import fr.istic.synthlab.presentation.IPModuleVCO;

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
