package fr.istic.synthlab.abstraction.port;

import com.jsyn.ports.ConnectableInput;

import fr.istic.synthlab.abstraction.module.IModule;

/**
 * Implementation of an input port
 */
public class InputPort extends Port implements IInputPort {

	private ConnectableInput port;

	public InputPort(String name, ConnectableInput jSynPort, IModule module) {
		super(name, module);
		this.port = jSynPort;
	}

	@Override
	public ConnectableInput getJSyn() {
		return this.port;
	}

}