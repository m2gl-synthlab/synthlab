package fr.istic.synthlab.abstraction;

import com.jsyn.Synthesizer;

/**
 * Interface for a synthesizer
 */
public interface ISynthesizer {

	/**
	 * Return the jSyn component of the synthesizer
	 * 
	 * @return
	 */
	public Synthesizer getJSyn();

	/**
	 * Tell if the synthesizer is running or not
	 * 
	 * @return isRunning ?
	 */
	public boolean isRunning();

	/**
	 * Start the synthesizer
	 */
	public void start();

	/**
	 * Start a synthesizer's module
	 * 
	 * @param module
	 */
	public void startModule(IModule module);

	/**
	 * Stop the synthesizer
	 */
	public void stop();

	/**
	 * Stop a synthesizer's module
	 * 
	 * @param module
	 */
	public void stopModule(IModule module);

	/**
	 * Add a module to the synthesizer
	 * 
	 * @param module
	 */
	public void add(IModule module);

	/**
	 * Add a wire to the synthesizer
	 * 
	 * @param wire
	 */
	public void add(IWire wire);

	/**
	 * Remove this module from the synthesizer
	 * 
	 * @param module
	 */
	public void remove(IModule module);

	// TODO : Still in use ? need comments
	public IWire getCurrentWire();

	public void setCurrentWire(IWire wire);

	public void remove(IWire wire);

}
