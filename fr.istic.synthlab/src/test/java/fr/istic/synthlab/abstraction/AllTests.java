package fr.istic.synthlab.abstraction;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConvertTest.class, ModuleOUTTest.class, ModuleREPTest.class,
		 ModuleVCATest.class, ModuleVCOTest.class,
		SynthesizerTest.class, WireTest.class,ModuleMIXTest.class,ModuleEGTest.class,ModuleAudioScopeTest.class })
public class AllTests {

}
