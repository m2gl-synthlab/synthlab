package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.presentation.IPWire;

public interface ICWire extends IWire {

	/**
	 * Return the wire's presentation
	 * 
	 * @return presentation
	 */
	public IPWire getPresentation();

}
