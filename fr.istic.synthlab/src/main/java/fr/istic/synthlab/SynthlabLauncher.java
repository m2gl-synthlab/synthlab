package fr.istic.synthlab;
import java.awt.Toolkit;

import fr.istic.synthlab.abstraction.IModule;
import fr.istic.synthlab.abstraction.ISynthesizer;
import fr.istic.synthlab.abstraction.IWire;
import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.factory.AFactory;
import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.PACFactory;


public class SynthlabLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PACFactory.setAFactory(AFactory.getInstance());
		IFactory factory = PACFactory.getFactory();
		
		
		ISynthesizer syn = factory.newSynthesizer(factory);
		IModule vco = factory.newVCO(factory);
		IModule vca = factory.newVCA(factory);
		syn.addModule(vco);
		syn.addModule(vca);
		
		IWire wire = factory.newWire(factory);
		wire.connect((OutputPort) vco.getPort(0));
		wire.connect((InputPort) vca.getPort(0));
		
		
		System.out.println("Beep!");
		Toolkit.getDefaultToolkit().beep();
		//JSyn.createSynthesizer().start();
		
	}

}
