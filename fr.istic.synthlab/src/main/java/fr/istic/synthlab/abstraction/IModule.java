package fr.istic.synthlab.abstraction;

import java.util.List;

import com.jsyn.unitgen.UnitGenerator;

/**
 * Interface of a synthetizer's module
 */
public interface IModule {

	/**
	 * 
	 * Return the jSyn components
	 * 
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

	/**
	 * Return the list of wire connected to the module
	 * 
	 * @return list of wires
	 */
	public List<IWire> getWires();
}
