package fr.istic.synthlab.factory.impl;

import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.IPFactory;

public class PACFactory {

	protected static IFactory aFactory;

	/**
	 * Set the AFactory
	 * 
	 * @param f
	 */
	public static void setAFactory(IFactory f) {
		aFactory = f;
	}

	/**
	 * Return the AFactory
	 * 
	 * @return
	 */
	public static IFactory getAFactory() {
		return (aFactory);
	}

	protected static IFactory cFactory;

	/**
	 * Set the CFactory
	 * 
	 * @param f
	 */
	public static void setCFactory(IFactory f) {
		cFactory = f;
	}

	/**
	 * Return the CFactory
	 * 
	 * @return
	 */
	public static IFactory getCFactory() {
		return (cFactory);
	}

	protected static IPFactory pFactory;

	/**
	 * Set the PFactory
	 * 
	 * @param f
	 */
	public static void setPFactory(IPFactory f) {
		pFactory = f;
	}

	/**
	 * Return the PFactory
	 * 
	 * @return
	 */
	public static IPFactory getPFactory() {
		return (pFactory);
	}

	/**
	 * Set the AFactory
	 * 
	 * @param f
	 */
	public static void setFactory(IFactory f) {
		aFactory = f;
	}

	/**
	 * Return the AFactory or CFactory if defined
	 * 
	 * @return
	 */
	public static IFactory getFactory() {
		IFactory factory = aFactory;
		if (cFactory != null) {
			factory = cFactory;
		}
		return (factory);
	}

}
