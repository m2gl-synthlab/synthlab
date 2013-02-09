package fr.istic.synthlab.controller.module.out;

import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.module.out.IPModuleOUT;

/**
 * Interface of a OUT module controller
 */
public interface ICModuleOUT extends ICModule, IModuleOUT {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.controller.ICModule#getPresentation()
	 */
	public IPModuleOUT getPresentation();

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
}
