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

import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.impl.PSynthesizer;

public class SynthFrame extends JFrame implements ISynthFrame {

	private static final long serialVersionUID = -7577358239451859975L;

	private IPSynthesizer pres;
	private JMenuBar mainMenuBar;
	private JMenu menuFile, menuHelp;
	private JMenuItem menuItemNew, menuItemOpen, menuItemSave;
	private JMenuItem menuItemQuit, menuItemDoc, menuItemAbout;

	private ICommand newSynthCommand;
	private ICommand saveSynthCommand;
	private ICommand openSynthCommand;
	private ICommand quitSynthCommand;
	private ICommand docSynthCommand;
	private ICommand aboutSynthCommand;

	public SynthFrame() {

		this.initComponents();
		this.configureView();
		this.defineCallbacks();

	}

	/**
	 * Initialize the frame's components
	 */
	private void initComponents() {
		mainMenuBar = new JMenuBar();

		// -------------------------- File Menu --------------------------
		menuFile = new JMenu("File");

		menuItemNew = new JMenuItem("New");
		menuFile.add(menuItemNew);

		menuItemOpen = new JMenuItem("Open");
		menuFile.add(menuItemOpen);

		menuItemSave = new JMenuItem("Save");
		menuFile.add(menuItemSave);

		menuItemQuit = new JMenuItem("Quit");
		menuFile.add(menuItemQuit);

		// -------------------------- Help Menu --------------------------
		menuHelp = new JMenu("Help");

		menuItemDoc = new JMenuItem("Documentation");
		menuHelp.add(menuItemDoc);

		menuItemAbout = new JMenuItem("About");
		menuHelp.add(menuItemAbout);

		// Ajout des 2 menus
		mainMenuBar.add(menuFile);
		mainMenuBar.add(menuHelp);

		this.setJMenuBar(mainMenuBar);

	}

	/**
	 * Configure the frame's components
	 */
	private void configureView() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(850, 700);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		this.setResizable(true);

		menuItemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		menuItemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				ActionEvent.CTRL_MASK));
		menuItemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		menuItemQuit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
				ActionEvent.CTRL_MASK));
		menuItemDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.CTRL_MASK));
		menuItemAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
				ActionEvent.CTRL_MASK));
	}

	/**
	 * Define some callbacks for interaction
	 */
	private void defineCallbacks() {
		menuItemNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (newSynthCommand != null)
					newSynthCommand.execute();
			}
		});
		menuItemOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (openSynthCommand != null)
					openSynthCommand.execute();
			}
		});
		menuItemSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (saveSynthCommand != null)
					saveSynthCommand.execute();
			}
		});
		menuItemQuit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (quitSynthCommand != null)
					quitSynthCommand.execute();
			}
		});
		menuItemDoc.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (docSynthCommand != null)
					docSynthCommand.execute();
			}
		});
		menuItemAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (aboutSynthCommand != null)
					aboutSynthCommand.execute();
			}
		});
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.ISynthFrame#displaySynth(fr.istic.synthlab.presentation.IPSynthesizer)
	 */
	@Override
	public void displaySynth(IPSynthesizer presentation) {
		
		pres = presentation;
		this.add((PSynthesizer)pres);
		this.setVisible(true);
	}

	/* (non-Javadoc)
	 * @see fr.istic.synthlab.ISynthFrame#quitSynth()
	 */
	@Override
	public void quitSynth() {
		this.setVisible(false);
	}

	/**
	 * @param newSynthCommand the newSynthCommand to set
	 */
	public void setNewSynthCommand(ICommand newSynthCommand) {
		this.newSynthCommand = newSynthCommand;
	}

	/**
	 * @param saveSynthCommand the saveSynthCommand to set
	 */
	public void setSaveSynthCommand(ICommand saveSynthCommand) {
		this.saveSynthCommand = saveSynthCommand;
	}

	/**
	 * @param openSynthCommand the openSynthCommand to set
	 */
	public void setOpenSynthCommand(ICommand openSynthCommand) {
		this.openSynthCommand = openSynthCommand;
	}

	/**
	 * @param quitSynthCommand the quitSynthCommand to set
	 */
	public void setQuitSynthCommand(ICommand quitSynthCommand) {
		this.quitSynthCommand = quitSynthCommand;
	}

	/**
	 * @param docSynthCommand the docSynthCommand to set
	 */
	public void setDocSynthCommand(ICommand docSynthCommand) {
		this.docSynthCommand = docSynthCommand;
	}

	/**
	 * @param aboutSynthCommand the aboutSynthCommand to set
	 */
	public void setAboutSynthCommand(ICommand aboutSynthCommand) {
		this.aboutSynthCommand = aboutSynthCommand;
	}
	

}
