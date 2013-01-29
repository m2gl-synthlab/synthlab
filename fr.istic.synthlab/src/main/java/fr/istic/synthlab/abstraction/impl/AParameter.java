package fr.istic.synthlab.abstraction.impl;

import fr.istic.synthlab.abstraction.IParameter;

public abstract class AParameter implements IParameter {

	private float min;
	private float max;
	private float value;

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.IParameter#setValue(float)
	 */
	@Override
	public void setValue(float val) {
		this.value = val;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.IParameter#getValue()
	 */
	@Override
	public float getValue() {
		return this.value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.IParameter#setMin(float)
	 */
	@Override
	public void setMin(float min) {
		this.min = min;
	}
	
	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IParameter#getMin()
	 */
	@Override
	public float getMin() {
		return min;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.IParameter#setMax(float)
	 */
	@Override
	public void setMax(float max) {
		this.max = max;
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IParameter#getMax()
	 */
	@Override
	public float getMax() {
		return max;
	}

}
