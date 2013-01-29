package fr.istic.synthlab;
import java.awt.Toolkit;

import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.command.DisplayCommand;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.impl.CModule;
import fr.istic.synthlab.controller.impl.CSynthesizer;
import fr.istic.synthlab.controller.impl.CWire;
import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class SynthApp implements ISynthApp {

	private ICSynthesizer synth;
	private ISynthFrame synthFrame;
	private ICommand displayCmd;
	
	public static void main(String[] args) {
		SynthApp app = new SynthApp();
		SynthFrame frame = new SynthFrame();
		frame.setTitle("Synthlab grp2");
		
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		IFactory factory = PACFactory.getFactory();
		
		ICSynthesizer syn = (CSynthesizer)factory.newSynthesizer(factory);
		
		app.setSynthesizer(syn);
		app.setDisplaySynthCommand(new DisplayCommand(app, frame));
		//app.setUndisplaySynthCommand(new DisplayCommand(app, frame));
		//TODO
		
		app.startSynth();
		
	}

	@Override
	public void startSynth() {
		newSynth();
		displayCmd.execute();
	}

	@Override
	public void newSynth() {
		
		//creer frame
		
		// Ajout des modules
		CModule vco = (CModule)PACFactory.getFactory().newVCO(PACFactory.getFactory());
		CModule vca = (CModule)PACFactory.getFactory().newVCA(PACFactory.getFactory());
		synth.addModule(vco);
		synth.addModule(vca);
		
		// Ajout des fils
		CWire wire = (CWire)PACFactory.getFactory().newWire(PACFactory.getFactory());
		wire.connect((OutputPort) vco.getPort(0));
		wire.connect((InputPort) vca.getPort(0));
		
		System.out.println("Beep!");
		Toolkit.getDefaultToolkit().beep();
		//JSyn.createSynthesizer().start();
	}

	@Override
	public void quitSynth() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}
