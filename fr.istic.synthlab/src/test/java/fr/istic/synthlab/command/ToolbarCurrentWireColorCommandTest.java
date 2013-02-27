package fr.istic.synthlab.command;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthApp;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.toolbar.ToolbarCurrentWireColorCommand;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class ToolbarCurrentWireColorCommandTest {
	
	private ICommand command;
	private ISynthFrame frame;
	private ISynthApp app;

	@Before
	public void setUp(){
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());		 app=new SynthApp(frame);
		frame=new SynthFrame(app);
		command=new ToolbarCurrentWireColorCommand(app, (SynthFrame) frame);
	}

	@Test
	public void testExecute() {
		
		
		Field color = null;
	

		   try {
			 color = frame.getClass().getDeclaredField("toolbarCurrentWireColor");
			 color.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		   
		   	   
		   try {
			color.set(frame,Color.CYAN);

			 
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		command.execute();
		assertEquals(Color.cyan,frame.getCurrentWireColor());
		assertEquals(Color.cyan,app.getSynthesizer().getCurrentWireColor());


	}

}
