/**
 * 
 */
package fr.istic.synthlab.controler.module;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jsyn.ports.UnitInputPort;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.eg.ModuleEG;
import fr.istic.synthlab.abstraction.port.InputPort;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.rec.CModuleREC;
import fr.istic.synthlab.controller.module.rec.ICModuleREC;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

/**
 * @author USER
 *
 */
public class CModuleRECTest {

	private ICSynthesizer synth;
	private ICModuleREC iTest;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		iTest = new CModuleREC(synth);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rec.CModuleREC#getPresentation()}.
	 */
	@Test
	public void testGetPresentation() {
		assertNotNull(iTest.getPresentation());
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rec.CModuleREC#p2cRecord()}.
	 */
	@Test
	public void testP2cRecord() {
		boolean testRecord = iTest.isRecording();
		iTest.p2cRecord();
		assertEquals(!testRecord,iTest.isRecording());
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rec.CModuleREC#p2cGainChanged(double)}.
	 */
	@Test
	public void testP2cGainChanged() {
		iTest.p2cGainChanged(2.0);
		assertEquals(2.0, iTest.getAttenuation(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rec.CModuleREC#p2cGainChanged(double)}.
	 */
	@Test
	public void testP2cGainChangedLessMin() {
		iTest.p2cGainChanged(-62.0);
		assertEquals(-60.0, iTest.getAttenuation(),0);
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rec.CModuleREC#p2cGainChanged(double)}.
	 */
	@Test
	public void testP2cGainChangedMoreMax() {
		iTest.p2cGainChanged(42.0);
		assertEquals(12.0, iTest.getAttenuation(),0);
	}


	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rec.CModuleREC#p2cClosing()}.
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
	 * Test method for {@link fr.istic.synthlab.controller.module.rec.CModuleREC#p2cClosing()}.
	 * Test de la méthode la p2cClosing lorsque tout les ports sont connecté
	 */
	@Test
	public void testP2cClosingAllWireConnected() {
		ModuleEG module = new ModuleEG(synth);
		UnitInputPort jSynPort = new UnitInputPort("TestInput");
		for(IWire w : iTest.getWires()){
			try {
				w.connect(new InputPort("TestInput", jSynPort, module));
			} catch (PortAlreadyInUseException e) {
				e.printStackTrace();
			} catch (BadConnectionException e) {
				e.printStackTrace();
			}
			assertTrue(w.isConnected());
		}
		iTest.p2cClosing();
		for (IWire w : iTest.getWires()) {
			assertFalse(w.isConnected());
		}
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rec.CModuleREC#p2cClosing()}.
	 * Test de la méthode la p2cClosing lorsque certains port sont connectés.
	 */
	@Test
	public void testP2cClosingHalfWireConnected() {
		int i = 0;
		ModuleEG module = new ModuleEG(synth);
		UnitInputPort jSynPort = new UnitInputPort("TestInput");
		for(IWire w : iTest.getWires()){
			if (i%2 == 0){
				try {
					w.connect(new InputPort("TestInput", jSynPort, module));
				} catch (PortAlreadyInUseException e) {
					e.printStackTrace();
				} catch (BadConnectionException e) {
					e.printStackTrace();
				}
				assertTrue(w.isConnected());
			}else{
				assertFalse(w.isConnected());
			}
			i++;
		}
		iTest.p2cClosing();
		for (IWire w : iTest.getWires()) {
			assertFalse(w.isConnected());
		}
	}
	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rec.CModuleREC#setParameter(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testSetParameter() {
		iTest.setParameter("attenuation", 2.0);
		assertEquals(2.0, iTest.getAttenuation(),0);
		iTest.setParameter("isRecording", 1.0);
		assertEquals(false, iTest.isRecording());
		iTest.setParameter("isRecording", 0.0);
		assertEquals(true, iTest.isRecording());
		iTest.setParameter("isRecording", 2.0);
		assertEquals(false, iTest.isRecording());
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rec.CModuleREC#getSynthesizerPresentation()}.
	 */
	@Test
	public void testGetSynthesizerPresentation() {
		assertNotNull(iTest.getSynthesizerPresentation());
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rec.CModuleREC#p2cRemoveModule(fr.istic.synthlab.controller.module.ICModule)}.
	 */
	@Test
	public void testP2cRemoveModule() {
		iTest.p2cRemoveModule(iTest);
		assertNull(iTest);
	}

}