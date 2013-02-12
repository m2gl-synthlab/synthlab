package fr.istic.synthlab.abstraction.synthesizer;

import java.util.List;

import com.jsyn.Synthesizer;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.wire.IWire;

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

	// TODO : need comments
	public IWire getCurrentWire();

	public void setCurrentWire(IWire wire);

	public void remove(IWire wire);

}