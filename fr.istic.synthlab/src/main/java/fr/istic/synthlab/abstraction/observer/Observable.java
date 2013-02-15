package fr.istic.synthlab.abstraction.observer;

public interface Observable {

	/**
	 * Calls update(T) on every registered observer
	 */
	public void notifyObservers();

	/**
	 * Adds an observer to be called when this observable object will call
	 * notifyObservers()
	 * 
	 * @param o
	 *            Observer<T> to add
	 */
	public void addObserver(Observer<? extends Observable> o);

	/**
	 * Removes the given observer from this observable object observers list
	 * 
	 * @param o
	 *            Observer<T> to remove
	 */
	public void removeObserver(Observer<? extends Observable> o);
}