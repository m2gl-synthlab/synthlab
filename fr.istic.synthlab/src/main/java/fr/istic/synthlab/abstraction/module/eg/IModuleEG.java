package fr.istic.synthlab.abstraction.module.eg;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

/**
 * Interface for an Envelop Generator module (ADSR)
 */
public interface IModuleEG extends IModule {

	/**
	 * Parameters name
	 */
	public static final String PARAM_NAME_ATTACK = "Attack";
	public static final String PARAM_NAME_DECAY = "Decay";
	public static final String PARAM_NAME_SUSTAIN = "Sustain";
	public static final String PARAM_NAME_RELEASE = "Release";

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
	 * @param substainTime
	 */
	void setSustain(double substainTime);

	/**
	 * Get the sustain time for the module
	 */
	public double getSustain();

	/**
	 * Set the release time for the module
	 * 
	 * @param releaseTime
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