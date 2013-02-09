package fr.istic.synthlab.presentation.module.eg;

import fr.istic.synthlab.controller.module.eg.ICModuleEG;
import fr.istic.synthlab.presentation.module.IPModule;

/**
 * Interface for a EG module presentation
 */
public interface IPModuleEG extends IPModule {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.presentation.IPModule#getControl()
	 */
	public ICModuleEG getControl();

	/**
	 * Inform the presentation of a new value
	 * 
	 * @param attack
	 */
	void c2pSetAttackValue(double attack);

	/**
	 * Inform the presentation of a new value
	 * 
	 * @param decay
	 */
	void c2pSetDecayValue(double decay);

	/**
	 * Inform the presentation of a new value
	 * 
	 * @param sustain
	 */
	void c2pSetSustainValue(double sustain);

	/**
	 * Inform the presentation of a new value
	 * 
	 * @param release
	 */
	void c2pSetReleaseValue(double release);

}
