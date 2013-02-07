package fr.istic.synthlab.presentation.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import javax.swing.JComponent;

public class LinesComponent extends JComponent {
	
	private static final long serialVersionUID = -2866492231404269355L;

	private static class Line {
		final Point start, end;
		
		final Color color;

		public Line(Point start, Point end, Color color) {
			this.start = start.getLocation();
			this.end = end.getLocation();
			this.color = color;
		}
	}

	private final LinkedList<Line> lines = new LinkedList<Line>();

	public void addLine(Point a, Point b) {
		addLine(a, b, Color.black);
	}

	public void addLine(Point a, Point b, Color color) {
		lines.add(new Line(a, b, color));
		repaint();
	}

	public void clearLines() {
		lines.clear();
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (Line line : lines) {
			g.setColor(line.color);
			g.drawLine(line.start.x, line.end.y, line.start.x, line.end.y);
		}
	}

}