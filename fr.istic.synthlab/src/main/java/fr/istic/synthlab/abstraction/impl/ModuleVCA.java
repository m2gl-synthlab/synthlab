package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCA implements IModule{

	public static final int INPUT_IN = 0;
	
	private LineOut vca;
	private List<IInputPort> inputs;
	
	public ModuleVCA() {
		this.vca = new LineOut();
		this.inputs = new ArrayList<IInputPort>();
		this.inputs.add(ModuleVCA.INPUT_IN, PACFactory.getFactory().newInputPort(vca.getInput()));
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
	public IOutputPort getOutput(int identifier) {
		System.err.println("No Output in "+ getClass().getSimpleName());
		return null;
	}

	@Override
	public IInputPort getInput(int identifier) {
		return inputs.get(identifier);
	}



}
