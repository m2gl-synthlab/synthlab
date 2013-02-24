/**
 * 
 */
package fr.istic.synthlab.controler.module;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.eg.ModuleEG;
import fr.istic.synthlab.abstraction.port.InputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.module.mix.CModuleMIX;
import fr.istic.synthlab.controller.module.mix.ICModuleMIX;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

/**
 * @author USER
 *
 */
public class CModuleMIXTest {

	private ICModuleMIX iTest;
	private ISynthesizer synth;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		iTest = new CModuleMIX(synth);
		synth.add(iTest);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#getPresentation()}.
	 */
	@Test
	public void testGetPresentation() {
		assertNotNull(iTest.getPresentation());
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cClosing()}.
	 * Test de la méthode pour p2cClosing lorsqu'aucun des ports n'est connecté
	 */
	@Test
	public void testP2cClosingWireNotConnected() {
		for (IWire w : iTest.getWires()) {
			assertFalse(w.isConnected());
		}
		iTest.p2cClosing();
		for (IWire w : iTest.getWires()) {
			assertFalse(w.isConnected());
		}
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cClosing()}.
	 * Test de la méthode la p2cClosing lorsque tout les ports sont connecté
	 */
	@Test
	public void testP2cClosingAllWireConnected() {
		IWire wireIn1 = new Wire(synth);
		IWire wireIn2 = new Wire(synth);
		IWire wireIn3 = new Wire(synth);
		IWire wireIn4 = new Wire(synth);
		IWire wireOut = new Wire(synth);
		
		try {
			wireIn1.connect(iTest.getInput(0));
		} catch (PortAlreadyInUseException e1) {
			e1.printStackTrace();
		} catch (BadConnectionException e1) {
			e1.printStackTrace();
		}
		
		try {
			wireIn2.connect(iTest.getInput(1));
		} catch (PortAlreadyInUseException e1) {
			e1.printStackTrace();
		} catch (BadConnectionException e1) {
			e1.printStackTrace();
		}
		
		try {
			wireIn3.connect(iTest.getInput(2));
		} catch (PortAlreadyInUseException e1) {
			e1.printStackTrace();
		} catch (BadConnectionException e1) {
			e1.printStackTrace();
		}
		
		try {
			wireIn4.connect(iTest.getInput(3));
		} catch (PortAlreadyInUseException e1) {
			e1.printStackTrace();
		} catch (BadConnectionException e1) {
			e1.printStackTrace();
		}
		
		try {
			wireOut.connect(iTest.getOutput());
		} catch (PortAlreadyInUseException e1) {
			e1.printStackTrace();
		} catch (BadConnectionException e1) {
			e1.printStackTrace();
		}
		
		for(IWire w : iTest.getWires()){
			assertTrue(w.isConnected());
		}
		iTest.p2cClosing();
		for (IWire w : iTest.getWires()) {
			assertFalse(w.isConnected());
		}
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain1Changed(double)}.
	 */
	@Test
	public void testP2cGain1Changed() {
		iTest.p2cGain1Changed(2.0);
		assertEquals(2.0, iTest.getAttenuation1(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain1Changed(double)}.
	 */
	@Test
	public void testP2cGain1ChangedLessMin() {
		iTest.p2cGain1Changed(-80.0);
		assertEquals(-60.0, iTest.getAttenuation1(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain1Changed(double)}.
	 */
	@Test
	public void testP2cGain1ChangedMoreMax() {
		iTest.p2cGain1Changed(50.0);
		assertEquals(12.0, iTest.getAttenuation1(),0);	
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain2Changed(double)}.
	 */
	@Test
	public void testP2cGain2Changed() {
		iTest.p2cGain2Changed(2.0);
		assertEquals(2.0, iTest.getAttenuation2(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain2Changed(double)}.
	 */
	@Test
	public void testP2cGain2ChangedLessMin() {
		iTest.p2cGain2Changed(-80.0);
		assertEquals(-60.0, iTest.getAttenuation2(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain2Changed(double)}.
	 */
	@Test
	public void testP2cGain2ChangedMoreMax() {
		iTest.p2cGain2Changed(50.0);
		assertEquals(12.0, iTest.getAttenuation2(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain3Changed(double)}.
	 */
	@Test
	public void testP2cGain3Changed() {
		iTest.p2cGain3Changed(2.0);
		assertEquals(2.0, iTest.getAttenuation3(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain3Changed(double)}.
	 */
	@Test
	public void testP2cGain3ChangedLessMin() {
		iTest.p2cGain3Changed(-80.0);
		assertEquals(-60.0, iTest.getAttenuation3(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain3Changed(double)}.
	 */
	@Test
	public void testP2cGain3ChangedMoreMax() {
		iTest.p2cGain3Changed(50.0);
		assertEquals(12.0, iTest.getAttenuation3(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain4Changed(double)}.
	 */
	@Test
	public void testP2cGain4Changed() {
		iTest.p2cGain4Changed(2.0);
		assertEquals(2.0, iTest.getAttenuation4(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain4Changed(double)}.
	 */
	@Test
	public void testP2cGain4ChangedLessMin() {
		iTest.p2cGain4Changed(-80.0);
		assertEquals(-60.0, iTest.getAttenuation4(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cGain4Changed(double)}.
	 */
	@Test
	public void testP2cGain4ChangedMoreMax() {
		iTest.p2cGain4Changed(50.0);
		assertEquals(12.0, iTest.getAttenuation4(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#setParameter(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testSetParameter() {
		iTest.setParameter("attenuation1", 2.0);
		assertEquals(2.0, iTest.getAttenuation1(),0);
		iTest.setParameter("attenuation2", 2.0);
		assertEquals(2.0, iTest.getAttenuation2(),0);
		iTest.setParameter("attenuation3", 2.0);
		assertEquals(2.0, iTest.getAttenuation3(),0);
		iTest.setParameter("attenuation4", 2.0);
		assertEquals(2.0, iTest.getAttenuation4(),0);	
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#setParameter(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testSetParameterFalseParameter() {
		double attenuation1 = iTest.getAttenuation1(), attenuation2 = iTest.getAttenuation2(), attenuation3 = iTest.getAttenuation3(), attenuation4 = iTest.getAttenuation4();
		iTest.setParameter("falseParameters", 2.0);
		assertEquals(attenuation1, iTest.getAttenuation1(),4);
		assertEquals(attenuation2, iTest.getAttenuation2(),4);
		assertEquals(attenuation3, iTest.getAttenuation3(),4);
		assertEquals(attenuation4, iTest.getAttenuation4(),4);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#getSynthesizerPresentation()}.
	 */
	@Test
	public void testGetSynthesizerPresentation() {
		assertNotNull(iTest.getSynthesizerPresentation());
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.mix.CModuleMIX#p2cRemoveModule(fr.istic.synthlab.controller.module.ICModule)}.
	 */
	@Test
	public void testP2cRemoveModule() {
		assertTrue(synth.getModules().contains(iTest));
		iTest.p2cRemoveModule(iTest);
		assertFalse(synth.getModules().contains(iTest));
	}
}
