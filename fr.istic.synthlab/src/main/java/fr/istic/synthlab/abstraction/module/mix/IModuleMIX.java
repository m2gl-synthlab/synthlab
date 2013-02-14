package fr.istic.synthlab.abstraction.module.mix;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

/**
 * Interface for a MIX Module
 */
public interface IModuleMIX extends IModule {

	/**
	 * Parameters name
	 */
	public static final String PARAM_NAME_GAIN1 = "Gain1";
	public static final String PARAM_NAME_GAIN2 = "Gain2";
	public static final String PARAM_NAME_GAIN3 = "Gain3";
	public static final String PARAM_NAME_GAIN4 = "Gain4";
	
	/**
	 * Return the input port 1 of the MIX module
	 * 
	 * @return input
	 */
	public IInputPort getInput(int number);

	/**
	 * Return the ouput port(number) of the MIX module
	 * 
	 * @return out
	 */
	public IOutputPort getOutput();

	/**
	 * Set the attenuation(number) value (between -inf and +12dB)
	 * 
	 * @param value
	 */
	public void setAttenuation1(double value);
	
	/**
	 * Set the attenuation(number) value (between -inf and +12dB)
	 * 
	 * @param value
	 */
	public void setAttenuation2(double value);
	
	/**
	 * Set the attenuation(number) value (between -inf and +12dB)
	 * 
	 * @param value
	 */
	public void setAttenuation3(double value);
	
	/**
	 * Set the attenuation(number) value (between -inf and +12dB)
	 * 
	 * @param value
	 */
	public void setAttenuation4(double value);
	
	
	/**
	 * Return the attenuation(number) value (between -inf and +12dB)
	 * 
	 * @return value
	 */
	public double getAttenuation1();
	
	/**
	 * Return the attenuation(number) value (between -inf and +12dB)
	 * 
	 * @return value
	 */
	public double getAttenuation2();
	
	/**
	 * Return the attenuation(number) value (between -inf and +12dB)
	 * 
	 * @return value
	 */
	public double getAttenuation3();
	
	/**
	 * Return the attenuation(number) value (between -inf and +12dB)
	 * 
	 * @return value
	 */
	public double getAttenuation4();

}
