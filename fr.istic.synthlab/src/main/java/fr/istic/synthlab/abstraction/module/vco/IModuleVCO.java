package fr.istic.synthlab.abstraction.module.vco;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

public interface IModuleVCO extends IModule {

	/**
	 * Parameters name
	 */
	public static final String PARAM_NAME_OCTAVE = "Octave";
	public static final String PARAM_NAME_TONE = "Tone";

	/**
	 * Set the octave value (between 0 and 14)
	 * 
	 * @param value
	 */
	public void setOctave(int value);

	/**
	 * Return the octave value (between 0 and 14)
	 * 
	 * @param value
	 */
	public int getOctave();

	/**
	 * Set the tone value (between -1 and 1)
	 * 
	 * @param value
	 */
	public void setTone(double value);

	/**
	 * Return the tone value (between -1 and 1)
	 * 
	 * @param value
	 */
	public double getTone();

	/**
	 * Return the frequency value (between 0 and 32768)
	 * 
	 * @param value
	 */
	public double getFrequency();

	/**
	 * Return the fm port of the VCO module
	 * 
	 * @return fm input
	 */
	public IInputPort getInputFm();

	/**
	 * Return the Square output port of the VCO module
	 * 
	 * @return square output
	 */
	public IOutputPort getOutputSquare();

	/**
	 * Return the Triangle output port of the VCO module
	 * 
	 * @return triangle output
	 */
	public IOutputPort getOutputTriangle();

	/**
	 * Return the Sawtooth output port of the VCO module
	 * 
	 * @return sawtooth output
	 */
	public IOutputPort getOutputSawtooth();

	/**
	 * Set the frequency
	 * 
	 * @param value
	 */
	void setFrequency(Double value);

}
