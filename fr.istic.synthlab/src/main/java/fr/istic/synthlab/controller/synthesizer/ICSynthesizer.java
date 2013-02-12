package fr.istic.synthlab.controller.synthesizer;

import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

public interface ICSynthesizer extends ISynthesizer {

	/**
	 * Return the synthesizer's presentation
	 * 
	 * @return presentation
	 */
	public IPSynthesizer getPresentation();

	/**
	 * The presentation ask to start the synthesizer
	 */
	public void p2cStart();

	/**
	 * The presentation ask to stop the synthesizer
	 */
	public void p2cStop();

	/**
	 * The presentation ask to add a module
	 * @param module
	 */
	public void p2cAddModule(ICModule module);

	/**
	 * The presentation ask to remove a module
	 * @param module
	 */
	public void p2cRemoveModule(ICModule module);

	public void p2cDisconnectCurrentWire();
}
