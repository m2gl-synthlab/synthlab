package fr.istic.synthlab.abstraction.module;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.jsyn.unitgen.PassThrough;

import fr.istic.synthlab.abstraction.exception.BadConnectionException;
import fr.istic.synthlab.abstraction.exception.PortAlreadyInUseException;
import fr.istic.synthlab.abstraction.filter.AmplitudeModulatorFilter;
import fr.istic.synthlab.abstraction.module.mix.IModuleMIX;
import fr.istic.synthlab.abstraction.module.mix.ModuleMIX;
import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.abstraction.module.out.ModuleOUT;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.rep.ModuleREP;
import fr.istic.synthlab.abstraction.module.vca.ModuleVCA;
import fr.istic.synthlab.abstraction.port.Port;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class ModuleMIXTest {

	private IModuleMIX m;
	private ISynthesizer synth;

	@Before
	public void setUp() throws Exception {
		PACFactory.setFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		synth = new CSynthesizer();
		m=new ModuleMIX(synth);
	
	}
	@Test
	public void testGetJSyn() {
		assertNotNull(m.getJSyn());
	}

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
			w.connect(m.getInput(3));
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
			w.connect(m.getInput(3));
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
			w.connect(m.getInput(3));
			w.connect(mrep.getOutput1());
			w2.connect(m.getInput(3));
			fail("Une exception devrait etre lancée");

		} catch (PortAlreadyInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Test
	public void testSetGetAttenuation1() {
		m.setAttenuation1(2.0);
		assertEquals(2.0, m.getAttenuation1(),0);
	}

	@Test
	public void testSetGetAttenuation2() {
		m.setAttenuation2(2.0);
		assertEquals(2.0, m.getAttenuation2(),0);
	}
	@Test
	public void testSetGetAttenuation3() {
		m.setAttenuation3(2.0);
		assertEquals(2.0, m.getAttenuation3(),0);
	}
	@Test
	public void testSetGetAttenuation4() {
		m.setAttenuation4(2.0);
		assertEquals(2.0, m.getAttenuation4(),0);
	}


}