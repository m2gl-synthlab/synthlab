package fr.istic.synthlab.command;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import fr.istic.synthlab.command.menu.AddModuleRECCommand;

@RunWith(Suite.class)
@SuiteClasses({ AddModuleEGCommandTest.class, AddModuleMIXCommandTest.class,
		AddModuleOUTCommandTest.class, AddModuleREPCommandTest.class,
		AddModuleVCACommandTest.class, AddModuleVCFHPCommandTest.class,
		AddModuleVCFLPCommandTest.class, AddModuleVCOCommandTest.class,
		NewSynthCommandTest.class, OpenAndSaveCommandTest.class,ToolbarCurrentWireColorCommandTest.class,
		ToolbarPausePlayCommandTest.class,AddModuleRECCommandTest.class
	 })
public class AllCommandTests {

}
