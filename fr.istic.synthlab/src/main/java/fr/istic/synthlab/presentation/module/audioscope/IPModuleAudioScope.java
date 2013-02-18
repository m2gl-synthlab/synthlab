package fr.istic.synthlab.presentation.module.audioscope;

import fr.istic.synthlab.controller.module.audioscope.ICModuleAudioScope;
import fr.istic.synthlab.presentation.module.IPModule;

public interface IPModuleAudioScope extends IPModule {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	public ICModuleAudioScope getControl();

}
