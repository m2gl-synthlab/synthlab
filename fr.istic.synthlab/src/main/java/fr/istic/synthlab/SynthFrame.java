package fr.istic.synthlab;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import fr.istic.synthlab.presentation.IPSynthesizer;

public class SynthFrame extends JFrame implements ISynthFrame {

	private IPSynthesizer pres;
	private JMenuBar mainMenuBar;
	private JMenu menuFile, menuHelp;
	private JMenuItem menuItemNew, menuItemOpen, menuItemSave;
	private JMenuItem menuItemQuit, menuItemDoc, menuItemAbout;
	
	public SynthFrame(){
		
		this.initComponents();
		this.configureView();
		this.defineCallbacks();
		
	}
	
	private void defineCallbacks() {
		menuItemNew.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menuItemOpen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menuItemSave.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menuItemQuit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menuItemDoc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		menuItemAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	private void configureView() {
		// TODO Auto-generated method stub
		
	}

	private void initComponents() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(850, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		this.setResizable(true);
		
		
		mainMenuBar = new JMenuBar();
		
		// -------------------------- File Menu --------------------------
		menuFile = new JMenu("File");
	
		menuItemNew = new JMenuItem("New");
		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		menuFile.add(menuItemNew);
		
		menuItemOpen = new JMenuItem("Open");
		menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		menuFile.add(menuItemOpen);
		
		menuItemSave = new JMenuItem("Save");
		menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		menuFile.add(menuItemSave);
		
		menuItemQuit = new JMenuItem("Quit");
		menuItemQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
		menuFile.add(menuItemQuit);
		

		// -------------------------- Help Menu --------------------------
		menuHelp = new JMenu("Help");
		
		menuItemDoc = new JMenuItem("Documentation");
		menuItemDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		menuHelp.add(menuItemDoc);
		
		menuItemAbout = new JMenuItem("About");
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));

		menuHelp.add(menuItemAbout);

		// Ajout des 2 menus
		mainMenuBar.add(menuFile);
		mainMenuBar.add(menuHelp);

		this.setJMenuBar(mainMenuBar);
		
	}

	@Override
	public void displaySynth(IPSynthesizer presentation) {
		this.setVisible(true);
		pres = presentation;
	}
	
	
}
