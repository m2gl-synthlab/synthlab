package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import com.alee.laf.desktoppane.WebDesktopPane;

import fr.istic.synthlab.abstraction.IInputPort;
import fr.istic.synthlab.abstraction.IOutputPort;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.IPWire;
import fr.istic.synthlab.presentation.util.SimpleMouseListener;

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

		addMouseListener(new SimpleMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getPoint().x;
				int y = e.getPoint().y;
				e.translatePoint(
						-(PSynthesizer.this.getComponentAt(x, y).getLocation().x),
						-(PSynthesizer.this.getComponentAt(x, y).getLocation().y));

				Component clickedComponent = getComponentAt(x, y);
				if (clickedComponent != null
						&& JInternalFrame.class.isInstance(clickedComponent)) {
					JPanel panelPorts = (JPanel) ((JInternalFrame) clickedComponent).getContentPane()
							.getComponentAt(e.getPoint());
					e.translatePoint(-panelPorts.getLocation().x,
							-panelPorts.getLocation().y);

					Component panelPort = panelPorts.getComponentAt(e
							.getPoint());
					while (panelPort != null) {
						e.translatePoint(-panelPort.getLocation().x,
								-panelPort.getLocation().y);
						if ((panelPort.getComponentAt(e.getPoint()) != null)
								&& (panelPort.getComponentAt(e.getPoint()) instanceof JPanel)) {
							panelPort = panelPort.getComponentAt(e.getPoint());
						} else {
							break;
						}
					}

					panelPort.dispatchEvent(e);
				}
			}
		});

		addMouseMotionListener(new MouseMotionListener() {

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
			public void mouseDragged(MouseEvent e) {
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
		((APModule) module).setVisible(true);
		this.add((JInternalFrame) module, 0);

		((APModule) module).setBounds(10, 10, module.getWidth(),
				module.getHeight());

		modules.add(module);
		((APModule) module).validate();
		((APModule) module).repaint();

		validate();
		repaint();
	}

	@Override
	public void c2pAddModuleOk(IPModule module) {

	}

	int i = 0;

	@Override
	public void c2pAddWire(IPWire wire) {

		this.add((PWire) wire);
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

	@Override
	public void removeWire(IPWire pres) {
		this.remove((PWire) pres);
		validate();
		repaint();
	}

	public void clickAt(int x, int y, MouseEvent e) {
		e.translatePoint(-(this.getComponentAt(x, y).getLocation().x), -(this
				.getComponentAt(x, y).getLocation().y));
		JPanel panelPorts = (JPanel) ((JInternalFrame) getComponentAt(x, y))
				.getContentPane().getComponentAt(e.getPoint());
		e.translatePoint(-panelPorts.getLocation().x,
				-panelPorts.getLocation().y);
		JPanel panelPort = (JPanel) panelPorts.getComponentAt(e.getPoint());
		e.translatePoint(-panelPort.getLocation().x, -panelPort.getLocation().y);
		panelPort.dispatchEvent(e);
	}

}