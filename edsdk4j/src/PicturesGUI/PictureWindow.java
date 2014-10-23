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

import edsdk.utils.CanonCamera;

public class PictureWindow extends JFrame{
	
	private int numberOfPicture; //the number of picture to display
	private Dimension dimension;
	private JPanel superPan;
	private PicturePanel picturePanel, picturePanel2, picturePanel3, picturePanel4, picturePanelRendered;
	private File file, fileRendered;
	private CanonCamera slr;
	private ImageBuilder builder;
	private BufferedImage defaultImage, defaultRender;
	
	public PictureWindow(int numberOfPicture, Dimension dimension, CanonCamera slr){
		super();
		this.numberOfPicture = numberOfPicture;
		this.dimension = dimension;
		this.slr=slr;
		
		this.setSize(dimension);
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
		superPan.setLayout(new GridLayout(2, (int) Math.ceil(numberOfPicture/2)));
		picturePanel = new PicturePanel(this, file, slr, false);
		picturePanel2 = new PicturePanel(this, file, slr, false);
		picturePanel3 = new PicturePanel(this, file, slr, false);
		picturePanel4 = new PicturePanel(this, file, slr, false);
		picturePanelRendered = new PicturePanel(this, fileRendered, slr, true);
		this.add(superPan, BorderLayout.CENTER);
		this.add(picturePanelRendered, BorderLayout.EAST);
		superPan.add(picturePanel);
		superPan.add(picturePanel2);
		superPan.add(picturePanel3);
		superPan.add(picturePanel4);
		builder = new ImageBuilder(picturePanelRendered.getImage(), 
				picturePanel.getImage(), 
				picturePanel2.getImage(), 
				picturePanel3.getImage(), 
				picturePanel4.getImage()
				);
		
		revalidate();
		repaint();
	}
	
	public void shootDone(){
		builder.Update(picturePanelRendered.getImage(), 
				picturePanel.getImage(), 
				picturePanel2.getImage(), 
				picturePanel3.getImage(), 
				picturePanel4.getImage()
				);
		this.revalidate();
		this.repaint();
	}

	public void refresh() {
		picturePanel.setImage(defaultImage);
		picturePanel2.setImage(defaultImage);
		picturePanel3.setImage(defaultImage);
		picturePanel4.setImage(defaultImage);
		picturePanelRendered.setImage(defaultRender);
		builder.Update(picturePanelRendered.getImage(), 
				picturePanel.getImage(), 
				picturePanel2.getImage(), 
				picturePanel3.getImage(), 
				picturePanel4.getImage()
				);
		revalidate();
		repaint();
		
	}
}
