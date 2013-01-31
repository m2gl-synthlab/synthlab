package fr.istic.synthlab.abstraction.impl;

import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.TimeStamp;

import fr.istic.synthlab.abstraction.IModule;

public class ModuleVCA implements IModule{

	private LineOut vca;
	private InputPort in;
	
	public ModuleVCA() {
		this.vca = new LineOut();
		this.in = new InputPort(vca.getInput());
	}

	@Override
	public UnitGenerator getJSyn() {
		return this.vca;
	}

	
	@Override
	public OutputPort getOutput() {
		System.err.println("No Output in "+ getClass().getSimpleName());
		return null;
	}

	@Override
	public UnitGenerator getUnitGenerator() {
		return vca.getUnitGenerator();
	}

	@Override
	public InputPort getInput() {
		return in;
	}

	@Override
	public void start() {
		vca.start();
	}

	@Override
	public void start(TimeStamp time) {
		vca.start(time);
	}

	@Override
	public void stop() {
		vca.stop();
	}

	@Override
	public void stop(TimeStamp time) {
		vca.stop(time);
	}

}
