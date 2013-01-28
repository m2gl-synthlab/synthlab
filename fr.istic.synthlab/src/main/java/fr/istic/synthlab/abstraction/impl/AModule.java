package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.IPort;

/**
 * 
 * @author Clément Hardouin
 */
public abstract class AModule implements IModule {

	private String name;
	private boolean isRunning;
	private List<IParameter> parameters;
	private List<IPort> ports;

	/**
	 * 
	 */
	public AModule(String name) {
		this.name = name;
		this.isRunning = false;
		this.parameters = new ArrayList<IParameter>();
		this.ports = new ArrayList<IPort>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.IModule#start()
	 */
	public void start() {
		if (!isRunning()) isRunning = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.IModule#stop()
	 */
	public void stop() {
		if (isRunning()) isRunning = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.IModule#isRunning()
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IModule#addParameter(fr.istic.synthlab.abstraction.IParameter)
	 */
	public void addParameter(IParameter param) {
		this.parameters.add(param);
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IModule#removeParameter(fr.istic.synthlab.abstraction.IParameter)
	 */
	public void removeParameter(IParameter param) {
		this.parameters.remove(param);
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IModule#getParameter(int)
	 */
	public IParameter getParameter(int i) {
		return this.parameters.get(i);
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IModule#getParameters()
	 */
	public List<IParameter> getParameters() {
		return this.parameters;
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IModule#addPort(fr.istic.synthlab.abstraction.IPort)
	 */
	public void addPort(IPort port) {
		this.ports.add(port);
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IModule#removePort(fr.istic.synthlab.abstraction.IPort)
	 */
	public void removePort(IPort port) {
		this.ports.remove(port);
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IModule#getPort(int)
	 */
	public IPort getPort(int i) {
		return this.ports.get(i);
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.abstraction.IModule#getPorts()
	 */
	public List<IPort> getPorts() {
		return this.ports;
	}

}
