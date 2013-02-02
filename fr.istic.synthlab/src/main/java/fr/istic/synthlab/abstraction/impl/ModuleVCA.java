package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IModule;

public class ModuleVCA implements IModule{

	public static final int INPUT_IN = 0;
	
	private LineOut vca;
	private List<InputPort> inputs;
	
	public ModuleVCA() {
		this.vca = new LineOut();
		this.inputs = new ArrayList<InputPort>();
		this.inputs.add(ModuleVCA.INPUT_IN, new InputPort(vca.getInput()));
		
	}

	@Override
	public UnitGenerator getJSyn() {
		return this.vca;
	}
	
	@Override
	public void start() {
		vca.start();
	}

	@Override
	public void stop() {
		vca.stop();
	}
	
	@Override
	public OutputPort getOutput(int identifier) {
		System.err.println("No Output in "+ getClass().getSimpleName());
		return null;
	}

	@Override
	public InputPort getInput(int identifier) {
		return inputs.get(identifier);
	}



}
