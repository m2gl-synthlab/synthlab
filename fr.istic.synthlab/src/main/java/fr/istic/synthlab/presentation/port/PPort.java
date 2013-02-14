package fr.istic.synthlab.presentation.port;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public abstract class PPort extends JPanel implements IPPort{

	private static final long serialVersionUID = 7146414541790834306L;

	private Color strokeColor = Color.BLACK;
	private Color fillColor = Color.WHITE;
	
	public static final int WIDTH = 80;
	public static final int HEIGHT = 80;
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
		g2d.fillOval((WIDTH-RADIUS)/2, (HEIGHT-RADIUS)/2, RADIUS, RADIUS);
		
		// Contour
		g2d.setColor(strokeColor);
		g2d.setStroke(new BasicStroke(2));
		g2d.drawOval((WIDTH-RADIUS)/2, (HEIGHT-RADIUS)/2, RADIUS, RADIUS);
		
		super.paint(g2d);
	}
	
}
