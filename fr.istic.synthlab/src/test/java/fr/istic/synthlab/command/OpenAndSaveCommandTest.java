package fr.istic.synthlab.command;

import static org.junit.Assert.assertEquals;

import java.awt.FileDialog;
import java.lang.reflect.Field;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

import fr.istic.synthlab.ISynthApp;
import fr.istic.synthlab.ISynthFrame;
import fr.istic.synthlab.SynthApp;
import fr.istic.synthlab.SynthFrame;
import fr.istic.synthlab.command.app.DisplayCommand;
import fr.istic.synthlab.command.menu.OpenSynthCommand;
import fr.istic.synthlab.command.menu.SaveAsSynthCommand;
import fr.istic.synthlab.command.menu.SaveSynthCommand;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF_HP;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class OpenAndSaveCommandTest {
	ICommand commandOpen;
	ICommand commandSave;
	ICommand commandReSave;


	ISynthApp app;
	ISynthFrame frame;
	

	@Before
	public void setUp() throws Exception {
	
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());

		
		 app = new SynthApp(frame);
		frame = new SynthFrame(app);
		commandOpen=new OpenSynthCommand(app,frame);
		commandSave=new SaveAsSynthCommand(app,frame);
		commandReSave=new SaveSynthCommand(app,frame);


		app.setDisplaySynthCommand(new DisplayCommand(frame));
	}


	/**
	 * On sauvegarde puis on ouvre le meme fichier 
	 */
	@Test
	public void testExecuteSame() {
		((SynthApp)app).setFrame((SynthFrame)frame);
		((SynthApp)app).getSynthesizer().setFrame((SynthFrame)frame);

		
		
		
		Field chooserSave = null;

		

		   try {
			 chooserSave = commandSave.getClass().getDeclaredField("chooser");

			
			 
			 chooserSave.setAccessible(true);

			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   FileDialog real_chooserSave=null;

		 
		   try {
			   real_chooserSave=(FileDialog) chooserSave.get(commandSave);
			   

			  

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   String fileToSave ="save";
		   String fileToOpen="open";
		   String directoryToSave ="saveD";
		   String directoryToOpen="openD";
		   
		   app.getSynthesizer().add(new CModuleVCF_HP(app.getSynthesizer()));
		   
		  while (!fileToOpen.equals(fileToSave) || !directoryToOpen.equals(directoryToSave)){
			 

			  JOptionPane.showMessageDialog((JFrame)frame,"You must save and open the same document to make this test (without modification test)");
		   commandSave.execute();
		   
		   
		   fileToSave=real_chooserSave.getFile();
		   directoryToSave=real_chooserSave.getDirectory();


		   
		   
		   app=new SynthApp(frame);
			app.setDisplaySynthCommand(new DisplayCommand(frame));

		   commandOpen=new OpenSynthCommand(app, frame);
		   
		   Field chooserOpen=null;
		   
		   try {
				 chooserOpen = commandOpen.getClass().getDeclaredField("chooser");

				
				 
				 chooserOpen.setAccessible(true);

				
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   FileDialog real_chooserOpen=null;

			 
			   try {
				   real_chooserOpen=(FileDialog) chooserOpen.get(commandOpen);
				   

				  

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   commandOpen.execute();
		   fileToOpen=real_chooserOpen.getFile();
		   directoryToOpen=real_chooserOpen.getDirectory();

		   
		  }

		   assertEquals(1,app.getSynthesizer().getModules().size());
		   assertEquals(CModuleVCF_HP.class,app.getSynthesizer().getModules().get(0).getClass());

		   
	


				
				
				
	}
	
	/**
	 * On sauvegarde,on modifie,on sauvegarde à nouveau puis on ouvre le meme fichier 
	 */
	
	@Test
	public void testExecuteModifySame() {
		((SynthApp)app).setFrame((SynthFrame)frame);
		((SynthApp)app).getSynthesizer().setFrame((SynthFrame)frame);

		
		
		
		Field chooserSave = null;

		

		   try {
			 chooserSave = commandSave.getClass().getDeclaredField("chooser");

			
			 
			 chooserSave.setAccessible(true);

			
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   FileDialog real_chooserSave=null;

		 
		   try {
			   real_chooserSave=(FileDialog) chooserSave.get(commandSave);
			   

			  

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   String fileToSave ="save";
		   String fileToOpen="open";
		   String directoryToSave ="saveD";
		   String directoryToOpen="openD";
		   
		   
		  while (!fileToOpen.equals(fileToSave) || !directoryToOpen.equals(directoryToSave)){


			  JOptionPane.showMessageDialog((JFrame)frame,"You must save and open the same document to make this test (with modification test)");
			   app.getSynthesizer().add(new CModuleVCF_HP(app.getSynthesizer()));

		   commandSave.execute();

		   
		   fileToSave=real_chooserSave.getFile();
		   directoryToSave=real_chooserSave.getDirectory();

		   
		   app.getSynthesizer().add(new CModuleVCF_LP(app.getSynthesizer()));
		   commandReSave.execute();

		   
		   app=new SynthApp(frame);
			app.setDisplaySynthCommand(new DisplayCommand(frame));

		   commandOpen=new OpenSynthCommand(app, frame);
		   
		   Field chooserOpen=null;
		   
		   try {
				 chooserOpen = commandOpen.getClass().getDeclaredField("chooser");

				
				 
				 chooserOpen.setAccessible(true);

				
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   FileDialog real_chooserOpen=null;

			 
			   try {
				   real_chooserOpen=(FileDialog) chooserOpen.get(commandOpen);
				   

				  

			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   
		   commandOpen.execute();
		   fileToOpen=real_chooserOpen.getFile();
		   directoryToOpen=real_chooserOpen.getDirectory();

		   
		  }

		   assertEquals(2,app.getSynthesizer().getModules().size());
		   assertEquals(CModuleVCF_HP.class,app.getSynthesizer().getModules().get(0).getClass());
		   assertEquals(CModuleVCF_LP.class,app.getSynthesizer().getModules().get(1).getClass());
	


				
				
				
	}
	
}
				
			
				
				

		


		
		
		
		
		
		
				
	


