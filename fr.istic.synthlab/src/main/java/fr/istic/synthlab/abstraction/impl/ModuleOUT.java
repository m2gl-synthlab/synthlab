package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.ChannelOut;
import com.jsyn.unitgen.UnitFilter;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModuleOUT;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleOUT extends AModule implements IModuleOUT {

	private static final String MODULE_NAME = "OUT";
	private static final String IN_NAME = "In";

	private ChannelOut out;
	private AttenuationFilter fade;

	private IInputPort in;
	private boolean isMute;

	public ModuleOUT(ISynthesizer synth) {
		super(MODULE_NAME, synth);
		System.out.println("ModuleOUT initialized");

		this.out = new ChannelOut();
		this.fade = new AttenuationFilter();

		this.in = PACFactory.getFactory().newInputPort(this, IN_NAME, fade.input);
		this.fade.attenuationValue = 0;

		fade.output.connect(out.input);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(out);
		generators.add(fade);
		return generators;
	}

	@Override
	public void start() {
		out.start();
	}

	@Override
	public void stop() {
		out.stop();
	}

	@Override
	public void setAttenuation(double value) {
		
		fade.attenuationValue = value;
	}

	@Override
	public double getAttenuation() {
		return fade.attenuationValue;
	}

	@Override
	public IInputPort getInput() {
		return this.in;
	}

	@Override
	public void setMute(boolean mute) {
		this.isMute = mute;
	}

	@Override
	public boolean isMute() {
		return isMute;
	}

	private class AttenuationFilter extends UnitFilter {
		double attenuationValue = 0; // Value between -1 and 1

		@Override
		public void generate(int start, int limit) {
			// Get signal arrays from ports.
			double[] inputs = input.getValues();
			double[] outputs = output.getValues();

			for (int i = start; i < limit; i++) {
				double x = inputs[i];
				outputs[i] = x * attenuationValue; // TODO : Check if ok -> probably not
				System.out.println(attenuationValue + " " +i + " = " + outputs[i]);
			}
		}
		
	}

}
