package fr.istic.synthlab.abstraction;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.istic.synthlab.abstraction.module.ModuleAudioScopeTest;
import fr.istic.synthlab.abstraction.module.ModuleEGTest;
import fr.istic.synthlab.abstraction.module.ModuleMIXTest;
import fr.istic.synthlab.abstraction.module.ModuleOUTTest;
import fr.istic.synthlab.abstraction.module.ModuleREPTest;
import fr.istic.synthlab.abstraction.module.ModuleVCATest;
import fr.istic.synthlab.abstraction.module.ModuleVCF_HPTest;
import fr.istic.synthlab.abstraction.module.ModuleVCF_LPTest;
import fr.istic.synthlab.abstraction.module.ModuleVCOTest;

@RunWith(Suite.class)
@SuiteClasses({ ConvertTest.class, ModuleOUTTest.class, ModuleREPTest.class,
		 ModuleVCATest.class, ModuleVCOTest.class,
		SynthesizerTest.class, WireTest.class,ModuleMIXTest.class,ModuleEGTest.class,ModuleAudioScopeTest.class,ModuleVCF_HPTest.class,
		ModuleVCF_LPTest.class
})
public class AllTests {

}
