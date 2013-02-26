package fr.istic.synthlab.presentation.port;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public abstract class PPort extends JPanel implements IPPort {

	private static final long serialVersionUID = 7146414541790834306L;

	public static final Color CONNECTION_ALLOWED_COLOR = new Color(0, 160, 0);
	public static final Color CONNECTION_NOT_ALLOWED_COLOR = new Color(160, 0, 0);

	private Color strokeColor = Color.BLACK;
	private Color fillColor = Color.WHITE;

	public static final int WIDTH = 60;
	public static final int HEIGHT = 70;
	public static final int RADIUS = 25;

	@Override
	public void setStrokeColor(Color c) {
		this.strokeColor = c;
		repaint();
		validate();
	}

	@Override
	public Color getStrokeColor() {
		return this.strokeColor;
	}

	@Override
	public void setFillColor(Color c) {
		this.fillColor = c;
		repaint();
		validate();
	}

	@Override
	public Color getFillColor() {
		return this.fillColor;
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Fond
		g2d.setColor(fillColor);
		g2d.fillOval((WIDTH - RADIUS) / 2, (HEIGHT - RADIUS) / 2, RADIUS, RADIUS);

		// Contour
		g2d.setColor(strokeColor);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawOval((WIDTH - RADIUS) / 2, (HEIGHT - RADIUS) / 2, RADIUS, RADIUS);

		if (getControl().isInUse()) {

			// Fond
			g2d.setColor(Color.BLACK);
			g2d.fillOval((WIDTH - 14) / 2, (HEIGHT - 14) / 2, 14, 14);

			// Contour
			g2d.setColor(Color.GRAY);
			g2d.setStroke(new BasicStroke(2));
			g2d.drawOval((WIDTH - 14) / 2, (HEIGHT - 14) / 2, 13, 13);

		}

		super.paint(g2d);
	}

	@Override
	public void c2pConnectionAttemptFailed() {
		new Thread() {
			@Override
			public void run() {
				super.run();
				Color c = getFillColor();
				for (int i = 0; i < 2; i++) {
					setFillColor(CONNECTION_NOT_ALLOWED_COLOR);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					setFillColor(c);
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
