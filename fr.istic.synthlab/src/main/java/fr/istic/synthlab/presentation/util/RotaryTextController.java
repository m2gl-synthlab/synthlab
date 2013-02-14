package fr.istic.synthlab.presentation.util;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import com.jsyn.swing.DoubleBoundedRangeModel;

public class RotaryTextController extends com.jsyn.swing.RotaryTextController {

	private static final long serialVersionUID = -5097930545464485642L;

	public RotaryTextController(DoubleBoundedRangeModel model, int textSize) {
		super(model, textSize);
		this.setBorder(new TitledBorder(
				model.getName()));
		for (int i = this.getComponentCount()-1; i>=0; i--) {
			Component c = this.getComponent(i);
			if (c instanceof JPanel)
				((JPanel) c).setOpaque(false);
		}
		this.setOpaque(false);
	}

}
