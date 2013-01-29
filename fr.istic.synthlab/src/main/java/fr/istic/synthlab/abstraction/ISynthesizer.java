package fr.istic.synthlab.abstraction;

import java.util.List;

/**
 * @author Clément Hardouin
 *
 */
public interface ISynthesizer {

	/**
	 * Start the synthetizer
	 */
	public void start();
	
	/**
	 * Stop the synthetizer
	 */
	public void stop();

	/**
	 * Is the synthetizer running ?
	 * @return boolean
	 */
	public boolean isRunning();
	
	/**
	 * Add the given module to the synthetizer
	 * @param module
	 */
	public void addModule(IModule module);
	
	/**
	 * Return the given module to the synthetizer
	 * @param module
	 */
	public IModule getModule(int i);
	
	/**
	 * Return the given module to the synthetizer
	 * @param module
	 */
	public List<IModule> getModules();
	
	/**
	 * Remobe the given module to the synthetizer
	 * @param module
	 */
	public void removeModule(IModule module);
	
	
}
