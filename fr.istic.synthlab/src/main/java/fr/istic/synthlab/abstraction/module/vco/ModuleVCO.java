package fr.istic.synthlab.abstraction.module.vco;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.unitgen.PassThrough;
import com.jsyn.unitgen.SawtoothOscillator;
import com.jsyn.unitgen.SquareOscillator;
import com.jsyn.unitgen.TriangleOscillator;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.filter.FrequencyModulatorFilter;
import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.observer.Observer;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.port.Port;
import fr.istic.synthlab.abstraction.synthesizer.ISynthesizer;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleVCO extends AModule implements IModuleVCO, Observer<Port>{

	private static final String MODULE_NAME = "VCO";
	private static final String IN_MOD_FREQ_NAME = "fm";
	private static final String OUT_SAWTOOTH_NAME = "SawTooth";
	private static final String OUT_SQUARE_NAME = "Square";
	private static final String OUT_TRIANGLE_NAME = "Triangle";
	
	public static final String PARAM_OCTAVE_NAME = "Octave";
	public static final String PARAM_TONE_NAME = "Tone";
	
	// Générateur Perso
	private FrequencyModulatorFilter frequencyModulator;

	// Input & Output du module
	private IInputPort fm;
	private IOutputPort outputSquare;
	private IOutputPort outputTriangle;
	private IOutputPort outputSawtooth;

	// PassThrough
	private PassThrough passThrough;
	
	// Générateurs de fréquence JSyn
	private SquareOscillator vcoSquare;
	private TriangleOscillator vcoTriangle;
	private SawtoothOscillator vcoSawtooth;
	
	public ModuleVCO(ISynthesizer synth) {
		super(MODULE_NAME, synth);
		System.out.println("ModuleVCO initialized");
		
		// Création des générateurs JSyn
		this.vcoSquare = new SquareOscillator();
		this.vcoTriangle = new TriangleOscillator();
		this.vcoSawtooth = new SawtoothOscillator();
		
		// fm -> modulator -> passThrough -|-> generator.frequency
		
		// Création du modulateur de fréquence
		this.frequencyModulator = new FrequencyModulatorFilter(5);

		// Création d'un module distribuant la nouvelle fréquence
		this.passThrough = new PassThrough();
		
		// Création d'un port d'entrée sur le générateur perso
		this.fm = PACFactory.getFactory().newInputPort(this, IN_MOD_FREQ_NAME, frequencyModulator.input);
		this.fm.addObserver(this);
		
		// Création des ports de sortie des générateurs JSsyn
		this.outputSquare = PACFactory.getFactory().newOutputPort(this, OUT_SQUARE_NAME, vcoSquare.output);
		this.outputTriangle = PACFactory.getFactory().newOutputPort(this, OUT_TRIANGLE_NAME, vcoTriangle.output);
		this.outputSawtooth = PACFactory.getFactory().newOutputPort(this, OUT_SAWTOOTH_NAME, vcoSawtooth.output);

		// Connexion du modulateur au PassThrough
		frequencyModulator.output.connect(passThrough.input);
		
		// Valeur par defaut
		this.setOctave(7);
		this.setTone(0);
		
		// Set de la frequence de base
		vcoSquare.frequency.set(getFrequency());
		vcoTriangle.frequency.set(getFrequency());
		vcoSawtooth.frequency.set(getFrequency());
		
		addPort(fm);
		addPort(outputSawtooth);
		addPort(outputSquare);
		addPort(outputTriangle);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(vcoSquare);
		generators.add(vcoTriangle);
		generators.add(vcoSawtooth);
		generators.add(frequencyModulator);
		generators.add(passThrough);
		return generators;
	}

	@Override
	public void start() {
		this.vcoSquare.start();
		this.vcoTriangle.start();
		this.vcoSawtooth.start();
		this.frequencyModulator.start();
		this.passThrough.start();
	}

	@Override
	public void stop() {
		this.vcoSquare.stop();
		this.vcoTriangle.stop();
		this.vcoSawtooth.stop();
		this.frequencyModulator.stop();
		this.passThrough.stop();
	}

	@Override
	public void setOctave(int value) {
		getParameters().put("octave", (double) value);
		updateFrequency();
	}

	@Override
	public int getOctave() {
		return getParameter("octave").intValue();
	}

	@Override
	public void setTone(double value) {
		getParameters().put("tone", value);
		updateFrequency();
	}

	@Override
	public double getTone() {
		return getParameter("tone");
	}
	
	private void updateFrequency() {
		getParameters().put("frequency", (double) Math.pow(2,getOctave()+getTone()));
		this.frequencyModulator.setBaseFrequency(getFrequency());
		if(!getInputFm().isInUse()){
			vcoSquare.frequency.set(getFrequency());
			vcoTriangle.frequency.set(getFrequency());
			vcoSawtooth.frequency.set(getFrequency());
		}
	}
	
	@Override
	public double getFrequency() {
		return getParameter("frequency").intValue();
	}
	
	@Override
	public void setFrequency(Double value) {
		getParameters().put("frequency", value);
		this.frequencyModulator.setBaseFrequency(getFrequency());
		if(!getInputFm().isInUse()){
			vcoSquare.frequency.set(getFrequency());
			vcoTriangle.frequency.set(getFrequency());
			vcoSawtooth.frequency.set(getFrequency());
		}
	}
	
	@Override
	public IInputPort getInputFm() {
		return fm;
	}

	@Override
	public IOutputPort getOutputSquare() {
		return outputSquare;
	}

	@Override
	public IOutputPort getOutputTriangle() {
		return outputTriangle;
	}

	@Override
	public IOutputPort getOutputSawtooth() {
		return outputSawtooth;
	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		if (fm.getWire() != null) {
			if(!wires.contains(fm.getWire()))
				wires.add(fm.getWire());
		}
		if (outputSquare.getWire() != null) {
			if(!wires.contains(outputSquare.getWire()))
				wires.add(outputSquare.getWire());
		}
		if (outputTriangle.getWire() != null) {
			if(!wires.contains(outputTriangle.getWire()))
				wires.add(outputTriangle.getWire());
		}
		if (outputSawtooth.getWire() != null) {
			if(!wires.contains(outputSawtooth.getWire()))
				wires.add(outputSawtooth.getWire());
		}
		return wires;

	}

	@Override
	public void update(Port subject) {
		// If the FM input port send a connection event
		if(subject == fm){
			// If it is in use
			if(fm.isInUse()){
				// Connect the frequency modulator to the output
				passThrough.output.connect(vcoSquare.frequency);
				passThrough.output.connect(vcoTriangle.frequency);
				passThrough.output.connect(vcoSawtooth.frequency);
				
			}else{
				// Disconnect the frequency modulator and set the frequency
				passThrough.output.disconnectAll();
				vcoSquare.frequency.set(getFrequency());
				vcoTriangle.frequency.set(getFrequency());
				vcoSawtooth.frequency.set(getFrequency());
			}
				
		}
	}
}
