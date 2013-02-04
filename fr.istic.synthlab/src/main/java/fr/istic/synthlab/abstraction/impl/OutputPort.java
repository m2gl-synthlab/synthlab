package fr.istic.synthlab.abstraction.impl;

import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;


/**
 * Implementation of an output port
 */
public class OutputPort implements IOutputPort {

	private String name;
	private UnitOutputPort port;
	private int defaultPart = 0;

	/**
	 * @param name
	 */
	public OutputPort(String name) {
		this.name = name;
		this.port = new UnitOutputPort(name);
	}

	public OutputPort(UnitOutputPort jSynPort, String name) {
		this.name = name;
		this.port = jSynPort;
	}
	
	public OutputPort(UnitOutputPort jSynPort, int part) {
		this.port = jSynPort;
		this.defaultPart = part;
	}

	@Override
	public UnitOutputPort getJSyn() {
		return this.port;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void connect(IInputPort inputPort) {
		port.connect(this.defaultPart, inputPort.getJSyn(), inputPort.getDefaultPart());
	}

	@Override
	public void disconnect(IInputPort inputPort) {
		port.disconnect(inputPort.getJSyn());
	}

	@Override
	public double getValue() {
		return port.get();
	}

	@Override
	public double getValue(int partNum) {
		return port.getValue(partNum);
	}

	@Override
	public int getNumParts() {
		return port.getNumParts();
	}
	
	@Override
	public int getDefaultPart() {
		return defaultPart ;
	}

}
