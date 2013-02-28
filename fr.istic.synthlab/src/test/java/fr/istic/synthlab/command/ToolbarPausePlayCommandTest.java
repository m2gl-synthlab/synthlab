package fr.istic.synthlab.command;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthApp;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.app.DisplayCommand;
import fr.istic.synthlab.command.toolbar.ToolbarPauseCommand;
import fr.istic.synthlab.command.toolbar.ToolbarPlayCommand;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class ToolbarPausePlayCommandTest {
	ICommand commandPause;
	ICommand commandPlay;

	ISynthApp app;
	ISynthFrame frame;
	

	@Before
	public void setUp() throws Exception {
	
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());

		
		 app = new SynthApp(frame);
		frame = new SynthFrame(app);
		commandPlay=new ToolbarPlayCommand((SynthApp)app);
		commandPause=new ToolbarPauseCommand((SynthApp)app);
		((SynthApp) app).setFrame((SynthFrame) frame);

		app.getSynthesizer().setFrame(frame);
		app.setDisplaySynthCommand(new DisplayCommand(frame));
	}


	@Test
	public void testExecutePause(){
		commandPause.execute();

		assertFalse(app.getSynthesizer().isRunning());
		
	}
	
	@Test
	public void testExecutePlay(){
	commandPlay.execute();
	


		assertTrue(app.getSynthesizer().isRunning());
		
	}
	
}
				
			
				
				

		


		
		
		
		
		
		
				
	


