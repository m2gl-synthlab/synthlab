package fr.istic.synthlab.controller.synthesizer;

import java.awt.Color;

import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;

public interface ICSynthesizer extends ISynthesizer {

	/**
	 * Return the synthesizer's presentation
	 * 
	 * @return presentation
	 */
	public IPSynthesizer getPresentation();

	/**
	 * The presentation ask to start the synthesizer
	 */
	public void p2cStart();

	/**
	 * The presentation ask to stop the synthesizer
	 */
	public void p2cStop();

	/**
	 * The presentation ask to add a module
	 * 
	 * @param module
	 */
	public void p2cAddModule(ICModule module);

	/**
	 * The presentation ask to remove a module
	 * 
	 * @param module
	 */
	public void p2cRemoveModule(ICModule module);

	/**
	 * Disconnect the current wire
	 */
	public void p2cDisconnectCurrentWire();

	/**
	 * set the current color for the next wire
	 * 
	 * @param currentWireColor
	 */
	public void setCurrentWireColor(Color currentWireColor);

	/**
	 * Return the current wire Color
	 * @return
	 */
	public Color getCurrentWireColor();

	/**
	 * Get the path
	 * @return
	 */
	public String[] getPath();

	/**
	 * Set the path
	 * @param path
	 * @param filename
	 */
	void setPath(String path, String filename);

	/**
	 * Set the frame
	 * @param frame
	 */
	void setFrame(ISynthFrame frame);
}
