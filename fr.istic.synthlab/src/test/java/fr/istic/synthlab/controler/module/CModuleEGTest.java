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
import fr.istic.synthlab.controller.module.eg.CModuleEG;
import fr.istic.synthlab.controller.module.eg.ICModuleEG;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

/**
 * @author USER
 *
 */
public class CModuleEGTest {

	ICModuleEG iTest;
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
		iTest = new CModuleEG(synth);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#getPresentation()}.
	 */
	@Test
	public void testGetPresentation() {
		assertNotNull(iTest.getPresentation());
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cAttackChanged(double)}.
	 * Test de la méthode p2cAttackChanged lorsqu'on modifie une seule fois l'attaque
	 */
	//TODO à voir pour les valeur => en expotentielle 2.00 = 1.9955
	@Test
	public void testP2cAttackChangedOnce() {
		iTest.p2cAttackChanged(2);
		assertEquals(1.9955, iTest.getAttack(),4);				
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cAttackChanged(double)}.
	 * Test de la méthode p2cAttackChanged lorsqu'on modifie deux fois l'attaque
	 */
	//TODO à voir pour les valeur => en expotentielle 2.00 = 1.9955 idem pour 3
	@Test
	public void testP2cAttackChangedTwice() {
		iTest.p2cAttackChanged(2);
		assertEquals(1.9955, iTest.getAttack(),4);	
		iTest.p2cAttackChanged(3);
		assertEquals(2.9955, iTest.getAttack(),4);	
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cAttackChanged(double)}.
	 * Test de la méthode p2cAttackChanged lorsqu'on modifie la valeur de l'attaque en dessous du minimum
	 */
	@Test
	public void testP2cAttackChangedLessMin() {
		iTest.p2cAttackChanged(-1);
		assertEquals(0, iTest.getAttack(),0);				
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cAttackChanged(double)}.
	 * Test de la méthode p2cAttackChanged lorsqu'on modifie la valeur de l'attaque en dessus du maximum
	 */
	@Test
	public void testP2cAttackChangedMoreMax() {
		iTest.p2cAttackChanged(6);
		assertEquals(5, iTest.getAttack(),0);				
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cDecayChanged(double)}.
	 * Test de la méthode p2cDecayChanged lorsqu'on modifie une seule fois le decay
	 */
	//TODO à voir pour les valeur => en expotentielle 2.00 = 1.9955
	@Test
	public void testP2cDecayChangedOnce() {
		iTest.p2cDecayChanged(2);
		assertEquals(1.9955, iTest.getDecay(),4);		
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cAttackChanged(double)}.
	 * Test de la méthode p2cAttackChanged lorsqu'on modifie deux fois l'attaque
	 */
	//TODO à voir pour les valeur => en expotentielle 2.00 = 1.9955
	@Test
	public void testP2cDecayChangedTwice() {
		iTest.p2cDecayChanged(2);
		assertEquals(1.9955, iTest.getDecay(),4);	
		iTest.p2cDecayChanged(3);
		assertEquals(2.9955, iTest.getDecay(),4);	
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cDecayChanged(double)}.
	 * Test de la méthode p2cDecayChanged lorsqu'on modifie la valeur du decay en dessous du minimum
	 */
	@Test
	public void testP2cDecayChangedLessMin() {
		iTest.p2cDecayChanged(-1.5);
		assertEquals(0, iTest.getDecay(),0);		
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cDecayChanged(double)}.
	 * Test de la méthode p2cDecayChanged lorsqu'on modifie la valeur du decay au dessus du maximum
	 */
	@Test
	public void testP2cDecayChangedMoreMax() {
		iTest.p2cDecayChanged(6);
		assertEquals(5, iTest.getDecay(),0);		
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cSustainChanged(double)}.
	 * Test de p2cSustainChanged lorsqu'on modifie une fois le sustain
	 */
	//TODO à voir pour les valeur => en expotentielle 2.00 = 1.9955
	@Test
	public void testP2cSustainChangedOnce() {
		iTest.p2cSustainChanged(2);
		assertEquals(1.9955, iTest.getSustain(),4);		}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cSustainChanged(double)}.
	 * Test de p2cSustainChanged lorsqu'on modifie deux fois le sustain
	 */
	//TODO à voir pour les valeur => en expotentielle 3.00 = 2.9985
	@Test
	public void testP2cSustainChangedTwice() {
		iTest.p2cSustainChanged(3);
		assertEquals(2.9985, iTest.getSustain(),4);	
		iTest.p2cSustainChanged(2);
		assertEquals(1.9955, iTest.getSustain(),4);	
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cSustainChanged(double)}.
	 * 	Test de la méthode p2cSustainChanged lorsqu'on modifie la valeur du sustain en dessous du minimum
	 */
	@Test
	public void testP2cSustainChangedLessMin() {
		iTest.p2cSustainChanged(-2);
		assertEquals(0, iTest.getSustain(),0);		
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cSustainChanged(double)}.
	 * 	Test de la méthode p2cSustainChanged lorsqu'on modifie la valeur du sustain au dessus du maximum
	 */
	@Test
	public void testP2cSustainChangedMoreMax() {
		iTest.p2cSustainChanged(6);
		assertEquals(5, iTest.getSustain(),0);			
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cReleaseChanged(double)}.
	 */
	//TODO à voir pour les valeur => en expotentielle 2.00 = 1.9955
	@Test
	public void testP2cReleaseChangedOnce() {
		iTest.p2cReleaseChanged(2);
		assertEquals(1.9955, iTest.getRelease(),4);	
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cReleaseChanged(double)}.
	 */
	//TODO à voir pour les valeur => en expotentielle 2.00 = 1.9955
	@Test
	public void testP2cReleaseChangedTwice() {
		iTest.p2cReleaseChanged(1);
		assertEquals(1.0030, iTest.getRelease(),4);	
		iTest.p2cReleaseChanged(4);
		assertEquals(4.0020, iTest.getRelease(),4);		
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cReleaseChanged(double)}.
	 */
	@Test
	public void testP2cReleaseChangedLessMin() {
		iTest.p2cReleaseChanged(-1);
		assertEquals(0, iTest.getRelease(),0);	
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cReleaseChanged(double)}.
	 */
	@Test
	public void testP2cReleaseChangedMoreMax() {
		iTest.p2cReleaseChanged(6);
		assertEquals(5, iTest.getRelease(),0);	
	}

	/**
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cClosing()}.
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
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cClosing()}.
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
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#p2cClosing()}.
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
	 * Test method for {@link fr.istic.synthlab.controller.module.eg.CModuleEG#setParameter(java.lang.String, java.lang.Double)}.
	 */
	@Test
	public void testSetParameter() {
		iTest.setParameter("attackTime", 2.0);
		assertEquals(1.9955, iTest.getAttack(),4);
		iTest.setParameter("decayTime", 2.0);
		assertEquals(1.9955, iTest.getDecay(),4);
		iTest.setParameter("substainTime", 2.0);
		assertEquals(1.9955, iTest.getSustain(),4);
		iTest.setParameter("releaseTime", 2.0);
		assertEquals(1.9955, iTest.getRelease(),4);
	}

}
