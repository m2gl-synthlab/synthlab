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
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.util.SimpleMouseListener;

public class PInputPort extends JPanel implements IPInputPort {

	private static final long serialVersionUID = -3189854166979295463L;
	private static final int CLICK_STATE_DEFAULT = 0;
	private static final int CLICK_STATE_ALLOWED = 1;
	private static final int CLICK_STATE_NOT_ALLOWED = 2;

	public static int width = 80;
	public static int height = 80;
	private ICInputPort ctrl;

	private int clickState; 

	public PInputPort(ICInputPort control) {
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
		this.addMouseListener(new SimpleMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Math.pow((e.getX() - 40), 2) + Math.pow((e.getY() - 40), 2) < Math
						.pow(15, 2)) {
					ctrl.p2cMouseClicked();
				}else{
					clickState=CLICK_STATE_DEFAULT;
					repaint();
					validate();
				}
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				((APModule)((ICModule)getControl().getModule()).getPresentation()).dispatchEvent(e);
				if (Math.pow((e.getX() - 40), 2) + Math.pow((e.getY() - 40), 2) < Math.pow(10, 2)) {
					ctrl.p2cCanConnect();
				} else {
					clickState=CLICK_STATE_DEFAULT;
					repaint();
					validate();
				}
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
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(2));
		g2d.drawOval(40 - 10, 40 - 10, 20, 20);
		
		if(clickState == CLICK_STATE_ALLOWED){
			g2d.setColor(Color.GREEN);
		} else if(clickState == CLICK_STATE_NOT_ALLOWED){
			g2d.setColor(Color.RED);
		} else {
			g2d.setColor(new Color(0, 184, 73));
		}
		
		g2d.fillOval(40 - 9, 40 - 9, 18, 18);

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
