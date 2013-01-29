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

public class PSynthesizer extends JFrame implements IPSynthesizer, ActionListener, ItemListener {

	ICSynthesizer ctrl;
	
	public PSynthesizer(ICSynthesizer control){
		ctrl = control;
		// Construction de la Frame contenant le Panel du Synthetizer
		JFrame frame = new JFrame("Synthetizer Grp2");
		frame.setJMenuBar(createJMenuBar());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(850, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
		frame.setResizable(true);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		JLabel label = new JLabel("PSynthetizer!");
		panel.add(label);

		frame.getContentPane().add(panel);
		
		
		
	}

	private JMenuBar createJMenuBar() {
		JMenuBar mainMenuBar;
		JMenu menuFile, menuHelp;
		JMenuItem menuItemNew, menuItemOpen, menuItemSave, menuItemQuit, menuItemDoc, menuItemAbout;
		
		mainMenuBar = new JMenuBar();
		
		// -------------------------- File Menu --------------------------
		menuFile = new JMenu("File");
	
		menuItemNew = new JMenuItem("New");
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuItemNew.addActionListener(this);
		menuFile.add(menuItemNew);
		
		menuItemOpen = new JMenuItem("Open");
		menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuItemOpen.addActionListener(this);
		menuFile.add(menuItemOpen);
		
		menuItemSave = new JMenuItem("Save");
		menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuItemSave.addActionListener(this);
		menuFile.add(menuItemSave);
		
		menuItemQuit = new JMenuItem("Quit");
		menuItemQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menuItemQuit.addActionListener(this);
		menuFile.add(menuItemQuit);
		

		// -------------------------- Help Menu --------------------------
		menuHelp = new JMenu("Help");
		
		menuItemDoc = new JMenuItem("Documentation");
		menuItemDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		menuItemDoc.addActionListener(this);
		menuHelp.add(menuItemDoc);
		
		menuItemAbout = new JMenuItem("About");
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		menuItemAbout.addActionListener(this);
		menuHelp.add(menuItemAbout);

		// Ajout des 2 menus
		mainMenuBar.add(menuFile);
		mainMenuBar.add(menuHelp);
		
		return mainMenuBar;
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