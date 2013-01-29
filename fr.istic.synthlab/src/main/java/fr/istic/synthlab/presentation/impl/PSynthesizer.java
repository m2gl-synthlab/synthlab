package fr.istic.synthlab.presentation.impl;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPSynthesizer;

public class PSynthesizer extends JFrame implements IPSynthesizer, ActionListener, ItemListener {

	ICSynthesizer ctrl;
	
	public PSynthesizer(ICSynthesizer control){
		ctrl = control;
		//this.setLayout(new BorderLayout());
		

		
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
		JMenu menu1, menu2, submenu;
		JMenuItem plainTextMenuItem, textIconMenuItem, iconMenuItem, subMenuItem;
		JRadioButtonMenuItem rbMenuItem;
		JCheckBoxMenuItem cbMenuItem;
		mainMenuBar = new JMenuBar();
		menu1 = new JMenu("Menu 1");
		menu1.setMnemonic(KeyEvent.VK_M);
		mainMenuBar.add(menu1);
		// Creating the MenuItems
		plainTextMenuItem = new JMenuItem("Menu item with Plain Text",
				KeyEvent.VK_T);
		// can be done either way for assigning shortcuts
		// menuItem.setMnemonic(KeyEvent.VK_T);
		// Accelerators, offer keyboard shortcuts to bypass navigating the menu
		// hierarchy.
		plainTextMenuItem.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_1, ActionEvent.ALT_MASK));
		plainTextMenuItem.addActionListener(this);
		menu1.add(plainTextMenuItem);
		textIconMenuItem = new JMenuItem("Menu Item with Text & Image");
		textIconMenuItem.setMnemonic(KeyEvent.VK_B);
		textIconMenuItem.addActionListener(this);
		menu1.add(textIconMenuItem);
		// Menu Item with just an Image
		iconMenuItem = new JMenuItem();
		iconMenuItem.setMnemonic(KeyEvent.VK_D);
		iconMenuItem.addActionListener(this);
		menu1.add(iconMenuItem);
		menu1.addSeparator();
		// Radio Button Menu items follow a seperator
		ButtonGroup itemGroup = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem(
				"Menu Item with Radio Button");
		rbMenuItem.setSelected(true);
		rbMenuItem.setMnemonic(KeyEvent.VK_R);
		itemGroup.add(rbMenuItem);
		rbMenuItem.addActionListener(this);
		menu1.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem(
				"Menu Item 2 with Radio Button");
		itemGroup.add(rbMenuItem);
		rbMenuItem.addActionListener(this);
		menu1.add(rbMenuItem);
		menu1.addSeparator();
		// Radio Button Menu items follow a seperator
		cbMenuItem = new JCheckBoxMenuItem("Menu Item with check box");
		cbMenuItem.setMnemonic(KeyEvent.VK_C);
		cbMenuItem.addItemListener(this);
		menu1.add(cbMenuItem);
		cbMenuItem = new JCheckBoxMenuItem("Menu Item 2 with check box");
		cbMenuItem.addItemListener(this);
		menu1.add(cbMenuItem);
		menu1.addSeparator();
		// Sub Menu follows a seperator
		submenu = new JMenu("Sub Menu");
		submenu.setMnemonic(KeyEvent.VK_S);
		subMenuItem = new JMenuItem("Sub MenuItem 1");
		subMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				ActionEvent.CTRL_MASK));
		subMenuItem.addActionListener(this);
		submenu.add(subMenuItem);
		subMenuItem = new JMenuItem("Sub MenuItem 2");
		submenu.add(subMenuItem);
		subMenuItem.addActionListener(this);
		menu1.add(submenu);
		// Build second menu in the menu bar.
		menu2 = new JMenu("Menu 2");
		menu2.setMnemonic(KeyEvent.VK_N);
		mainMenuBar.add(menu2);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}
}