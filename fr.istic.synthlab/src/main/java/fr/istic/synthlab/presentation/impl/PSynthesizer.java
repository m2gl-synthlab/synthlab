package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;

import com.alee.laf.desktoppane.WebDesktopPane;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.IPWire;

public class PSynthesizer extends WebDesktopPane implements IPSynthesizer {

	private static final long serialVersionUID = -1444696064954307756L;
	private ICSynthesizer ctrl;

	private List<IPModule> modules;
	
	
	public PSynthesizer(ICSynthesizer control) {
		super();
		ctrl = control;
		modules = new ArrayList<IPModule>();
		
		configView();
		defineCallbacks();
	}
	
	private void configView() {
		this.setBackground(Color.DARK_GRAY);
	}

	private void defineCallbacks() {

		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// Gestion du currentWire
				IInputPort input = ctrl.getCurrentWire().getInput();
				IOutputPort output = ctrl.getCurrentWire().getOutput();
				
				Point offset = ((Component) e.getSource()).getLocation();

				Point mouse = e.getPoint();
				
				
				Point coord = new Point(mouse.x + offset.x, mouse.y + offset.y);
				
				if(input == null && output != null){
					((ICWire)getControl().getCurrentWire()).getPresentation().setInputPoint(coord);
				}
				if(output == null && input != null){
					((ICWire)getControl().getCurrentWire()).getPresentation().setOutputPoint(coord);
				}
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
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
		validate();
		repaint();
	}

	@Override
	public void c2pStop() {
		validate();
		repaint();
	}

	@Override
	public void c2pAddModule(IPModule module) {
		((JInternalFrame) module).setVisible(true);
		this.add((JInternalFrame) module,0);

		//TODO : beurk positionnement à la main
		((JInternalFrame)module).setBounds(((modules.size())*(module.getWidth()+5)), 5, module.getWidth(), module.getHeight());
		
		modules.add(module);
		((JInternalFrame)module).validate();
		((JInternalFrame)module).repaint();
		
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