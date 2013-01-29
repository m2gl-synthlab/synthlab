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
	 * Add a module presentation to the synthesizer
	 * 
	 * @param module
	 *            presentation
	 */
	public void addModule(IPModule module);

	/**
	 * Supprimer un composant du plan de travail
	 */
	/**
	 * Remove a module presentation from the synthesizer
	 * 
	 * @param module
	 */
	public void removeModule(IPModule module);

}
