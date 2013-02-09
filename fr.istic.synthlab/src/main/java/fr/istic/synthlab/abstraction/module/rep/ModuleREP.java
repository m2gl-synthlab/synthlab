package fr.istic.synthlab.abstraction.module.rep;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;

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
	private Identitygenerator none_output;

	public ModuleREP(ISynthesizer synth) {
		super(MODULE_NAME, synth);

		this.none_output = new Identitygenerator();
		
		this.in = PACFactory.getFactory().newInputPort(this, IN_NAME,
				none_output.input);

		this.output1 = PACFactory.getFactory().newOutputPort(this,
				OUT1_NAME, none_output.output1);
		this.output2 = PACFactory.getFactory().newOutputPort(this,
				OUT2_NAME, none_output.output2);
		this.output3 = PACFactory.getFactory().newOutputPort(this,
				OUT3_NAME, none_output.output3);

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

	// crée un input Jsyn sans effet
	private class Identitygenerator extends UnitGenerator {

		UnitOutputPort output1 = new UnitOutputPort(OUT1_NAME);
		UnitOutputPort output2 = new UnitOutputPort(OUT2_NAME);
		UnitOutputPort output3 = new UnitOutputPort(OUT2_NAME);
		UnitInputPort input = new UnitInputPort(IN_NAME);

		public Identitygenerator() {
			this.addPort(output1);
			this.addPort(output2);
			this.addPort(output3);
			this.addPort(input);
		}
		
		@Override
		public void generate(int start, int limit) {
			// Get signal arrays from ports.
			double[] inputs = input.getValues();
			double[] outputs1 = output1.getValues();
			double[] outputs2 = output2.getValues();
			double[] outputs3 = output3.getValues();

			for (int i = start; i < limit; i++) {
				double x = inputs[i];
				outputs1[i] = x;
				outputs2[i] = x;
				outputs3[i] = x;
			}
		}
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
	public IInputPort getIn() {
		// TODO Auto-generated method stub
		return in;
	}

	public IOutputPort getOut1(){
		return output1;
	}
	
	public IOutputPort getOut2(){
		return output2;
	}
	
	public IOutputPort getOut3(){
		return output3;
	}

}
