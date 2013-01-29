package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.presentation.IPSynthesizer;

public interface ICSynthesizer extends ISynthesizer {
	
	/**
	 * Return the synthesizer's presentation
	 * 
	 * @return presentation
	 */
	public IPSynthesizer getPresentation();
	
}
