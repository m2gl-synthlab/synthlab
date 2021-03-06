package fr.istic.synthlab.presentation.port;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JLabel;

import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.presentation.util.SimpleMouseListener;

public class POutputPort extends PPort implements IPOutputPort {

	private static final long serialVersionUID = 4664436294243269232L;

	public static final Color DEFAULT_COLOR = new Color(81, 107, 163);
	public static final Color DEFAULT_STROKE_COLOR = Color.BLACK;

	private ICOutputPort ctrl;

	private JLabel name;

	public POutputPort(ICOutputPort control) {
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
		this.name = new JLabel(ctrl.getName(), JLabel.CENTER);
		this.add(name);
		setFillColor(DEFAULT_COLOR);
		setStrokeColor(DEFAULT_STROKE_COLOR);
	}

	private void defineCallbacks() {

		/** Gestion du click */
		this.addMouseListener(new SimpleMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Math.pow((e.getX() - (WIDTH) / 2), 2) + Math.pow((e.getY() - (HEIGHT) / 2), 2) < Math.pow(15, 2)) {
					ctrl.p2cMouseClicked();
				} else {
					setFillColor(DEFAULT_COLOR);
					setStrokeColor(DEFAULT_STROKE_COLOR);
				}
			}
		});

		/** Gestion du déplacement de la souris */
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (Math.pow((e.getX() - (WIDTH) / 2), 2) + Math.pow((e.getY() - (HEIGHT) / 2), 2) < Math.pow(10, 2)) {
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
	public ICOutputPort getControl() {
		return ctrl;
	}

	@Override
	public void c2pNameChanged() {
		this.name = new JLabel(ctrl.getName(), JLabel.CENTER);
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
	}
}
