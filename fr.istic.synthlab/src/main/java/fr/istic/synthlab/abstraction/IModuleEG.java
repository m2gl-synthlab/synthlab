package fr.istic.synthlab.abstraction;

/**
 * Interface for an Envelop Generator module (ADSR)
 */
public interface IModuleEG extends IModule {

	/**
	 * Set the attack time for the module
	 */
	public void setAttack(double attackTime);

	/**
	 * Get the attack time for the module
	 */
	public double getAttack();

	/**
	 * Set the decay time for the module
	 */
	public void setDecay(double decayTime);

	/**
	 * Get the decay time for the module
	 */
	public double getDecay();

	/**
	 * Get the sustain time for the module
	 */
	public double getSubstain();

	/**
	 * Set the release time for the module
	 */
	public void setRelease(double releaseTime);

	/**
	 * Get the release time for the module
	 */
	public double getRelease();

	/**
	 * Return the gate input port of the EG module
	 * 
	 * @return input
	 */
	public IInputPort getGateInput();

	/**
	 * Return the output port of the EG module
	 * 
	 * @return output
	 */
	public IOutputPort getOutput();

}
