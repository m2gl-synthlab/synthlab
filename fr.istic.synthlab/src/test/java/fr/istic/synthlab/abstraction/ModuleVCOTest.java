package fr.istic.synthlab.abstraction;

import java.lang.reflect.Field;

import junit.framework.TestCase;

import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.vco.IModuleVCO;
import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.abstraction.port.Port;
import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class ModuleVCOTest extends TestCase {
	
	private IModuleVCO m;
	public void setUp(){
		PACFactory.setFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		m=new ModuleVCO();
	

		

	}
	

	public void testStart() {
		Field vcoSquare=null;
		Field vcoTriangle=null;
		Field vcoSawTooth=null;

		   try {
			 vcoSquare = m.getClass().getDeclaredField("vcoSquare");
			 vcoTriangle= m.getClass().getDeclaredField("vcoTriangle");
			 vcoSawTooth = m.getClass().getDeclaredField("vcoSawtooth");
			 
			 vcoSquare.setAccessible(true);
			 vcoTriangle.setAccessible(true);
			 vcoSawTooth.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   PassThrough p=null;
		   SquareOscillator sqo=null;
		   TriangleOscillator tro=null;
		   SawtoothOscillator sto=null;
		   
		   try {
			 sqo=(SquareOscillator) vcoSquare.get(m);
			 tro=(TriangleOscillator) vcoTriangle.get(m);
			 sto=(SawtoothOscillator) vcoSawTooth.get(m);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   Synthesizer synth=new Synthesizer();
		   m.start();
		
	}
	

	
	

	public void testStop() {
		fail("Not yet implemented");
	}

	public void testSetGetOctave() {
		m.setOctave(5);
		m.setTone(0.4);
		assertEquals(5, m.getOctave());
		assertEquals( (double) Math.pow(2,5+0.4), m.getFrequency());

		
	}


	public void testSetGetTone() {
		m.setTone(0.24);
		m.setOctave(6);
		
		assertEquals(0.24, m.getTone());	

		assertEquals( (double) Math.pow(2,6+0.24), m.getFrequency());
		
	}

	


	public void testSetGetFrequency() {
		m.setFrequency(140.0);
		assertEquals(140.0, m.getFrequency());
	}

	
	public void testGetWiresUpdate() {
		IWire w=new Wire();
		try {
			w.connect(m.getInputFm());
		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			w.connect(m.getOutputSawtooth());
		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		((ModuleVCO) m).update((Port) m.getInputFm());
((ModuleVCO) m).update((Port) m.getInputFm());
		
		Field f = null;
		Field vcoSquare=null;
		Field vcoTriangle=null;
		Field vcoSawTooth=null;

		   try {
			 f = m.getClass().getDeclaredField("passThrough");
			 vcoSquare = m.getClass().getDeclaredField("vcoSquare");
			 vcoTriangle= m.getClass().getDeclaredField("vcoTriangle");
			 vcoSawTooth = m.getClass().getDeclaredField("vcoSawtooth");
			 
			 vcoSquare.setAccessible(true);
			 vcoTriangle.setAccessible(true);
			 vcoSawTooth.setAccessible(true);
			 f.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   PassThrough p=null;
		   SquareOscillator sqo=null;
		   TriangleOscillator tro=null;
		   SawtoothOscillator sto=null;
		   
		   try {
			 p=(PassThrough) f.get(m);
			 sqo=(SquareOscillator) vcoSquare.get(m);
			 tro=(TriangleOscillator) vcoTriangle.get(m);
			 sto=(SawtoothOscillator) vcoSawTooth.get(m);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		   
		assertTrue(p.output.isConnected());
		assertTrue (tro.frequency.isConnected());
		assertTrue (sto.frequency.isConnected());
		assertTrue (sqo.frequency.isConnected());
		
		

		
	}
	
	public void testUpdateWithoutConnect() {

		
		((ModuleVCO) m).update((Port) m.getInputFm());
		
		Field f = null;
		Field vcoSquare=null;
		Field vcoTriangle=null;
		Field vcoSawTooth=null;

		   try {
			 f = m.getClass().getDeclaredField("passThrough");
			 vcoSquare = m.getClass().getDeclaredField("vcoSquare");
			 vcoTriangle= m.getClass().getDeclaredField("vcoTriangle");
			 vcoSawTooth = m.getClass().getDeclaredField("vcoSawtooth");
			 
			 vcoSquare.setAccessible(true);
			 vcoTriangle.setAccessible(true);
			 vcoSawTooth.setAccessible(true);
			 f.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   PassThrough p=null;
		   SquareOscillator sqo=null;
		   TriangleOscillator tro=null;
		   SawtoothOscillator sto=null;
		   
		   try {
			 p=(PassThrough) f.get(m);
			 sqo=(SquareOscillator) vcoSquare.get(m);
			 tro=(TriangleOscillator) vcoTriangle.get(m);
			 sto=(SawtoothOscillator) vcoSawTooth.get(m);

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		assertFalse(p.output.isConnected());
		assertEquals (m.getFrequency(),tro.frequency.get());
		assertEquals (m.getFrequency(),sto.frequency.get());
		assertEquals (m.getFrequency(),sqo.frequency.get());


		
	}
	
	

}
