package fr.istic.synthlab.abstraction.port;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.wire.IWire;

public class InputPort implements IInputPort {

	private String name;
	private int defaultPart = 0;
	private UnitInputPort port;
	private IModule parentModule;
	
	private IWire wire;

	/**
	 * @param name
	 */
	public InputPort(String name) {
		this.port = new UnitInputPort(name);
		this.name = name;
	}

	public InputPort(UnitInputPort jSynPort, String name) {
		this.port = jSynPort;
		this.name = name;
	}

	public InputPort(UnitInputPort jSynPort, int part, String name) {
		this.port = jSynPort;
		this.defaultPart = part;
		this.name = name;
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

	@Override
	public IWire getWire() {
		return wire;
	}

	@Override
	public void setWire(IWire wire) {
		this.wire = wire;
	}
	

}