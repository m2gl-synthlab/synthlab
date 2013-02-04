package fr.istic.synthlab.abstraction.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsyn.unitgen.ChannelOut;
import com.jsyn.unitgen.FourWayFade;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleOUT implements IModule {

	public static final int INPUT_IN = 0;
	
	public static final int PARAM_GAIN = 0;
	public static final int PARAM_SWITCH_ON_OFF = 1;

	private static final String MODULE_NAME = "OUT";

	private static final String IN_NAME = "In";
	private static final String GAIN_NAME = "Gain";
	private static final String MUTE_NAME = "Mute";

	private ISynthesizer parentSynth;
	
	private ChannelOut vca;
	private FourWayFade fader;
	private Map<Integer, IInputPort> inputs;
	private Map<Integer, IParameter> params;

	public ModuleOUT(String name) {
		this.vca = new ChannelOut();
		this.fader = new FourWayFade();
		
		this.inputs = new HashMap<Integer, IInputPort>();
		this.params = new HashMap<Integer, IParameter>();
	
		this.inputs.put(ModuleOUT.INPUT_IN, PACFactory.getFactory().newInputPort(this, IN_NAME, fader.input, 1));
		
		
		
		
		
		fader.fade.setMinimum(-1);
		fader.output.connect(vca.input);
		
		IParameter gain = PACFactory.getFactory().newParameter(this, GAIN_NAME, fader.fade.getMinimum(),12,0);
		gain.connect(fader.fade);
		this.params.put(ModuleOUT.PARAM_GAIN, gain);
		
		IParameter switchOnOff = PACFactory.getFactory().newSwitch(this, MUTE_NAME, false);
		switchOnOff.connect(vca.input);
		this.params.put(ModuleOUT.PARAM_SWITCH_ON_OFF, switchOnOff);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(vca);
		return generators;
	}

	@Override
	public String getName() {
		return MODULE_NAME;
	}

	@Override
	public void start() {
		vca.start();
	}

	@Override
	public void stop() {
		vca.stop();
	}

	@Override
	public IOutputPort getOutput(int identifier) {
		System.err.println("No Output in " + getClass().getSimpleName());
		return null;
	}

	@Override
	public IInputPort getInput(int identifier) {
		return inputs.get(identifier);
	}

	@Override
	public IParameter getParameter(int identifier) {
		return params.get(identifier);
	}

	@Override
	public ISynthesizer getSynthesizer() {
		return parentSynth;
	}

	@Override
	public void setSynthesizer(ISynthesizer synth) {
		parentSynth = synth;
	}

}
