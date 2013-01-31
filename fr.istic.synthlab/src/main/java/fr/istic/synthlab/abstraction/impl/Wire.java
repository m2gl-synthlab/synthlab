package fr.istic.synthlab.abstraction.impl;

import fr.istic.synthlab.abstraction.IPort;
import fr.istic.synthlab.abstraction.IWire;

/**
 * Class that represent a wire between two port
 * 
 * @author Clément Hardouin
 */
public class Wire implements IWire {

	private InputPort input;
	private OutputPort output;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.IWire#getInput()
	 */
	public IPort getInput() {
		return input;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.IWire#getOutput()
	 */
	@Override
	public IPort getOutput() {
		return output;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.istic.synthlab.abstraction.IWire#connect(fr.istic.synthlab.abstraction
	 * .impl.InputPort)
	 */
	@Override
	public void connect(InputPort port) {
		this.input = port;
		System.err.println("Input ok");
		connect();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.istic.synthlab.abstraction.IWire#connect(fr.istic.synthlab.abstraction
	 * .impl.OutputPort)
	 */
	@Override
	public void connect(OutputPort port) {
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
