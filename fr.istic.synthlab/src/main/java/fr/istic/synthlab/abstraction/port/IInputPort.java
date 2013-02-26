package fr.istic.synthlab.abstraction.port;

import com.jsyn.ports.ConnectableInput;

/**
 * Interface for a module's input port
 */
public interface IInputPort extends IPort {

	/**
	 * Return the jSyn input port component
	 * 
	 * @return the JSyn component
	 */
	public ConnectableInput getJSyn();

}
