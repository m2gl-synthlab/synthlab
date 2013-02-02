package fr.istic.synthlab.abstraction.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IParameter;

public class Parameter implements IParameter {

	private double min = 0;
	private double max = 100;
	private double value = 0;
	
	private IInputPort port;

	@Override
	public void setValue(double val) {
		this.value = val;
		if(val > max) this.value = max;
		if(val < min) this.value = min;
		
		if(port!=null){
			port.set(val);
		}
	}

	@Override
	public double getValue() {
		return this.value;
	}

	@Override
	public void setMin(double min) {
		this.min = min;
		setValue(value);
	}
	
	@Override
	public double getMin() {
		return min;
	}

	@Override
	public void setMax(double max) {
		this.max = max;
		setValue(value);
	}

	@Override
	public double getMax() {
		return max;
	}

	@Override
	public void connect(IInputPort input) {
		this.port = input;
		this.port.set(value);
	}

	@Override
	public IInputPort getPort() {
		return this.port;
	}

}
