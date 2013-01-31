package fr.istic.synthlab.abstraction.impl;

import com.jsyn.ports.ConnectableInput;
import com.jsyn.ports.ConnectableOutput;
import com.jsyn.ports.PortBlockPart;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.TimeStamp;

import fr.istic.synthlab.abstraction.IPort;

public class InputPort implements IPort {

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
	
	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IPort#getName()
	 */
	@Override
	public String getName(){
		return this.name;
	}

	@Override
	public void connect(ConnectableInput arg0) {
		System.err.println(getClass().getSimpleName() + " can't connect with " + arg0.getClass().getSimpleName());
	}

	@Override
	public void disconnect(ConnectableInput arg0) {
		System.err.println(getClass().getSimpleName() + " can't disconnect with " + arg0.getClass().getSimpleName());
	}



	
	@Override
	public UnitInputPort getJSynIn() {
		return this.port;
	}

	@Override
	public UnitOutputPort getJSynOut() {
		System.err.println(getClass().getSimpleName() + " isn't an Output");
		return null;
	}
	
	
	

	@Override
	public int getNumParts() {
		return port.getNumParts();
	}

	@Override
	public UnitGenerator getUnitGenerator() {
		return port.getUnitGenerator();
	}

	@Override
	public double getValue(int arg0) {
		return port.getValue(arg0);
	}
	
	
	
	@Override
	public void connect(ConnectableOutput arg0) {
		port.connect(arg0);
	}

	@Override
	public void disconnect(ConnectableOutput arg0) {
		port.disconnect(arg0);
	}

	@Override
	public PortBlockPart getPortBlockPart() {
		return port.getPortBlockPart();
	}

	@Override
	public void set(int arg0, double arg1, TimeStamp arg2) {
		port.set(arg0, arg1, arg2);
	}
	
}
