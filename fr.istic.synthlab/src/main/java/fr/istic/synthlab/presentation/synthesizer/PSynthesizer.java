package fr.istic.synthlab.presentation.synthesizer;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.module.IPModule;
import fr.istic.synthlab.presentation.wire.IPWire;
import fr.istic.synthlab.presentation.wire.PWire;

public class PSynthesizer extends JLayeredPane implements IPSynthesizer {

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

		/** Gestion de l'affichage du cable en cours */
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// Gestion du currentWire
				if (ctrl.getCurrentWire() != null) {
					IInputPort input = ctrl.getCurrentWire().getInput();
					IOutputPort output = ctrl.getCurrentWire().getOutput();

					Point mouse = getMousePosition(true);
					if (input == null && output != null) {
						((ICWire) getControl().getCurrentWire())
								.getPresentation().setInputPoint(mouse);
					}
					if (output == null && input != null) {
						((ICWire) getControl().getCurrentWire())
								.getPresentation().setOutputPoint(mouse);
					}
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {}
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
		((APModule) module).setVisible(true);
		this.add((JPanel) module, 0);
		
		((APModule) module).setBounds(10, 10, module.getWidth(), module.getHeight());

		modules.add(module);
		((APModule) module).validate();
		((APModule) module).repaint();

		validate();
		repaint();
	}

	@Override
	public void c2pAddModuleOk(IPModule module) {

	}

	@Override
	public void c2pAddWire(IPWire wire) {
		this.add((PWire) wire);
		validate();
		repaint();
	}

	@Override
	public void c2pRemoveModuleOk(IPModule module) {
		System.out.println("remove");
		this.remove((APModule)module);
		validate();
		repaint();
	}

	@Override
	public void removeWire(IPWire pres) {
		this.remove((PWire) pres);
		validate();
		repaint();
	}

}