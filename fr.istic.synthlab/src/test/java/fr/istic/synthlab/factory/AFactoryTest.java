package fr.istic.synthlab.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;

import fr.istic.synthlab.abstraction.module.audioscope.IModuleAudioScope;
import fr.istic.synthlab.abstraction.module.audioscope.ModuleAudioScope;
import fr.istic.synthlab.abstraction.module.eg.IModuleEG;
import fr.istic.synthlab.abstraction.module.eg.ModuleEG;
import fr.istic.synthlab.abstraction.module.mix.IModuleMIX;
import fr.istic.synthlab.abstraction.module.mix.ModuleMIX;
import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.abstraction.module.out.ModuleOUT;
import fr.istic.synthlab.abstraction.module.rep.IModuleREP;
import fr.istic.synthlab.abstraction.module.rep.ModuleREP;
import fr.istic.synthlab.abstraction.module.vca.IModuleVCA;
import fr.istic.synthlab.abstraction.module.vca.ModuleVCA;
import fr.istic.synthlab.abstraction.module.vcf.IModuleVCF;
import fr.istic.synthlab.abstraction.module.vcf.ModuleVCF_HP;
import fr.istic.synthlab.abstraction.module.vcf.ModuleVCF_LP;
import fr.istic.synthlab.abstraction.module.vco.IModuleVCO;
import fr.istic.synthlab.abstraction.module.vco.ModuleVCO;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.port.InputPort;
import fr.istic.synthlab.abstraction.port.OutputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.abstraction.wire.Wire;
import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.PACFactory;

public class AFactoryTest {
	IFactory fact;

	@Before
	public void setUp() throws Exception {
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(null);

		fact=AFactory.getInstance();
	}

	@Test
	public void testNewSynthesizer() {
		ISynthesizer synth=fact.newSynthesizer();
		assertEquals(Synthesizer.class,synth.getClass());
	}

	@Test
	public void testNewVCO() {
		IModuleVCO vco=fact.newVCO(new Synthesizer());
		assertEquals(ModuleVCO.class, vco.getClass());
	}

	@Test
	public void testNewVCA() {
		IModuleVCA vca=fact.newVCA(new Synthesizer());
		assertEquals(ModuleVCA.class, vca.getClass());	}

	@Test
	public void testNewVCFA_LP() {
		IModuleVCF vcflp=(IModuleVCF) fact.newVCFA_LP(new Synthesizer());
		assertEquals(ModuleVCF_LP.class, vcflp.getClass());	}

	@Test
	public void testNewVCFA_HP() {
		IModuleVCF vcfhp=(IModuleVCF) fact.newVCFA_HP(new Synthesizer());
		assertEquals(ModuleVCF_HP.class, vcfhp.getClass());	}

	@Test
	public void testNewOUT() {
		IModuleOUT out=fact.newOUT(new Synthesizer());
		assertEquals(ModuleOUT.class, out.getClass());	}

	@Test
	public void testNewEG() {
		IModuleEG eg=fact.newEG(new Synthesizer());
		assertEquals(ModuleEG.class, eg.getClass());	}

	@Test
	public void testNewAudioScope() {
		IModuleAudioScope scope=fact.newAudioScope(new Synthesizer());
		assertEquals(ModuleAudioScope.class, scope.getClass());	}

	@Test
	public void testNewREP() {
		IModuleREP rep=fact.newREP(new Synthesizer());
		assertEquals(ModuleREP.class, rep.getClass());	}

	@Test
	public void testNewMIX() {
		IModuleMIX mix=(IModuleMIX) fact.newMIX(new Synthesizer());
		assertEquals(ModuleMIX.class, mix.getClass());	}

	@Test
	public void testNewWire() {
		IWire wire=fact.newWire(new Synthesizer());
		assertEquals(Wire.class, wire.getClass());	}

	@Test
	public void testNewInputPort() {
		IInputPort ip=fact.newInputPort(new Synthesizer(),new ModuleVCO(new Synthesizer()),"input", new UnitInputPort("port"));
		assertEquals(InputPort.class, ip.getClass());	}

	@Test
	public void testNewOutputPort() {
		IOutputPort op=fact.newOutputPort(new Synthesizer(),new ModuleVCO(new Synthesizer()),"input", new UnitOutputPort("port"));
		assertEquals(OutputPort.class, op.getClass());	}

}
