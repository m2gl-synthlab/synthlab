package fr.istic.synthlab.controller.port;

import fr.istic.synthlab.abstraction.port.IPort;

/**
 * Interface for a generic port controller
 */
public interface ICPort extends IPort {

	/**
	 * The view inform the controller that the mouse hover the port
	 */
	public void p2cMouseHover();

	/**
	 * The view inform the controller that a click happened on the port
	 */
	public void p2cMouseClicked();

}
