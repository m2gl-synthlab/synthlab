package fr.istic.synthlab.abstraction.module;

import junit.framework.TestCase;

import com.jsyn.unitgen.SineOscillator;

import fr.istic.synthlab.abstraction.module.out.IModuleOUT;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class ModuleTest extends TestCase {

	public void setUp() {
		// Initialize factories
		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
	}

	public void testCModuleOUT() {
		ISynthesizer synth = PACFactory.getFactory().newSynthesizer();

		SineOscillator osc = new SineOscillator();
		synth.getJSyn().add(osc);

		IModuleOUT out = PACFactory.getFactory().newOUT(synth);
		synth.add(out);
		osc.output.connect(out.getInput().getJSyn());

		synth.start();
		for (int i = 0; i < 10; i++) {
			//TODO : Something need to be done
			System.out.println("Testing CModuleOUT : " + osc.output.get());
		}
		synth.stop();
	}
}
