package fr.istic.synthlab.presentation.module;

import java.awt.Point;

import fr.istic.synthlab.controller.module.ICModule;

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

	/**
	 * Return the module height
	 * 
	 * @return height
	 */
	public int getHeight();

	/**
	 * Return the module width
	 * 
	 * @return width
	 */
	public int getWidth();

	/**
	 * Return the module position
	 * 
	 * @return position
	 */
	public Point getPosition();

	/**
	 * Set the module position
	 * 
	 * @param point
	 */
	public void setPosition(Point point);

}
