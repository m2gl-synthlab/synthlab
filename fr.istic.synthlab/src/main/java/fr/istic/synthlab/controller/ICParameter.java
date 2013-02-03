package fr.istic.synthlab.controller;

import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.presentation.IPParameter;

public interface ICParameter extends IParameter {

	/**
	 * Return the parameter's presentation
	 * 
	 * @return presentation
	 */
	public IPParameter getPresentation();

	public String getName();
	
	public void p2cSetValue(double value);
	
}
