package fr.istic.synthlab;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.controller.impl.CModule;
import fr.istic.synthlab.controller.impl.CSynthesizer;
import fr.istic.synthlab.controller.impl.CWire;
import fr.istic.synthlab.factory.AFactory;
import fr.istic.synthlab.factory.CFactory;
import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.PACFactory;
import fr.istic.synthlab.factory.PFactory;


public class SynthlabLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		PACFactory.setAFactory(AFactory.getInstance());
		PACFactory.setCFactory(CFactory.getInstance());
		PACFactory.setPFactory(PFactory.getInstance());
		IFactory factory = PACFactory.getFactory();
		
		
		CSynthesizer syn = (CSynthesizer)factory.newSynthesizer(factory);
		CModule vco = (CModule)factory.newVCO(factory);
		CModule vca = (CModule)factory.newVCA(factory);
		syn.addModule(vco);
		syn.addModule(vca);
		
		CWire wire = (CWire)factory.newWire(factory);
		wire.connect((OutputPort) vco.getPort(0));
		wire.connect((InputPort) vca.getPort(0));
		
		
		
		// Construction de la Frame contenant le Panel du Synthetizer
		JFrame frame = new JFrame("Synthetizer Grp2");
		frame.getContentPane().add((Component)syn.getPresentation());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(850, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setResizable(true);
		frame.setVisible(true);
		
		System.out.println("Beep!");
		Toolkit.getDefaultToolkit().beep();
		//JSyn.createSynthesizer().start();
		
	}

}
