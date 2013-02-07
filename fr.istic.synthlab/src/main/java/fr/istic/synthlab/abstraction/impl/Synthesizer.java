package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.JSyn;
import com.jsyn.engine.SynthesisEngine;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;

/**
 * Implementation of a Synthesizer
 */
public class Synthesizer implements ISynthesizer {

	private com.jsyn.Synthesizer synth;

	private List<IModule> modules;
	private List<IWire> wires;

	private IWire currentWire;
	
	/**
	 * Constructor
	 */
	public Synthesizer() {
		this.synth = JSyn.createSynthesizer();
		modules = new ArrayList<IModule>();
		wires = new ArrayList<IWire>();
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
		for(UnitGenerator gen : module.getJSyn()){
			this.synth.add(gen);
		}
	}

	@Override
	public void remove(IModule module) {
		modules.remove(module);
		for(UnitGenerator gen : module.getJSyn()){
			this.synth.remove(gen);
		}
	}
	
	@Override
	public void add(IWire wire) {
		wires.add(wire);
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

	public List<IModule> getModules() {
		return modules;
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

	@Override
	public IWire getCurrentWire() {
		return currentWire;
	}

	@Override
	public void setCurrentWire(IWire wire) {
		currentWire = wire;
	}

	//TODO SERT A RIEN
	@Override
	public void remove(IWire wire) {
		wires.remove(wire);
		wire = null;
	}
	

}
