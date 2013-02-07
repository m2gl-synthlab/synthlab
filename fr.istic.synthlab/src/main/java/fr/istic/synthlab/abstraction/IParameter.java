package fr.istic.synthlab.abstraction;

import com.jsyn.ports.UnitInputPort;

/**
 * Interface for a module's parameter
 */
public interface IParameter {

	/**
	 * Return the name of the parameter
	 * 
	 * @return the name
	 */
	public String getName();

	/**
	 * Set the value of this parameter
	 * 
	 * @param val
	 */
	public void setValue(double val);

	/**
	 * Return the current value of the parameter
	 * 
	 * @return the value
	 */
	public double getValue();

	/**
	 * Set the minimum value of this parameter
	 * 
	 * @param min
	 */
	public void setMin(double min);

	/**
	 * Return the minimum value of the parameter
	 */
	public double getMin();

	/**
	 * Set the maximum value of this parameter
	 * 
	 * @param max
	 */
	public void setMax(double max);

	/**
	 * Return the maximum value of the parameter
	 */
	public double getMax();

	/**
	 * Connect the parametter to the given port
	 * 
	 * @param input
	 */
	public void connect(UnitInputPort input);

	/**
	 * Return the port
	 * 
	 * @param input
	 */
	public UnitInputPort getPort();
}
