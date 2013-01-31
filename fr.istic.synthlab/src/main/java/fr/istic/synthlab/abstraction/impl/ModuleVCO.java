package fr.istic.synthlab.abstraction.impl;

import com.jsyn.unitgen.PulseOscillator;
import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.TimeStamp;

import fr.istic.synthlab.abstraction.IModule;

public class ModuleVCO implements IModule{

	private PulseOscillator vco;
	private OutputPort out;
	
	
	public ModuleVCO() {
		this.vco = new PulseOscillator();
		this.out = new OutputPort(vco.getOutput());
	}

	@Override
	public UnitGenerator getJSyn() {
		return vco;
	}
	
	@Override
	public void start() {
		this.vco.start();
		System.out.println("Synth : JSyn vco started " + this.vco.isEnabled());
	}

	@Override
	public void start(TimeStamp arg0) {
		this.vco.start(arg0);
	}

	@Override
	public void stop() {
		this.vco.stop();
	}

	@Override
	public void stop(TimeStamp arg0) {
		this.vco.stop(arg0);
	}

	@Override
	public InputPort getInput() {
		System.err.println("No Input in "+ getClass().getSimpleName());
		return null;
	}

	@Override
	public OutputPort getOutput() {
		return out;
	}

	@Override
	public UnitGenerator getUnitGenerator() {
		return this.vco.getUnitGenerator();
	}
	

}
