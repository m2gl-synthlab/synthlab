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
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.module.out.CModuleOUT;
import fr.istic.synthlab.controller.module.out.ICModuleOUT;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

/**
 * @author USER
 * 
 */
public class CModuleOUTTest {

	private ISynthesizer synth;
	private ICModuleOUT iTest;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		iTest = new CModuleOUT(synth);
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
	 * {@link fr.istic.synthlab.controller.module.out.CModuleOUT#getPresentation()}
	 * .
	 */
	@Test
	public void testGetPresentation() {
		assertNotNull(iTest.getPresentation());
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.out.CModuleOUT#p2cMute()}.
	 */
	@Test
	public void testP2cMute() {
		boolean isMute = iTest.isMute();
		iTest.p2cMute();
		assertEquals(!isMute, iTest.isMute());
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.out.CModuleOUT#p2cGainChanged(double)}
	 * .
	 */
	@Test
	public void testP2cGainChanged() {
		iTest.p2cGainChanged(2.0);
		assertEquals(2.0, iTest.getAttenuation(), 0);
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.out.CModuleOUT#p2cGainChanged(double)}
	 * .
	 */
	@Test
	public void testP2cGainChangedLessMin() {
		iTest.p2cGainChanged(-62.0);
		assertEquals(-60.0, iTest.getAttenuation(), 0);
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.out.CModuleOUT#p2cGainChanged(double)}
	 * .
	 */
	@Test
	public void testP2cGainChangedMoreMax() {
		iTest.p2cGainChanged(42.0);
		assertEquals(12.0, iTest.getAttenuation(), 0);
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.out.CModuleOUT#p2cClosing()}.
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
	 * {@link fr.istic.synthlab.controller.module.out.CModuleOUT#p2cClosing()}.
	 * Test de la méthode la p2cClosing lorsque tout les ports sont connecté
	 */
	@Test
	public void testP2cClosingAllWireConnected() {
		IWire wireIn = new Wire(synth);

		try {
			wireIn.connect(iTest.getInput());
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
	 * {@link fr.istic.synthlab.controller.module.out.CModuleOUT#setParameter(java.lang.String, java.lang.Double)}
	 * .
	 */
	@Test
	public void testSetParameter() {
		iTest.setParameter("attenuation", 2.0);
		assertEquals(2.0, iTest.getAttenuation(), 0);
		iTest.setParameter("mute", 1.0);
		assertEquals(true, iTest.isMute());
		iTest.setParameter("mute", 0.0);
		assertEquals(false, iTest.isMute());
		iTest.setParameter("mute", 2.0);
		assertEquals(true, iTest.isMute());
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.out.CModuleOUT#setParameter(java.lang.String, java.lang.Double)}
	 * .
	 */
	@Test
	public void testSetParameterFalseParameter() {
		double attenuation = iTest.getAttenuation();
		boolean mute = iTest.isMute();
		iTest.setParameter("falseParameters", 2.0);
		assertEquals(attenuation, iTest.getAttenuation(), 4);
		assertEquals(mute, iTest.isMute());
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.out.CModuleOUT#getSynthesizerPresentation()}
	 * .
	 */
	@Test
	public void testGetSynthesizerPresentation() {
		assertNotNull(iTest.getSynthesizerPresentation());
	}

	/**
	 * Test method for
	 * {@link fr.istic.synthlab.controller.module.out.CModuleOUT#p2cRemoveModule(fr.istic.synthlab.controller.module.ICModule)}
	 * .
	 */
	@Test
	public void testP2cRemoveModule() {
		assertTrue(synth.getModules().contains(iTest));
		iTest.p2cRemoveModule(iTest);
		assertFalse(synth.getModules().contains(iTest));
	}

}
