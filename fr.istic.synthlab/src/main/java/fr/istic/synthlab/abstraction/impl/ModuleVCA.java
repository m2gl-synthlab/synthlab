package fr.istic.synthlab.abstraction.impl;

import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.TimeStamp;

import fr.istic.synthlab.abstraction.IModule;

public class ModuleVCA implements IModule{

	private LineOut out;
	
	public ModuleVCA() {
		this.out = new LineOut();
	}

	@Override
	public OutputPort getOutput() {
		System.err.println("No Ouput in "+ getClass().getSimpleName());
		return null;
	}

	@Override
	public UnitGenerator getUnitGenerator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputPort getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start(TimeStamp time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop(TimeStamp time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UnitGenerator getJSyn() {
		return this.out;
	}

	

}
