package fr.istic.synthlab.abstraction.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IWire;

/**
 * Class that represent a wire between two port
 * 
 * @author Clément Hardouin
 */
public class Wire implements IWire {

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
		System.err.println("Input ok");
		connect();
	}

	@Override
	public void connect(IOutputPort port) {
		this.output = port;
		System.err.println("Output ok");
		connect();
	}

	private void connect() {
		if (output == null)
			System.err.println("Output null");
		if (input == null)
			System.err.println("Input null");

		if (output != null && input != null)
			this.output.connect(input);

	}

}
