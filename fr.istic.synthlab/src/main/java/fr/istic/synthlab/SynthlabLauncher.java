package fr.istic.synthlab;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import fr.istic.synthlab.abstraction.impl.InputPort;
import fr.istic.synthlab.abstraction.impl.OutputPort;
import fr.istic.synthlab.controller.CModule;
import fr.istic.synthlab.controller.CSynthetizer;
import fr.istic.synthlab.controller.CWire;
import fr.istic.synthlab.factory.CFactory;
import fr.istic.synthlab.factory.IFactory;
import fr.istic.synthlab.factory.PACFactory;


public class SynthlabLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		PACFactory.setCFactory(CFactory.getInstance());
		IFactory factory = PACFactory.getFactory();
		
		
		CSynthetizer syn = (CSynthetizer)factory.newSynthesizer(factory);
		
		// Construction de la Frame contenant le Panel du Synthetizer
		JFrame frame = new JFrame("Synthetizer Grp2");
		frame.getContentPane().add(syn.getPresentation());
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
