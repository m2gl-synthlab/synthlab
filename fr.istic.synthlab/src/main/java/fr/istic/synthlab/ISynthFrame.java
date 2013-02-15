package fr.istic.synthlab;

import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

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

//	public void setSynthesizer(ICSynthesizer synth);

	public void addToMenu(ICSynthesizer currentSynth);

	public void stop();

	public void removeFromMenu(ICSynthesizer currentSynth);


}
