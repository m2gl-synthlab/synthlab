package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public static final int INPUT_VAL = 0;
	
	public static final int INPUT_AM_VAL = 1;
	
	public static final int PARAM_WIDTH = 2;

	private static final String MODULE_NAME = "VCA";

	public static final String INPUT_NAME = "INPUT";
	
	public static final String INPUT_AM_NAME = "AM";

	public static final String OUTPUT_NAME = "OUT";
	
	public static final String AMPLITUDE_NAME = "Ampl";

	private Map<Integer, IInputPort> inputs;
	private IOutputPort output;
	private Map<Integer, IParameter> params;

	public ModuleVCA(ISynthesizer synth) {
		super(MODULE_NAME, synth);

		this.inputs = new HashMap<Integer, IInputPort>();
		this.params = new HashMap<Integer, IParameter>();
		
		//Ajout du port OUTPUT
		this.output = PACFactory.getFactory().newOutputPort(this, OUTPUT_NAME, vcoSquare.getOutput()));
		
		//Ajout du port INPUT
		this.inputs.put(INPUT_VAL, PACFactory.getFactory().newInputPort(this, INPUT_VAL, vcoTriangle.frequency));
		
		//Ajout du port AM
		this.inputs.put(INPUT_AM_VAL, PACFactory.getFactory().newInputPort(this, INPUT_AM_VAL, vcoTriangle.frequency));
		

//
//		
		IParameter amplitude = PACFactory.getFactory().newParameter(this, AMPLITUDE_NAME, 0, 1, SineOscillator.DEFAULT_AMPLITUDE);
//		amplitude.connect(vcoSquare.amplitude);
//		amplitude.connect(vcoSine.amplitude);
//		amplitude.connect(vcoSawtooth.amplitude);
//		amplitude.connect(vcoTriangle.amplitude);
//		this.params.put(ModuleVCA.PARAM_AMPLITUDE, amplitude);
//
//		IParameter frequency = PACFactory.getFactory().newParameter(this, FREQUENCY_NAME,
//				vcoSquare.frequency.getMinimum(),
//				vcoSquare.frequency.getMaximum(),
//				SineOscillator.DEFAULT_FREQUENCY);
//		frequency.connect(vcoSquare.frequency);
//		frequency.connect(vcoSine.frequency);
//		frequency.connect(vcoSawtooth.frequency);
//		frequency.connect(vcoTriangle.frequency);
//		this.params.put(ModuleVCA.PARAM_FREQUENCY, frequency);
		
		}

		/**
		 * fonction d'amplification du signal d'entr� par un facteur K
		 * 
		 */
		public void amplify() {
			bufferOut = ByteBuffer.allocate(bufferIn.capacity());
			bufferIn.clear();
			bufferOut.clear();
			// bufferIn.rewind();
			while (bufferIn.hasRemaining()) {
				// System.out.println("current double: " + bufferIn.getDouble());
				// TODO Cela marche
				if (flagGain == false) {
					bufferOut.putDouble(bufferIn.getDouble() * amplifierK / 100);
				} else {
					bufferOut.putDouble(bufferIn.getDouble()
							* Math.pow(2, (amplifierK / 100)
									+ ((bufferInGain.getDouble()) / 65536)) / 2);
					// bufferOut.putDouble((amplifierK/100)*((bufferIn.getDouble()+(bufferInGain.getDouble()))/2));
					// 65536
				}
			}
			bufferIn.clear();
			bufferOut.flip();
		}
		
		
		//crée un input Jsyn sans effet
		private class IdentityFilter extends UnitFilter {

			public void generate(int start, int limit) {
				// Get signal arrays from ports.
				double[] inputs = input.getValues();
				double[] outputs = output.getValues();

				for (int i = start; i < limit; i++) {
					double x = inputs[i];
					outputs[i] = Math.pow(10, attenuationValue/20) * x; // TODO : Check if ok -> probably not
					System.out.println(attenuationValue + " " +i + " = " + outputs[i]);
				}
			}
			
		}	
		

	@Override
	public List<UnitGenerator> getJSyn() {
//		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
//		generators.add(vcoSquare);
//		generators.add(vcoTriangle);
//		generators.add(vcoSine);
//		generators.add(vcoSawtooth);
//		return generators;
		return null;
	}

	@Override
	public String getName() {
		return MODULE_NAME;
	}

	@Override
	public void start() {
//		this.vcoSquare.start();
//		this.vcoTriangle.start();
//		this.vcoSine.start();
//		this.vcoSawtooth.start();
	}

	@Override
	public void stop() {
//		this.vcoSquare.stop();
//		this.vcoTriangle.stop();
//		this.vcoSine.stop();
//		this.vcoSawtooth.stop();
	}

	@Override
	public IInputPort getInput(int identifier) {
		return inputs.get(identifier);
	}

	@Override
	public IOutputPort getOutput(int identifier) {
		//identifier ne sert à rien ici
		return output;
	}

	@Override
	public IParameter getParameter(int identifier) {
		return params.get(identifier);
	}


	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		for(IInputPort inputPort : inputs.values()){
			wires.add(inputPort.getWire());
		}

		wires.add(output.getWire());
		
		return wires;
	}

}
