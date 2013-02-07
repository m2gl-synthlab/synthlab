package fr.istic.synthlab.abstraction.impl;

import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IWire;


/**
 * Implementation of an output port
 */
public class OutputPort implements IOutputPort {

	private String name;
	private UnitOutputPort port;
	private int defaultPart = 0;
	private IWire wire;
	
	private IModule parentModule;

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
	
	public OutputPort(UnitOutputPort jSynPort, int part, String name) {
		this.port = jSynPort;
		this.defaultPart = part;
		this.name = name;
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
		System.out.println("Output going to be connected");
		if(!port.isConnected()){
			System.out.println("!port.isConnected()");
			port.connect(this.defaultPart, inputPort.getJSyn(), inputPort.getDefaultPart());
			
		}
		if(port.isConnected()){
			System.out.println("Output connected"); //TODO resoudre ce mystere ou bug de JSyn...
		}
	}

	@Override
	public void disconnect(IInputPort inputPort) {
		System.out.println("Output going to be disconnected");
		port.disconnectAll();
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
