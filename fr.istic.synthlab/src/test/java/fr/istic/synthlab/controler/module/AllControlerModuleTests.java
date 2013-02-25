package fr.istic.synthlab.controler.module;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CModuleAudioScopeTest.class, CModuleEGTest.class, CModuleMIXTest.class, CModuleOUTTest.class, CModuleRECTest.class, CModuleREPTest.class,
		CModuleVCATest.class, CModuleVCF_HPTest.class, CModuleVCF_LPTest.class, CModuleVCOTest.class })
public class AllControlerModuleTests {

}
