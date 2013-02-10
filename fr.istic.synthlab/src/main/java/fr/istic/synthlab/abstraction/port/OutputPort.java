package fr.istic.synthlab.abstraction.port;

import com.jsyn.ports.ConnectableOutput;

import fr.istic.synthlab.abstraction.module.IModule;

/**
 * Implementation of an output port
 */
public class OutputPort extends Port implements IOutputPort {

	private ConnectableOutput port;

	public OutputPort(String name, ConnectableOutput jSynPort, IModule module) {
		super(name, module);
		this.port = jSynPort;
	}

	@Override
	public ConnectableOutput getJSyn() {
		return this.port;
	}

}
