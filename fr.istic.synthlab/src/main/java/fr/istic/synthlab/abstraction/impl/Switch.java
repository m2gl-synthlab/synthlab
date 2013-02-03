package fr.istic.synthlab.abstraction.impl;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.abstraction.IParameter;

public class Switch implements IParameter {

	private String name;
	private boolean value = false;
	
	private UnitInputPort port;

	public Switch(String name, boolean value){
		this.name = name;
		this.value = value;
	}
	
	
	@Override
	public void setValue(double val) {
		if(val >= 0) 
			this.value = true;
		else
			this.value = false;
		
		applyValue();
	}
	
	private void applyValue() {
		if(port!=null){
			port.getUnitGenerator().setEnabled(!value);
		}
	}

	@Override
	public double getValue() {
		return (this.value) ? getMin() : getMax();
	}

	@Override
	public void setMin(double min) {
		applyValue();
	}
	
	@Override
	public double getMin() {
		return -1;
	}

	@Override
	public void setMax(double max) {
		applyValue();
	}

	@Override
	public double getMax() {
		return 1;
	}

	@Override
	public void connect(UnitInputPort input) {
		this.port = input;
		applyValue();
	}

	@Override
	public UnitInputPort getPort() {
		return this.port;
	}


	@Override
	public String getName() {
		return this.name;
	}

}
