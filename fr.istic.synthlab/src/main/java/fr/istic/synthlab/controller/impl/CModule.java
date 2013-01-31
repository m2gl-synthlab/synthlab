package fr.istic.synthlab.controller.impl;

import java.util.List;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.IPort;
import fr.istic.synthlab.controller.ICModule;
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addParameter(IParameter param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeParameter(IParameter param) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IParameter getParameter(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IParameter> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPort(IPort port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePort(IPort port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IPModule getPresentation() {
		return pres;
	}

	@Override
	public IPort getSynthLabPort(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IPort> getSynthLabPorts() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
