package fr.istic.synthlab.presentation.port;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.util.SimpleMouseListener;

public class PInputPort extends PPort implements IPInputPort, IPPort{

	private static final long serialVersionUID = -3189854166979295463L;
	
	public static final Color DEFAULT_COLOR = new Color(150,200,100);
	public static final Color DEFAULT_STROKE_COLOR = Color.BLACK;
	public static final Color CONNECTION_ALLOWED_COLOR = new Color(0,160,0);
	public static final Color CONNECTION_NOT_ALLOWED_COLOR = new Color(160,0,0);
	
	private ICInputPort ctrl;

	public PInputPort(ICInputPort control) {
		super();
		ctrl = control;

		configView();
		defineCallbacks();
		repaint();
		validate();
	}

	private void configView() {
		this.setOpaque(false);
		this.setSize(WIDTH, HEIGHT);
		this.setPreferredSize(this.getSize());
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		JLabel name = new JLabel(ctrl.getName());
		this.add(name);
		setFillColor(DEFAULT_COLOR);
		setStrokeColor(DEFAULT_STROKE_COLOR);
	}

	private void defineCallbacks() {
		this.addMouseListener(new SimpleMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Math.pow((e.getX() - (WIDTH)/2), 2) + Math.pow((e.getY() -(HEIGHT)/2), 2) < Math.pow(15, 2)) {
					ctrl.p2cMouseClicked();
				}else{
					setFillColor(DEFAULT_COLOR);
					setStrokeColor(DEFAULT_STROKE_COLOR);
				}
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				((APModule)((ICModule)getControl().getModule()).getPresentation()).dispatchEvent(e);
				if (Math.pow((e.getX() - (WIDTH)/2), 2) + Math.pow((e.getY() - (HEIGHT)/2), 2) < Math.pow(10, 2)) {
					ctrl.p2cMouseHover();
				} else {
					setFillColor(DEFAULT_COLOR);
					setStrokeColor(DEFAULT_STROKE_COLOR);
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
	
	@Override
	public void c2pNameChanged() {
		this.setBorder(BorderFactory.createTitledBorder(ctrl.getName()));
	}

	@Override
	public void c2pConnectionAllowed() {
		this.setStrokeColor(CONNECTION_ALLOWED_COLOR);
	}

	@Override
	public void c2pConnectionNotAllowed() {
		this.setStrokeColor(CONNECTION_NOT_ALLOWED_COLOR);
	}

	@Override
	public void c2pConnectionAttemptSucceed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void c2pConnectionAttemptFailed() {
		new Thread(){
			@Override
			public void run() {
				super.run();
				for(int i=0 ; i<2; i++){
					setFillColor(CONNECTION_NOT_ALLOWED_COLOR);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					setFillColor(DEFAULT_COLOR);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
			}
		}.start();
		
	}


}
