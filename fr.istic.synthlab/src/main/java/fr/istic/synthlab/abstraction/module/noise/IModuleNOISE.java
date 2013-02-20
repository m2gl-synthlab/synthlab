package fr.istic.synthlab.abstraction.module.noise;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IOutputPort;

/**
 * Interface for a white NOISE Module
 */
public interface IModuleNOISE extends IModule {

	/**
	 * Parameters name
	 */
	public static final String PARAM_NAME_GAIN = "Gain";

	/**
	 * Set the attenuation value (between -inf and +12dB)
	 * 
	 * @param value
	 */
	public void setAttenuation(double value);

	/**
	 * Return the attenuation value (between -inf and +12dB)
	 * 
	 * @param value
	 */
	public double getAttenuation();

	/**
	 * Return the output port of the NOISE module
	 * 
	 * @return output
	 */
	public IOutputPort getOutput();

}
