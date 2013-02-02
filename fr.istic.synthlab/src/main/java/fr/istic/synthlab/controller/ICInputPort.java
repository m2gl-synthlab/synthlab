package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.presentation.IPInputPort;

public interface ICInputPort extends IInputPort {
	/**
	 * Return the input port's presentation
	 * 
	 * @return presentation
	 */
	public IPInputPort getPresentation();
}
