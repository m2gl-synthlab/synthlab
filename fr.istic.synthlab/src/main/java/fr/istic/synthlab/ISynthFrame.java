package fr.istic.synthlab;

import java.awt.Color;

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
	 * Add a synthesizer in the list and update the title bar
	 * 
	 * @param currentSynth
	 */
	public void addToMenu(ICSynthesizer currentSynth);

	/**
	 * Remove a synthesizer from the menu and update the title bar
	 * 
	 * @param currentSynth
	 */
	public void removeInMenu(ICSynthesizer currentSynth);

	/**
	 * Set the play/stop button to the Stop state
	 */
	public void stopTheButton();

	/**
	 * Select the given synthesizer in the menu
	 * 
	 * @param currentSynth
	 */
	public void selectInMenu(ICSynthesizer currentSynth);

	/**
	 * Return the current wire Color
	 * @return
	 */
	Color getCurrentWireColor();

}
