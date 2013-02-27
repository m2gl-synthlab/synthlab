package fr.istic.synthlab.abstraction.module.rec;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jsyn.unitgen.UnitGenerator;
import com.jsyn.util.WaveRecorder;

import fr.istic.synthlab.abstraction.filter.AttenuationFilter;
import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.util.Convert;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;
import fr.istic.synthlab.factory.impl.PACFactory;

/**
 * OUT Module Send the input attenuated signal to the sound card.
 */
public class ModuleREC extends AModule implements IModuleREC {

	private static final String IN_NAME = "In";

	protected static final boolean DEFAULT_STATE_RECORDING = false;

	// Dernier fichier enregistré
	private File wavFile;

	private WaveRecorder rec;
	private AttenuationFilter attenuator;

	private IInputPort in;
	private ISynthesizer synth;

	public ModuleREC(ISynthesizer synth) {
		super(synth, MODULE_NAME);

		this.synth = synth;

		this.attenuator = new AttenuationFilter();

		this.in = PACFactory.getFactory().newInputPort(synth, this, IN_NAME, attenuator.input);
		this.setAttenuation(0);

		// Set the default state
		setRecording(DEFAULT_STATE_RECORDING);

		addPort(in);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(attenuator);
		return generators;
	}

	@Override
	public void start() {

		if (isRecording()) {
			System.out.println("Start recording ...");
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
				this.wavFile = new File(((ICSynthesizer) synth).getPath()[1] + "_" + dateFormat.format(new Date()) + ".wav");
				this.rec = new WaveRecorder(synth.getJSyn(), wavFile);
				attenuator.output.connect(rec.getInput());
				this.rec.start();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void stop() {
		try {
			System.out.println("Stop recording");
			attenuator.output.disconnectAll();
			this.rec.stop();
			this.rec.close();
		} catch (Exception e) {
		}
	}

	@Override
	public void setAttenuation(double value) {
		getParameters().put("attenuation", (double) value);
		this.attenuator.setAttenuation(Convert.dB2V(value));
	}

	@Override
	public double getAttenuation() {
		return getParameter("attenuation");
	}

	@Override
	public IInputPort getInput() {
		return this.in;
	}

	@Override
	public void setRecording(boolean isRecording) {
		getParameters().put("isRecording", isRecording ? 1.0 : 0.0);
		if (isRecording()) {
			start();
		} else {
			stop();
		}
	}

	@Override
	public boolean isRecording() {
		return (getParameter("isRecording") == 1.0);
	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		if (in.getWire() != null) {
			if (!wires.contains(in.getWire()))
				wires.add(in.getWire());
		}
		return wires;
	}
}
