package fr.istic.synthlab.presentation.module;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.presentation.synthesizer.IPSynthesizer;
import fr.istic.synthlab.presentation.synthesizer.PSynthesizer;

/**
 * Abstract class for a module presentation
 */
public abstract class APModule extends JPanel implements IPModule {

	private static final long serialVersionUID = -7353924524014867459L;

	private ICModule ctrl;

	int dX;
	int dY;

	// TODO : Put height and width here

	public APModule(ICModule control) {
		super();
		// super(control.getName(), false, true, false, false);
		this.ctrl = control;
		
		this.setFocusable(true);
		this.setVisible(true);
		
		IPSynthesizer presSynth = ((ICSynthesizer)ctrl.getSynthesizer()).getPresentation();
		((JLayeredPane) presSynth).setLayer(this, 0, -1);
		
		
		// this.addInternalFrameListener(new SimpleInternalFrameListener() {
		// @Override
		// public void internalFrameClosing(InternalFrameEvent e) {
		// ctrl.p2cClosing();
		// }
		// });

		this.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				System.out.println("focus lost");
				IPSynthesizer presSynth = ((ICSynthesizer)ctrl.getSynthesizer()).getPresentation();
				((JLayeredPane) presSynth).setLayer(APModule.this, 0, -1);
			}

			@Override
			public void focusGained(FocusEvent e) {
				System.out.println("focus gained");
				IPSynthesizer presSynth = ((ICSynthesizer)ctrl.getSynthesizer()).getPresentation();
				for (IWire w : ctrl.getWires()) {
					if (w != null) {
						// TODO : Set wire Z position to this Z position+1
						((ICWire) w).getPresentation().setOnTop(true);
					}
				}
				((JLayeredPane) presSynth).setLayer(APModule.this, 0, 0);
			}
		});

		// deplacement du module
		this.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent event) {
			}

			@Override
			public void ancestorRemoved(AncestorEvent event) {
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
				List<IWire> wires = ctrl.getWires();
				for (IWire wire : wires) {
					if (wire != null) {
						((ICWire) wire).getPresentation().updateDisplay();
					}
				}
			}
		});

		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				((PSynthesizer) ((ICSynthesizer) getControl().getSynthesizer())
						.getPresentation()).dispatchEvent(e);
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getLocationOnScreen().x - dX,
						e.getLocationOnScreen().y - dY);
				dX = e.getLocationOnScreen().x - getX();
				dY = e.getLocationOnScreen().y - getY();
			}
		});

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				APModule.this.requestFocus();
				if (contains(e.getPoint())) {
					dX = e.getLocationOnScreen().x - getX();
					dY = e.getLocationOnScreen().y - getY();
				}
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

}
