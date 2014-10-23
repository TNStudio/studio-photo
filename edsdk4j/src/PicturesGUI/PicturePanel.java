package PicturesGUI;

import imageProcessing.ImageConverter;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import observerPattern.MyObserver;
import parainnage.Modele;
import edsdk.utils.CanonCamera;

public class PicturePanel extends JPanel implements MyObserver{
	
	private BufferedImage image = null;
	private double rapport, rapportRendu;
	private Modele modele;
	
	public PicturePanel(Modele modele){
		super();
		this.modele = modele;
		modele.addObserver(this);
		File defaultImage = new File("default.jpg");
		try {
			image = ImageIO.read(defaultImage);
		} catch (IOException e) {
			System.out.println("l'image du panneau n'a pas été chargé");
		}
		rapport = 1.5;
		revalidate();
	
	}

	protected void paintComponent(Graphics g) {
			if(this.getHeight()*rapport<this.getWidth()){
				g.drawImage(this.image, 0, 0,(int) Math.ceil(this.getHeight()*rapport), this.getHeight(), this);
			} else {
				g.drawImage(this.image, 0, 0,this.getWidth(), (int) Math.ceil(this.getWidth()/(rapport)), this);
			}
	}
	
	public void waitProc(){
		File waitfile = new File("wait.jpg");
		try {
			image = ImageIO.read(waitfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		revalidate();
		repaint();
	}
	
	

	/**
	 * @return the image
	 */
	public BufferedImage getImage() {
		return (BufferedImage) image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	public void update() {
		/*File defaultImage = new File("Canvas.png");
		try {
			image = ImageIO.read(defaultImage);
		} catch (IOException e) {
			System.out.println("l'image du panneau n'a pas été chargé");
		}*/
		image = modele.getImage();
		revalidate();
		repaint();
		System.out.println("j'ai rafraichi !");
	}
	
	
}