package fr.istic.synthlab.presentation;

import fr.istic.synthlab.controller.ICWire;


public interface IPWire {
	/**
	 * Return the presentation's controller
	 * 
	 * @return the controller
	 */
	public ICWire getControl();

//	public void connect(IPInputPort inputPortPresentation,
//			IPOutputPort outputPortPresentation);
//	
//	public int getx();
//	public int gety();
//
//	public int getWidth();
//	public int getHeight();
	
	public void c2pConnectOut(IPOutputPort outputPortPresentation);
	public void c2pConnectIn(IPInputPort inputPortPresentation);

	public void c2pDisconnectIn(IPInputPort pInputPort);
	public void c2pDisconnectOut(IPOutputPort pOutputPort);
	
	public void updateDisplay();

}
