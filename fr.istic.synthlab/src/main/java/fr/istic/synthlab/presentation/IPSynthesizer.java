package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICSynthesizer;

public interface IPSynthesizer {

	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICSynthesizer getControl();

	/**
	 * Try to start the synthesizer
	 */
	public void start();

	/**
	 * Try to stop the synthesizer
	 */
	public void stop();

	/**
	 * Add a module presentation to the synthesizer
	 * 
	 * @param module
	 *            presentation
	 */
	public void addModule(IPModule module);

	/**
	 * Remove a module presentation from the synthesizer
	 * 
	 * @param module
	 */
	public void removeModule(IPModule module);

	// INTERACTIONS
	/**
	 * Start the synthesizer's presentation
	 */
	public void c2pStart();

	/**
	 * Stop the synthesizer's presentation
	 */
	public void c2pStop();

	/**
	 * The presentation must add the given module presentation
	 * 
	 * @param presentation
	 */
	public void c2pAddModule(IPModule module);

	/**
	 * The presentation can remove the given module presentation
	 * 
	 * @param presentation
	 */
	public void c2pRemoveModuleOk(IPModule module);

	/**
	 * The presentation can add the given module presentation
	 * 
	 * @param presentation
	 */
	public void c2pAddModuleOk(IPModule module);

	/**
	 * The presentation can add the given wire presentation
	 * 
	 * @param wire
	 */
	public void c2pAddWire(IPWire presentation);

	public void removeWire(IPWire pres);

}
