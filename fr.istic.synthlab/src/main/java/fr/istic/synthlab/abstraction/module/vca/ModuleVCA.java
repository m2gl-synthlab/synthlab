package fr.istic.synthlab.abstraction.module.vca;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.unitgen.UnitFilter;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCA extends AModule implements IModuleVCA {

	private static final String MODULE_NAME = "VCA";
	private static final String INPUT_NAME = "Input";
	private static final String INPUT_AM_NAME = "AM";
	private static final String OUTPUT_NAME = "Output";
	
	public static final String PARAM_AMPLITUDE_NAME = "Gain";

	// Input & Output du module
	private IInputPort input, inputAm;
	private IOutputPort output;
	
	// Generateur Perso
	private AttenuationFilter attenuator;

	public ModuleVCA(ISynthesizer synth) {
		super(MODULE_NAME, synth);

		this.attenuator = new AttenuationFilter();

		// Ajout du port OUTPUT
		this.output = PACFactory.getFactory().newOutputPort(this, OUTPUT_NAME,
				attenuator.output);

		// Ajout du port INPUT
		this.input = PACFactory.getFactory().newInputPort(this, INPUT_NAME,
				attenuator.input);

		// Ajout du port AM
		this.inputAm = PACFactory.getFactory().newInputPort(this,
				INPUT_AM_NAME, attenuator.inputAm);
		
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
	
	/**
	 * Attenuation Filter
	 */
	private class AttenuationFilter extends UnitFilter {
		double attenuationValue = 0; // Value between -30 and 12
		
		UnitInputPort inputAm = new UnitInputPort(INPUT_AM_NAME);
		
		public AttenuationFilter() {
			this.addPort(inputAm);
		}

		@Override
		public void generate(int start, int limit) {
			// Get signal arrays from ports.
			double[] inputs = input.getValues();
			double[] inputsAm = inputAm.getValues();
			double[] outputs = output.getValues();

			for (int i = start; i < limit; i++) {
				double freq = inputs[i];
				double am = inputsAm[i];
				outputs[i] = Math.pow(10, (attenuationValue / 20) + am) * freq;
				// see : http://fr.wikipedia.org/wiki/Niveau_(audio)
			}
		}
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(attenuator);
		return generators;
	}

	@Override
	public String getName() {
		return MODULE_NAME;
	}

	@Override
	public void start() {
		for (UnitGenerator gen : getJSyn())
			getSynthesizer().getJSyn().add(gen);
	}
	
	@Override
	public void stop() {
		this.attenuator.stop();
	}

	@Override
	public IInputPort getInput() {
		return input;
	}

	@Override
	public IInputPort getInputAM() {
		return inputAm;
	}
	
	@Override
	public IOutputPort getOutput() {
		return output;
	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		wires.add(input.getWire());
		wires.add(inputAm.getWire());
		wires.add(output.getWire());
		return wires;
	}

	@Override
	public void setAttenuationValue(double value) {
		this.attenuator.attenuationValue = value;
	}

	@Override
	public double getAttenuationValue() {
		return this.attenuator.attenuationValue;
	}

}
