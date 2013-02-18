package fr.istic.synthlab.presentation.wire;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.QuadCurve2D;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

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
	private PInputPort inputPort;
	private POutputPort outputPort;
	private Color currentColor = null;

	private static boolean displayShadow = true;

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
		this.posInput = new Point(0, 0);
		this.posOutput = new Point(0, 0);
		this.currentColor = ctrl.getCurrentWireColor();
	}

	private void defineCallbacks() {
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

		// Si le port de sortie est branché
		if (outputPort != null) {
			posOutput = new Point(outputPort.getX() + (POutputPort.WIDTH / 2), outputPort.getY() + (POutputPort.HEIGHT / 2));

			Component c2 = outputPort;
			while (!(c2.getParent() instanceof PSynthesizer)) {
				posOutput.x += c2.getParent().getX();
				posOutput.y += c2.getParent().getY();
				c2 = c2.getParent();
			}
		}

		// Si le port d'entrée est branché
		if (inputPort != null) {
			posInput = new Point(inputPort.getX() + (PInputPort.WIDTH / 2), inputPort.getY() + (PInputPort.HEIGHT / 2));

			Component c = inputPort;
			while (!(c.getParent() instanceof PSynthesizer)) {
				posInput.x += c.getParent().getX();
				posInput.y += c.getParent().getY();
				c = c.getParent();
			}
		}

		// Calcule de la boite englobante
		int x, y, w = 0;

		if (posInput.x < posOutput.x) {
			x = posInput.x;
			w = posOutput.x - posInput.x;
		} else {
			x = posOutput.x;
			w = posInput.x - posOutput.x;
		}

		if (posInput.y < posOutput.y) {
			y = posInput.y;
		} else {
			y = posOutput.y;
		}

		if (getParent() == null) {
			JPanel pan = new JPanel();
			pan.add(this);
		}
		setBounds(x-10, y-10 , w+20, getParent().getHeight()); // On ajoute une marge pour éviter de couper le cable
		
		IPSynthesizer presSynth = ctrl.getSynthesizerPresentation();
		((JLayeredPane) presSynth).setLayer(this, 0, 0);

		repaint();
		validate();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;

		// Calcule de la boite englobante
		int w, h = 0;

		if (posInput.x < posOutput.x) {
			w = posOutput.x - posInput.x;
		} else {
			w = posInput.x - posOutput.x;
		}

		if (posInput.y < posOutput.y) {
			h = posOutput.y - posInput.y;
		} else {
			h = posInput.y - posOutput.y;
		}

		// Lisse l'affichage
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Calcul d'un point centrale
		Point mid = new Point(w / 2, h + w / 4);
		Point midShadow = new Point(w / 2, 10 + h + w / 3);

		// Création de la courbe
		QuadCurve2D curve = new QuadCurve2D.Double(posInput.getX() - getX(), posInput.getY() - getY(), mid.getX(), mid.getY(), posOutput.getX() - getX(),
				posOutput.getY() - getY());
		// Création de la courbe de l'ombre
		QuadCurve2D shadowCurve = new QuadCurve2D.Double(posInput.getX() - getX(), posInput.getY() - getY(), midShadow.getX(), midShadow.getY(), posOutput.getX() - getX(),
						posOutput.getY() - getY());


		// Si les ombres sont activées
		if(displayShadow ){
			// On dessine plusieurs couche d'ombre avec transparence
			g2.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			g2.setColor(new Color(100,100,100,30));
			g2.draw(shadowCurve);
			
			g2.setStroke(new BasicStroke(4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			g2.setColor(new Color(100,100,100,10));
			g2.draw(shadowCurve);
			
			g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
			g2.setColor(new Color(100,100,100,10));
			g2.draw(shadowCurve);
		}
		
		// Dessine la courbe en noir comme base
		g2.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		g2.setColor(Color.BLACK);
		g2.draw(curve);

		// Ajoute plusieurs traits de couleur avec transparence pour le dégradé 
		g2.setStroke(new BasicStroke(7, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		g2.setColor(new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), 60));
		g2.draw(curve);
		
		g2.setStroke(new BasicStroke(6, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		g2.setColor(new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), 100));
		g2.draw(curve);

		g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		g2.setColor(new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(),120));
		g2.draw(curve);
		
		// Ajoute un trait fin au centre
		g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		g2.setColor(new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue()));
		g2.draw(curve);
	}

	@Override
	public void c2pDisconnectIn(IPInputPort pInputPort) {
		// Si le cable est encore attaché a l'output
		if (getControl().getOutput() != null) {
			// On set le cable courrant du synthetiseur
			ctrl.setCurrentWire();
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
			ctrl.setCurrentWire();
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
		IPSynthesizer presSynth = ctrl.getSynthesizerPresentation();
		if(isOnTop){
			((JLayeredPane) presSynth).setLayer(this, 0, 0);
		} else {
			((JLayeredPane) presSynth).setLayer(this, 0, -1);
		}

		repaint();
		validate();
	}

	@Override
	public Color getColor() {
		return currentColor;
	}

	@Override
	public void setColor(Color color) {
		currentColor = color;
	}

}