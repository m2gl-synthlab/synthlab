package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.presentation.IPOutputPort;

public interface ICOutputPort extends IOutputPort {
	/**
	 * Return the output port's presentation
	 * 
	 * @return presentation
	 */
	public IPOutputPort getPresentation();

	void p2cConnect();

	public void p2cDisconnect();
}