package fr.istic.synthlab.abstraction;

public interface IModuleVCO extends IModule {

	/**
	 * Set the octave value (between -5 and 5)
	 * 
	 * @param value
	 */
	public void setOctave(int value);

	/**
	 * Return the octave value (between -5 and 5)
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
	 * Return the Sine output port of the VCO module
	 * 
	 * @return sine output
	 */
	public IOutputPort getOutputSine();

	/**
	 * Return the Sawtooth output port of the VCO module
	 * 
	 * @return sawtooth output
	 */
	public IOutputPort getOutputSawtooth();
}
