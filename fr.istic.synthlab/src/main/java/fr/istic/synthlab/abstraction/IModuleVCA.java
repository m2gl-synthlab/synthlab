package fr.istic.synthlab.abstraction;

public interface IModuleVCA extends IModule {

	IInputPort getInput();

	IInputPort getInputAM();

	IOutputPort getOutput();

	/**
	 * Set the attenuation value (between -30 and 12)
	 * 
	 * @param Attenuatuin value
	 */
	public void setAttenuationValue(double value);

	/**
	 * Return the attenuation value (between -30 and 12)
	 * 
	 * @param Attenuation value
	 */
	public double getAttenuationValue();

}
