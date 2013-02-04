package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import com.alee.laf.panel.WebPanel;

import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.IPWire;

public class PSynthesizer extends JLayeredPane implements IPSynthesizer {

	private static final long serialVersionUID = -1444696064954307756L;
	private ICSynthesizer ctrl;

	private List<IPModule> modules;
	private JPanel modulePanel;
	
	
	public PSynthesizer(ICSynthesizer control) {
		super();
		ctrl = control;
		modules = new ArrayList<IPModule>();
		modulePanel = new JPanel();
		
		configView();
		defineCallbacks();
		
	}
	


	private void configView() {
		modulePanel.setOpaque(false);
		modulePanel.setLayout(null);
		modulePanel.setPreferredSize(new Dimension(1400, 500));
		modulePanel.setBounds(15, 15, 1400, 500);
		this.add(modulePanel, new Integer(0));
	}

	private void defineCallbacks() {
	}
	
	@Override
	public ICSynthesizer getControl() {
		return ctrl;
	}

	@Override
	public void start() {
		this.getControl().p2cStart();
	}

	@Override
	public void stop() {
		this.getControl().p2cStop();
	}

	@Override
	public void addModule(IPModule module) {
		this.getControl().p2cAddModule(module.getControl());
	}

	@Override
	public void removeModule(IPModule module) {
		this.getControl().p2cRemoveModule(module.getControl());
	}

	@Override
	public void c2pStart() {
		this.setBackground(Color.GREEN);
		validate();
		repaint();
	}

	@Override
	public void c2pStop() {
		this.setBackground(Color.RED);
		validate();
		repaint();
	}

	@Override
	public void c2pAddModule(IPModule module) {
		
		modulePanel.add((WebPanel) module);

		//TODO : beurk positionnement à la main
		((WebPanel)module).setBounds(((modules.size())*(module.getWidth()+5)), 5, module.getWidth(), module.getHeight());
		
		modules.add(module);
		((WebPanel)module).validate();
		((WebPanel)module).repaint();
		
		validate();
		repaint();
	}

	@Override
	public void c2pAddModuleOk(IPModule module) {
		
	}
	
	int i=0;
	@Override
	public void c2pAddWire(IPWire wire) {
		this.add((PWire) wire, new Integer(++i));
		System.out.println("c2pAddWire Adding wire");
		validate();
		repaint();
	}

	@Override
	public void c2pRemoveModuleOk(IPModule module) {
		this.remove((PModule) module);
		validate();
		repaint();
	}

}