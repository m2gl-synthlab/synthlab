package fr.istic.synthlab.abstraction.module.mix;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

/**
 * Interface for a MIX Module
 */
public interface IModuleMIX extends IModule {

	/**
	 * Return the input port 1 of the MIX module
	 * 
	 * @return input
	 */
	public IInputPort getInPut(int number);

	/**
	 * Return the ouput port(number) of the MIX module
	 * 
	 * @return out
	 */
	public IOutputPort getOut();

	/**
	 * Set the attenuation(number) value (between -inf and +12dB)
	 * 
	 * @param numero
	 * @param value
	 */
	public void setAttenuation(int number, double value);
	
	
	/**
	 * Return the attenuation(number) value (between -inf and +12dB)
	 * 
	 * @param numero
	 * @return value
	 */
	public double getAttenuation(int number);

}
