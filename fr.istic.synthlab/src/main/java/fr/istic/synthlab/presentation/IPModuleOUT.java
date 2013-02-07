package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICModuleOUT;

public interface IPModuleOUT extends IPModule {

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	public ICModuleOUT getControl();

	/**
	 * Called by the controller to inform the presentation of the new mute value
	 */
	public void c2pMute();

	/**
	 * Called by the controller to inform the presentation of the new mute value
	 */
	public void c2pUnmute();

	/**
	 * Called by the controller to inform the presentation of the new gain value
	 * 
	 * @param gain
	 */
	public void c2pSetGainValue(double gain);

}
