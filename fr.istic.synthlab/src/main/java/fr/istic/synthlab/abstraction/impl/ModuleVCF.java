package fr.istic.synthlab.abstraction.impl;

import com.jsyn.unitgen.FilterLowPass;
import com.jsyn.unitgen.UnitGenerator;
import com.softsynth.shared.time.TimeStamp;

import fr.istic.synthlab.abstraction.IModule;

public class ModuleVCF implements IModule{

	private FilterLowPass vcf;
	private OutputPort out;
	private InputPort in;
	
	
	public ModuleVCF() {
		this.vcf = new FilterLowPass();
		this.out = new OutputPort(vcf.getOutput());
		this.in = new InputPort(vcf.getInput());
	}

	@Override
	public UnitGenerator getJSyn() {
		return vcf;
	}
	
	@Override
	public void start() {
		this.vcf.start();
		System.out.println("Synth : JSyn vcf started " + this.vcf.isEnabled());
	}

	@Override
	public void start(TimeStamp arg0) {
		this.vcf.start(arg0);
	}

	@Override
	public void stop() {
		this.vcf.stop();
	}

	@Override
	public void stop(TimeStamp arg0) {
		this.vcf.stop(arg0);
	}

	@Override
	public InputPort getInput() {
		return in;
	}

	@Override
	public OutputPort getOutput() {
		return out;
	}

	@Override
	public UnitGenerator getUnitGenerator() {
		return this.vcf.getUnitGenerator();
	}
	

}
