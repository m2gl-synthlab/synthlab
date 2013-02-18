package fr.istic.synthlab.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

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
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.CWire;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;
import fr.istic.synthlab.presentation.module.audioscope.IPModuleAudioScope;
import fr.istic.synthlab.presentation.module.audioscope.PModuleAudioScope;
import fr.istic.synthlab.presentation.module.eg.IPModuleEG;
import fr.istic.synthlab.presentation.module.eg.PModuleEG;
import fr.istic.synthlab.presentation.module.mix.IPModuleMIX;
import fr.istic.synthlab.presentation.module.mix.PModuleMIX;
import fr.istic.synthlab.presentation.module.out.IPModuleOUT;
import fr.istic.synthlab.presentation.module.out.PModuleOUT;
import fr.istic.synthlab.presentation.module.rep.IPModuleREP;
import fr.istic.synthlab.presentation.module.rep.PModuleREP;
import fr.istic.synthlab.presentation.module.vca.IPModuleVCA;
import fr.istic.synthlab.presentation.module.vca.PModuleVCA;
import fr.istic.synthlab.presentation.module.vcf.IPModuleVCF;
import fr.istic.synthlab.presentation.module.vcf.PModuleVCF;
import fr.istic.synthlab.presentation.module.vco.IPModuleVCO;
import fr.istic.synthlab.presentation.module.vco.PModuleVCO;
import fr.istic.synthlab.presentation.port.IPInputPort;
import fr.istic.synthlab.presentation.port.IPOutputPort;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;
import fr.istic.synthlab.presentation.synthesizer.PSynthesizer;
import fr.istic.synthlab.presentation.wire.IPWire;
import fr.istic.synthlab.presentation.wire.PWire;

public class PFactoryTest {
	IPFactory fact;
	ICSynthesizer synth;

	@Before
	public void setUp() throws Exception {
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());

		fact=PFactory.getInstance();
	}

	@Test
	public void testNewSynthesizer() {
		IPSynthesizer synth=fact.newSynthesizer(new CSynthesizer());
		assertEquals(PSynthesizer.class,synth.getClass());
	}

	@Test
	public void testNewVCO() {
		IPModuleVCO vco=fact.newVCO(new CModuleVCO(new CSynthesizer()));
		assertEquals(PModuleVCO.class, vco.getClass());
	}

	@Test
	public void testNewVCA() {
		IPModuleVCA vca=fact.newVCA(new CModuleVCA(new CSynthesizer()));
		assertEquals(PModuleVCA.class, vca.getClass());	}

	@Test
	public void testNewVCFA_LP() {
		IPModuleVCF vcflp= fact.newVCF(new CModuleVCF_LP(new CSynthesizer()));
		assertEquals(PModuleVCF.class, vcflp.getClass());	}

	@Test
	public void testNewVCFA_HP() {
		IPModuleVCF vcfhp= fact.newVCF(new CModuleVCF_HP(new CSynthesizer()));
		assertEquals(PModuleVCF.class, vcfhp.getClass());	}

	@Test
	public void testNewOUT() {
		IPModuleOUT out=fact.newOUT(new CModuleOUT(new CSynthesizer()));
		assertEquals(PModuleOUT.class, out.getClass());	}

	@Test
	public void testNewEG() {
		IPModuleEG eg=fact.newEG(new CModuleEG(new CSynthesizer()));
		assertEquals(PModuleEG.class, eg.getClass());	}

	@Test
	public void testNewAudioScope() {
		IPModuleAudioScope scope=fact.newAudioScope(new CModuleAudioScope(new CSynthesizer()));
		assertEquals(PModuleAudioScope.class, scope.getClass());	}

	@Test
	public void testNewREP() {
		IPModuleREP rep=fact.newREP(new CModuleREP(new CSynthesizer()));
		assertEquals(PModuleREP.class, rep.getClass());	}

	@Test
	public void testNewMIX() {
		IPModuleMIX mix= fact.newMIX(new CModuleMIX(new CSynthesizer()));
		assertEquals(PModuleMIX.class, mix.getClass());	}

	@Test
	public void testNewWire() {
		IPWire wire=fact.newWire(new CWire(new CSynthesizer()));
		assertEquals(PWire.class, wire.getClass());	}

	@Test
	public void testNewInputPort() {
		IPInputPort ip=fact.newInputPort(new CInputPort(new CSynthesizer(),"input", new UnitInputPort("port"), new CModuleVCO(new CSynthesizer())));
		assertEquals(PInputPort.class, ip.getClass());	}

	@Test
	public void testNewOutputPort() {
		IPOutputPort ip=fact.newOutputPort(new COutputPort(new CSynthesizer(),"input", new UnitOutputPort("port"), new CModuleVCO(new CSynthesizer())));
		assertEquals(POutputPort.class, ip.getClass());	}

}
