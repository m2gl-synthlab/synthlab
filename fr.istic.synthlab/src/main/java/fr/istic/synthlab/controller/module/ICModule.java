package fr.istic.synthlab.controller.module;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.presentation.module.IPModule;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

/**
 * Generic interface for module controllers
 */
public interface ICModule extends IModule {

	/**
	 * Return the module's presentation
	 * 
	 * @return presentation
	 */
	public IPModule getPresentation();

	/**
	 * Called when the presentation is closing
	 */
	public void p2cClosing();

	/**
	 * Set the given parameter
	 * 
	 * @param key
	 * @param value
	 */
	public void setParameter(String key, Double value);

	public IPSynthesizer getSynthesizerPresentation();

	public void p2cRemoveModule(ICModule module);
}
