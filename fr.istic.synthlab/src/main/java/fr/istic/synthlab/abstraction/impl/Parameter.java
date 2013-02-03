package fr.istic.synthlab.abstraction.impl;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IParameter;

public class Parameter implements IParameter {

	private String name;
	private double min;
	private double max;
	private double value;
	
	private IInputPort port;

	public Parameter(String name, double min, double max, double value){
		this.name = name;
		this.max = max;
		this.min = min;
		this.value = value;
	}
	
	
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


	@Override
	public String getName() {
		return this.name;
	}

}
