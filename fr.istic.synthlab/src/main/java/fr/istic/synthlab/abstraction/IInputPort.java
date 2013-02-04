package fr.istic.synthlab.abstraction;

import com.jsyn.ports.UnitInputPort;

/**
 * Interface for a module's input port
 */
public interface IInputPort {// extends ConnectableInput, GettablePort, SettablePort{

	/**
	 * Return the jSyn output port component
	 * 
	 * @return
	 */
	public UnitInputPort getJSyn();

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
	 * Connect to the given output port
	 * 
	 * @param outPort
	 */
	public void connect(IOutputPort outPort);

	/**
	 * Disconnect from the given output port
	 * 
	 * @param outPort
	 */
	public void disconnect(IOutputPort outPort);

	/**
	 * Set a new value to the port
	 * 
	 * @param value
	 */
	public void set(double value);

	/**
	 * Set a new value to the port on the given part
	 * 
	 * @param partNum
	 * @param value
	 */
	public void set(int partNum, double value);

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
	
}
