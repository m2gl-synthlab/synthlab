package fr.istic.synthlab.abstraction;

import com.jsyn.ports.UnitOutputPort;

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
	 * @return
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
	
	public IWire getWire();
	public void setWire(IWire wire);
}
