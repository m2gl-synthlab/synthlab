package fr.istic.synthlab.abstraction.module;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.junit.Test;

import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.rep.ModuleREP;
import fr.istic.synthlab.abstraction.module.vco.IModuleVCO;
import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.abstraction.port.Port;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class ModuleVCOTest extends TestCase {
	
	private IModuleVCO m;
	private ISynthesizer synth;
	public void setUp(){
		PACFactory.setFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		m=new ModuleVCO(synth);
	

		

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
	
	public void testGetJSyn() {
		assertNotNull(m.getJSyn());
	}
	public void testGetWires(){
		IWire w=new Wire(synth);
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
		
		assertEquals(1, m.getWires().size());
		assertEquals(w, m.getWires().get(0));


		
	}
	
	@Test
	public void testGetWiresDifferentBad(){
		IWire w=new Wire(synth);		
		IWire w2=new Wire(synth);
		IModuleREP mrep=new ModuleREP(synth);

		try {
			w.connect(m.getInputFm());
			w.connect(mrep.getOutput1());
			w2.connect(m.getInputFm());
			fail("Une exception devrait etre lancée");

		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		

	public void testGetWiresDifferent(){
		IWire w=new Wire(synth);		
		IWire w2=new Wire(synth);
		IModuleREP mrep=new ModuleREP(synth);

		try {
			w.connect(m.getInputFm());
			w2.connect(mrep.getInput());

		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			w.connect(m.getOutputSawtooth());
			w2.connect(m.getOutputSquare());


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
	@Test
	public void testGetWiresNotConnected(){

		assertEquals(0, m.getWires().size());
		
	}
	

}

