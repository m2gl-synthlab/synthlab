package fr.istic.synthlab.abstraction.synthesizer;

import java.util.ArrayList;
import java.util.List;

import com.jsyn.JSyn;
import com.jsyn.unitgen.UnitGenerator;

import fr.istic.synthlab.abstraction.module.IModule;
import fr.istic.synthlab.abstraction.wire.IWire;

/**
 * Implementation of a Synthesizer
 */
public class Synthesizer implements ISynthesizer {
	
	private com.jsyn.Synthesizer synth;

	private List<IModule> modules;
	private List<IWire> wires;

	private IWire currentWire;
	/**
	 * Constructor
	 */
	public Synthesizer() {
		this.synth = JSyn.createSynthesizer();
		modules = new ArrayList<IModule>();
		wires = new ArrayList<IWire>();
//		instance = this;
	}

	@Override
	public com.jsyn.Synthesizer getJSyn() {

		return synth;
	}

	@Override
	public void add(IModule module) {
		modules.add(module);
		if (module.getJSyn() != null)
			for (UnitGenerator gen : module.getJSyn())
				this.synth.add(gen);

	}

	@Override
	public void remove(IModule module) {
		modules.remove(module);
		for (UnitGenerator gen : module.getJSyn())
			this.synth.remove(gen);
	}

	@Override
	public void add(IWire wire) {
		wires.add(wire);
	}

	@Override
	public void start() {
		if (!isRunning()) {
			this.synth.start();
			for (IModule mod : this.modules) {
				mod.start();
			}
		}
	}

	@Override
	public void startModule(IModule module) {
		module.start();
	}

	@Override
	public void stop() {
		if (isRunning()) {
			this.synth.stop();
			for (IModule mod : this.modules) {
				mod.stop();
			}
		}
	}

	public List<IModule> getModules() {
		return modules;
	}

	@Override
	public void stopModule(IModule module) {
		module.stop();
	}

	@Override
	public boolean isRunning() {
		return this.synth.isRunning();
	}

	@Override
	public IWire getCurrentWire() {
		return currentWire;
	}

	@Override
	public void setCurrentWire(IWire wire) {
		currentWire = wire;
	}

	@Override
	public void remove(IWire wire) {
		wires.remove(wire);
		wire = null;
		currentWire = null;
	}

}
