package fr.istic.synthlab.abstraction.module.mix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.filter.AttenuationFilter;
import fr.istic.synthlab.abstraction.filter.QuadrupleMixFilter;
import fr.istic.synthlab.abstraction.module.AModule;
import fr.istic.synthlab.abstraction.port.IInputPort;
import fr.istic.synthlab.abstraction.port.IOutputPort;
import fr.istic.synthlab.abstraction.port.IPort;
import fr.istic.synthlab.abstraction.util.Convert;
import fr.istic.synthlab.abstraction.wire.IWire;
import fr.istic.synthlab.factory.impl.PACFactory;

public class ModuleMIX  extends AModule implements IModuleMIX {

	private static final String MODULE_NAME = "MIX",
			IN1_NAME = "In1",
			IN2_NAME = "In2",
			IN3_NAME = "In3",
			IN4_NAME = "In4",
			OUT_NAME = "Out";

	//TODO private ChannelOut out;

	private AttenuationFilter attenuator1, attenuator2, attenuator3, attenuator4;

	//Inputs et output du module
	private IInputPort in1;
	private IInputPort in2;
	private IInputPort in3;
	private IInputPort in4;
	private IOutputPort output;
	private QuadrupleMixFilter mixer;

	public ModuleMIX() {
		super(MODULE_NAME);

		this.mixer = new QuadrupleMixFilter();

		this.attenuator1 = new AttenuationFilter();
		this.attenuator2 = new AttenuationFilter();
		this.attenuator3 = new AttenuationFilter();
		this.attenuator4 = new AttenuationFilter();

		this.in1 = PACFactory.getFactory().newInputPort(this, IN1_NAME, attenuator1.input);
		this.in2 = PACFactory.getFactory().newInputPort(this, IN2_NAME, attenuator2.input);
		this.in3 = PACFactory.getFactory().newInputPort(this, IN3_NAME, attenuator3.input);
		this.in4 = PACFactory.getFactory().newInputPort(this, IN4_NAME, attenuator4.input);

		this.setAttenuation(1, 0); this.setAttenuation(2, 0); this.setAttenuation(3, 0); this.setAttenuation(4, 0);
		
		this.attenuator1.output.connect(mixer.getInput1());
		this.attenuator2.output.connect(mixer.getInput2());
		this.attenuator3.output.connect(mixer.getInput3());
		this.attenuator4.output.connect(mixer.getInput4());
		
		this.output = PACFactory.getFactory().newOutputPort(this, OUT_NAME,	mixer.getOutput());
		
		addPort(in1); addPort(in2); addPort(in3); addPort(in4); addPort(output);
	}

	@Override
	public List<UnitGenerator> getJSyn() {
		List<UnitGenerator> generators = new ArrayList<UnitGenerator>();
		generators.add(attenuator1); generators.add(attenuator2);
		generators.add(attenuator3); generators.add(attenuator4);
		return generators;
	}

	@Override
	public void start() {
		// TODO out.start();
		this.attenuator1.start();
		this.attenuator2.start();
		this.attenuator3.start();
		this.attenuator4.start();
		//this.inputAmplitudeModulator.start();

	}

	@Override
	public void stop() {
		// TODO out.stop();
		this.attenuator1.stop();
		this.attenuator2.stop();
		this.attenuator3.stop();
		this.attenuator4.stop();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IWire> getWires() {
		List<IWire> wires = new ArrayList<IWire>();
		if (in1.getWire() != null) {
			if(!wires.contains(in1.getWire()))
				wires.add(in1.getWire());
		}
		if (in2.getWire() != null) {
			if(!wires.contains(in2.getWire()))
				wires.add(in2.getWire());
		}
		if (in3.getWire() != null) {
			if(!wires.contains(in3.getWire()))
				wires.add(in3.getWire());
		}
		if (in4.getWire() != null) {
			if(!wires.contains(in4.getWire()))
				wires.add(in4.getWire());
		}
		return wires;
	}

	@Override
	public boolean containsPort(IPort port) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addPort(IPort port) {
		// TODO Auto-generated method stub

	}

	@Override
	public Double getParameter(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, Double> getParameters() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IPort getPortByName(String portName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IInputPort getInput(int number) {
		switch (number){
		case 1:
			return this.in1;
		case 2:
			return this.in2;
		case 3:
			return this.in3;
		case 4:
			return this.in4;
		}
		return null;
	}

	@Override
	public IOutputPort getOutput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttenuation (int number, double value) {
		switch(number){
		case 1:
			getParameters().put("attenuation1", (double) value);
			this.attenuator1.setAttenuation(Convert.dB2V(value)); break;
		case 2:
			getParameters().put("attenuation2", (double) value);
			this.attenuator2.setAttenuation(Convert.dB2V(value)); break;
		case 3:
			getParameters().put("attenuation3", (double) value);
			this.attenuator3.setAttenuation(Convert.dB2V(value)); break;
		case 4:
			getParameters().put("attenuation4", (double) value);
			this.attenuator4.setAttenuation(Convert.dB2V(value)); break;
		}
	}


	@Override
	public double getAttenuation(int number) {
		switch(number){
		case 1:
			return getParameter("attenuation1"); 
		case 2:
			return getParameter("attenuation2");
		case 3:
			return getParameter("attenuation2");
		case 4:
			return getParameter("attenuation3");
		}
		return 0;
	}

}
