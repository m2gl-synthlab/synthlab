package fr.istic.synthlab.abstraction;

import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.TimeStamp;

import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.OutputPort;

/**
 * Interface of a synthetizer's module
 * 
 */
public interface IModule{
	

	public UnitGenerator getJSyn();
	
	public InputPort getInput();
	public void start();
	public void start(TimeStamp time);
	public void stop();
	public void stop(TimeStamp time);
	public OutputPort getOutput();
	public UnitGenerator getUnitGenerator();
	
	
//
///**
// * STATE
// */
//	/**
//	 * Start the module (receive and emit)
//	 */
//	public void start();
//
//	/**
//	 * Stop the module
//	 */
//	public void stop();
//
//	/**
//	 * Return the state of the module
//	 * 
//	 * @return isRunning
//	 */
//	public boolean isRunning();
//
///**
// * PARAMETERS
// */
//	/**
//	 * Add a parameter to the module
//	 * 
//	 * @param param
//	 */
//	public void addParameter(IParameter param);
//
//	/**
//	 * Remove the given parameter from the module
//	 * 
//	 * @param param
//	 */
//	public void removeParameter(IParameter param);
//
//	/**
//	 * Return the parameter
//	 * 
//	 * @param i
//	 *            index of the parameter
//	 * @return the parameter n°i
//	 */
//	public IParameter getParameter(int i);
//
//	/**
//	 * Return all the parameters
//	 * 
//	 * @return all the parameters
//	 */
//	public List<IParameter> getParameters();
//
///**
// * PORTS
// */
//	/**
//	 * Add a port to the module
//	 * 
//	 * @param param
//	 */
//	public void addPort(IPort port);
//
//	/**
//	 * Remove the given port from the module
//	 * 
//	 * @param param
//	 */
//	public void removePort(IPort port);
//
//	/**
//	 * Return the port
//	 * 
//	 * @param i
//	 *            index of the port
//	 * @return the port n°i
//	 */
//	public IPort getPort(int i);
//
//	/**
//	 * Return all the ports
//	 */
//	public List<IPort> getPorts();

}
