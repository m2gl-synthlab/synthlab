package fr.istic.synthlab.presentation.impl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.presentation.IPOutputPort;

public class POutputPort extends JPanel implements IPOutputPort {
	
	private static final long serialVersionUID = 4664436294243269232L;

	public static int width = 80;
	public static int height = 80;
	
	private ICOutputPort ctrl;

	private JLabel image;

	public POutputPort(ICOutputPort control) {
		ctrl = control;

		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setSize(width, height);
		
		image = new JLabel(new ImageIcon("res/output.png"));
		add(image);
		
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(ctrl.getName()));
	}

	private void defineCallbacks() {
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("POutputPort clicked");
				ctrl.p2cConnect();
			}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
			@Override
			public void mousePressed(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				((APModule)((ICModule)getControl().getModule()).getPresentation()).dispatchEvent(e);
			}
			@Override
			public void mouseDragged(MouseEvent e) {
			}
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
}
