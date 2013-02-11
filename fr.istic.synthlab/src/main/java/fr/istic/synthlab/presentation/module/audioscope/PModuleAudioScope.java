package fr.istic.synthlab.presentation.module.audioscope;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JPanel;

import com.jsyn.scope.AudioScopeModel;
import com.jsyn.scope.swing.AudioScopeView;

import fr.istic.synthlab.controller.module.audioscope.ICModuleAudioScope;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.controller.port.ICOutputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.port.POutputPort;

/**
 * Presentation for a OUT module
 */
public class PModuleAudioScope extends APModule implements IPModuleAudioScope {

	private static final long serialVersionUID = -8519084219674310285L;

	private ICModuleAudioScope ctrl;
	private AudioScopeModel model;
	
	private PInputPort inputPort;
	private POutputPort outputPort;
	
	

	private int width;
	private int height;

	/**
	 * @param control
	 */
	public PModuleAudioScope(ICModuleAudioScope control) {
		super(control);
		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		JPanel panelScope= new JPanel();
		JPanel panelPort = new JPanel();
		
		panelScope.setOpaque(false);
		panelPort.setOpaque(false);

		AudioScopeView scope = new AudioScopeView();
		model = ctrl.getModel();
		scope.setModel(model);
		scope.setControlsVisible(false);
		panelScope.add(scope);

		inputPort = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		outputPort = (POutputPort) ((ICOutputPort) ctrl.getOutput()).getPresentation();
		panelPort.add(inputPort);
		panelPort.add(outputPort);

		this.setAutoscrolls(true);
		

		super.setWidth(350);
		super.setHeigth(350);
		
		Dimension size = new Dimension(super.getWidth(), super.getHeigth());
		this.setSize(size);
		this.setPreferredSize(size);
		
		this.addTitleBar();
		this.addPanel(panelScope, 350, 100);
		this.addPanel(panelPort, 350, 100);

	}

	private void defineCallbacks() {
	}
	

	@Override
	public ICModuleAudioScope getControl() {
		return ctrl;
	}

}
