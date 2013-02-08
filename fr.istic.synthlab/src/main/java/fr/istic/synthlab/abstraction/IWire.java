package fr.istic.synthlab.abstraction;

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
	 */
	public void connect(IInputPort port);

	/**
	 * Connect the wire with an output port
	 * 
	 * @param port
	 */
	public void connect(IOutputPort port);
	
	// TODO : Maybe a cable could be half disconnected ? need comments
	public void disconnect();
}