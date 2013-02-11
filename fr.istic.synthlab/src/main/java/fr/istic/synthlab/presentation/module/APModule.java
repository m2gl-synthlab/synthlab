package fr.istic.synthlab.presentation.module;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.alee.laf.panel.WebPanel;

import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;
import fr.istic.synthlab.presentation.synthesizer.PSynthesizer;
import fr.istic.synthlab.presentation.util.SimpleMouseListener;
import fr.istic.synthlab.presentation.util.TitleBar;

/**
 * Abstract class for a module presentation
 */
public abstract class APModule extends WebPanel implements IPModule {

	private static final long serialVersionUID = -7353924524014867459L;

	private ICModule ctrl;

	private int width;
	private int heigth;
	int dX;
	int dY;
	int x;
	int y;

	public APModule(ICModule control) {
		super();
		this.ctrl = control;

		configView();
		defineCallbacks();
	}
	
	private void configView(){
		
		this.setFocusable(true);
		this.setVisible(true);
		this.setUndecorated(false);
		this.setRound(7);
		this.setLayout(null);
	
		x=0;
		y=25;

		IPSynthesizer presSynth = ((ICSynthesizer) ctrl.getSynthesizer()).getPresentation();
		((JLayeredPane) presSynth).setLayer(this, 0, -1);
	}
	
	private void defineCallbacks() {
		
		/** Demande de focus */
		this.addMouseListener(new SimpleMouseListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				APModule.this.requestFocus();
				if (contains(e.getPoint())) {
					dX = e.getLocationOnScreen().x - getX();
					dY = e.getLocationOnScreen().y - getY();
				}
			}
		});
		
		/** Gestion du focus */
		this.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {}
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("focus gained");
				IPSynthesizer presSynth = ((ICSynthesizer) ctrl
						.getSynthesizer()).getPresentation();
				for (IWire w : ctrl.getWires()) {
					if (w != null) {
						// TODO : Set wire Z position to this Z position+1
						((ICWire) w).getPresentation().setOnTop(true);
					}
				}
				((JLayeredPane) presSynth).setLayer(APModule.this, 0, 0);
			}
		});

		/** Gestion des déplacements */
		this.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorMoved(AncestorEvent event) {
				List<IWire> wires = ctrl.getWires();
				for (IWire wire : wires) {
					if (wire != null) {
						((ICWire) wire).getPresentation().updateDisplay();
					}
				}
			}
			@Override
			public void ancestorAdded(AncestorEvent event) {}
			@Override
			public void ancestorRemoved(AncestorEvent event) {}
		});

		/** Gestion du déplacement du cable au dessus du module */
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				((PSynthesizer) ((ICSynthesizer) getControl().getSynthesizer()).getPresentation()).dispatchEvent(e);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getLocationOnScreen().x - dX,	e.getLocationOnScreen().y - dY);
				dX = e.getLocationOnScreen().x - getX();
				dY = e.getLocationOnScreen().y - getY();
			}
		});

	}
	
	/**
	 * Add a panel the given dimension automatically
	 * @param panel
	 * @param width
	 * @param height
	 */
	public void addPanel(JPanel panel, int width, int height){
		panel.setBounds(x, y, width, height);
		y += height;
		this.add(panel);
	}
	
	/**
	 * Add the title bar
	 */
	public void addTitleBar(){
		TitleBar bar = new TitleBar(ctrl);
		bar.setBounds(0, 5, width, 20);
		this.add(bar);
	}


	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

}
