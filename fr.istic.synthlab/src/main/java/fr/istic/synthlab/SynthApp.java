package fr.istic.synthlab;

import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;
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
		this.synth = (ICSynthesizer) PACFactory.getFactory().newSynthesizer();
		displayCmd.execute();

		// Add a OUT module
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
