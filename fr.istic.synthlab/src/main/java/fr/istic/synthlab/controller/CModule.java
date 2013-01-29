package fr.istic.synthlab.controller;

import java.util.List;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.IPort;

public class CModule implements IModule {

	private String name;

	public CModule(String name){
		this.name = name;
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		
	}

	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addParameter(IParameter param) {
		// TODO Auto-generated method stub
		
	}

	public void removeParameter(IParameter param) {
		// TODO Auto-generated method stub
		
	}

	public IParameter getParameter(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IParameter> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	public void addPort(IPort port) {
		// TODO Auto-generated method stub
		
	}

	public void removePort(IPort port) {
		// TODO Auto-generated method stub
		
	}

	public IPort getPort(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<IPort> getPorts() {
		// TODO Auto-generated method stub
		return null;
	}

}
