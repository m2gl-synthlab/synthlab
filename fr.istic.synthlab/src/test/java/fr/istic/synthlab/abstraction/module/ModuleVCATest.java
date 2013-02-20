package fr.istic.synthlab.abstraction.module;

import java.lang.reflect.Field;

import junit.framework.TestCase;

import com.jsyn.unitgen.PassThrough;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.filter.AmplitudeModulatorFilter;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.rep.ModuleREP;
import fr.istic.synthlab.abstraction.module.vca.IModuleVCA;
import fr.istic.synthlab.abstraction.module.vca.ModuleVCA;
import fr.istic.synthlab.abstraction.port.Port;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class ModuleVCATest extends TestCase {
	
	private IModuleVCA m;
	private ISynthesizer synth;
	public void setUp(){
		PACFactory.setFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		m=new ModuleVCA(synth);
	

		

	}
	


	public void testStart() {
		fail("Not yet implemented");
	}

	public void testStop() {
		fail("Not yet implemented");
	}
	
	public void testGetWires(){
		IWire w=new Wire(synth);
		try {
			w.connect(m.getInputAM());
		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			w.connect(m.getOutput());
		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(1, m.getWires().size());
		assertEquals(w, m.getWires().get(0));


		
	}
	
	public void testGetJSyn() {
		assertNotNull(m.getJSyn());
	}
	
	public void testGetWiresDifferent(){
		IWire w=new Wire(synth);		
		IWire w2=new Wire(synth);
		IModuleREP mrep=new ModuleREP(synth);

		try {
			w.connect(mrep.getOutput1());
			w2.connect(mrep.getInput());

		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			w.connect(m.getInput());
			w2.connect(m.getOutput());


		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		assertEquals(2, m.getWires().size());
		assertEquals(w, m.getWires().get(0));
		assertEquals(w2, m.getWires().get(1));


		
	}
	
	public void testUpdate() {
		IWire w=new Wire(synth);
		try {
			w.connect(m.getInputAM());
		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			w.connect(m.getOutput());
		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		((ModuleVCA) m).update((Port) m.getInputAM());
		
		Field pta = null;
		Field ptb=null;
		Field iam=null;

		   try {
			 pta = m.getClass().getDeclaredField("passThroughA");
			 ptb = m.getClass().getDeclaredField("passThroughB");
			 iam= m.getClass().getDeclaredField("inputAmplitudeModulator");
			 
			 pta.setAccessible(true);
			 ptb.setAccessible(true);
			 iam.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   PassThrough passThroughA=null;
		   PassThrough passThroughB=null;
		   AmplitudeModulatorFilter inputAmplitudeModulatorFilter=null;
		   
		   try {
			   passThroughA=(PassThrough) pta.get(m);
			   passThroughB=(PassThrough) ptb.get(m);
			   inputAmplitudeModulatorFilter=(AmplitudeModulatorFilter) iam.get(m);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		   
		assertTrue(passThroughA.output.isConnected());
		assertTrue (passThroughB.input.isConnected());
		assertTrue (inputAmplitudeModulatorFilter.input.isConnected());
		assertTrue (inputAmplitudeModulatorFilter.output.isConnected());
		
		

		
	}
	
	public void testUpdateWithoutConnect() {

((ModuleVCA) m).update((Port) m.getInputAM());
		
		Field pta = null;
		Field ptb=null;
		Field iam=null;

		   try {
			 pta = m.getClass().getDeclaredField("passThroughA");
			 ptb = m.getClass().getDeclaredField("passThroughB");
			 iam= m.getClass().getDeclaredField("inputAmplitudeModulator");
			 
			 pta.setAccessible(true);
			 ptb.setAccessible(true);
			 iam.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   PassThrough passThroughA=null;
		   PassThrough passThroughB=null;
		   AmplitudeModulatorFilter inputAmplitudeModulatorFilter=null;
		   
		   try {
			   passThroughA=(PassThrough) pta.get(m);
			   passThroughB=(PassThrough) ptb.get(m);
			   inputAmplitudeModulatorFilter=(AmplitudeModulatorFilter) iam.get(m);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		   
		assertTrue(passThroughA.output.isConnected());
		assertTrue (passThroughB.input.isConnected());
		assertFalse (inputAmplitudeModulatorFilter.input.isConnected());
		assertFalse (inputAmplitudeModulatorFilter.output.isConnected());
		
		

		
	}
	
	public void testGetWiresDifferentBad(){
		IWire w=new Wire(synth);		
		IWire w2=new Wire(synth);
		IModuleREP mrep=new ModuleREP(synth);

		try {
			w.connect(m.getInput());
			w.connect(mrep.getOutput1());
			w2.connect(m.getInput());
			fail("Une exception devrait etre lancée");

		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		


		
	
	public void testSetGetAttenuation() {
		m.setAttenuation(4.0);
		assertEquals(4.0, m.getAttenuation());
	}


	
}
