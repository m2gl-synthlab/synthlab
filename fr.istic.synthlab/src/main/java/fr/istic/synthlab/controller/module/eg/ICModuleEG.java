package fr.istic.synthlab.controller.module.eg;

import fr.istic.synthlab.abstraction.module.eg.IModuleEG;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.module.eg.IPModuleEG;

/**
 * Interface of a OUT module controller
 */
public interface ICModuleEG extends ICModule, IModuleEG {

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.controller.ICModule#getPresentation()
	 */
	public IPModuleEG getPresentation();

	/**
	 * Called by the presentation when the attack changed
	 * 
	 * @param attack
	 */
	public void p2cAttackChanged(double attack);

	/**
	 * Called by the presentation when the decay changed
	 * 
	 * @param decay
	 */
	public void p2cDecayChanged(double decay);

	/**
	 * Called by the presentation when the sustain changed
	 * 
	 * @param sustain
	 */
	public void p2cSustainChanged(double sustain);

	/**
	 * Called by the presentation when the release changed
	 * 
	 * @param release
	 */
	public void p2cReleaseChanged(double release);
}
