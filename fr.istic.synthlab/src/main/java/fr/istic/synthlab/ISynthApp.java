package fr.istic.synthlab;

import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;

/**
 * Interface for the synthesizer application
 */
public interface ISynthApp {

	/**
	 * Create a new instance, display and add it to the menu
	 */
	public void newSynth();

	/**
	 * Restart the synthesizer
	 */
	public void startSynth();

	/**
	 * Quit the synthesizer
	 */
	public void quitSynth();

	/**
	 * Set the current synthesizer controller
	 * 
	 * @param synthesizer
	 */
	public void setSynthesizer(String string);

	/**
	 * Return the current synthesizer controller
	 * 
	 * @return the current synthesizer controller
	 */
	public ICSynthesizer getSynthesizer();

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

	/**
	 * Save the current instance to an XML file
	 */
	public void saveToXML(String fileDir, String filename);

	/**
	 * Load an instance from an XML file
	 */
	public void loadFromXML(String dir, String file);

	/**
	 * Return the path of the current instance
	 * 
	 * @return
	 */
	public String[] getCurrentFile();

	/**
	 * Display a new default synthesizer
	 */
	void displayNewDefaultSynth();

	/**
	 * Close the given synth
	 */
	public void remove(ISynthesizer synth);

}
