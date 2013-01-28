package fr.istic.synthlab.abstraction.impl;

import fr.istic.synthlab.abstraction.IPort;

public class OutputPort implements IPort{

	private String name;

	/**
	 * @param name
	 */
	public OutputPort(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IPort#getName()
	 */
	public String getName() {
		return this.name;
	}

}
