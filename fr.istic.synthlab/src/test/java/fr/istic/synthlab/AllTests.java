package fr.istic.synthlab;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.istic.synthlab.abstraction.ConvertTest;
import fr.istic.synthlab.abstraction.SynthesizerTest;
import fr.istic.synthlab.abstraction.WireTest;
import fr.istic.synthlab.abstraction.filter.AmplitudeModulatorFilterTest;
import fr.istic.synthlab.abstraction.filter.AttenuationFilterTest;
import fr.istic.synthlab.abstraction.filter.FrequencyModulatorFilterTest;
import fr.istic.synthlab.abstraction.filter.QuadrupleMixFilterTest;
import fr.istic.synthlab.abstraction.filter.TriplePassThroughFilterTest;
import fr.istic.synthlab.abstraction.module.ModuleAudioScopeTest;
import fr.istic.synthlab.abstraction.module.ModuleEGTest;
import fr.istic.synthlab.abstraction.module.ModuleMIXTest;
import fr.istic.synthlab.abstraction.module.ModuleOUTTest;
import fr.istic.synthlab.abstraction.module.ModuleREPTest;
import fr.istic.synthlab.abstraction.module.ModuleVCATest;
import fr.istic.synthlab.abstraction.module.ModuleVCF_HPTest;
import fr.istic.synthlab.abstraction.module.ModuleVCF_LPTest;
import fr.istic.synthlab.abstraction.module.ModuleVCOTest;
import fr.istic.synthlab.command.AddModuleEGCommandTest;
import fr.istic.synthlab.command.AddModuleMIXCommandTest;
import fr.istic.synthlab.command.AddModuleOUTCommandTest;
import fr.istic.synthlab.command.AddModuleREPCommandTest;
import fr.istic.synthlab.command.AddModuleVCACommandTest;
import fr.istic.synthlab.command.AddModuleVCFHPCommandTest;
import fr.istic.synthlab.command.AddModuleVCFLPCommandTest;
import fr.istic.synthlab.command.AddModuleVCOCommandTest;
import fr.istic.synthlab.command.NewSynthCommandTest;
import fr.istic.synthlab.command.OpenAndSaveCommandTest;
import fr.istic.synthlab.controler.module.CModuleAudioScopeTest;
import fr.istic.synthlab.controler.module.CModuleEGTest;
import fr.istic.synthlab.controler.module.CModuleMIXTest;
import fr.istic.synthlab.controler.module.CModuleOUTTest;
import fr.istic.synthlab.controler.module.CModuleRECTest;
import fr.istic.synthlab.controler.module.CModuleREPTest;
import fr.istic.synthlab.controler.module.CModuleVCATest;
import fr.istic.synthlab.controler.module.CModuleVCF_HPTest;
import fr.istic.synthlab.controler.module.CModuleVCF_LPTest;
import fr.istic.synthlab.controler.module.CModuleVCOTest;
import fr.istic.synthlab.util.SaveLoadTest;

@RunWith(Suite.class)
@SuiteClasses({ AmplitudeModulatorFilterTest.class,
		AttenuationFilterTest.class, FrequencyModulatorFilterTest.class,
		QuadrupleMixFilterTest.class, TriplePassThroughFilterTest.class, CModuleAudioScopeTest.class, CModuleEGTest.class,
		CModuleMIXTest.class, CModuleOUTTest.class, CModuleRECTest.class,
		CModuleREPTest.class, CModuleVCATest.class, CModuleVCF_HPTest.class,
		CModuleVCF_LPTest.class, CModuleVCOTest.class ,AddModuleEGCommandTest.class, AddModuleMIXCommandTest.class,
		AddModuleOUTCommandTest.class, AddModuleREPCommandTest.class,
		AddModuleVCACommandTest.class, AddModuleVCFHPCommandTest.class,
		AddModuleVCFLPCommandTest.class, AddModuleVCOCommandTest.class,
		NewSynthCommandTest.class, OpenAndSaveCommandTest.class,ConvertTest.class, ModuleOUTTest.class, ModuleREPTest.class,
		 ModuleVCATest.class, ModuleVCOTest.class,
		SynthesizerTest.class, WireTest.class,ModuleMIXTest.class,ModuleEGTest.class,ModuleAudioScopeTest.class,ModuleVCF_HPTest.class,
		ModuleVCF_LPTest.class,SaveLoadTest.class })
public class AllTests {

}
