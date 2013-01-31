package fr.istic.synthlab;
import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.ModuleVCO;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.impl.CModule;
import fr.istic.synthlab.controller.impl.CWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class SynthApp implements ISynthApp {

	private ICSynthesizer synth;
	private ICommand displayCmd;
	private ICommand undisplayCmd;

	@Override
	public void startSynth() {
		newSynth();
		displayCmd.execute();
	}

	@Override
	public void newSynth() {
		// Ajout des modules
		CModule vco = (CModule)PACFactory.getFactory().newVCO(PACFactory.getFactory());//implemented
//		CModule vco2 = (CModule)PACFactory.getFactory().newVCO(PACFactory.getFactory());
		CModule vca = (CModule)PACFactory.getFactory().newVCA(PACFactory.getFactory());
		
		ModuleVCO abstModuleVCO = (ModuleVCO)vco.getAbs();
		double[] values = abstModuleVCO.generateOscillo();
		
		synth.addModule(vco);
//		synth.addModule(vco2);
		synth.addModule(vca);
		
		// Ajout des fils
		CWire wire = (CWire)PACFactory.getFactory().newWire(PACFactory.getFactory());
		wire.connect((OutputPort) vco.getSynthLabPort(0));
		wire.connect((InputPort) vca.getSynthLabPort(0));
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
