package fr.istic.synthlab.controller.impl;

import java.util.List;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.IPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICPort;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.presentation.IPModule;

public class CModule implements ICModule{

	private IPModule pres;
	private IModule abs;
	
	public CModule(String name) {
		this.abs = PACFactory.getAFactory().newModule(name, PACFactory.getAFactory());
		this.pres = PACFactory.getPFactory().newModule(this);
	}
	
	public IModule getAbs() {
		return abs;
	}

	@Override
	public void start() {
		abs.start();
	}

	@Override
	public void stop() {
		abs.stop();
	}

	@Override
	public boolean isRunning() {
		return abs.isRunning();
	}

	@Override
	public void addParameter(IParameter param) {
		abs.addParameter(param);
	}

	@Override
	public void removeParameter(IParameter param) {
		abs.removeParameter(param);
	}

	@Override
	public IParameter getParameter(int i) {
		return abs.getParameter(i);
	}

	@Override
	public List<IParameter> getParameters() {
		return abs.getParameters();
	}

	@Override
	public void addPort(IPort port) {
		abs.addPort(port);
		pres.addPort(((ICPort)port).getPresentation());
	}

	@Override
	public void removePort(IPort port) {
		abs.removePort(port);
	}

	@Override
	public IPort getSynthLabPort(int i) {
		return abs.getSynthLabPort(i);
	}

	@Override
	public List<IPort> getSynthLabPorts() {
		return abs.getSynthLabPorts();
	}

	public IPModule getPresentation() {
		return pres;
	}
	
}
