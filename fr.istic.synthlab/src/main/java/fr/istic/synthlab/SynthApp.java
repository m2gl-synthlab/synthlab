package fr.istic.synthlab;

import java.util.List;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.util.ReadXMLFile;
import fr.istic.synthlab.util.WriteXMLFile;
//github.com/m2gl-synthlab/synthlab.git/


/**
 * Application
 */
public class SynthApp implements ISynthApp {

	private ICSynthesizer synth;
	private ICommand displayCmd;
	private ICommand undisplayCmd;

	@Override
	public void startSynth() {
		if(synth == null){
			newSynth();
		}
		displayCmd.execute();
	}

	@Override
	public void newSynth() {
		// Replace the current synthesizer with a new one
		if(this.synth != null)
			synth.stop();
		this.synth = (ICSynthesizer) PACFactory.getFactory().newSynthesizer();
		displayCmd.execute();

		// Add an OUT module
		IModuleOUT out = PACFactory.getFactory().newOUT(synth);
		synth.add(out);
		synth.start();
	}

	@Override
	public void quitSynth() {
		this.synth = null;
		undisplayCmd.execute();
		System.exit(0);
	}
	
	@Override
	public void saveToXML() {
		List<IModule> modules = CSynthesizer.getInstance().getModules();
		WriteXMLFile writeToXML = new WriteXMLFile();
		writeToXML.saveModules(modules);
	}

	@Override
	public void loadFromXML() {
		synth.stop();
		this.synth = (ICSynthesizer) PACFactory.getFactory().newSynthesizer();
		
		ReadXMLFile readXML = new ReadXMLFile();
		List<IModule> modules = readXML.getModules();
		
		for(IModule module : modules){
			synth.add(module);
		}

		displayCmd.execute();
		synth.start();
	}

	@Override
	public void setSynthesizer(ICSynthesizer syn) {
		this.synth = syn;
	}

	@Override
	public ICSynthesizer getSynthesizer() {
		return synth;
	}

	@Override
	public void setDisplaySynthCommand(ICommand displaySynthCommand) {
		this.displayCmd = displaySynthCommand;
	}

	@Override
	public void setUndisplaySynthCommand(ICommand undisplaySynthCommand) {
		this.undisplayCmd = undisplaySynthCommand;
	}
}
