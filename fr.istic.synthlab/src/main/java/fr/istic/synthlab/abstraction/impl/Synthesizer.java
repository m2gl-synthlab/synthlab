package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.JSyn;
import com.jsyn.engine.SynthesisEngine;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;

/**
 * Implementation of a Synthesizer
 */
public class Synthesizer implements ISynthesizer {

	private com.jsyn.Synthesizer synth;

	private List<IModule> modules;
	/**
	 * Constructor
	 */
	public Synthesizer() {
		this.synth = JSyn.createSynthesizer();
		modules = new ArrayList<IModule>();
	}

	/**
	 * Constructor
	 */
	public Synthesizer(SynthesisEngine jSynSynth) {
		this.synth = jSynSynth;
		modules = new ArrayList<IModule>();
	}

	@Override
	public com.jsyn.Synthesizer getJSyn() {
		return synth;
	}

	@Override
	public void add(IModule module) {
		modules.add(module);
		this.synth.add(module.getJSyn());
	}

	@Override
	public void remove(IModule module) {
		modules.add(module);
		this.synth.remove(module.getJSyn());
	}
	
	@Override
	public void add(IWire wire) {
		
	}

	@Override
	public void start() {
		if (!isRunning()) {
			this.synth.start(22000);
			for (IModule mod : this.modules) {
				mod.start();
			}
		}
	}

	@Override
	public void startModule(IModule module) {
		// this.synth.startUnit(module.getJSyn());
		module.start();
	}

	@Override
	public void stop() {
		if (isRunning()) {
			this.synth.stop();
			for (IModule mod : this.modules) {
				mod.stop();
			}
		}
	}

	@Override
	public void stopModule(IModule module) {
		// this.synth.stopUnit(module.getJSyn());
		module.stop();
	}

	@Override
	public boolean isRunning() {
		return this.synth.isRunning();
	}
	

}
