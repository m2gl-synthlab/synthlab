package fr.istic.synthlab.controller.module.mix;

import fr.istic.synthlab.abstraction.module.mix.IModuleMIX;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.module.mix.IPModuleMIX;

public interface ICModuleMIX extends ICModule, IModuleMIX {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.controller.ICModule#getPresentation()
	 */
	public IPModuleMIX getPresentation();

	/**
	 * Called by the presentation when the gain 1 changed
	 * 
	 * @param gain
	 */
	public void p2cGain1Changed(double gain);

	/**
	 * Called by the presentation when the gain 2 changed
	 * 
	 * @param gain
	 */
	public void p2cGain2Changed(double gain);

	/**
	 * Called by the presentation when the gain 3 changed
	 * 
	 * @param gain
	 */
	public void p2cGain3Changed(double gain);

	/**
	 * Called by the presentation when the gain 4 changed
	 * 
	 * @param gain
	 */
	public void p2cGain4Changed(double gain);

}
