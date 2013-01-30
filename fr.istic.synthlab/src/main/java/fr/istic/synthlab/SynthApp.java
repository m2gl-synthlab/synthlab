package fr.istic.synthlab;
import java.awt.Toolkit;

import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.command.DisplayCommand;
import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.command.UndisplayCommand;
import fr.istic.synthlab.command.menu.AboutCommand;
import fr.istic.synthlab.command.menu.DocumentationCommand;
import fr.istic.synthlab.command.menu.NewSynthCommand;
import fr.istic.synthlab.command.menu.OpenSynthCommand;
import fr.istic.synthlab.command.menu.QuitSynthCommand;
import fr.istic.synthlab.command.menu.SaveSynthCommand;
import fr.istic.synthlab.command.toolbar.ToolbarDefaultCommand;
import fr.istic.synthlab.command.toolbar.ToolbarModuleCommand;
import fr.istic.synthlab.command.toolbar.ToolbarWireCommand;
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
	private ICommand displayCmd;
	private ICommand undisplayCmd;
	
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
		app.setUndisplaySynthCommand(new UndisplayCommand(app, frame));

		frame.setNewSynthCommand(new NewSynthCommand(app));
		frame.setOpenSynthCommand(new OpenSynthCommand());
		frame.setSaveSynthCommand(new SaveSynthCommand());
		frame.setQuitSynthCommand(new QuitSynthCommand(app));
		frame.setDocSynthCommand(new DocumentationCommand());
		frame.setAboutSynthCommand(new AboutCommand());
		frame.setToolbarDefaultCommand(new ToolbarDefaultCommand());
		frame.setToolbarWireCommand(new ToolbarWireCommand());
		frame.setToolbarModuleCommand(new ToolbarModuleCommand());
		
		
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
