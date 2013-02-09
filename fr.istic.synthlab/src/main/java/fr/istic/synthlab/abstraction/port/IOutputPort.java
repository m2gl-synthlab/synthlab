package fr.istic.synthlab.abstraction.port;

import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.wire.IWire;

/**
 * Interface for a module's input port
 */
public interface IOutputPort {// extends ConnectableInput, GettablePort{

	/**
	 * Return the jSyn output port component
	 * 
	 * @return
	 */
	public UnitOutputPort getJSyn();

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
	 * Connect to the given input port
	 * 
	 * @param inPort
	 */
	public void connect(IInputPort inPort);

	/**
	 * Disconnect from the given input port
	 * 
	 * @param inPort
	 */
	public void disconnect(IInputPort inPort);

	/**
	 * Return the port's value
	 * 
	 * @return
	 */
	public double getValue();

	/**
	 * Return the port's value
	 * 
	 * @param partNum
	 * @return value
	 */
	public double getValue(int partNum);

	/**
	 * Return the number of part for this port (stereo = 2)
	 * 
	 * @return part count
	 */
	public int getNumParts();

	/**
	 * Return the default part
	 * 
	 * @return default part number
	 */
	public int getDefaultPart();

	/**
	 * Return the module witch contains the current port
	 * 
	 * @return the module
	 */
	public IModule getModule();

	/**
	 * Set the module witch contains the current port
	 * 
	 * @return the module
	 */
	public void setModule(IModule mod);

	/**
	 * Return the connected wire if any
	 * 
	 * @return the connected wire
	 */
	public IWire getWire();

	/**
	 * Set the connected wire
	 * 
	 * @param wire
	 */
	public void setWire(IWire wire);
}
