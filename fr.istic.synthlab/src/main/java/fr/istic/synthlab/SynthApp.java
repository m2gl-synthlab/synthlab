package fr.istic.synthlab;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.impl.ModuleVCA;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.ISynthApp#startSynth()
	 */
	@Override
	public void startSynth() {
		newSynth();
		displayCmd.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.ISynthApp#newSynth()
	 */
	@Override
	public void newSynth() {
		// Replace the current synthesizer with a new one

		this.synth = (ICSynthesizer) PACFactory.getFactory().newSynthesizer();

		// Add the basics modules (Oscilator + Out)
		IModule vco0 = PACFactory.getFactory().newVCO();
		IModule vco1 = PACFactory.getFactory().newVCO();
		IModule vca = PACFactory.getFactory().newVCA();
		IModule vcf = PACFactory.getFactory().newVCF();

		synth.add(vco0);
		synth.add(vco1);
		synth.add(vcf);
		synth.add(vca);

		
		
		// Ajout des fils
		CWire wire0 = (CWire) PACFactory.getFactory().newWire();
		CWire wire1 = (CWire) PACFactory.getFactory().newWire();
		CWire wire2 = (CWire) PACFactory.getFactory().newWire();

		wire0.connect(vco0.getOutput(ModuleVCO.OUTPUT_OUT));
		wire0.connect(vcf.getInput(ModuleVCF.INPUT_IN));
		
		wire1.connect(vco1.getOutput(ModuleVCO.OUTPUT_OUT));
		wire1.connect(vcf.getInput(ModuleVCF.INPUT_AMPLITUDE));

		wire2.connect(vcf.getOutput(ModuleVCF.OUTPUT_OUT));
		wire2.connect(vca.getInput(ModuleVCA.INPUT_IN));

		synth.start();
		vco0.start();
		vco1.start();
		vcf.start();
		vca.start();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.ISynthApp#quitSynth()
	 */
	@Override
	public void quitSynth() {
		this.synth = null;
		undisplayCmd.execute();
		System.exit(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.istic.synthlab.ISynthApp#setSynthesizer(fr.istic.synthlab.controller
	 * .ICSynthesizer)
	 */
	@Override
	public void setSynthesizer(ICSynthesizer syn) {
		this.synth = syn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.ISynthApp#getSynthesizer()
	 */
	@Override
	public ICSynthesizer getSynthesizer() {
		return synth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.istic.synthlab.ISynthApp#setDisplaySynthCommand(fr.istic.synthlab.
	 * command.ICommand)
	 */
	@Override
	public void setDisplaySynthCommand(ICommand displaySynthCommand) {
		this.displayCmd = displaySynthCommand;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.istic.synthlab.ISynthApp#setUndisplaySynthCommand(fr.istic.synthlab
	 * .command.ICommand)
	 */
	@Override
	public void setUndisplaySynthCommand(ICommand undisplaySynthCommand) {
		this.undisplayCmd = undisplaySynthCommand;
	}
}
