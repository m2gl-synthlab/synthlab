package fr.istic.synthlab.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.istic.synthlab.factory.impl.AFactory;
import fr.istic.synthlab.factory.impl.CFactory;
import fr.istic.synthlab.factory.impl.PACFactory;
import fr.istic.synthlab.factory.impl.PFactory;

public class PACFactoryTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSetGetAFactory() {
		IFactory fact=AFactory.getInstance();
		PACFactory.setAFactory(fact);
		assertEquals(fact, PACFactory.getAFactory());
	}



	@Test
	public void testSetGetCFactory() {
		IFactory fact=CFactory.getInstance();
		PACFactory.setCFactory(fact);
		assertEquals(fact, PACFactory.getCFactory());
	}

	

	@Test
	public void testSetGetPFactory() {
		IPFactory fact=PFactory.getInstance();
		PACFactory.setPFactory(fact);
		assertEquals(fact, PACFactory.getPFactory());
	}

	
	@Test
	public void testSetGetFactoryNullCFactory() {
		IFactory fact=AFactory.getInstance();

		PACFactory.setAFactory(fact);
		PACFactory.setCFactory(null);
		assertEquals(fact, PACFactory.getFactory());
		
	}
	
	
	@Test
	public void testSetGetFactoryNotNullCFactory() {
		IFactory fact=AFactory.getInstance();
		IFactory fact2=CFactory.getInstance();

		PACFactory.setAFactory(fact);
		PACFactory.setCFactory(fact2);
		assertEquals(fact2, PACFactory.getFactory());
		
	}

	

}
