package fr.istic.synthlab.abstraction.module.keyboard;

import java.util.List;

import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;

public class ModuleKeyboard extends AModule implements IModuleKeyboard {

	private static final String MODULE_NAME = "EG";
	
	public ModuleKeyboard(ICSynthesizer synth) {
		super(synth, MODULE_NAME);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		return null;
	}

	@Override
	public void start() {
		
	}

	@Override
	public List<IWire> getWires() {
		return null;
	}

	@Override
	public void play(double frequency) {		
	}

	@Override
	public void stop() {		
	}

	@Override
	public IOutputPort getOutput() {
		return null;
	}

}
