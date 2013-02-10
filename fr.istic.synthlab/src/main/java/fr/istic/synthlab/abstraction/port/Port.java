package fr.istic.synthlab.abstraction.port;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.wire.IWire;

/**
 * Class that represent a module port
 */
public class Port implements IPort {

	private String name;
	private IWire wire;
	private IModule module;

	public Port(String name, IModule module) {
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
	}

	@Override
	public IWire getWire() {
		return this.wire;
	}

	@Override
	public IModule getModule() {
		return this.module;
	}

}
