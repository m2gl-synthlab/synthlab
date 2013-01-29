package fr.istic.synthlab.abstraction.impl;

import fr.istic.synthlab.abstraction.IPort;
import fr.istic.synthlab.abstraction.IWire;

/**
 * Class that represent a wire between two port
 * 
 * @author Clément Hardouin
 */
public class Wire implements IWire {

	private IPort input;
	private IPort output;

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
	}

}
