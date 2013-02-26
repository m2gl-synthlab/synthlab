package fr.istic.synthlab.abstraction.module.rec;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;

/**
 * Interface for a REC Module
 */
public interface IModuleREC extends IModule {

	/**
	 * Module name
	 */
	public static final String MODULE_NAME = "REC";
	
	/**
	 * Parameters name
	 */
	public static final String PARAM_NAME_GAIN = "Gain";

	/**
	 * Start/Stop recording sound in a wav file
	 */
	public void setRecording(boolean mute);

	/**
	 * Start sending sound to the sound card
	 */
	public boolean isRecording();

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
	 * Return the input port of the OUT module
	 * 
	 * @return input
	 */
	public IInputPort getInput();

}
