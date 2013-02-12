package fr.istic.synthlab.abstraction.module;

import java.util.HashMap;
import java.util.List;

import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;

/**
 * Interface of a synthetizer's module
 */
public interface IModule {

	/**
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
	 * Return the list of wire connected to the module
	 * 
	 * @return list of wires
	 */
	public List<IWire> getWires();

	/**
	 * Indicate if the port is contained by the module or not
	 * @param port
	 * @return
	 */
	public boolean containsPort(IPort port);

	/**
	 * Add the given port to the list of module's ports
	 * @param port
	 */
	public void addPort(IPort port);

	/**
	 * return the asked parameter value
	 * @param key
	 * @return
	 */
	public Double getParameter(String key);

	/**
	 * Return the list of all parameters
	 * @return
	 */
	public HashMap<String, Double> getParameters();
	
	/**
	 * Return a port by its name
	 * @param portName
	 * @return
	 */
	public IInputPort getPortByName(String portName);
}
