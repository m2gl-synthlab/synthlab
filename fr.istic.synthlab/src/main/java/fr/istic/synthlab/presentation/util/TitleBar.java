package fr.istic.synthlab.presentation.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import com.alee.laf.panel.WebPanel;

import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;

public class TitleBar extends WebPanel {

	private static final long serialVersionUID = 276841652604347112L;

	ICModule module = null;
	JLabel titleLbl;
	JLabel closeLbl;

	public TitleBar(ICModule mod) {
		super();
		this.module = mod;

		configView();
		defineCallbacks();

	}

	public void configView() {
		this.setOpaque(false);
		this.setLayout(new BorderLayout());

		this.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));

		titleLbl = new JLabel("   " + module.getName());
		this.add(titleLbl, BorderLayout.CENTER);

		closeLbl = new JLabel("<html><b>X</b>&nbsp;&nbsp;</html>");
		this.add(closeLbl, BorderLayout.EAST);
	}

	public void defineCallbacks() {
		closeLbl.addMouseListener(new SimpleMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					module.p2cClosing();
					((ICSynthesizer) CSynthesizer.getInstance()).p2cRemoveModule(module);
				}
			}
		});
	}

}
