package fr.istic.synthlab.presentation.wire;

import java.awt.Color;
import java.awt.Point;

import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.presentation.port.IPInputPort;
import fr.istic.synthlab.presentation.port.IPOutputPort;

public interface IPWire {
	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICWire getControl();

	/**
	 * Refresh the wire view
	 */
	public void updateDisplay();

	/**
	 * React to a connection
	 * 
	 * @param pOutputPort
	 */
	public void c2pConnectOut(IPOutputPort pOutputPort);

	/**
	 * React to a connection
	 * 
	 * @param pInputPort
	 */
	public void c2pConnectIn(IPInputPort pInputPort);

	/**
	 * React to a disconnection
	 * 
	 * @param pInputPort
	 */
	public void c2pDisconnectIn(IPInputPort pInputPort);

	/**
	 * React to a disconnection
	 * 
	 * @param pOutputPort
	 */
	public void c2pDisconnectOut(IPOutputPort pOutputPort);

	/**
	 * Set a position for the input pin of the wire
	 * 
	 * @param mouse
	 */
	public void setInputPoint(Point mouse);

	/**
	 * Set a position for the output pin of the wire
	 * 
	 * @param mouse
	 */
	public void setOutputPoint(Point mouse);

	public void setOnTop(boolean isOnTop);

	public Color getColor();

	public void setColor(Color color);

}
