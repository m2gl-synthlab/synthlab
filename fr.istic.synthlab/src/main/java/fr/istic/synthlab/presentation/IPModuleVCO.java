package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICModuleVCO;

public interface IPModuleVCO extends IPModule {
	
	/* (non-Javadoc)
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	public ICModuleVCO getControl();
	
	/**
	 * Called by the controller to inform the presentation of the new octave value
	 * 
	 * @param octave
	 */
	public void c2pSetOctaveValue(int octave);
	
	/**
	 * Called by the controller to inform the presentation of the new tone value
	 * 
	 * @param tone
	 */
	public void c2pSetToneValue(double tone);
	
	/**
	 * Called by the controller to inform the presentation of the new frequency value
	 * 
	 * @param frequency
	 */
	public void c2pSetFrequencyValue(double frequency);

}
