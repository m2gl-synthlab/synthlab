package fr.istic.synthlab.abstraction.wire;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

/**
 * Interface for a Wire that can connect with input and output port
 */
public interface IWire {

	/**
	 * Return the input port
	 * 
	 * @return input port
	 */
	public IInputPort getInput();

	/**
	 * Return the output port
	 * 
	 * @return output port
	 */
	public IOutputPort getOutput();

	/**
	 * Connect the wire with an input port
	 * 
	 * @param port
	 * @throws PortAlreadyInUseException
	 * @throws BadConnectionException
	 */
	public void connect(IInputPort port) throws PortAlreadyInUseException, BadConnectionException;

	/**
	 * Connect the wire with an output port
	 * 
	 * @param port
	 * @throws PortAlreadyInUseException
	 * @throws BadConnectionException
	 */
	public void connect(IOutputPort port) throws PortAlreadyInUseException, BadConnectionException;

	/**
	 * Return true if the cable is connected to an input and an output
	 * 
	 * @return the connection status
	 */
	public boolean isConnected();

	// TODO : need comments
	public void disconnect();
}