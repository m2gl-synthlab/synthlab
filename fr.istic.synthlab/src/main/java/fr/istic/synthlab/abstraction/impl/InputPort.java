package fr.istic.synthlab.abstraction.impl;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;

public class InputPort implements IInputPort {

	private String name;
	private int defaultPart = 0;
	private UnitInputPort port;
	private IModule parentModule;

	/**
	 * @param name
	 */
	public InputPort(String name) {
		this.port = new UnitInputPort(name);
		this.name = name;
	}

	public InputPort(UnitInputPort jSynPort) {
		this.port = jSynPort;
		this.name = jSynPort.getName();
	}
	
	public InputPort(UnitInputPort jSynPort, int part) {
		this.port = jSynPort;
		this.defaultPart = part;
		this.name = jSynPort.getName();
	}

	@Override
	public UnitInputPort getJSyn() {
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
	public void connect(IOutputPort outPort) {
		if(!port.isConnected())
			port.connect(this.defaultPart, outPort.getJSyn(), outPort.getDefaultPart());
	}

	@Override
	public void disconnect(IOutputPort outPort) {
		if(port.isConnected())
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

	@Override
	public int getDefaultPart() {
		return this.defaultPart;
	}

	@Override
	public IModule getModule() {
		return parentModule;
	}

	@Override
	public void setModule(IModule mod) {
		parentModule = mod;
	}

}
