package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICModule;

public interface IPModule {
	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICModule getControl();

	public void addInputPort(IPInputPort presentation);

	public void addOutputPort(IPOutputPort presentation);

	public void addParameter(IPParameter presentation);

	public int getHeight();
	public int getWidth();
	
}
