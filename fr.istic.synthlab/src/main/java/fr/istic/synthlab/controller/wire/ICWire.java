package fr.istic.synthlab.controller.wire;

import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.presentation.wire.IPWire;

public interface ICWire extends IWire {

	/**
	 * Return the wire's presentation
	 * 
	 * @return presentation
	 */
	public IPWire getPresentation();

}
