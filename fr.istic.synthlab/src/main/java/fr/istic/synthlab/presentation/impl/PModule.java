package fr.istic.synthlab.presentation.impl;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.istic.synthlab.controller.ICModule;
import fr.istic.synthlab.presentation.IPInputPort;
import fr.istic.synthlab.presentation.IPModule;
import fr.istic.synthlab.presentation.IPOutputPort;
import fr.istic.synthlab.presentation.IPParameter;

/**
 * Presentation of a module
 */
public class PModule extends JPanel implements IPModule {

	private static final long serialVersionUID = -8519084219674310285L;
	private ICModule ctrl;
	private int width;
	private int height;


	/**
	 * @param control
	 */
	public PModule(ICModule control) {
		this.ctrl = control;

		configView();
		defineCallbacks();
		
	}
	
	public void paintComponent(Graphics g){
	    try {
	      Image img = ImageIO.read(new File("res/synthe.png"));
	      g.drawImage(img, 0, 0,this.getWidth(),this.getHeight(), this);
	      //Pour une image de fond
	      //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }               
	  }               

	private void configView() {
		width = 350;
		height =250;
		this.setSize(width, height);
		this.setPreferredSize(this.getSize());
		this.setBorder(BorderFactory.createTitledBorder(ctrl.getName()));
	}

	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	private void defineCallbacks() {
	}

	@Override
	public ICModule getControl() {
		return ctrl;
	}

	@Override
	public void addInputPort(IPInputPort presentation) {
		add((PInputPort)presentation);

	}

	@Override
	public void addOutputPort(IPOutputPort presentation) {
		add((POutputPort)presentation);
	}

	@Override
	public void addParameter(IPParameter presentation) {
		add((Component) presentation);
	}

}
