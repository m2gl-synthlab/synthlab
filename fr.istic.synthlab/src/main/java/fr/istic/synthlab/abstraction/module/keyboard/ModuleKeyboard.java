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
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IWire> getWires() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void play(double frequency) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IOutputPort getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

}
