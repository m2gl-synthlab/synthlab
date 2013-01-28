package fr.istic.synthlab.abstraction;

import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.OutputPort;

public interface IWire {

	/**
	 * Return the input port
	 * 
	 * @return input port
	 */
	public IPort getInput();

	/**
	 * Return the output port
	 * 
	 * @return output port
	 */
	public IPort getOutput();
	
	
	public void connect(InputPort port);
	
	public void connect(OutputPort port);
}
