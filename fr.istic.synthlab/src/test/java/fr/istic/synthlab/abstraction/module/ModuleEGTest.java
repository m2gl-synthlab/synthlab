package fr.istic.synthlab.abstraction.module;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.module.eg.IModuleEG;
import fr.istic.synthlab.abstraction.module.eg.ModuleEG;
import fr.istic.synthlab.abstraction.module.mix.ModuleMIX;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.rep.ModuleREP;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class ModuleEGTest {

	private IModuleEG m;
	private ISynthesizer synth;

	@Before
	public void setUp() throws Exception {
		PACFactory.setFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		m=new ModuleEG(synth);
	
	}
	@Test
	public void testGetJSyn() {
		assertNotNull(m.getJSyn());
	}

	@Test
	public void testSetGetAttack() {
		m.setAttack(2.0);
		assertEquals(2.0, m.getAttack(),0);
	}



	@Test
	public void testSetGetDecay() {
		m.setDecay(2.0);
		assertEquals(2.0, m.getDecay(),0);	}

	@Test
	public void testSetGetSustain() {
		m.setSustain(2.0);
		assertEquals(2.0, m.getSustain(),0);	}


	@Test
	public void testSetGetRelease() {
		m.setRelease(2.0);
		assertEquals(2.0, m.getRelease(),0);	}



	@Test
	public void testStart() {
		fail("Not yet implemented");
	}

	@Test
	public void testStop() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetWires(){
		IWire w=new Wire(synth);
		try {
			w.connect(m.getGateInput());
		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			w.connect(m.getOutput());
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
	public void testGetWiresDifferent(){
		IWire w=new Wire(synth);		
		IWire w2=new Wire(synth);
		IModuleREP mrep=new ModuleREP(synth);

		try {
			w.connect(mrep.getOutput1());
			w2.connect(mrep.getInput());

		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			w.connect(m.getGateInput());
			w2.connect(m.getOutput());


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



	
	
	@Test
	public void testGetWiresDifferentBad(){
		IWire w=new Wire(synth);		
		IWire w2=new Wire(synth);
		IModuleREP mrep=new ModuleREP(synth);

		try {
			w.connect(m.getGateInput());
			w.connect(mrep.getOutput1());
			w2.connect(m.getGateInput());
			fail("Une exception devrait etre lancée");

		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
