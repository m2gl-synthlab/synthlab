package fr.istic.synthlab.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthApp;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.menu.AddModuleVCACommand;
import fr.istic.synthlab.controller.module.vca.CModuleVCA;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class AddModuleVCACommandTest {
	ICommand command;
	ISynthApp app;

	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());

		ISynthFrame frame = null;

		app = new SynthApp(frame);
		frame = new SynthFrame(app);
		command = new AddModuleVCACommand(app);
	}

	@Test
	public void testExecute() {
		command.execute();
		assertEquals(1, app.getSynthesizer().getModules().size());
		assertEquals(CModuleVCA.class, app.getSynthesizer().getModules().get(0).getClass());
	}

}
