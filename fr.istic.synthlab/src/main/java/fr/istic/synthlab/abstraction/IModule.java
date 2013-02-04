package fr.istic.synthlab.abstraction;

import java.util.List;

import com.jsyn.unitgen.UnitGenerator;

/**
 * Interface of a synthetizer's module
 */
public interface IModule {

	/**

	 * Return the jSyn components
	 * @return jSyn components
	 */
	public List<UnitGenerator> getJSyn();
	
	/**
	 * Start the module
	 */
	public void start();

	/**
	 * Stop the module
	 */
	public void stop();

	/**
	 * Return the module's name
	 * 
	 * @return
	 */
	public String getName();

	/**
	 * Return the input port designed by identifier
	 * 
	 * @param identifier
	 * @return the outputPort
	 */
	public IInputPort getInput(int identifier);

	/**
	 * Return the output port designed by identifier
	 * 
	 * @param identifier
	 * @return the outputPort
	 */
	public IOutputPort getOutput(int identifier);

	/**
	 * Return the parameter designed by identifier
	 * 
	 * @param identifier
	 * @return the parameter
	 */
	public IParameter getParameter(int identifier);

	/**
	 * Return the Synthesizer witch contains the current module
	 * 
	 * @return the Synthesizer
	 */
	public ISynthesizer getSynthesizer();

	/**
	 * Set the Synthesizer witch contains the current Synthesizer
	 * 
	 * @return the Synthesizer
	 */
	public void setSynthesizer(ISynthesizer synth);
}
