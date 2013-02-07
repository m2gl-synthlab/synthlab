package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.data.SegmentedEnvelope;
import com.jsyn.unitgen.UnitFilter;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModuleEG;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

/**
 * OUT Module
 * Send the input attenuated signal to the sound card.
 */
public class ModuleEG extends AModule implements IModuleEG{

	private static final String MODULE_NAME = "EG";
	private static final String IN_NAME = "Gate";
	private static final String OUT_NAME = "Out";

	private IInputPort gate;
	private IOutputPort out;
	
	private double release;
	private double sustain;
	private double decay;
	private double attack;
	
	private SegmentedEnvelope env;

	public ModuleEG(ISynthesizer synth) {
		super(MODULE_NAME, synth);


		this.gate = PACFactory.getFactory().newInputPort(this, IN_NAME, null);
		this.out = PACFactory.getFactory().newOutputPort(this, OUT_NAME, null);

		
//	    double[] data =
//	        {
//	            0.02, 1.0,  // duration,value pair for frame[0]
//	            0.30, 0.1,  // duration,value pair for frame[1]
//	            0.50, 0.7,  // duration,value pair for frame[2]
//	            0.50, 0.9,  // duration,value pair for frame[3]
//	            0.80, 0.0   // duration,value pair for frame[4]
//	        };
//	        myEnvData = new SegmentedEnvelope( data );
		
	}

	@Override
	public void setAttack(double attackTime) {
		this.attack = attackTime;
	}

	@Override
	public double getAttack() {
		return this.attack;
	}

	@Override
	public void setDecay(double decayTime) {
		this.decay = decayTime;
	}

	@Override
	public double getDecay() {
		return this.decay;
	}

	@Override
	public void setSustain(double substainTime) {
		this.sustain = substainTime;
	}
	
	@Override
	public double getSustain() {
		return this.sustain;
	}

	@Override
	public void setRelease(double releaseTime) {
		this.release = releaseTime;
	}

	@Override
	public double getRelease() {
		return this.release;
	}

	@Override
	public IInputPort getGateInput() {
		return this.gate;
	}

	@Override
	public IOutputPort getOutput() {
		return this.out;
	}
	
	
	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		return generators;
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		wires.add(gate.getWire());
		return wires;
	}
	
	/**
	 * Attenuation Filter
	 */
	private class AttenuationFilter extends UnitFilter {
		double attenuationValue = 0; // Value between -inf and 12

		@Override
		public void generate(int start, int limit) {
			// Get signal arrays from ports.
			double[] inputs = input.getValues();
			double[] outputs = output.getValues();

			for (int i = start; i < limit; i++) {
				double x = inputs[i];
				outputs[i] = Math.pow(10, attenuationValue / 20) * x;
				// see : http://fr.wikipedia.org/wiki/Niveau_(audio)
			}
		}

	}

	
}
