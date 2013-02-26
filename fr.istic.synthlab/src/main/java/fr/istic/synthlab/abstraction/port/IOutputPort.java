package fr.istic.synthlab.abstraction.port;

import com.jsyn.ports.ConnectableOutput;

/**
 * Interface for a module's output port
 */
public interface IOutputPort extends IPort {

	/**
	 * Return the jSyn output port component
	 * 
	 * @return the JSyn component
	 */
	public ConnectableOutput getJSyn();

}
