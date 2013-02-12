package fr.istic.synthlab.abstraction.module.vcf;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;


public interface IModuleVCF extends IModule {

	/**
	 * Constants
	 */
	public static final String PARAM_CUT_FREQUENCY_NAME = "Cut";
	public static final String PARAM_RESONANCE_NAME = "Res";
	
	/**
	 * Set the cut frequency (between 0 and 22000)
	 * 
	 * @param Cut frequency value
	 */
	public void setCutFrequency(int value);

	/**
	 * Return the cut frequency value (between 0 and 22000)
	 * 
	 * @param Cut frequency value
	 */
	public int getCutFrequency();

	/**
	 * Set the resonance value (between 0 and 1)
	 * 
	 * @param Resonance value
	 */
	public void setResonance(double value);

	/**
	 * Return the resonance value (between 0 and 1)
	 * 
	 * @param Resonance value
	 */
	public double getResonance();
	
	/**
	 * Return the input port of the VCF module
	 * 
	 * @return input
	 */
	public IInputPort getInput();

	/**
	 * Return the fm port of the VCF module
	 * 
	 * @return fm input
	 */
	public IInputPort getInputFm();

	/**
	 * Return the output port of the VCF module
	 * 
	 * @return output
	 */
	public IOutputPort getOutput();

	/**
	 * set the frequency
	 * @param value
	 */
	void setFrequency(Double value);
}

