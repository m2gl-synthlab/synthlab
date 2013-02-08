package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModuleVCA;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCA extends AModule implements IModuleVCA {

	private static final String MODULE_NAME = "VCA";
	public static final String INPUT_NAME = "INPUT";
	public static final String INPUT_AM_NAME = "AM";
	public static final String OUTPUT_NAME = "OUT";
	public static final String AMPLITUDE_NAME = "Gain";

	private IInputPort in, in_am;

	private IOutputPort output;
	private Identitygenerator none_output;

	private IParameter parameter;
	public double frequency;

	public ModuleVCA(ISynthesizer synth) {
		super(MODULE_NAME, synth);

		this.none_output = new Identitygenerator();

		// Ajout du port OUTPUT
		this.output = PACFactory.getFactory().newOutputPort(this, OUTPUT_NAME,
				none_output.output);

		// Ajout du port INPUT
		this.in = PACFactory.getFactory().newInputPort(this, INPUT_NAME,
				none_output.input1);

		// Ajout du port AM
		this.in_am = PACFactory.getFactory().newInputPort(this,
				INPUT_AM_NAME, none_output.input2);

		// TODO A REVOIR
		this.parameter = PACFactory.getFactory().newParameter(this,
				AMPLITUDE_NAME, 0, 1, 1);
		
		this.amplify();

	}

	/**
	 * fonction d'amplification du signal d'entr� par un facteur K
	 * 
	 */
	public void amplify() {
//		bufferOut = ByteBuffer.allocate(bufferIn.capacity());
//		bufferIn.clear();
//		bufferOut.clear();
//		// bufferIn.rewind();
//		while (bufferIn.hasRemaining()) {
//			// System.out.println("current double: " + bufferIn.getDouble());
//			// TODO Cela marche
//			if (flagGain == false) {
//				bufferOut.putDouble(bufferIn.getDouble() * amplifierK / 100);
//			} else {
//				bufferOut.putDouble(bufferIn.getDouble()
//						* Math.pow(2,
//								(amplifierK / 100)
//										+ ((bufferInGain.getDouble()) / 65536))
//						/ 2);
//				// bufferOut.putDouble((amplifierK/100)*((bufferIn.getDouble()+(bufferInGain.getDouble()))/2));
//				// 65536
//			}
//		}
//		bufferIn.clear();
//		bufferOut.flip();
		
		System.out.println("AMPLIFIER-VCA");
	}

	// crée un input Jsyn sans effet
	private class Identitygenerator extends UnitGenerator {

		UnitOutputPort output = new UnitOutputPort(OUTPUT_NAME);
		UnitInputPort input1 = new UnitInputPort(INPUT_NAME);
		UnitInputPort input2 = new UnitInputPort(INPUT_AM_NAME);

		public Identitygenerator() {
			this.addPort(output);
			this.addPort(input1);
			this.addPort(input2);
		}
		
		@Override
		public void generate(int start, int limit) {
			// Get signal arrays from ports.
			double[] inputs1 = input1.getValues();
			double[] inputs2 = input2.getValues();
			double[] outputs = output.getValues();

			for (int i = start; i < limit; i++) {
				double x = inputs1[i];
				double y = inputs2[i];
				//TODO changer la formule
				frequency = x*y;
				outputs[i] = frequency;
			}
		}
	}

	@Override
	public List<UnitGenerator> getJSyn() {

		return null;
	}

	@Override
	public String getName() {
		return MODULE_NAME;
	}

	@Override
	public void start() {

	}

	@Override
	public void stop() {

	}

	@Override
	public IInputPort getInput() {
		return in;
	}

	@Override
	public IInputPort getInputAM() {
		return in_am;
	}
	@Override
	public IOutputPort getOutput() {
		return output;
	}

	@Override
	public IParameter getParameter() {
		return parameter;
	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		wires.add(in.getWire());
		wires.add(in_am.getWire());
		wires.add(output.getWire());
		return wires;
	}

}
