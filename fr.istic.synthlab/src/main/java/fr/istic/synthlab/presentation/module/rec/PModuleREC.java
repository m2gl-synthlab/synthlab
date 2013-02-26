package fr.istic.synthlab.presentation.module.rec;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.alee.extended.button.WebSwitch;
import com.jsyn.swing.DoubleBoundedRangeModel;

import fr.istic.synthlab.abstraction.module.rec.IModuleREC;
import fr.istic.synthlab.controller.module.rec.ICModuleREC;
import fr.istic.synthlab.controller.port.ICInputPort;
import fr.istic.synthlab.presentation.module.APModule;
import fr.istic.synthlab.presentation.port.PInputPort;
import fr.istic.synthlab.presentation.util.RotaryTextController;

/**
 * Presentation for a OUT module
 */
public class PModuleREC extends APModule implements IPModuleREC {

	private static final long serialVersionUID = -8519084219674310285L;

	private ICModuleREC ctrl;
	private DoubleBoundedRangeModel model;
	private WebSwitch recordSwitch;
	private PInputPort inputPort;

	/**
	 * @param control
	 */
	public PModuleREC(ICModuleREC control) {
		super(control);

		this.ctrl = control;
		configView();
		defineCallbacks();
	}

	private void configView() {
		JPanel panelGain = new JPanel();
		panelGain.setOpaque(false);
		JPanel panelInput = new JPanel();
		panelInput.setOpaque(false);
		JPanel panelRecord = new JPanel();
		panelRecord.setOpaque(false);

		model = new DoubleBoundedRangeModel(IModuleREC.PARAM_NAME_GAIN, 7200, -60, 12, ctrl.getAttenuation());
		RotaryTextController gainRotary = new RotaryTextController(model, 1);
		panelGain.add(gainRotary);

		recordSwitch = new WebSwitch();
		recordSwitch.setRound(4);
		recordSwitch.setSelected(ctrl.isRecording());
		panelRecord.add(recordSwitch);

		inputPort = (PInputPort) ((ICInputPort) ctrl.getInput()).getPresentation();
		panelInput.add(inputPort);

		this.setAutoscrolls(true);
		super.setWidth(100);
		super.setHeigth(220);

		Dimension size = new Dimension(super.getWidth(), super.getHeight());
		this.setSize(size);
		this.setPreferredSize(size);

		this.addTitleBar();
		this.addPanel(panelGain, 100, 100);
		this.addPanel(panelRecord, 100, 30);
		this.addPanel(panelInput, 100, 60);

	}

	private void defineCallbacks() {

		// Slider change listener
		model.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				ctrl.p2cGainChanged(model.getDoubleValue());
			}
		});

		recordSwitch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ctrl.p2cRecord();
			}
		});

	}

	@Override
	public void c2pRecord(boolean isRecording) {
		this.recordSwitch.setSelected(isRecording);
	}

	@Override
	public void c2pSetGainValue(double gain) {
		model.setDoubleValue(gain);
	}

	@Override
	public ICModuleREC getControl() {
		return ctrl;
	}

}
