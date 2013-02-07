package fr.istic.synthlab;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.IModuleOUT;
import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.ICSynthesizer;
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
		
		displayCmd.execute();

		// Add a OUT module
		IModuleOUT out = PACFactory.getFactory().newOUT(synth);
		synth.add(out);
		
		// Add a VCO module
		IModule vco = PACFactory.getFactory().newVCO(synth);
		vco.getParameter(ModuleVCO.PARAM_FREQUENCY).setValue(200);
		synth.add(vco);
		
		// Add a VCF module
//		IModule vcf = PACFactory.getFactory().newVCF(synth);
//		synth.add(vcf);

		
		// Add a wire between VCO and VCF
//		IWire wire0 = PACFactory.getFactory().newWire();
//		wire0.connect(vco.getOutput(ModuleVCO.OUTPUT_TRIANGLE));
//		wire0.connect(vcf.getInput(ModuleVCF.INPUT_IN));
//		synth.add(wire0);
		
		
		// Add a wire between CVF and OUT
//		IWire wire1 = PACFactory.getFactory().newWire();
//		wire1.connect(vcf.getOutput(ModuleVCF.OUTPUT_OUT));
//		wire1.connect(out.getInput(ModuleOUT.INPUT_IN));
//		synth.add(wire1);
		
		//wire1.disconnect();
		
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
