package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;

import com.alee.laf.panel.WebPanel;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPParameter;

/**
 * Presentation of a module
 */
public class PModule extends WebPanel implements IPModule {

	private static final long serialVersionUID = -8519084219674310285L;
	private ICModule ctrl;
	private int width;
	private int height;

	private int px;
	private int py;

	private Point origin;

	/**
	 * @param control
	 */
	public PModule(ICModule control) {
		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setLayout(new FlowLayout());
		this.setUndecorated(false);
		this.setRound(20);
		width = 350;
		height = 350;
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (contains(e.getPoint())) {
					px = e.getLocationOnScreen().x - getX();
					py = e.getLocationOnScreen().y - getY();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// no use
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// no use
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// no use
			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
				// no use
			}

			@Override
		    public void mouseDragged(MouseEvent e) {
		            setLocation(e.getLocationOnScreen().x - px, e.getLocationOnScreen().y - py);
		            px = e.getLocationOnScreen().x - getX();
		            py = e.getLocationOnScreen().y - getY();
		            
		    }
		});
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void defineCallbacks() {
	}

	@Override
	public ICModule getControl() {
		return ctrl;
	}

	@Override
	public void addInputPort(IPInputPort presentation) {
		add((PInputPort) presentation);

	}

	@Override
	public void addOutputPort(IPOutputPort presentation) {
		add((POutputPort) presentation);
	}

	@Override
	public void addParameter(IPParameter presentation) {
		add((Component) presentation);
	}

}
