package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IPort;
import fr.istic.synthlab.presentation.IPPort;

public interface ICPort extends IPort{
	/**
	 * Return the port's presentation
	 * 
	 * @return presentation
	 */
	public IPPort getPresentation();
}
