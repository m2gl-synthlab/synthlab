package fr.istic.synthlab.presentation.module.noise;

import fr.istic.synthlab.controller.module.noise.ICModuleNOISE;
import fr.istic.synthlab.presentation.module.IPModule;

/**
 * Interface for an NOISE module presentation
 */
public interface IPModuleNOISE extends IPModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	public ICModuleNOISE getControl();

	/**
	 * Called by the controller to inform the presentation of the new gain value
	 * 
	 * @param gain
	 */
	public void c2pSetGainValue(double gain);

}
