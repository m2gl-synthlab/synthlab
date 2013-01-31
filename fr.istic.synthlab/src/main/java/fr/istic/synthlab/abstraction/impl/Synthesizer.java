package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.JSyn;
import com.jsyn.devices.AudioDeviceManager;
import com.jsyn.engine.SynthesisEngine;
import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.ScheduledCommand;
import com.softsynth.shared.time.TimeStamp;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.ISynthesizer;

/**
 * @author Clément Hardouin
 *
 */
public class Synthesizer implements ISynthesizer {


	private com.jsyn.Synthesizer synth;
	
	private List<IModule> modules;

	/**
	 * Constructor
	 */
	public Synthesizer() {
		this.synth= JSyn.createSynthesizer();
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
		this.synth.add(module.getJSyn());
		modules.add(module);
	}

	@Override
	public TimeStamp createTimeStamp() {
		return this.synth.createTimeStamp();
	}

	@Override
	public AudioDeviceManager getAudioDeviceManager() {
		return this.synth.getAudioDeviceManager();
	}

	@Override
	public double getCurrentTime() {
		return this.synth.getCurrentTime();
	}

	@Override
	public double getFramePeriod() {
		return this.synth.getFramePeriod();
	}

	@Override
	public int getFrameRate() {
		return this.synth.getFrameRate();
	}

	@Override
	public double getUsage() {
		return this.synth.getUsage();
	}

	@Override
	public String getVersion() {
		return this.synth.getVersion();
	}

	@Override
	public int getVersionCode() {
		return this.synth.getVersionCode();
	}

	@Override
	public boolean isRealTime() {
		return this.synth.isRealTime();
	}

	@Override
	public boolean isRunning() {
		return this.synth.isRunning();
	}

	@Override
	public void queueCommand(ScheduledCommand arg0) {
		this.synth.queueCommand(arg0);
	}

	@Override
	public void remove(UnitGenerator arg0) {
		this.synth.remove(arg0);
	}

	@Override
	public void scheduleCommand(TimeStamp arg0, ScheduledCommand arg1) {
		this.synth.scheduleCommand(arg0, arg1);
	}

	@Override
	public void scheduleCommand(double arg0, ScheduledCommand arg1) {
		this.synth.scheduleCommand(arg0, arg1);
	}

	@Override
	public void setRealTime(boolean arg0) {
		this.synth.setRealTime(arg0);
	}

	@Override
	public void sleepFor(double arg0) throws InterruptedException {
		this.synth.sleepFor(arg0);
	}

	@Override
	public void sleepUntil(double arg0) throws InterruptedException {
		this.synth.sleepUntil(arg0);
	}

	@Override
	public void start() {
		this.synth.start();
		System.out.println("Synth : JSyn synth started " + this.synth.isRunning()); // TODO : remove
	}

	@Override
	public void start(int arg0) {
		this.synth.start(arg0);
	}

	@Override
	public void start(int arg0, int arg1, int arg2, int arg3, int arg4) {
		this.synth.start(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void startUnit(UnitGenerator arg0) {
		this.synth.startUnit(arg0);
	}

	@Override
	public void startUnit(UnitGenerator arg0, double arg1) {
		this.synth.startUnit(arg0, arg1);
	}

	@Override
	public void startUnit(UnitGenerator arg0, TimeStamp arg1) {
		this.synth.startUnit(arg0, arg1);
	}

	@Override
	public void stop() {
		this.synth.stop();
	}

	@Override
	public void stopUnit(UnitGenerator arg0) {
		this.synth.stopUnit(arg0);
	}

	@Override
	public void stopUnit(UnitGenerator arg0, double arg1) {
		this.synth.stopUnit(arg0, arg1);
	}

	@Override
	public void stopUnit(UnitGenerator arg0, TimeStamp arg1) {
		this.synth.stopUnit(arg0, arg1);
	}



//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fr.istic.synthlab.abstraction.ISynthesizer#start()
//	 */
//	@Override
//	public void start() {
//		if(!isRunning()){
//			isRunning = true;
//			for (IModule m : modules) {
//				m.start();
//			}
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fr.istic.synthlab.abstraction.ISynthesizer#stop()
//	 */
//	@Override
//	public void stop() {
//		if(isRunning()){
//			for (IModule m : modules) {
//				m.stop();
//			}
//			isRunning = false;
//		}
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fr.istic.synthlab.abstraction.ISynthesizer#isRunning()
//	 */
//	@Override
//	public boolean isRunning() {
//		return isRunning;
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fr.istic.synthlab.abstraction.ISynthesizer#addModule(fr.istic.synthlab.abstraction.IModule)
//	 */
//	@Override
//	public void addModule(IModule module) {
//		this.add((UnitGenerator) module);
//		modules.add(module);
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @see fr.istic.synthlab.abstraction.ISynthesizer#removeModule(fr.istic.synthlab.abstraction.IModule)
//	 */
//	@Override
//	public void removeModule(IModule module) {
//		modules.remove(module);
//	}
//
//	/* (non-Javadoc)
//	 * @see fr.istic.synthlab.abstraction.ISynthesizer#getModule(int)
//	 */
//	@Override
//	public IModule getModule(int i) {
//		return modules.get(i);
//	}
//
//	/* (non-Javadoc)
//	 * @see fr.istic.synthlab.abstraction.ISynthesizer#getModules()
//	 */
//	@Override
//	public List<IModule> getModules() {
//		return modules;
//	}


}
