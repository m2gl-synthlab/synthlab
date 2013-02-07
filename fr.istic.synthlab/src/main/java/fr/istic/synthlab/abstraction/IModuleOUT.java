package fr.istic.synthlab.abstraction;

/**
 * Interface for a OUT Module
 */
public interface IModuleOUT extends IModule {

	/**
	 * Start/Stop sending sound to the sound card. (
	 */
	public void setMute(boolean mute);

	/**
	 * Start sending sound to the sound card
	 */
	public boolean isMute();

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
