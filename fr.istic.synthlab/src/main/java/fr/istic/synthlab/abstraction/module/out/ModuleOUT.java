package fr.istic.synthlab.abstraction.module.out;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.ChannelOut;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.filter.AttenuationFilter;
import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.util.Convert;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

/**
 * OUT Module Send the input attenuated signal to the sound card.
 */
public class ModuleOUT extends AModule implements IModuleOUT {

	private static final String MODULE_NAME = "OUT";
	private static final String IN_NAME = "In";

	private ChannelOut out;
	private AttenuationFilter attenuator;

	private IInputPort in;

	private boolean isMute;

	public ModuleOUT() {
		super(MODULE_NAME);
		this.out = new ChannelOut();
		this.attenuator = new AttenuationFilter();

		this.in = PACFactory.getFactory().newInputPort(this, IN_NAME, attenuator.input);
		this.setAttenuation(0);

		attenuator.output.connect(out.input);
		
		addPort(in);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(out);
		generators.add(attenuator);
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
		getParameters().put("attenuation", (double) value);
		this.attenuator.setAttenuation(Convert.dB2V(value));
	}

	@Override
	public double getAttenuation() {
		return getParameter("attenuation").intValue();
	}

	@Override
	public IInputPort getInput() {
		return this.in;
	}

	@Override
	public void setMute(boolean mute) {
		this.isMute = mute;
		if (isMute()) {
			stop();
		} else {
			start();
		}
	}

	@Override
	public boolean isMute() {
		return isMute;
	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		if (in.getWire() != null) {
			if(!wires.contains(in.getWire()))
				wires.add(in.getWire());
		}
		return wires;
	}

}
