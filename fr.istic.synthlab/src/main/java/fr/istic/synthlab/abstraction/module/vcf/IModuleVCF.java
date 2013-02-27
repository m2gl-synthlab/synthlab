package fr.istic.synthlab.abstraction.module.vcf;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

public interface IModuleVCF extends IModule {

	/**
	 * Parameters name
	 */
	public static final String PARAM_NAME_CUT_FREQUENCY = "Cut";
	public static final String PARAM_NAME_RESONANCE = "Res";

	/**
	 * Set the cut frequency (between 0 and 22000)
	 * 
	 * @param Cut
	 *            frequency value
	 */
	public void setCutFrequency(int value);

	/**
	 * Return the cut frequency value (between 0 and 22000)
	 * 
	 * @param Cut
	 *            frequency value
	 */
	public int getCutFrequency();

	/**
	 * Set the resonance value (between 1 and 50)
	 * 
	 * @param Resonance
	 *            value
	 */
	public void setResonance(double value);

	/**
	 * Return the resonance value (between 1 and 50)
	 * 
	 * @param Resonance
	 *            value
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
}
