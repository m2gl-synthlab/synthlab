package fr.istic.synthlab.presentation.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.module.ICModule;

public class TitleBar extends JPanel {

	ICModule mod=null;
	public TitleBar(final ICModule mod){
		super();
		this.mod = mod;
		this.add(new JLabel(mod.getName()));
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("res/close.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon im = new ImageIcon(img.getScaledInstance(20, 20, ALLBITS));
		JButton lab = new JButton(im);
		this.add(lab);
		
		lab.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mod.p2cClosing();
			}
		});
		
		this.setOpaque(false);
	}

}
