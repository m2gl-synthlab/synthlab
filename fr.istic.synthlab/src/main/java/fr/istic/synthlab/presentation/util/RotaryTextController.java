package fr.istic.synthlab.presentation.util;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jsyn.swing.DoubleBoundedRangeModel;

public class RotaryTextController extends JPanel {

	private static final long serialVersionUID = -5097930545464485642L;

	public RotaryTextController(DoubleBoundedRangeModel model, int textSize) {
		// Rotary
		com.jsyn.swing.RotaryTextController rotaryPanel = new com.jsyn.swing.RotaryTextController(model, textSize);
		for (int i = 0; i < rotaryPanel.getComponentCount(); i++) {
			Component c = rotaryPanel.getComponent(i);
			if (c instanceof JPanel)
				((JPanel) c).setOpaque(false);
		}
		rotaryPanel.setOpaque(false);

		setLayout(new BorderLayout());
		this.add(new JLabel(model.getName(),JLabel.CENTER), BorderLayout.CENTER);
		this.add(rotaryPanel, BorderLayout.PAGE_END);

		this.setOpaque(false);
	}

}
