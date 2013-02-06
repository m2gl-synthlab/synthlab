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
		if (input == null) {
			this.input = port;
			connect();
		}
	}

	@Override
	public void connect(IOutputPort port) {
		if (output == null) {
			this.output = port;
			connect();
		}
	}

	/**
	 * Try to connect the 2 ports
	 */
	private void connect() {
		if (output != null && input != null){
			this.output.connect(input);
			output.setWire(this);
			input.setWire(this);
		}
	}

	@Override
	public void disconnect() {
		System.out.println("Wire disconnect");
		if((output != null)&&(input != null)){
			System.out.println("Wire disconnect ports not null");
			output.disconnect(input);
			output.setWire(null);
			input.setWire(null);
		}
		input.getModule().getSynthesizer().remove(this);
	}

}
