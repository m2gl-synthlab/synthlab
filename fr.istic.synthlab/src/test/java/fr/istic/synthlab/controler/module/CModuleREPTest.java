/**
 * 
 */
package fr.istic.synthlab.controler.module;

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
import fr.istic.synthlab.controller.module.rep.CModuleREP;
import fr.istic.synthlab.controller.module.rep.ICModuleREP;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

/**
 * @author USER
 *
 */
public class CModuleREPTest {

	private ICModuleREP iTest;
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
		iTest = new CModuleREP(synth);
		synth.add(iTest);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rep.CModuleREP#getPresentation()}.
	 */
	@Test
	public void testGetPresentation() {
		assertNotNull(iTest.getPresentation());
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rep.CModuleREP#p2cClosing()}.
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
	 * Test method for {@link fr.istic.synthlab.controller.module.rep.CModuleREP#p2cClosing()}.
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
	 * Test method for {@link fr.istic.synthlab.controller.module.rep.CModuleREP#p2cClosing()}.
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
	 * Test method for {@link fr.istic.synthlab.controller.module.rep.CModuleREP#getSynthesizerPresentation()}.
	 */
	@Test
	public void testGetSynthesizerPresentation() {
		assertNotNull(iTest.getSynthesizerPresentation());
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.rep.CModuleREP#p2cRemoveModule(fr.istic.synthlab.controller.module.ICModule)}.
	 */
	//TODO Test qui ne passe pas ...
	@Test
	public void testP2cRemoveModule() {
		assertTrue(synth.getModules().contains(iTest));
		iTest.p2cRemoveModule(iTest);
		assertFalse(synth.getModules().contains(iTest));
	}
}
