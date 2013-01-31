package fr.istic.synthlab.abstraction.impl;

import com.jsyn.ports.PortBlockPart;
import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.TimeStamp;

import fr.istic.synthlab.abstraction.IPort;

public class OutputPort implements IPort{

	
	private UnitOutputPort port;
	
	/**
	 * @param name
	 */
	public OutputPort(String name) {
		this.port = new UnitOutputPort(name);
	}
	
	public OutputPort(UnitOutputPort jSynPort) {
		this.port = jSynPort;
	}
	
	
	@Override
	public UnitOutputPort getJSynOut() {
		return this.port;
	}

	@Override
	public UnitInputPort getJSynIn() {
		System.err.println(getClass().getSimpleName() + " isn't an Input");
		return null;
	}
	
	

	@Override
	public String getName() {
		return port.getName();
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
	public void connect(InputPort arg0) {
		System.err.println("Connecting");
		port.connect(arg0.getJSynIn());
	}

	@Override
	public void disconnect(InputPort arg0) {
		port.disconnect(arg0.getJSynIn());
	}
	
	
	
	@Override
	public void connect(OutputPort arg0) {
		System.err.println(getClass().getSimpleName() + " can't connect with " + arg0.getClass().getSimpleName());
	}

	@Override
	public void disconnect(OutputPort arg0) {
		System.err.println(getClass().getSimpleName() + " can't disconnect with " + arg0.getClass().getSimpleName());
	}

	@Override
	public PortBlockPart getPortBlockPart() {
		System.err.println(getClass().getSimpleName() + " can't get PortBlockPart");
		return null;
	}

	@Override
	public void set(int arg0, double arg1, TimeStamp arg2) {
		System.err.println(getClass().getSimpleName() + " can't be set");
	}



}
