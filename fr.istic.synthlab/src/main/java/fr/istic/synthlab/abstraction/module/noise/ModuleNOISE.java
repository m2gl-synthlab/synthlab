package fr.istic.synthlab.abstraction.module.noise;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.unitgen.WhiteNoise;

import fr.istic.synthlab.abstraction.filter.AttenuationFilter;
import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.util.Convert;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

/**
 * NOISE Module Send a white noise with an attenuation parameter
 */
public class ModuleNOISE extends AModule implements IModuleNOISE {

	private static final String MODULE_NAME = "NOISE";
	private static final String OUT_NAME = "Out";

	protected static final boolean DEFAULT_STATE_MUTE = true;

	private WhiteNoise noise;
	private AttenuationFilter attenuator;	

	private IOutputPort out;

	public ModuleNOISE(ISynthesizer synth) {
		super(synth, MODULE_NAME);
		this.noise = new WhiteNoise();
		this.attenuator = new AttenuationFilter();

		this.out = PACFactory.getFactory().newOutputPort(synth, this, OUT_NAME,
				attenuator.output);
		this.setAttenuation(0);
		
		// Connect WhiteNoise generator to AttenuationFilter
		noise.output.connect(attenuator.input);

		addPort(out);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(noise);
		generators.add(attenuator);
		return generators;
	}

	@Override
	public void start() {
		noise.start();
		attenuator.start();
	}

	@Override
	public void stop() {
		noise.stop();
		attenuator.stop();
	}

	@Override
	public void setAttenuation(double value) {
		getParameters().put("attenuation", (double) value);
		this.attenuator.setAttenuation(Convert.dB2V(value));
	}

	@Override
	public double getAttenuation() {
		return getParameter("attenuation");
	}

	@Override
	public IOutputPort getOutput() {
		return this.out;
	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		if (out.getWire() != null) {
			if (!wires.contains(out.getWire()))
				wires.add(out.getWire());
		}
		return wires;
	}

}
