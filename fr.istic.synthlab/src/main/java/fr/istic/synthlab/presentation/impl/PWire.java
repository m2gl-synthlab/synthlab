package fr.istic.synthlab.presentation.impl;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JDesktopPane;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPSynthesizer;
import fr.istic.synthlab.presentation.IPWire;
import fr.istic.synthlab.presentation.util.SimpleMouseListener;

/**
 * Presentation for a Wire
 */
public class PWire extends JPanel implements IPWire {

	private static final long serialVersionUID = 433233331577188149L;

	private ICWire ctrl;

	private Point posInput;
	private Point posOutput;
	private int height = 0;
	private int width = 0;
	private PInputPort inputPort;
	private POutputPort outputPort;

	/**
	 * @param control
	 */
	public PWire(ICWire control) {
		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		setOpaque(false);
	}

	private void defineCallbacks() {
		this.addMouseListener(new SimpleMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
	}

	@Override
	public ICWire getControl() {
		return ctrl;
	}

	@Override
	public void c2pConnectOut(IPOutputPort outputPort) {
		// System.out.println("Connecting to out");
		this.outputPort = (POutputPort) outputPort;
		if (inputPort != null) {
			setVisible(true);
			updateDisplay();
		}
	}

	@Override
	public void c2pConnectIn(IPInputPort inputPort) {
		// System.out.println("Connecting to In");
		this.inputPort = (PInputPort) inputPort;
		if (outputPort != null) {
			setVisible(true);
			updateDisplay();
		}
	}

	@Override
	public void updateDisplay() {

		if (outputPort != null) {
			posOutput = new Point(outputPort.getX(), outputPort.getY());

			Component c2 = outputPort;
			while (!(c2.getParent() instanceof PSynthesizer)) {
				posOutput.x += c2.getParent().getX();
				posOutput.y += c2.getParent().getY();
				c2 = c2.getParent();
			}
		} else {

		}

		if (inputPort != null) {
			posInput = new Point(inputPort.getX(), inputPort.getY());

			Component c = inputPort;
			while (!(c.getParent() instanceof PSynthesizer)) {
				posInput.x += c.getParent().getX();
				posInput.y += c.getParent().getY();
				c = c.getParent();
			}
		} else {

		}

		int x = 0;
		int y = 0;

		if (posInput.x < posOutput.x) {
			x = posInput.x;
			width = posOutput.x - posInput.x;
		} else {
			x = posOutput.x;
			width = posInput.x - posOutput.x;
		}

		if (posInput.y < posOutput.y) {
			y = posInput.y;
			height = posOutput.y - posInput.y;
		} else {
			y = posOutput.y;
			height = posInput.y - posOutput.y;
		}

		setPreferredSize(new Dimension(width, height));
		setBounds(x + (POutputPort.width / 2), y + (POutputPort.height / 2),
				width, height);

		ICSynthesizer synth = (ICSynthesizer) outputPort.getControl()
				.getModule().getSynthesizer();
		IPSynthesizer presSynth = synth.getPresentation();
		((JDesktopPane) presSynth).setLayer(this, 0, 0);

		repaint();
		validate();
	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(3));

		if (posInput.x > posOutput.x) {
			if (posInput.y > posOutput.y) {
				g2.drawLine(0, 0, width, height);
			} else {
				g2.drawLine(0, height, width, 0);
			}
		} else {
			if (posInput.y > posOutput.y) {
				g2.drawLine(0, height, width, 0);
			} else {
				g2.drawLine(0, 0, width, height);
			}
		}
	}

	@Override
	public void c2pDisconnectIn(IPInputPort pInputPort) {
		// Si le cable est encore attaché a l'output
		if (getControl().getOutput() != null) {
			// On set le cable courrant du synthetiseur
			getControl().getInput().getModule().getSynthesizer()
					.setCurrentWire(getControl());
			inputPort = null;
			updateDisplay();
		} else {
			this.setVisible(false);
		}
	}

	@Override
	public void c2pDisconnectOut(IPOutputPort pOutputPort) {
		// Si le cable est encore attaché a l'output
		if (getControl().getInput() != null) {
			// On set le cable courrant du synthetiseur
			getControl().getOutput().getModule().getSynthesizer()
					.setCurrentWire(getControl());
			outputPort = null;
			updateDisplay();
		} else {
			this.setVisible(false);
		}
	}

	@Override
	public void setInputPoint(Point mouse) {
		posInput = mouse;
		updateDisplay();
	}

	@Override
	public void setOutputPoint(Point mouse) {
		posOutput = mouse;
		updateDisplay();
	}

}
