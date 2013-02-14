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
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.module.IPModule;
import fr.istic.synthlab.presentation.util.SimpleMouseListener;
import fr.istic.synthlab.presentation.wire.IPWire;
import fr.istic.synthlab.presentation.wire.PWire;

public class PSynthesizer extends JLayeredPane implements IPSynthesizer {

	private static final long serialVersionUID = -1444696064954307756L;

	private List<IPModule> modules;

	public PSynthesizer() {
		super();
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
				if (CSynthesizer.getInstance().getCurrentWire() != null) {
					IInputPort input = CSynthesizer.getInstance().getCurrentWire().getInput();
					IOutputPort output = CSynthesizer.getInstance().getCurrentWire().getOutput();

					Point mouse = getMousePosition(true);
					if (input == null && output != null) {
						((ICWire) CSynthesizer.getInstance().getCurrentWire())
								.getPresentation().setInputPoint(mouse);
					}
					if (output == null && input != null) {
						((ICWire) CSynthesizer.getInstance().getCurrentWire())
								.getPresentation().setOutputPoint(mouse);
					}
				}
			}

			@Override
			public void mouseDragged(MouseEvent e) {
			}

		});

		this.addMouseListener(new SimpleMouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
				CSynthesizer.getInstance().p2cDisconnectCurrentWire();
			}
		});

	}

	@Override
	public void start() {
		CSynthesizer.getInstance().p2cStart();
	}

	@Override
	public void stop() {
		CSynthesizer.getInstance().p2cStop();
	}

	@Override
	public void addModule(IPModule module) {
		CSynthesizer.getInstance().p2cAddModule(module.getControl());
	}

	@Override
	public void removeModule(IPModule module) {
		CSynthesizer.getInstance().p2cRemoveModule(module.getControl());
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

		System.out.println(module.getPosition());
		
		int x =0;
		int y =0;
		
		if((module.getPosition().x==0)&&(module.getPosition().y==0)){
			x = 10;
			y = 10;
		} else {
			x = module.getPosition().x;
			y = module.getPosition().y;
		}
		
		((APModule) module).setBounds(x, y, module.getWidth(),
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

	@Override
	public void c2pAddWire(IPWire wire) {
		this.add((PWire) wire);
		validate();
		repaint();
	}

	@Override
	public void c2pRemoveModuleOk(IPModule module) {
		System.out.println("remove");
		this.remove((APModule) module);
		
		
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