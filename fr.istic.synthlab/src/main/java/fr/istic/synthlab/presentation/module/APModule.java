package fr.istic.synthlab.presentation.module;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.InternalFrameEvent;

import com.alee.laf.desktoppane.WebInternalFrame;

import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.controller.wire.ICWire;
import fr.istic.synthlab.presentation.synthesizer.PSynthesizer;
import fr.istic.synthlab.presentation.util.SimpleInternalFrameListener;

/**
 * Abstract class for a module presentation
 */
public abstract class APModule extends WebInternalFrame implements IPModule {

	private static final long serialVersionUID = -7353924524014867459L;
	
	private ICModule ctrl;

	//TODO : Put height and width here
	
	public APModule(ICModule control) {
		super(control.getName(), false, true, false, false);
		this.ctrl = control;
		this.setFocusable(true);
		
		this.addInternalFrameListener(new SimpleInternalFrameListener() {
			@Override
			public void internalFrameClosing(InternalFrameEvent e) {
				ctrl.p2cClosing();
			}
		});

		this.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				for(IWire w : ctrl.getWires()){
					if(w!=null){
						//TODO : Set wire Z position to this Z position+1
						((ICWire)w).getPresentation().setOnTop(true);
					}
				}
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
			}
		});
		
	}
	
}
