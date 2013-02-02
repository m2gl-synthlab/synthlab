package fr.istic.synthlab.abstraction.impl;

import java.util.HashMap;
import java.util.Map;

import com.jsyn.unitgen.LineOut;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.abstraction.IParameter;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleOUT implements IModule {

	public static final int INPUT_IN = 0;
	public static final int INPUT_GAIN = 1;

	private LineOut vca;
	private Map<Integer, IInputPort> inputs;
	private Map<Integer, IParameter> params;

	public ModuleOUT(String name) {
		this.vca = new LineOut();

		this.inputs = new HashMap<Integer, IInputPort>();
		this.params = new HashMap<Integer, IParameter>();

		this.inputs.put(ModuleOUT.INPUT_IN, PACFactory.getFactory()
				.newInputPort(vca.getInput()));
		this.inputs.put(ModuleOUT.INPUT_GAIN, PACFactory.getFactory()
				.newInputPort(vca.input));

		IParameter gain = PACFactory.getFactory().newParameter("Gain");
		gain.setValue(0);
		gain.connect(inputs.get(ModuleOUT.INPUT_GAIN));
		this.params.put(ModuleOUT.INPUT_GAIN, gain);
	}

	@Override
	public UnitGenerator getJSyn() {
		return this.vca;
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

}
