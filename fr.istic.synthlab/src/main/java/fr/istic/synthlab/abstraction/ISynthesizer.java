package fr.istic.synthlab.abstraction;

import java.util.List;


public interface ISynthesizer {

	/**
	 * Start the synthesizer
	 */
	public void start();
	
	/**
	 * Stop the synthesizer
	 */
	public void stop();

	/**
	 * Is the synthesizer running ?
	 * @return boolean
	 */
	public boolean isRunning();
	
	/**
	 * Add the given module to the synthesizer
	 * @param module
	 */
	public void addModule(IModule module);
	
	/**
	 * Return the given module from the synthesizer
	 * @param module
	 */
	public IModule getModule(int i);
	
	/**
	 * Return all modules from the synthesizer
	 * @param module
	 */
	public List<IModule> getModules();
	
	/**
	 * Remove the given module from the synthesizer
	 * @param module
	 */
	public void removeModule(IModule module);
	
	
}
