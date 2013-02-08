package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModuleREP;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleREP extends AModule implements IModuleREP {

	private static final String MODULE_NAME = "REP";
	private static final String IN_NAME = "In";
	private static final String OUT1_NAME = "Out1";
	private static final String OUT2_NAME = "Out2";
	private static final String OUT3_NAME = "Out3";

	private static final int OUT1 = 0;
	private static final int OUT2 = 1;
	private static final int OUT3 = 2;

	private IInputPort in;
	private IOutputPort out1, out2, out3;

	private Map<Integer, IOutputPort> outputs;

	public ModuleREP(ISynthesizer synth) {
		super(MODULE_NAME, synth);

		this.outputs = new HashMap<Integer, IOutputPort>();

		this.in = PACFactory.getFactory().newInputPort(this, IN_NAME);
		this.out1 = PACFactory.getFactory().newOutputPort(this, OUT1_NAME);
		this.out2 = PACFactory.getFactory().newOutputPort(this, OUT2_NAME);
		this.out3 = PACFactory.getFactory().newOutputPort(this, OUT3_NAME);

		this.outputs.put(ModuleREP.OUT1, PACFactory.getFactory().newOutputPort(this, OUT1_NAME, out1.getJSyn()));
		this.outputs.put(ModuleREP.OUT2, PACFactory.getFactory().newOutputPort(this, OUT2_NAME, out2.getJSyn() ));
		this.outputs.put(ModuleREP.OUT3, PACFactory.getFactory().newOutputPort(this, OUT3_NAME, out3.getJSyn()));

		out1.connect(in);
		out2.connect(in);
		out3.connect(in);		
	}

	@Override
	public String getName() {
		return MODULE_NAME;
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		wires.add(in.getWire());
		for(IOutputPort outputPort : outputs.values()){
			wires.add(outputPort.getWire());
		}
		return wires;
	}

	@Override
	public IInputPort getInput() {
		// TODO Auto-generated method stub
		return in;
	}
	
	public IOutputPort getOutput(int ref){
		return outputs.get(ref);
	}

}
