package fr.istic.synthlab;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.impl.ModuleOUT;
import fr.istic.synthlab.abstraction.impl.ModuleVCF;
import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.impl.CWire;
import fr.istic.synthlab.factory.impl.PACFactory;

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

		// Add the basics modules (Oscilator + Out)
		IModule vco = PACFactory.getFactory().newVCO();
		IModule out = PACFactory.getFactory().newOUT();
		IModule vcf = PACFactory.getFactory().newVCF();

		synth.add(vco);
		synth.add(vcf);
		synth.add(out);

		// Set up the frequecy parameter
		vco.getParameter(ModuleVCO.PARAM_FREQUENCY).setValue(200);
		
		// Ajout des fils
		CWire wire0 = (CWire) PACFactory.getFactory().newWire();
		CWire wire1 = (CWire) PACFactory.getFactory().newWire();

		wire0.connect(vco.getOutput(ModuleVCO.OUTPUT_OUT));
		wire0.connect(vcf.getInput(ModuleVCF.INPUT_IN));

		wire1.connect(vcf.getOutput(ModuleVCF.OUTPUT_OUT));
		wire1.connect(out.getInput(ModuleOUT.INPUT_IN));

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
