package fr.istic.synthlab.abstraction.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IWire;

/**
 * Class that represent a wire between two port
 */
public class Wire implements IWire {

	/**
	 * 
	 */
	private IInputPort input;
	private IOutputPort output;

	public IInputPort getInput() {
		return input;
	}

	@Override
	public IOutputPort getOutput() {
		return output;
	}

	@Override
	public void connect(IInputPort port) {
		this.input = port;
		connect();
	}

	@Override
	public void connect(IOutputPort port) {
		this.output = port;
		connect();
	}

	/**
	 * Try to connect the 2 ports
	 */
	private void connect() {
		if (output != null && input != null)
			this.output.connect(input);
	}

}
