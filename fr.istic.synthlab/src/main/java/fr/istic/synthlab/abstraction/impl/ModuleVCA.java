package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.IdentifierHelper;

import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SineOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitFilter;
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

	private IdentityFilter none_output, none_input, none_input_am;
	private IInputPort input, input_am;
	private IOutputPort output;

	private IParameter parameter;

	public ModuleVCA(ISynthesizer synth) {
		super(MODULE_NAME, synth);

		this.none_output = new IdentityFilter();
		this.none_input = new IdentityFilter();
		this.none_input_am = new IdentityFilter();
		// Ajout du port OUTPUT
		this.output = PACFactory.getFactory().newOutputPort(this, OUTPUT_NAME,
				none_output.getOutput());

		// Ajout du port INPUT
		this.input = PACFactory.getFactory().newInputPort(this, INPUT_NAME,
				none_input.getInput());

		// Ajout du port AM
		this.input_am = PACFactory.getFactory().newInputPort(this,
				INPUT_AM_NAME, none_input_am.getInput());

		// TODO A REVOIR
		this.parameter = PACFactory.getFactory().newParameter(this,
				AMPLITUDE_NAME, 0, 1, 0);
		
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
	private class IdentityFilter extends UnitFilter {
		public void generate(int start, int limit) {
			// Get signal arrays from ports.
			double[] inputs = input.getValues();
			double[] outputs = output.getValues();

			for (int i = start; i < limit; i++) {

				outputs[i] = inputs[i];
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
		return input;
	}

	@Override
	public IInputPort getInputAM() {
		return input_am;
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
		wires.add(input.getWire());
		wires.add(input_am.getWire());
		wires.add(output.getWire());
		return wires;
	}

}
