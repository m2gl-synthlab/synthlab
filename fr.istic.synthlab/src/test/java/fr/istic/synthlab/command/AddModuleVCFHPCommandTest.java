package fr.istic.synthlab.command;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthApp;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.menu.AddModuleVCFHPCommand;
import fr.istic.synthlab.controller.module.eg.CModuleEG;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF_HP;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class AddModuleVCFHPCommandTest {
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
		command=new AddModuleVCFHPCommand(app);
	}


	@Test
	public void testExecute() {
		command.execute();
		assertEquals(1,app.getSynthesizer().getModules().size());
		assertEquals(CModuleVCF_HP.class,app.getSynthesizer().getModules().get(0).getClass());
	}

}
