
package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IModuleVCF;
import fr.istic.synthlab.presentation.IPModuleVCF;

public interface ICModuleVCF extends ICModule, IModuleVCF {

	/**
	 * Called by the presentation when the cut frequency param change
	 * 
	 * @param cut frequency
	 */
	public void p2cCutFrequencyChanged(double cutFrequency);

	/**
	 * Called by the presentation when the resonance param changed
	 * 
	 * @param resonance
	 */
	public void p2cResonanceChanged(double resonance);

	public IPModuleVCF getPresentation();
	
}
