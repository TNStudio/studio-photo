package PicturesGUI;

import imageProcessing.ImageBuilder;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import parainnage.Modele;
import edsdk.utils.CanonCamera;

public class PictureWindow extends JFrame{
	

	private JPanel superPan;
	private PicturePanel picturePanel;
	private File file, fileRendered;
	private ImageBuilder builder;
	private BufferedImage defaultImage, defaultRender;
	private Modele modele;
	
	public PictureWindow(Modele modele){
		super();
		this.modele = modele;

		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		file = new File("default.jpg");
		fileRendered = new File("Canvas.png");
		try {
			defaultImage = ImageIO.read(file);
			defaultRender = ImageIO.read(fileRendered);
		} catch (IOException e) {
			
			System.out.println("l'image par défaut n'a pas été chargé");
		}
		superPan = new JPanel();
		superPan.setLayout(new BorderLayout());
		picturePanel = new PicturePanel(modele);
		this.add(superPan, BorderLayout.CENTER);
		superPan.add(picturePanel, BorderLayout.CENTER);		
		revalidate();
		repaint();
	}
	
}
