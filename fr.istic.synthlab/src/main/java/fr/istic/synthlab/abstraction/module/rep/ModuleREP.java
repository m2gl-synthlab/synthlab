package fr.istic.synthlab.abstraction.module.rep;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.filter.TriplePassThroughFilter;
import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleREP extends AModule implements IModuleREP {

	private static final String MODULE_NAME = "REP";
	private static final String IN_NAME = "In";
	private static final String OUT1_NAME = "Out1";
	private static final String OUT2_NAME = "Out2";
	private static final String OUT3_NAME = "Out3";

	private IInputPort in;
	private IOutputPort output1;
	private IOutputPort output2;
	private IOutputPort output3;
	private TriplePassThroughFilter passThrough;

	public ModuleREP(ISynthesizer synth) {
		super(MODULE_NAME, synth);

		this.passThrough = new TriplePassThroughFilter();

		this.in = PACFactory.getFactory().newInputPort(this, IN_NAME,
				passThrough.getInput());

		this.output1 = PACFactory.getFactory().newOutputPort(this, OUT1_NAME,
				passThrough.getOutput1());
		this.output2 = PACFactory.getFactory().newOutputPort(this, OUT2_NAME,
				passThrough.getOutput2());
		this.output3 = PACFactory.getFactory().newOutputPort(this, OUT3_NAME,
				passThrough.getOutput3());

	}

	@Override
	public String getName() {
		return MODULE_NAME;
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		return generators;
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
		if (in.getWire() != null) {
			wires.add(in.getWire());
		}
		if (output1.getWire() != null) {
			wires.add(output1.getWire());
		}
		if (output2.getWire() != null) {
			wires.add(output2.getWire());
		}
		if (output3.getWire() != null) {
			wires.add(output3.getWire());
		}
		return wires;
	}

	@Override
	public IInputPort getInput() {
		return in;
	}

	@Override
	public IOutputPort getOutput1() {
		return output1;
	}

	@Override
	public IOutputPort getOutput2() {
		return output2;
	}

	@Override
	public IOutputPort getOutput3() {
		return output3;
	}

}