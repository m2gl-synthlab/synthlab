package fr.istic.synthlab;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

import com.softsynth.jsyn.view102.UsageDisplay;

import fr.istic.synthlab.command.ICommand;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.impl.PSynthesizer;

public class SynthFrame extends JFrame implements ISynthFrame {

	private static final long serialVersionUID = -7577358239451859975L;

	// constantes pour les boutons de la toolbar
	private static final int BUTTON_DEFAULT = 0;
	private static final int BUTTON_WIRE = 1;
	private static final int BUTTON_MODULE = 2;

	private static final int BUTTON_PLAY = 3;
	private static final int BUTTON_PAUSE = 4;
	private static final int BUTTON_RECORD = 5;

	private IPSynthesizer pres;

	// Menu
	private JMenuBar mainMenuBar;
	private JMenu menuFile, menuHelp;
	private JMenuItem menuItemNew, menuItemOpen, menuItemSave;
	private JMenuItem menuItemQuit, menuItemDoc, menuItemAbout;

	// Toolbar
	private JToolBar toolBar = new JToolBar();
	private String[] iconFiles = { "res/default.png", "res/wire.png", "res/module.png", "res/play.png", "res/pause.png", "res/record.png" };
	private String[] buttonLabels = { "Default", "Wire", "Module", "Play", "Pause", "Record" };
	private Image[] icons = new Image[iconFiles.length];
	private JButton[] buttons = new JButton[buttonLabels.length];
	

	// Command
	private ICommand newSynthCommand;
	private ICommand saveSynthCommand;
	private ICommand openSynthCommand;
	private ICommand quitSynthCommand;
	private ICommand docSynthCommand;
	private ICommand aboutSynthCommand;
	private ICommand toolbarDefaultCommand;
	private ICommand toolbarWireCommand;
	private ICommand toolbarModuleCommand;

	private ICommand toolbarPlayCommand;
	private ICommand toolbarPauseCommand;
	private ICommand toolbarRecordCommand;

	
	// Debug
	private UsageDisplay usage;
	
	
	public SynthFrame() {
		this.initComponents();
		this.configureView();
		this.defineCallbacks();
	}

	/**
	 * Initialize the frame's components
	 */
	private void initComponents() {
		// instanciation de la barre de menu
		mainMenuBar = new JMenuBar();

		// -------------------------- File Menu --------------------------
		menuFile = new JMenu("File");

//		this.add(new UsageDisplay());
		// instanciation des items
		menuItemNew = new JMenuItem("New");
		menuItemOpen = new JMenuItem("Open");
		menuItemSave = new JMenuItem("Save");
		menuItemQuit = new JMenuItem("Quit");

		// ajout des items au menu File
		menuFile.add(menuItemNew);
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemSave);
		menuFile.add(menuItemQuit);
		
		// -------------------------- Help Menu --------------------------
		menuHelp = new JMenu("Help");

		// instanciation des items
		menuItemDoc = new JMenuItem("Documentation");
		menuItemAbout = new JMenuItem("About");

		// ajout des items au menu Help
		menuHelp.add(menuItemDoc);
		menuHelp.add(menuItemAbout);
		
		// ajout des menus a la barre de menus
		mainMenuBar.add(menuFile);
		mainMenuBar.add(menuHelp);
		this.setJMenuBar(mainMenuBar);

		// instanciation des boutons de la toolbar
		Container frameContainer = getContentPane();
		frameContainer.setLayout(new BorderLayout());
		for (int i = 0; i < buttonLabels.length; ++i) {
			try {
				Image img = ImageIO.read( new File(iconFiles[i]) );
				icons[i] = img.getScaledInstance(25, 25, Image.SCALE_DEFAULT);
			} catch (IOException e) {
				e.printStackTrace();
			}
			buttons[i] = new JButton(new ImageIcon(icons[i]));
			buttons[i].setToolTipText(buttonLabels[i]);
			toolBar.add(buttons[i]);
		}

