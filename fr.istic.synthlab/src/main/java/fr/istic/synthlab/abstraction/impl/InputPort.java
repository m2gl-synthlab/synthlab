package fr.istic.synthlab.abstraction.impl;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;

public class InputPort implements IInputPort {

	private String name;
	private UnitInputPort port;

	/**
	 * @param name
	 */
	public InputPort(String name) {
		this.port = new UnitInputPort(name);
	}

	public InputPort(UnitInputPort jSynPort) {
		this.port = jSynPort;
	}

	@Override
	public UnitInputPort getJSyn() {
		return this.port;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void connect(IOutputPort outPort) {
		port.connect(outPort.getJSyn());
	}

	@Override
	public void disconnect(IOutputPort outPort) {
		port.disconnect(outPort.getJSyn());
	}

	@Override
	public void set(double value) {
		port.set(value);
	}

	@Override
	public void set(int partNum, double value) {
		port.set(partNum, value);
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

}
