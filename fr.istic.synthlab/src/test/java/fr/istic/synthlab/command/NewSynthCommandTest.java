package fr.istic.synthlab.command;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthApp;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.app.DisplayCommand;
import fr.istic.synthlab.command.menu.NewSynthCommand;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class NewSynthCommandTest {
	ICommand command;
	ISynthApp app;
	ISynthFrame frame;
	

	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());

		
		 app = new SynthApp(frame);
		frame = new SynthFrame(app);
	command=new NewSynthCommand(app);
		app.setDisplaySynthCommand(new DisplayCommand(frame));
	}


	@Test
	public void testExecute() {
		ICSynthesizer synth=app.getSynthesizer();
		synth.setFrame(frame);
		((SynthApp) app).setFrame((SynthFrame) frame);
		
		command.execute();
		
		
		assertFalse(synth==app.getSynthesizer());
		
		
	}

}
