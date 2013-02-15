package fr.istic.synthlab.abstraction.filter;

import com.jsyn.ports.UnitInputPort;
import com.jsyn.ports.UnitOutputPort;
import com.jsyn.unitgen.UnitGenerator;

public class QuadrupleMixFilter extends UnitGenerator {

	private static final String IN1_NAME = "In1";
	private static final String IN2_NAME = "In2";
	private static final String IN3_NAME = "In3";
	private static final String IN4_NAME = "In4";
	private static final String OUT_NAME = "Out";

	UnitInputPort input1 = new UnitInputPort(IN1_NAME);
	UnitInputPort input2 = new UnitInputPort(IN2_NAME);
	UnitInputPort input3 = new UnitInputPort(IN3_NAME);
	UnitInputPort input4 = new UnitInputPort(IN4_NAME);
	UnitOutputPort output = new UnitOutputPort(OUT_NAME);

	public QuadrupleMixFilter() {
		this.addPort(input1);
		this.addPort(input2);
		this.addPort(input3);
		this.addPort(input4);
		this.addPort(output);
	}

	@Override
	public void generate(int start, int limit) {
		double[] inputs1 = input1.getValues();
		double[] inputs2 = input2.getValues();
		double[] inputs3 = input3.getValues();
		double[] inputs4 = input4.getValues();
		double[] outputs = output.getValues();

		for (int i = start; i < limit; i++) {
			outputs[i] = inputs1[i] + inputs2[i] + inputs3[i] + inputs4[i];
		}
	}

	public UnitInputPort getInput1() {
		return input1;
	}

	public UnitInputPort getInput2() {
		return input2;
	}

	public UnitInputPort getInput3() {
		return input3;
	}

	public UnitInputPort getInput4() {
		return input4;
	}

	public UnitOutputPort getOutput() {
		return output;
	}

}
