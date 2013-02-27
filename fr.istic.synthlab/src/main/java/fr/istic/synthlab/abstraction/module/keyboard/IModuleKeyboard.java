package fr.istic.synthlab.abstraction.module.keyboard;

import java.util.List;

import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;

public interface IModuleKeyboard {
	
	public List<UnitGenerator> getJSyn();

	public void start();

	public List<IWire> getWires();

	public void play(double frequency);

	public void stop();
	

	public IOutputPort getOutput();
	
	

}


