package fr.istic.synthlab;

import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;

/**
 * Interface for the synthesizer frame
 */
public interface ISynthFrame {

	/**
	 * Display the synthesizer presentation
	 * 
	 * @param presentation
	 */
	public void displaySynth();

	/**
	 * Close the synthesizer
	 */
	public void quitSynth();

	/**
	 * Add a synthesizer in the list
	 * 
	 * @param currentSynth
	 */
	public void addToMenu(ICSynthesizer currentSynth);

	/**
	 * Remove a synthesizer from the list
	 * 
	 * @param currentSynth
	 */
	public void removeFromMenu(ICSynthesizer currentSynth);

	// TODO : need comments
	public void stop();

}
