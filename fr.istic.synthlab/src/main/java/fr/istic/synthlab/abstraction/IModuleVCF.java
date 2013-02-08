package fr.istic.synthlab.abstraction;


public interface IModuleVCF extends IModule {

	/**
	 * Set the cut frequency (between 0 and 32768)
	 * 
	 * @param Cut frequency value
	 */
	public void setCutFrequency(double value);

	/**
	 * Return the cut frequency value (between 0 and 32768)
	 * 
	 * @param Cut frequency value
	 */
	public double getCutFrequency();

	/**
	 * Set the resonance value (between 0 and 100)
	 * 
	 * @param Resonance value
	 */
	public void setResonance(double value);

	/**
	 * Return the resonance value (between 0 and 100)
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
}

