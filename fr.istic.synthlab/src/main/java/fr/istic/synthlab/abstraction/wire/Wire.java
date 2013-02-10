package fr.istic.synthlab.abstraction.wire;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;

/**
 * Class that represent a wire between two port
 */
public class Wire implements IWire {

	private IInputPort input;
	private IOutputPort output;

	@Override
	public IInputPort getInput() {
		return input;
	}

	@Override
	public IOutputPort getOutput() {
		return output;
	}

	@Override
	public void connect(IInputPort port) throws PortAlreadyInUseException, BadConnectionException {
		if (input != null) {
			throw new BadConnectionException("The wire is already connected to an output");
		} else {
			if(port.getWire() != null){
				throw new PortAlreadyInUseException("The port is already in use");
			}else{
				this.input = port;
				connect();
			}
		}
	}

	@Override
	public void connect(IOutputPort port) throws PortAlreadyInUseException, BadConnectionException {
		if (output != null) {
			throw new BadConnectionException("The wire is already connected to an output");
		} else {
			if(port.getWire() != null){
				throw new PortAlreadyInUseException("The port is already in use");
			}else{
				this.output = port;
				connect();
			}
		}
	}

	/**
	 * Try to connect the 2 ports
	 */
	private void connect() {
		if (output != null && input != null) {
			System.out.println(getClass().getSimpleName() + " connect()");
			this.output.getJSyn().connect(input.getJSyn());
			this.output.setWire(this);
			this.input.setWire(this);
		}
	}

	@Override
	public void disconnect() {
		if (output != null && input != null) {
			this.output.getJSyn().disconnect(this.input.getJSyn());
			this.output.setWire(null);
			this.input.setWire(null);
		}
		input.getModule().getSynthesizer().remove(this);
	}

	@Override
	public boolean isConnected() {
		return (this.output != null) && (this.input != null);
	}

}
