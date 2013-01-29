package fr.istic.synthlab.abstraction.impl;

import fr.istic.synthlab.abstraction.IPort;

public class InputPort implements IPort {

	private String name;

	/**
	 * @param name
	 */
	public InputPort(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IPort#getName()
	 */
	@Override
	public String getName(){
		return this.name;
	}
	
}
