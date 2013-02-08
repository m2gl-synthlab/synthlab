package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICModuleVCF;

public interface IPModuleVCF extends IPModule {
	
	/* (non-Javadoc)
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	public ICModuleVCF getControl();
	
	/**
	 * Called by the controller to inform the presentation of the new cut frequency value
	 * 
	 * @param cut frequency
	 */
	public void c2pSetCutFrequencyValue(double cutFrequency);
	
	/**
	 * Called by the controller to inform the presentation of the new resonance value
	 * 
	 * @param resonance
	 */
	public void c2pSetResonanceValue(double resonance);
	
}
