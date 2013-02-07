package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.ChannelOut;
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

	private static final String MODULE_NAME = "OUT";
	private static final String IN_NAME = "In";

	private AttenuationFilter fade;

	private IInputPort gate;

	public ModuleEG(ISynthesizer synth) {
		super(MODULE_NAME, synth);

//		this.out = new ChannelOut();
		this.fade = new AttenuationFilter();

		this.gate = PACFactory.getFactory().newInputPort(this, IN_NAME, fade.input);
		this.fade.attenuationValue = 0;

//		fade.output.connect(out.input);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
//		generators.add(out);
		generators.add(fade);
		return generators;
	}

	@Override
	public void start() {
//		out.start();
	}

	@Override
	public void stop() {
//		out.stop();
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

	@Override
	public void setAttack(double attackTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getAttack() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setDecay(double decayTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDecay() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getSubstain() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRelease(double releaseTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getRelease() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IInputPort getGateInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IOutputPort getOutput() {
		// TODO Auto-generated method stub
		return null;
	}
}
