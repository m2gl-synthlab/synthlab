package fr.istic.synthlab.presentation.impl;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.presentation.IPInputPort;

public class PInputPort extends JPanel implements IPInputPort {

	private static final long serialVersionUID = -3189854166979295463L;

	public static int width = 80;
	public static int height = 80;
	private ICInputPort ctrl;
	private JLabel image;
	
	

	public PInputPort(ICInputPort control) {
		ctrl = control;

		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setSize(width, height);
		JPanel pane = new JPanel();
		image = new JLabel(new ImageIcon("res/input.png"));
		pane.setLayout(new BorderLayout());
		pane.add(image, BorderLayout.CENTER);
		add(pane);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(ctrl.getName()));
	}

	private void defineCallbacks() {
		this.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("PInputPort clicked");
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
				((PModule)((ICModule)getControl().getModule()).getPresentation()).dispatchEvent(e);
			}
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
	}

	@Override
	public ICInputPort getControl() {
		return ctrl;
	}
	
	public void c2pSetName() {
		this.setBorder(BorderFactory.createTitledBorder(ctrl.getName()));
	}



}
