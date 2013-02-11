package fr.istic.synthlab.abstraction.module.audioscope;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.ports.UnitOutputPort;
import com.jsyn.scope.AudioScope;
import com.jsyn.scope.AudioScopeModel;
import com.jsyn.unitgen.UnitFilter;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
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

		this.in = PACFactory.getFactory().newInputPort(this, IN_NAME, adapter.input);
		this.out = PACFactory.getFactory().newOutputPort(this, OUT_NAME, adapter.output);

		this.scope.addProbe((UnitOutputPort) this.out.getJSyn());
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
		return in;
	}

	@Override
	public IOutputPort getOutput() {
		return out;
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
		if (out.getWire() != null) {
			wires.add(out.getWire());
		}
		return wires;
	}

	/**
	 * Identity Filter
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
