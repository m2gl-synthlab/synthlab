package fr.istic.synthlab;
import java.awt.Toolkit;

import com.jsyn.JSyn;


public class SynthlabLauncher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Beep!");
		Toolkit.getDefaultToolkit().beep();
		JSyn.createSynthesizer().start();
	}

}
