package fr.istic.synthlab.abstraction.filter;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;

public class TriplePassThroughFilter extends UnitGenerator {

	private static final String OUT1_NAME = "Out1";
	private static final String OUT2_NAME = "Out2";
	private static final String OUT3_NAME = "Out3";
	private static final String IN_NAME = "In";

	UnitOutputPort output1 = new UnitOutputPort(OUT1_NAME);
	UnitOutputPort output2 = new UnitOutputPort(OUT2_NAME);
	UnitOutputPort output3 = new UnitOutputPort(OUT3_NAME);
	UnitInputPort input = new UnitInputPort(IN_NAME);

	public TriplePassThroughFilter() {
		this.addPort(output1);
		this.addPort(output2);
		this.addPort(output3);
		this.addPort(input);
	}

	@Override
	public void generate(int start, int limit) {
		double[] inputs = input.getValues();
		double[] outputs1 = output1.getValues();
		double[] outputs2 = output2.getValues();
		double[] outputs3 = output3.getValues();

		for (int i = start; i < limit; i++) {
			double x = inputs[i];
			outputs1[i] = x;
			outputs2[i] = x;
			outputs3[i] = x;
		}
	}

	public UnitInputPort getInput() {
		return input;
	}

	public UnitOutputPort getOutput1() {
		return output1;
	}

	public UnitOutputPort getOutput2() {
		return output2;
	}

	public UnitOutputPort getOutput3() {
		return output3;
	}

}
