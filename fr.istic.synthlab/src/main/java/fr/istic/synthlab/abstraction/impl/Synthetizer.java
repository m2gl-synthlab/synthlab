package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import fr.istic.synthlab.abstraction.IClock;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.ISynthesizer;

/**
 * @author Clément Hardouin
 *
 */
public class Synthetizer implements ISynthesizer {

	private IClock clock;
	private List<IModule> modules;
	private boolean isRunning; // TODO : Pattern State ?

	/**
	 * Constructor
	 */
	public Synthetizer() {
		modules = new ArrayList<IModule>();
		clock = new Clock();
		isRunning = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.ISynthesizer#start()
	 */
	public void start() {
		if(!isRunning()){
			isRunning = true;
			for (IModule m : modules) {
				m.start();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.ISynthesizer#stop()
	 */
	public void stop() {
		if(isRunning()){
			for (IModule m : modules) {
				m.stop();
			}
			isRunning = false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.ISynthesizer#isRunning()
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.ISynthesizer#addModule(fr.istic.synthlab.abstraction.IModule)
	 */
	public void addModule(IModule module) {
		modules.add(module);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.abstraction.ISynthesizer#removeModule(fr.istic.synthlab.abstraction.IModule)
	 */
	public void removeModule(IModule module) {
		modules.remove(module);
	}

}
