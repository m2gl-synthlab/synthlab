package fr.istic.synthlab.abstraction;

/**
 * Interface for a module's parameter
 * 
 * @author Clément Hardouin
 * 
 */
public interface IParameter {

	/**
	 * Set the value of this parameter
	 * 
	 * @param val
	 */
	public void setValue(float val);

	/**
	 * Return the current value of the parameter
	 * 
	 * @return the value
	 */
	public float getValue();

	/**
	 * Set the minimum value of this parameter
	 * 
	 * @param min
	 */
	public void setMin(float min);

	/**
	 * Return the minimum value of the parameter
	 */
	public float getMin();

	/**
	 * Set the maximum value of this parameter
	 * 
	 * @param max
	 */
	public void setMax(float max);

	/**
	 * Return the maximum value of the parameter
	 */
	public float getMax();
}
