package fr.istic.synthlab;

import fr.istic.synthlab.presentation.IPSynthesizer;

public interface ISynthFrame {

	/**
	 * Display the synthesizer presentation
	 * @param presentation
	 */
	public void displaySynth(IPSynthesizer presentation);

	/**
	 * Close the synthesizer
	 */
	public void quitSynth();

}
