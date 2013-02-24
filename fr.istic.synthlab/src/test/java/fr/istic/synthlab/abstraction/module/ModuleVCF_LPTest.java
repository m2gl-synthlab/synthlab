package fr.istic.synthlab.abstraction.module;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.PassThrough;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.rep.ModuleREP;
import fr.istic.synthlab.abstraction.module.vcf.IModuleVCF;
import fr.istic.synthlab.abstraction.module.vcf.ModuleVCF_LP;
import fr.istic.synthlab.abstraction.port.Port;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class ModuleVCF_LPTest {

	IModuleVCF m;
	private ISynthesizer synth;
	@Before
	public void setUp() throws Exception {
		PACFactory.setFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		m=new ModuleVCF_LP(synth);

	}
	@Test
	public void testGetJSyn() {
		assertNotNull(m.getJSyn());
	}


	@Test
	public void testSetGetCutFrequency() {
		m.setCutFrequency(3000);
		assertEquals(3000, m.getCutFrequency());
	}


	@Test
	public void testSetGetResonance() {
		m.setResonance(25.5);
		assertEquals(25.5, m.getResonance(),0);	}




	public void testGetWires(){
		IWire w=new Wire(synth);
		try {
			w.connect(m.getInputFm());
		} catch (PortAlreadyInUseException e) {
			e.printStackTrace();
		} catch (BadConnectionException e) {
			e.printStackTrace();
		}
		try {
			w.connect(m.getOutput());
		} catch (PortAlreadyInUseException e) {
			e.printStackTrace();
		} catch (BadConnectionException e) {
			e.printStackTrace();
		}
		assertEquals(1, m.getWires().size());
		assertEquals(w, m.getWires().get(0));
	}

	@Test
	public void testGetWiresDifferent(){
		IWire w=new Wire(synth);		
		IWire w2=new Wire(synth);
		IModuleREP mrep=new ModuleREP(synth);

		try {
			w.connect(mrep.getOutput1());
			w2.connect(mrep.getInput());

		} catch (PortAlreadyInUseException e) {
			e.printStackTrace();
		} catch (BadConnectionException e) {
			e.printStackTrace();
		}
		try {
			w.connect(m.getInput());
			w2.connect(m.getOutput());
		} catch (PortAlreadyInUseException e) {
			e.printStackTrace();
		} catch (BadConnectionException e) {
			e.printStackTrace();
		}	
		assertEquals(2, m.getWires().size());
		assertEquals(w, m.getWires().get(0));
		assertEquals(w2, m.getWires().get(1));
	}

	@Test
	public void testUpdate() {
		IWire w=new Wire(synth);
		try {
			w.connect(m.getInputFm());
		} catch (PortAlreadyInUseException e) {
			e.printStackTrace();
		} catch (BadConnectionException e) {
			e.printStackTrace();
		}
		try {
			w.connect(m.getOutput());
		} catch (PortAlreadyInUseException e) {
			e.printStackTrace();
		} catch (BadConnectionException e) {
			e.printStackTrace();
		}

		((ModuleVCF_LP) m).update((Port) m.getInputFm());

		Field ptr = null;
		Field fj1=null;
		Field fj2=null;

		try {
			ptr = m.getClass().getDeclaredField("passThrough");
			fj1 = m.getClass().getDeclaredField("filterJSyn1");
			fj2= m.getClass().getDeclaredField("filterJSyn2");

			ptr.setAccessible(true);
			fj1.setAccessible(true);
			fj2.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		PassThrough passThrough=null;
		FilterLowPass filterJSyn1=null;
		FilterLowPass filterJSyn2=null;

		try {
			passThrough=(PassThrough) ptr.get(m);
			filterJSyn1=(FilterLowPass) fj1.get(m);
			filterJSyn2=(FilterLowPass) fj2.get(m);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		assertTrue(passThrough.output.isConnected());
		assertTrue(filterJSyn1.frequency.isConnected());
		assertTrue(filterJSyn2.output.isConnected());
	}

	@Test
	public void testUpdateWithoutConnect() {

		((ModuleVCF_LP) m).update((Port) m.getInputFm());

		Field ptr = null;
		Field fj1=null;
		Field fj2=null;

		try {
			ptr = m.getClass().getDeclaredField("passThrough");
			fj1 = m.getClass().getDeclaredField("filterJSyn1");
			fj2= m.getClass().getDeclaredField("filterJSyn2");

			ptr.setAccessible(true);
			fj1.setAccessible(true);
			fj2.setAccessible(true);
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		PassThrough passThrough=null;
		FilterLowPass filterJSyn1=null;
		FilterLowPass filterJSyn2=null;

		try {
			passThrough=(PassThrough) ptr.get(m);
			filterJSyn1=(FilterLowPass) fj1.get(m);
			filterJSyn2=(FilterLowPass) fj2.get(m);

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		assertFalse(passThrough.output.isConnected());
		assertFalse(filterJSyn1.frequency.isConnected());
		assertFalse(filterJSyn2.output.isConnected());

		assertEquals(m.getCutFrequency(), filterJSyn1.frequency.get(),0);
		assertEquals(m.getCutFrequency(), filterJSyn2.frequency.get(),0);
	}

	@Test
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
			e.printStackTrace();
		} catch (BadConnectionException e) {
			e.printStackTrace();
		}
	}
}
