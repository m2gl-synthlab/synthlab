package fr.istic.synthlab.presentation.module.audioscope;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
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
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		this.add(panelScope, c);
		
		c.gridy = 2;
		this.add(panelPort, c);

		Dimension size = new Dimension(600, 400);
		this.setPreferredSize(size);

		// TODO : Not sure if it's the better way to define the size...
		width = size.width;
		height = size.height;

	}

	private void defineCallbacks() {
	}
	
	
	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	@Override
	public ICModuleAudioScope getControl() {
		return ctrl;
	}

}
