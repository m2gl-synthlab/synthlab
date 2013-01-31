package fr.istic.synthlab;

import fr.istic.synthlab.abstraction.IModule;
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

		this.synth = (ICSynthesizer) PACFactory.getFactory().newSynthesizer(
				PACFactory.getFactory());

		// Add the basics modules (Oscilator + Out)
		IModule vco = PACFactory.getFactory().newVCO(PACFactory.getFactory());// implemented
		IModule vca = PACFactory.getFactory().newVCA(PACFactory.getFactory());
		IModule vcf = PACFactory.getFactory().newVCF(PACFactory.getFactory());

		synth.add(vco);
		synth.add(vcf);
		synth.add(vca);

		// Ajout des fils
		CWire wire0 = (CWire) PACFactory.getFactory().newWire(
				PACFactory.getFactory());
		CWire wire1 = (CWire) PACFactory.getFactory().newWire(
				PACFactory.getFactory());

		wire0.connect(vco.getOutput());
		wire0.connect(vcf.getInput());

		wire1.connect(vcf.getOutput());
		wire1.connect(vca.getInput());

		synth.start();
		vco.start();
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
