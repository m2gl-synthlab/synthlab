package fr.istic.synthlab.abstraction.observer;

public interface Observer<T extends Observable> {

	/**
	 * Called when the subject's state changes (which means, when the subject
	 * calls notifyObservers())
	 * 
	 * @param subject
	 *            the observed subject
	 */
	public void update(T subject);
}