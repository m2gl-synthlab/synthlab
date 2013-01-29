package fr.istic.synthlab;

import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.impl.CSynthesizer;

public interface ISynthApp {

	/**
	 * Start the synthesizer
	 */
	public void startSynth();

	/**
	 * Restart the synthesizer
	 */
	public void newSynth();

	/**
	 * Quit the synthesizer
	 */
	public void quitSynth();

	/**
	 * Set the current synthesizer controller
	 * 
	 * @param synthesizer
	 */
	public void setSynthesizer(CSynthesizer synth);

	/**
	 * Return the current synthesizer controller
	 * 
	 * @return the current synthesizer controller
	 */
	public CSynthesizer getSynthesizer();

	/**
	 * Register a command to display the synthesizer
	 * 
	 * @param displaySynthCommand
	 */
	public void setDisplaySynthCommand(ICommand displaySynthCommand);

	/**
	 * Register a command to stop displaying the synthesizer
	 * 
	 * @param undisplaySynthCommand
	 */
	public void setUndisplaySynthCommand(ICommand undisplaySynthCommand);

}
