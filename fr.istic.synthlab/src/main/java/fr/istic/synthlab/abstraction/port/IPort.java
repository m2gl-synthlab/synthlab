package fr.istic.synthlab.abstraction.port;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.wire.IWire;

/**
 * Interface for a module port
 */
public interface IPort {

	/**
	 * Set the port's name
	 */
	public void setName(String name);

	/**
	 * Return the port's name
	 * 
	 * @return the name
	 */
	public String getName();

	/**
	 * Set a connected wire
	 * 
	 * @param wire
	 */
	public void setWire(IWire wire);

	/**
	 * Return the connected wire if any
	 * 
	 * @return the connected wire
	 */
	public IWire getWire();

	/**
	 * Return the module witch contains the current port
	 * 
	 * @return the module
	 */
	public IModule getModule();
}
