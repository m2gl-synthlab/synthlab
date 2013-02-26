package fr.istic.synthlab.presentation.port;

import java.awt.Color;

import fr.istic.synthlab.controller.port.ICPort;

/**
 * Interface for generic port presentation
 */
public interface IPPort {

	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICPort getControl();

	/**
	 * Inform the view that the name need to be updated
	 */
	public void c2pNameChanged();

	/**
	 * Inform the view that the connection on this port is not allowed
	 */
	public void c2pConnectionAllowed();

	/**
	 * Inform the view that the connection on this port is allowed
	 */
	public void c2pConnectionNotAllowed();

	/**
	 * Inform the view that the connection attempt has succeed
	 */
	public void c2pConnectionAttemptSucceed();

	/**
	 * Inform the view that the connection attempt has failed
	 */
	public void c2pConnectionAttemptFailed();

	/**
	 * Set the stroke color of the port
	 * 
	 * @param c
	 */
	public void setStrokeColor(Color c);

	/**
	 * Return the stroke color of the port
	 * 
	 * @return color
	 */
	public Color getStrokeColor();

	/**
	 * Set the port fill color
	 * 
	 * @param c
	 */
	public void setFillColor(Color c);

	/**
	 * return the port fill color
	 * 
	 * @return color
	 */
	public Color getFillColor();

}
