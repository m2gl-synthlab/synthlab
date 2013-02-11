package fr.istic.synthlab.presentation.util;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

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
		
		//this.setSize(mod.getPresentation().getWidth(), 20);
		this.setLayout(new BorderLayout());
		
		
		this.add(new JLabel(mod.getName()), BorderLayout.WEST);
		
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("res/close.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageIcon im = new ImageIcon(img.getScaledInstance(20, 20, ALLBITS));
		JLabel lab = new JLabel(im);
		this.add(lab, BorderLayout.EAST);
		
		lab.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				module.p2cClosing();
				((ICSynthesizer)CSynthesizer.getInstance()).p2cRemoveModule(module) ;
			}
		});
		
		this.setOpaque(false);
	}

}
