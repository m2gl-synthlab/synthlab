package fr.istic.synthlab.controller.module.noise;

import fr.istic.synthlab.abstraction.module.noise.IModuleNOISE;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.module.noise.IPModuleNOISE;

/**
 * Interface of a NOISE module controller
 */
public interface ICModuleNOISE extends ICModule, IModuleNOISE {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.controller.ICModule#getPresentation()
	 */
	public IPModuleNOISE getPresentation();

	/**
	 * Called by the presentation when the gain changed
	 * 
	 * @param gain
	 */
	public void p2cGainChanged(double gain);
}
