package fr.istic.synthlab.presentation.impl;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import com.alee.laf.desktoppane.WebInternalFrame;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPParameter;

public abstract class APModule extends WebInternalFrame implements IPModule {

	private static final long serialVersionUID = -7353924524014867459L;
	private ICModule ctrl;
	
	public APModule(ICModule control) {
		super(control.getName(), false, true, false, false);
		System.out.println("APModule initialized");
		this.ctrl = control;
		
		
//		this.addAncestorListener(new AncestorListener() {
//			@Override
//			public void ancestorAdded(AncestorEvent event) {}
//			@Override
//			public void ancestorRemoved(AncestorEvent event) {}
//			
//			@Override
//			public void ancestorMoved(AncestorEvent event) {
//	            List<ICWire> wires = ctrl.getWires();
//	            for(ICWire wire : wires){
//	            	if(wire!=null){
//	            		wire.getPresentation().updateDisplay();
//	            	}
//	            }
//			}
//		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseMoved(MouseEvent e) {
				((PSynthesizer)((ICSynthesizer)getControl().getSynthesizer()).getPresentation()).dispatchEvent(e);
			}
			@Override
			public void mouseDragged(MouseEvent e) {
			}
		});
		

	}
	

	
	@Override
	public void addInputPort(IPInputPort presentation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addOutputPort(IPOutputPort presentation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addParameter(IPParameter presentation) {
		// TODO Auto-generated method stub
		
	}

}
