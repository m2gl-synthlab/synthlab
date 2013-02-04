package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPParameter;

/**
 * Presentation of a module
 */
public class PModule extends JPanel implements IPModule {

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

	public void paintComponent(Graphics g) {
		try {
			Image img = ImageIO.read(new File("res/synthe.png"));
			g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			// Pour une image de fond
			// g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void configView() {
		width = 350;
		height = 350;
		this.setSize(width, height);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(ctrl.getName()));
		this.setBackground(Color.GRAY);
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
