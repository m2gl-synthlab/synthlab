package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.JSyn;
import com.jsyn.engine.SynthesisEngine;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.ISynthesizer;

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
		System.out.println("Adding module" + this + " " +this.modules.size());
		modules.add(module);
		this.synth.add(module.getJSyn());
	}

	@Override
	public void remove(IModule module) {
		modules.add(module);
		this.synth.remove(module.getJSyn());
	}

	@Override
	public void start() {
		
		this.synth.start();
		System.out.println("Synth.start() " + this + " " + this.modules.size());
		for(IModule mod : this.modules){

			System.out.println("IModule.start()");
			mod.start();
		}
	}

	@Override
	public void startModule(IModule module) {
		//this.synth.startUnit(module.getJSyn());
		module.start();
	}

	@Override
	public void stop() {
		this.synth.stop();

		this.synth.start();
		for(IModule mod : this.modules){
			mod.stop();
		}
	}

	@Override
	public void stopModule(IModule module) {
		//this.synth.stopUnit(module.getJSyn());
		module.stop();
	}

	@Override
	public boolean isRunning() {
		return this.synth.isRunning();
	}

	// @Override
	// public void queueCommand(ScheduledCommand arg0) {
	// this.synth.queueCommand(arg0);
	// }
	// @Override
	// public void queueCommand(ScheduledCommand arg0) {
	// this.synth.queueCommand(arg0);
	// }
	//
	// @Override
	// public void scheduleCommand(TimeStamp arg0, ScheduledCommand arg1) {
	// this.synth.scheduleCommand(arg0, arg1);
	// }
	//
	// @Override
	// public void scheduleCommand(double arg0, ScheduledCommand arg1) {
	// this.synth.scheduleCommand(arg0, arg1);
	// }
	//
	// @Override
	// public void setRealTime(boolean arg0) {
	// this.synth.setRealTime(arg0);
	// }
	//
	// @Override
	// public void sleepFor(double arg0) throws InterruptedException {
	// this.synth.sleepFor(arg0);
	// }
	//
	// @Override
	// public void sleepUntil(double arg0) throws InterruptedException {
	// this.synth.sleepUntil(arg0);
	// }
	//
	// @Override
	// public void start(int arg0) {
	// this.synth.start(arg0);
	// }
	//
	// @Override
	// public void start(int arg0, int arg1, int arg2, int arg3, int arg4) {
	// this.synth.start(arg0, arg1, arg2, arg3, arg4);
	// }
	//
	// @Override
	// public void startUnit(UnitGenerator arg0, double arg1) {
	// this.synth.startUnit(arg0, arg1);
	// }
	//
	// @Override
	// public void startUnit(UnitGenerator arg0, TimeStamp arg1) {
	// this.synth.startUnit(arg0, arg1);
	// }
	//
	// @Override
	// public void stopUnit(UnitGenerator arg0, double arg1) {
	// this.synth.stopUnit(arg0, arg1);
	// }
	//
	// @Override
	// public void stopUnit(UnitGenerator arg0, TimeStamp arg1) {
	// this.synth.stopUnit(arg0, arg1);
	// }

	// /*
	// * (non-Javadoc)
	// *
	// * @see fr.istic.synthlab.abstraction.ISynthesizer#start()
	// */
	// @Override
	// public void start() {
	// if(!isRunning()){
	// isRunning = true;
	// for (IModule m : modules) {
	// m.start();
	// }
	// }
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see fr.istic.synthlab.abstraction.ISynthesizer#stop()
	// */
	// @Override
	// public void stop() {
	// if(isRunning()){
	// for (IModule m : modules) {
	// m.stop();
	// }
	// isRunning = false;
	// }
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see fr.istic.synthlab.abstraction.ISynthesizer#isRunning()
	// */
	// @Override
	// public boolean isRunning() {
	// return isRunning;
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// fr.istic.synthlab.abstraction.ISynthesizer#addModule(fr.istic.synthlab.abstraction.IModule)
	// */
	// @Override
	// public void addModule(IModule module) {
	// this.add((UnitGenerator) module);
	// modules.add(module);
	// }
	//
	// /*
	// * (non-Javadoc)
	// *
	// * @see
	// fr.istic.synthlab.abstraction.ISynthesizer#removeModule(fr.istic.synthlab.abstraction.IModule)
	// */
	// @Override
	// public void removeModule(IModule module) {
	// modules.remove(module);
	// }
	//
	// /* (non-Javadoc)
	// * @see fr.istic.synthlab.abstraction.ISynthesizer#getModule(int)
	// */
	// @Override
	// public IModule getModule(int i) {
	// return modules.get(i);
	// }
	//
	// /* (non-Javadoc)
	// * @see fr.istic.synthlab.abstraction.ISynthesizer#getModules()
	// */
	// @Override
	// public List<IModule> getModules() {
	// return modules;
	// }

}
