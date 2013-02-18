package fr.istic.synthlab.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.module.audioscope.IModuleAudioScope;
import fr.istic.synthlab.abstraction.module.eg.IModuleEG;
import fr.istic.synthlab.abstraction.module.mix.IModuleMIX;
import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.vca.IModuleVCA;
import fr.istic.synthlab.abstraction.module.vcf.IModuleVCF;
import fr.istic.synthlab.abstraction.module.vco.IModuleVCO;
import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.audioscope.CModuleAudioScope;
import fr.istic.synthlab.controller.module.eg.CModuleEG;
import fr.istic.synthlab.controller.module.mix.CModuleMIX;
import fr.istic.synthlab.controller.module.out.CModuleOUT;
import fr.istic.synthlab.controller.module.rep.CModuleREP;
import fr.istic.synthlab.controller.module.vca.CModuleVCA;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF_HP;
import fr.istic.synthlab.controller.module.vcf.CModuleVCF_LP;
import fr.istic.synthlab.controller.module.vco.CModuleVCO;
import fr.istic.synthlab.controller.port.CInputPort;
import fr.istic.synthlab.controller.port.COutputPort;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.wire.CWire;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class CFactoryTest {
	IFactory fact;

	@Before
	public void setUp() throws Exception {
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());

		fact=CFactory.getInstance();
	}

	@Test
	public void testNewSynthesizer() {
		ISynthesizer synth=fact.newSynthesizer();
		assertEquals(CSynthesizer.class,synth.getClass());
	}

	@Test
	public void testNewVCO() {
		IModuleVCO vco=fact.newVCO(new CSynthesizer());
		assertEquals(CModuleVCO.class, vco.getClass());
	}

	@Test
	public void testNewVCA() {
		IModuleVCA vca=fact.newVCA(new CSynthesizer());
		assertEquals(CModuleVCA.class, vca.getClass());	}

	@Test
	public void testNewVCFA_LP() {
		IModuleVCF vcflp=(IModuleVCF) fact.newVCFA_LP(new CSynthesizer());
		assertEquals(CModuleVCF_LP.class, vcflp.getClass());	}

	@Test
	public void testNewVCFA_HP() {
		IModuleVCF vcfhp=(IModuleVCF) fact.newVCFA_HP(new CSynthesizer());
		assertEquals(CModuleVCF_HP.class, vcfhp.getClass());	}

	@Test
	public void testNewOUT() {
		IModuleOUT out=fact.newOUT(new CSynthesizer());
		assertEquals(CModuleOUT.class, out.getClass());	}

	@Test
	public void testNewEG() {
		IModuleEG eg=fact.newEG(new CSynthesizer());
		assertEquals(CModuleEG.class, eg.getClass());	}

	@Test
	public void testNewAudioScope() {
		IModuleAudioScope scope=fact.newAudioScope(new CSynthesizer());
		assertEquals(CModuleAudioScope.class, scope.getClass());	}

	@Test
	public void testNewREP() {
		IModuleREP rep=fact.newREP(new CSynthesizer());
		assertEquals(CModuleREP.class, rep.getClass());	}

	@Test
	public void testNewMIX() {
		IModuleMIX mix=(IModuleMIX) fact.newMIX(new CSynthesizer());
		assertEquals(CModuleMIX.class, mix.getClass());	}

	@Test
	public void testNewWire() {
		IWire wire=fact.newWire(new CSynthesizer());
		assertEquals(CWire.class, wire.getClass());	}

	@Test
	public void testNewInputPort() {
		IInputPort ip=fact.newInputPort(new CSynthesizer(),new ModuleVCO(new CSynthesizer()),"input", new UnitInputPort("port"));
		assertEquals(CInputPort.class, ip.getClass());	}

	@Test
	public void testNewOutputPort() {
		IOutputPort op=fact.newOutputPort(new CSynthesizer(),new ModuleVCO(new CSynthesizer()),"input", new UnitOutputPort("port"));
		assertEquals(COutputPort.class, op.getClass());	}

}
