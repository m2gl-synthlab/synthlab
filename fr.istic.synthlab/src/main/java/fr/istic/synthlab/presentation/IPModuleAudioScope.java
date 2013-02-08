package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICModuleAudioScope;


public interface IPModuleAudioScope extends IPModule {

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	public ICModuleAudioScope getControl();

}
