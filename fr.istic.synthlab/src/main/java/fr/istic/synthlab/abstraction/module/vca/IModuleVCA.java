package fr.istic.synthlab.abstraction.module.vca;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

/**
 * Interface for a VCA Module
 */
public interface IModuleVCA extends IModule {

	/**
	 * Parameters name
	 */
	public static final String PARAM_NAME_GAIN = "Gain";

	/**
	 * Return the module input port
	 * 
	 * @return input
	 */
	IInputPort getInput();

	/**
	 * Return the module am input port
	 * 
	 * @return am
	 */
	IInputPort getInputAM();

	/**
	 * Return the module output port
	 * 
	 * @return output
	 */
	IOutputPort getOutput();

	/**
	 * Set the attenuation value (between -30 and 12)
	 * 
	 * @param Base
	 *            Amplitude value
	 */
	public void setAttenuation(double value);

	/**
	 * Return the attenuation value (between -30 and 12)
	 * 
	 * @param Base
	 *            Amplitude value
	 */
	public double getAttenuation();

}
