package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IModuleAudioScope;
import fr.istic.synthlab.presentation.IPModuleAudioScope;


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
