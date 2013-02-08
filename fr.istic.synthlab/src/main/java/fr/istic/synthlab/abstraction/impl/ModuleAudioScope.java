package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.scope.AudioScope;
import com.jsyn.scope.AudioScopeModel;
import com.jsyn.unitgen.UnitFilter;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModuleAudioScope;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

/**
 * AudioScope Module Display an AudioScope
 */
public class ModuleAudioScope extends AModule implements IModuleAudioScope {

	private static final String MODULE_NAME = "AudioScope";

	private static final String OUT_NAME = "Out";
	private static final String IN_NAME = "In";

	private AudioScope scope;
	private IdentityFilter adapter;

	private IInputPort in;
	private IOutputPort out;

	public ModuleAudioScope(ISynthesizer synth) {
		super(MODULE_NAME, synth);
		this.scope = new AudioScope(synth.getJSyn());
		this.adapter = new IdentityFilter();

		this.in = PACFactory.getFactory().newInputPort(this, IN_NAME,
				adapter.input);
		this.out = PACFactory.getFactory().newOutputPort(this, OUT_NAME,
				adapter.output);

		this.scope.addProbe(this.out.getJSyn());
		this.scope.setTriggerMode(AudioScope.TriggerMode.NORMAL);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(adapter);
		return generators;
	}

	@Override
	public void start() {
		scope.start();
	}

	@Override
	public void stop() {
		scope.stop();
	}

	@Override
	public IInputPort getInput() {
		return this.in;
	}

	@Override
	public IOutputPort getOutput() {
		return this.out;
	}

	@Override
	public AudioScopeModel getModel() {
		return scope.getModel();
	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		if (in.getWire() != null) {
			wires.add(in.getWire());
		}
		return wires;
	}

	/**
	 * Attenuation Filter
	 */
	private class IdentityFilter extends UnitFilter {

		@Override
		public void generate(int start, int limit) {
			// Get signal arrays from ports.
			double[] inputs = input.getValues();
			double[] outputs = output.getValues();

			for (int i = start; i < limit; i++) {
				double x = inputs[i];
				outputs[i] = x;
			}
		}
	}

}
