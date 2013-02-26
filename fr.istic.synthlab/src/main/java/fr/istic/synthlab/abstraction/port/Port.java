package fr.istic.synthlab.abstraction.port;

import java.util.ArrayList;
import java.util.List;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.observer.Observable;
import fr.istic.synthlab.abstraction.observer.Observer;
import fr.istic.synthlab.abstraction.wire.IWire;

/**
 * Class that represent a module port
 */
public class Port implements IPort {

	private List<Observer<Port>> observers;

	private String name;
	private IWire wire;
	private IModule module;

	public Port(String name, IModule module) {
		this.observers = new ArrayList<Observer<Port>>();
		this.name = name;
		this.module = module;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setWire(IWire wire) {
		this.wire = wire;
		notifyObservers();
	}

	@Override
	public IWire getWire() {
		return this.wire;
	}

	@Override
	public IModule getModule() {
		return this.module;
	}

	@Override
	public boolean isInUse() {
		return (getWire() != null);
	}

	/** OBSERVER */
	@Override
	public void notifyObservers() {
		for (Observer<Port> o : observers) {
			o.update(this);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addObserver(Observer<? extends Observable> o) {
		observers.add((Observer<Port>) o);
	}

	@Override
	public void removeObserver(Observer<? extends Observable> o) {
		observers.remove(o);
	}

}
