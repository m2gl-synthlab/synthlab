package fr.istic.synthlab.presentation;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PSynthetizer extends JPanel {

	public PSynthetizer(){

		this.setLayout(new BorderLayout());
		JLabel label = new JLabel("Le tout premier texte !");
		this.add(label);
	}
}
