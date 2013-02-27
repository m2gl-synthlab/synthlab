package fr.istic.synthlab.controller.wire;

import java.awt.Color;

import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;
import fr.istic.synthlab.presentation.wire.IPWire;

public interface ICWire extends IWire {

	/**
	 * Return the wire's presentation
	 * 
	 * @return presentation
	 */
	public IPWire getPresentation();

	public Color getCurrentWireColor();

	public IPSynthesizer getSynthesizerPresentation();

	public void setCurrentWire();

}