		frameContainer.add(BorderLayout.NORTH, toolBar);
		
		
		// Debug
		usage = new UsageDisplay();
		frameContainer.add(BorderLayout.SOUTH, usage);
	}

	/**
	 * Configure the frame's components
	 */
	private void configureView() {
		// parametrage de la JFrame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1400, 880);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height
				/ 2 - this.getSize().height / 2);
		this.setResizable(true);

		// ajout des raccourcis clavier au elements du menu
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
		
		buttons[BUTTON_DEFAULT].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolbarDefaultCommand != null)
					toolbarDefaultCommand.execute();
			}
		});
		
		buttons[BUTTON_WIRE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolbarWireCommand != null)
					toolbarWireCommand.execute();
			}
		});

		buttons[BUTTON_MODULE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolbarModuleCommand != null)
					toolbarModuleCommand.execute();
			}
		});
		

		buttons[BUTTON_PLAY].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolbarPlayCommand != null)
					toolbarPlayCommand.execute();
			}
		});
		

		buttons[BUTTON_PAUSE].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolbarPauseCommand != null)
					toolbarPauseCommand.execute();
			}
		});
		

		buttons[BUTTON_RECORD].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (toolbarRecordCommand != null)
					toolbarRecordCommand.execute();
			}
		});
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.istic.synthlab.ISynthFrame#displaySynth(fr.istic.synthlab.presentation
	 * .IPSynthesizer)
	 */
	@Override
	public void displaySynth(IPSynthesizer presentation) {
		pres = presentation;
		this.add((PSynthesizer) pres);
		this.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.istic.synthlab.ISynthFrame#quitSynth()
	 */
	@Override
	public void quitSynth() {
		this.setVisible(false);
	}

	/**
	 * @param newSynthCommand
	 *            the newSynthCommand to set
	 */
	public void setNewSynthCommand(ICommand newSynthCommand) {
		this.newSynthCommand = newSynthCommand;
	}

	/**
	 * @param saveSynthCommand
	 *            the saveSynthCommand to set
	 */
	public void setSaveSynthCommand(ICommand saveSynthCommand) {
		this.saveSynthCommand = saveSynthCommand;
	}

	/**
	 * @param openSynthCommand
	 *            the openSynthCommand to set
	 */
	public void setOpenSynthCommand(ICommand openSynthCommand) {
		this.openSynthCommand = openSynthCommand;
	}

	/**
	 * @param quitSynthCommand
	 *            the quitSynthCommand to set
	 */
	public void setQuitSynthCommand(ICommand quitSynthCommand) {
		this.quitSynthCommand = quitSynthCommand;
	}

	/**
	 * @param docSynthCommand
	 *            the docSynthCommand to set
	 */
	public void setDocSynthCommand(ICommand docSynthCommand) {
		this.docSynthCommand = docSynthCommand;
	}

	/**
	 * @param aboutSynthCommand
	 *            the aboutSynthCommand to set
	 */
	public void setAboutSynthCommand(ICommand aboutSynthCommand) {
		this.aboutSynthCommand = aboutSynthCommand;
	}

	/**
	 * @param toolbarDefaultCommand
	 *            the toolbarDefaultCommand to set
	 */
	public void setToolbarDefaultCommand(ICommand toolbarDefaultCommand) {
		this.toolbarDefaultCommand = toolbarDefaultCommand;
	}
	
	/**
	 * @param toolbarDefaultWire
	 *            the toolbarWireCommand to set
	 */
	public void setToolbarWireCommand(ICommand toolbarWireCommand) {
		this.toolbarWireCommand = toolbarWireCommand;
	}
	
	/**
	 * @param toolbarModuleCommand
	 *            the toolbarModuleCommand to set
	 */
	public void setToolbarModuleCommand(ICommand toolbarModuleCommand) {
		this.toolbarModuleCommand = toolbarModuleCommand;
	}
	
	/**
	 * @param toolbarModuleCommand
	 *            the toolbarModuleCommand to set
	 */
	public void setToolbarPlayCommand(ICommand toolbarPlayCommand) {
		this.toolbarPlayCommand = toolbarPlayCommand;
	}
	
	/**
	 * @param toolbarModuleCommand
	 *            the toolbarModuleCommand to set
	 */
	public void setToolbarPauseCommand(ICommand toolbarPauseCommand) {
		this.toolbarPauseCommand = toolbarPauseCommand;
	}

	/**
	 * @param toolbarModuleCommand
	 *            the toolbarModuleCommand to set
	 */
	public void setToolbarRecordCommand(ICommand toolbarRecordCommand) {
		this.toolbarRecordCommand = toolbarRecordCommand;
	}


}
