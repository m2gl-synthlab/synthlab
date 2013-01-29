package fr.istic.synthlab.presentation.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPSynthesizer;

public class PSynthesizer extends JPanel implements IPSynthesizer, ActionListener, ItemListener {

	ICSynthesizer ctrl;
	
	public PSynthesizer(ICSynthesizer control){
		ctrl = control;
		// Construction de la Frame contenant le Panel du Synthetizer

		
		JLabel label = new JLabel("PSynthetizer!");
		this.add(label);		
	}

	

	public void addPModule(IPModule ipModule) {
		this.add((Component)ipModule);
	}

	@Override
	public ICSynthesizer getControl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addModule(IPModule module) {
		this.add((Component) module);
	}

	@Override
	public void removeModule(IPModule module) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (((JMenuItem)e.getSource()).getText()){
			case "New": System.out.println("new");
				break;
			case "Open": System.out.println("open");
				break;
			case "Save":System.out.println("save");
				break;
			case "Quit":System.out.println("quit");
				break;
			case "Documentation":System.out.println("doc");
				break;
			case "About":System.out.println("about");
				break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}