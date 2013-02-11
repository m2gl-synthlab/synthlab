package fr.istic.synthlab.presentation.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

import com.alee.laf.panel.WebPanel;

import fr.istic.synthlab.controller.module.ICModule;
import fr.istic.synthlab.controller.synthesizer.CSynthesizer;
import fr.istic.synthlab.controller.synthesizer.ICSynthesizer;

public class TitleBar extends WebPanel {

	ICModule module=null;
	public TitleBar(ICModule mod){
		super();
		this.module = mod;
		this.setOpaque(false);
		this.setLayout(new BorderLayout());
		
		this.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLACK));
		
		JLabel titleLbl = new JLabel("   "+mod.getName());
		this.add(titleLbl, BorderLayout.CENTER);
		
		JLabel closeLbl = new JLabel("<html><b>X</b>&nbsp;&nbsp;</html>");
		this.add(closeLbl, BorderLayout.EAST);
		
		closeLbl.addMouseListener(new SimpleMouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				module.p2cClosing();
				((ICSynthesizer)CSynthesizer.getInstance()).p2cRemoveModule(module) ;
			}
		});
	}

}
