package fr.istic.synthlab.command.menu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.istic.synthlab.command.ICommand;

public class AboutCommand implements ICommand {

	public void execute() {
		System.out.println("Command "+this.getClass().getSimpleName()+" not implemented...");
		JOptionPane.showMessageDialog(new JFrame("About"),
				"        SynthLab/Groupe 2\n" +
				"Chevalier - Chevalot - Guerra\n" +
				"   Hardouin - Kwete - Le Ho\n" +
				"          ISTIC 2013", "A propos", 
				JOptionPane.PLAIN_MESSAGE);
	}
}
