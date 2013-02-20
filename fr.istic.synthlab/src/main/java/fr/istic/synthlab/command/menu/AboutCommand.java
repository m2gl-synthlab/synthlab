package fr.istic.synthlab.command.menu;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import fr.istic.synthlab.command.ICommand;

public class AboutCommand implements ICommand {

	public void execute() {
		String html = "<html><body><div align=\"center\">SynthLab (Groupe 2)<br/> Chevallier - Chevalot - Guerra <br/> Hardouin - Kwete - Le Ho <br/> ISTIC 2013 </div></body></html>";
		JOptionPane.showMessageDialog(new JFrame("About"), html, "About", JOptionPane.INFORMATION_MESSAGE);
	}
}
