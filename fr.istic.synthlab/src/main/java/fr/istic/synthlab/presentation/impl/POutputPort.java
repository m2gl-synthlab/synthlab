package fr.istic.synthlab.presentation.impl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICOutputPort;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.util.SimpleMouseListener;

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
		this.addMouseListener(new SimpleMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				System.out.println("POutputPort clicked");
//				if(SwingUtilities.isRightMouseButton(e)){
//					System.out.println("right click!!");
//				}
				
				if(ctrl.getWire() != null){
					System.out.println("ctrl.getWire() != null");
					ctrl.p2cDisconnect();
				} else {
					System.out.println("ctrl.getWire() = null");
					ctrl.p2cConnect();
				}
			}
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
