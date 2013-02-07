package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICModule;

/**
 * Generic interface for module presentation
 */
public interface IPModule {
	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICModule getControl();

	@Deprecated
	public void addInputPort(IPInputPort presentation);

	@Deprecated
	public void addOutputPort(IPOutputPort presentation);

	@Deprecated
	public void addParameter(IPParameter presentation);

	public int getHeight();

	public int getWidth();

}
