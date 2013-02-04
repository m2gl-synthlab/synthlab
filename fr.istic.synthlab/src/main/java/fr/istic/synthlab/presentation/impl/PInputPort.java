package fr.istic.synthlab.presentation.impl;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICInputPort;
import fr.istic.synthlab.presentation.IPInputPort;

public class PInputPort extends JPanel implements MouseListener, IPInputPort {

	private static final long serialVersionUID = -3189854166979295463L;

	private ICInputPort ctrl;
	private JLabel image;
	
	

	public PInputPort(ICInputPort control) {
		ctrl = control;

		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setSize(100, 100);
		JPanel pane = new JPanel();
		image = new JLabel(new ImageIcon("res/input.png"));
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
	public ICInputPort getControl() {
		return ctrl;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("PInputPort clicked");
		ctrl.p2cConnect();
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
