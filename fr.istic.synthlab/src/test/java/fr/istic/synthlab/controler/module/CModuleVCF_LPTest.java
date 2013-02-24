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
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP;
import fr.istic.synthlab.controller.module.vcf.ICModuleVCF;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

/**
 * @author USER
 *
 */
public class CModuleVCF_LPTest {

	private ICModuleVCF iTest;
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
		iTest = new CModuleVCF_LP(synth);
		synth.add(iTest);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#getPresentation()}.
	 */
	@Test
	public void testGetPresentation() {
		assertNotNull(iTest.getPresentation());
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#p2cCutFrequencyChanged(int)}.
	 */
	@Test
	public void testP2cCutFrequencyChanged() {
		iTest.p2cCutFrequencyChanged(80);
		assertEquals(89.3864, iTest.getCutFrequency(),4);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#p2cCutFrequencyChanged(int)}.
	 */
	@Test
	public void testP2cCutFrequencyChangedLessMin() {
		iTest.p2cCutFrequencyChanged(2);
		assertEquals(10, iTest.getCutFrequency(),4);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#p2cCutFrequencyChanged(int)}.
	 */
	@Test
	public void testP2cCutFrequencyChangedMoreMax() {
		iTest.p2cCutFrequencyChanged(102455);
		assertEquals(10000, iTest.getCutFrequency(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#p2cResonanceChanged(double)}.
	 */
	@Test
	public void testP2cResonanceChanged() {
		iTest.p2cResonanceChanged(12);
		assertEquals(11.9820, iTest.getResonance(),4);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#p2cResonanceChanged(double)}.
	 */
	@Test
	public void testP2cResonanceChangedLessMin() {
		iTest.p2cResonanceChanged(-12);
		assertEquals(1, iTest.getResonance(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#p2cResonanceChanged(double)}.
	 */
	@Test
	public void testP2cResonanceChangedMoreMax() {
		iTest.p2cResonanceChanged(120);
		assertEquals(50, iTest.getResonance(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#p2cClosing()}.
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
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#p2cClosing()}.
	 * Test de la méthode la p2cClosing lorsque tout les ports sont connecté
	 */
	@Test
	public void testP2cClosingAllWireConnected() {
		IWire wireIn = new Wire(synth);
		IWire wireInFM = new Wire(synth);
		IWire wireOut = new Wire(synth);
		
		try {
			wireIn.connect(iTest.getInput());
		} catch (PortAlreadyInUseException e1) {
			e1.printStackTrace();
		} catch (BadConnectionException e1) {
			e1.printStackTrace();
		}
		
		try {
			wireInFM.connect(iTest.getInputFm());
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
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#setParameter(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testSetParameter() {
		iTest.setParameter("cutFrequency", 900.0);
		assertEquals(919.8680, iTest.getCutFrequency(),4);
		iTest.setParameter("resonnance", 2.0);
		assertEquals(2.0035, iTest.getResonance(),4);	
	}
	
	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#setParameter(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testSetParameterFalseParameter() {
		double resonnance = iTest.getResonance(), frequency = iTest.getCutFrequency();
		iTest.setParameter("falseParameters", 2.0);
		assertEquals(resonnance, iTest.getResonance(),4);
		assertEquals(frequency, iTest.getCutFrequency(),4);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#getSynthesizerPresentation()}.
	 */
	@Test
	public void testGetSynthesizerPresentation() {
		assertNotNull(iTest.getSynthesizerPresentation());
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP#p2cRemoveModule(fr.istic.synthlab.controller.module.ICModule)}.
	 */
	@Test
	public void testP2cRemoveModule() {
		assertTrue(synth.getModules().contains(iTest));
		iTest.p2cRemoveModule(iTest);
		assertFalse(synth.getModules().contains(iTest));
	}

}
