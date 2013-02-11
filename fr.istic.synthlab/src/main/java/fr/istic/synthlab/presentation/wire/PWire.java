package fr.istic.synthlab.presentation.wire;

import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.istic.synthlab.abstraction.synthesizer.Synthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.presentation.port.IPInputPort;
import fr.istic.synthlab.presentation.port.IPOutputPort;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;
import fr.istic.synthlab.presentation.synthesizer.PSynthesizer;

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
	private ICSynthesizer synth = null;

	/**
	 * @param control
	 */
	public PWire(ICWire control) {
		this.ctrl = control;
		synth = (ICSynthesizer) Synthesizer.getInstance();
		configView();
		defineCallbacks();
	}

	private void configView() {
		setOpaque(false);
	}

	private void defineCallbacks() {
		// DO NOT PUT ANYTHING HERE !!!
	}

	@Override
	public ICWire getControl() {
		return ctrl;
	}

	@Override
	public void c2pConnectOut(IPOutputPort outputPort) {
		this.outputPort = (POutputPort) outputPort;
		if (inputPort != null) {
			setVisible(true);
			updateDisplay();
		}
	}

	@Override
	public void c2pConnectIn(IPInputPort inputPort) {
		this.inputPort = (PInputPort) inputPort;
		if (outputPort != null) {
			setVisible(true);
			updateDisplay();
		}
	}

	@Override
	public void updateDisplay() {
		
		if (outputPort != null) {
			posOutput = new Point(outputPort.getX() + (POutputPort.width / 2), outputPort.getY()+ (POutputPort.height / 2));

			Component c2 = outputPort;
			while (!(c2.getParent() instanceof PSynthesizer)) {
				posOutput.x += c2.getParent().getX();
				posOutput.y += c2.getParent().getY();
				c2 = c2.getParent();
			}
		}

		if (inputPort != null) {
			posInput = new Point(inputPort.getX() + (PInputPort.width / 2), inputPort.getY() + + (PInputPort.height / 2));

			Component c = inputPort;
			while (!(c.getParent() instanceof PSynthesizer)) {
				posInput.x += c.getParent().getX();
				posInput.y += c.getParent().getY();
				c = c.getParent();
			}
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
//		setBounds(x + (POutputPort.width / 2), y + (POutputPort.height / 2), width, height);
		setBounds(x, y , width, height);

		IPSynthesizer presSynth = synth.getPresentation();
		((JLayeredPane) presSynth).setLayer(this, 0, 0);
		
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
			synth.setCurrentWire(getControl());
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
			synth.setCurrentWire(getControl());
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
	
	public void setOnTop(boolean isOnTop){
		IPSynthesizer presSynth = synth.getPresentation();
		if(isOnTop){
			((JLayeredPane) presSynth).setLayer(this, 0, 0);
		} else {
			((JLayeredPane) presSynth).setLayer(this, 0, -1);
		}
		
		repaint();
		validate();
	}

}