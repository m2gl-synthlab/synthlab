package fr.istic.synthlab.presentation.impl;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.presentation.IPOutputPort;

public class POutputPort extends JPanel implements MouseListener, IPOutputPort {
	
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
		JPanel pane = new JPanel();
		image = new JLabel(new ImageIcon("res/output.png"));
		image.addMouseListener(this);
		pane.setLayout(new BorderLayout());
		pane.add(image, BorderLayout.CENTER);
		add(pane);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(ctrl.getName()));
	}

	private void defineCallbacks() {
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
	public void mouseClicked(MouseEvent e) {
		System.out.println("POutputPort clicked");
		
		ctrl.p2cConnect();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
