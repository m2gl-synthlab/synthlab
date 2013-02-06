package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IModuleOUT;
import fr.istic.synthlab.presentation.IPModuleOUT;

public interface ICModuleOUT extends ICModule, IModuleOUT {

	/**
	 * Called by the presentation when the mute button is clicked
	 */
	public void p2cMute();

	/**
	 * Called by the presentation when the gain changed
	 * 
	 * @param gain
	 */
	public void p2cGainChanged(double gain);

	public IPModuleOUT getPresentation();

}
