package fr.istic.synthlab.controller.module.audioscope;

import fr.istic.synthlab.abstraction.module.audioscope.IModuleAudioScope;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.module.audioscope.IPModuleAudioScope;

/**
 * Interface of a AudioScope module controller
 */
public interface ICModuleAudioScope extends ICModule, IModuleAudioScope {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.controller.ICModule#getPresentation()
	 */
	public IPModuleAudioScope getPresentation();

}
