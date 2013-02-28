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

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.module.vco.CModuleVCO;
import fr.istic.synthlab.controller.module.vco.ICModuleVCO;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

/**
 * @author USER
 * 
 */
public class CModuleVCOTest {

	private ICModuleVCO iTest;
	private CSynthesizer synth;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		iTest = new CModuleVCO(synth);
		synth.add(iTest);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#getPresentation()}
	 * .
	 */
	@Test
	public void testGetPresentation() {
		assertNotNull(iTest.getPresentation());
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#p2cOctaveChanged(int)}
	 * .
	 */
	@Test
	public void testP2cOctaveChanged() {
		iTest.p2cOctaveChanged(5);
		assertEquals(5, iTest.getOctave(), 0);
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#p2cOctaveChanged(int)}
	 * .
	 */
	@Test
	public void testP2cOctaveChangedLessMin() {
		iTest.p2cOctaveChanged(-5);
		assertEquals(0, iTest.getOctave(), 0);
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#p2cOctaveChanged(int)}
	 * .
	 */
	@Test
	public void testP2cOctaveChangedMoreMax() {
		iTest.p2cOctaveChanged(25);
		assertEquals(14, iTest.getOctave(), 0);
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#p2cToneChanged(double)}
	 * .
	 */
	@Test
	public void testP2cToneChanged() {
		iTest.p2cToneChanged(0.5);
		assertEquals(0.5, iTest.getTone(), 0);
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#p2cToneChanged(double)}
	 * .
	 */
	@Test
	public void testP2cToneChangedLessMin() {
		iTest.p2cToneChanged(-2.5);
		assertEquals(-1.0, iTest.getTone(), 0);
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#p2cToneChanged(double)}
	 * .
	 */
	@Test
	public void testP2cToneChangedMoreMax() {
		iTest.p2cToneChanged(2.5);
		assertEquals(1.0, iTest.getTone(), 0);
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#p2cClosing()}.
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
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#p2cClosing()}.
	 * Test de la méthode la p2cClosing lorsque tout les ports sont connecté
	 */
	@Test
	public void testP2cClosingAllWireConnected() {
		IWire wireInFM = new Wire(synth);
		IWire wireOutSawtooth = new Wire(synth);
		IWire wireOutSquare = new Wire(synth);
		IWire wireOutTriangle = new Wire(synth);

		try {
			wireInFM.connect(iTest.getInputFm());
		} catch (PortAlreadyInUseException e1) {
			e1.printStackTrace();
		} catch (BadConnectionException e1) {
			e1.printStackTrace();
		}

		try {
			wireOutSawtooth.connect(iTest.getOutputSawtooth());
		} catch (PortAlreadyInUseException e1) {
			e1.printStackTrace();
		} catch (BadConnectionException e1) {
			e1.printStackTrace();
		}

		try {
			wireOutSquare.connect(iTest.getOutputSquare());
		} catch (PortAlreadyInUseException e1) {
			e1.printStackTrace();
		} catch (BadConnectionException e1) {
			e1.printStackTrace();
		}
		
		try {
			wireOutTriangle.connect(iTest.getOutputTriangle());
		} catch (PortAlreadyInUseException e1) {
			e1.printStackTrace();
		} catch (BadConnectionException e1) {
			e1.printStackTrace();
		}

		for (IWire w : iTest.getWires()) {
			assertTrue(w.isConnected());
		}
		iTest.p2cClosing();
		for (IWire w : iTest.getWires()) {
			assertFalse(w.isConnected());
		}
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#setParameter(java.lang.String, java.lang.Double)}
	 * .
	 */
	@Test
	public void testSetParameter() {
		iTest.setParameter("octave", 3.0);
		assertEquals(3, iTest.getOctave(), 0);
		iTest.setParameter("tone", 0.2);
		assertEquals(0.2, iTest.getTone(), 2);
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#setParameter(java.lang.String, java.lang.Double)}
	 * .
	 */
	@Test
	public void testSetParameterFalseParameter() {
		double octave = iTest.getOctave(), tone = iTest.getTone();
		iTest.setParameter("falseParameters", 2.0);
		assertEquals(octave, iTest.getOctave(), 4);
		assertEquals(tone, iTest.getTone(), 4);
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#getSynthesizerPresentation()}
	 * .
	 */
	@Test
	public void testGetSynthesizerPresentation() {
		assertNotNull(iTest.getSynthesizerPresentation());
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.vco.CModuleVCO#p2cRemoveModule(fr.istic.synthlab.controller.module.ICModule)}
	 * .
	 */
	@Test
	public void testP2cRemoveModule() {
		assertTrue(synth.getModules().contains(iTest));
		iTest.p2cRemoveModule(iTest);
		assertFalse(synth.getModules().contains(iTest));
	}

}
