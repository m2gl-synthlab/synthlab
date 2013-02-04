package fr.istic.synthlab.abstraction;

import junit.framework.TestCase;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class ModuleTest extends TestCase {

	public void setUp(){
		PACFactory.setPFactory(PFactory.getInstance());

	}
	
	public void testCModuleOUT() {
//		PFactory.getInstance().new;
		
	}
}
