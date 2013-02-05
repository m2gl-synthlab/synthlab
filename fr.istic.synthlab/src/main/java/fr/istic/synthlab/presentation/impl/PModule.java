package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.alee.laf.desktoppane.WebInternalFrame;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.controller.ICSynthesizer;
import fr.istic.synthlab.controller.ICWire;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPParameter;

/**
 * Presentation of a module
 */
public class PModule extends WebInternalFrame implements IPModule {

	private static final long serialVersionUID = -8519084219674310285L;
	private ICModule ctrl;
	private JPanel panelParameters;
	private JPanel panelInputPorts;
	private JPanel panelOutputPorts;
	private int width;
	private int height;
	
	/**
	 * @param control
	 */
	public PModule(ICModule control) {
		super("Module " + control.getName(), false, true, false, false);
		
		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		this.setBackground(Color.GRAY);
		panelParameters = new JPanel();
		panelInputPorts = new JPanel();
		panelOutputPorts = new JPanel();
		this.setLayout(new BoxLayout(this.getContentPane(),BoxLayout.PAGE_AXIS));
		this.setAutoscrolls(true);
		this.getContentPane().add(panelParameters,0);
		this.getContentPane().add(panelInputPorts,1);
		this.getContentPane().add(panelOutputPorts,2);

		width = 350;
		height = 350;
	}
	
	private void defineCallbacks() {
		this.addAncestorListener(new AncestorListener() {
			@Override
			public void ancestorAdded(AncestorEvent event) {}
			@Override
			public void ancestorRemoved(AncestorEvent event) {}
			
			@Override
			public void ancestorMoved(AncestorEvent event) {
	            List<ICWire> wires = ctrl.getWires();
	            for(ICWire wire : wires){
	            	if(wire!=null){
	            		wire.getPresentation().updateDisplay();
	            	}
	            }
			}
		});
		
		this.addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				((PSynthesizer)((ICSynthesizer)getControl().getSynthesizer()).getPresentation()).dispatchEvent(e);
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	@Override
	public ICModule getControl() {
		return ctrl;
	}

	@Override
	public void addInputPort(IPInputPort presentation) {
		panelInputPorts.add((PInputPort) presentation);

	}

	@Override
	public void addOutputPort(IPOutputPort presentation) {
		panelOutputPorts.add((POutputPort) presentation);
	}

	@Override
	public void addParameter(IPParameter presentation) {
		panelParameters.add((Component) presentation);
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

}
