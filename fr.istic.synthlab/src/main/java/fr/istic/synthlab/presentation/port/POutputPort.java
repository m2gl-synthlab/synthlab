package fr.istic.synthlab.presentation.port;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.util.SimpleMouseListener;

public class POutputPort extends JPanel implements IPOutputPort {

	private static final long serialVersionUID = 4664436294243269232L;
	private static final int CLICK_STATE_DEFAULT = 0;
	private static final int CLICK_STATE_ALLOWED = 1;
	private static final int CLICK_STATE_NOT_ALLOWED = 2;
	
	public static int width = 80;
	public static int height = 80;

	private ICOutputPort ctrl;

	private int clickState; 
	public POutputPort(ICOutputPort control) {
		ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setOpaque(false);
		this.setSize(width, height);
		this.setPreferredSize(this.getSize());
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel name = new JLabel(ctrl.getName());
		this.add(name);
	}

	private void defineCallbacks() {
		
		/** Gestion du click */
		this.addMouseListener(new SimpleMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Math.pow((e.getX() - 40), 2) + Math.pow((e.getY() - 40), 2) < Math
						.pow(15, 2)) {
					if (ctrl.getWire() != null) {
						ctrl.p2cDisconnect();
					} else {
						ctrl.p2cConnect();
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				clickState=CLICK_STATE_DEFAULT;
				repaint();
				validate();
			}
		});

		/** Gestion du déplacement de la souris */
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				// Sert pour le déplacement du cable au dessus des elements
				((APModule) ((ICModule) getControl().getModule())
						.getPresentation()).dispatchEvent(e);
				if (Math.pow((e.getX() - 40), 2) + Math.pow((e.getY() - 40), 2) < Math.pow(15, 2)) {
					ctrl.p2cCanConnect();
				} else {
					clickState=CLICK_STATE_DEFAULT;
					repaint();
					validate();
				}
			}
			@Override
			public void mouseDragged(MouseEvent e) {}
		});
	}

	@Override
	public ICOutputPort getControl() {
		return ctrl;
	}

	@Override
	public void c2pSetName() {
		this.setBorder(BorderFactory.createTitledBorder(ctrl.getName()));
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2));
		g2d.drawOval(40 - 15, 40 - 15, 30, 30);
		
		if(clickState == CLICK_STATE_ALLOWED){
			g2d.setColor(Color.GREEN);
		} else if(clickState == CLICK_STATE_NOT_ALLOWED){
			g2d.setColor(Color.RED);
		} else {
			g2d.setColor(new Color(184, 0, 18));
		}
		
		g2d.fillOval(40 - 14, 40 - 14, 28, 28);

		super.paint(g2d);
	}

	@Override
	public void c2pClickAllowed() {
		clickState = CLICK_STATE_ALLOWED;
		repaint();
		validate();
	}

	@Override
	public void c2pClickNotAllowed() {
		clickState = CLICK_STATE_NOT_ALLOWED;
		repaint();
		validate();
	}
}
