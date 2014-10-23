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
import edsdk.utils.CanonCamera;

public class PicturePanel extends JPanel{
	
	private BufferedImage image = null;
	private double rapport, rapportRendu;
	private CanonCamera slr;
	private boolean isRender = false;
	private File photoFolder;
	private PictureWindow father;
	
	public PicturePanel(final PictureWindow father, File file, final CanonCamera slr, boolean isRender){
		super();
		this.isRender = isRender;
		this.father=father;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			System.out.println("l'image du panneau n'a pas été chargé");
		}
		this.slr=slr;
		rapport = 1;
		rapportRendu = 0.25;
		photoFolder = new File("photo/");
		if(!isRender){
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseReleased(MouseEvent arg0) {
					shootProc();
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					waitProc();
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
				}

				@Override
				public void mouseClicked(MouseEvent arg0) {
					/*waitProc();
					shootProc();*/
				}
			});
		} else {
			this.setPreferredSize(new Dimension(400,400));
			
			this.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {	
					int numb = photoFolder.list().length;
					System.out.println(numb);
					File outputfile = new File("photo/photomaton"+numb+".png");
					try {
						ImageIO.write(image, "png", outputfile);
						father.refresh();
					} catch (IOException e1) {
						System.out.println("l'image n'a pas été écrite");
						System.exit(1);
					}
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {	
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {	
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {	
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});
		}
		revalidate();
	
	}

	protected void paintComponent(Graphics g) {
		if(!isRender){
			if(this.getHeight()*rapport<this.getWidth()){
				g.drawImage(this.image, 0, 0,(int) Math.ceil(this.getHeight()*rapport), this.getHeight(), this);
			} else {
				g.drawImage(this.image, 0, 0,this.getWidth(), (int) Math.ceil(this.getWidth()/(rapport)), this);
			}
		} else {
			if(this.getHeight()*rapportRendu<this.getWidth()){
				g.drawImage(this.image, 0, 0,(int) Math.ceil(this.getHeight()*rapportRendu), this.getHeight(), this);
			} else {
				g.drawImage(this.image, 0, 0,this.getWidth(), (int) Math.ceil(this.getWidth()/(rapportRendu)), this);
			}
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
	
	public void shootProc(){
		File file2 = slr.shoot();
		try {
			image = ImageIO.read(file2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ImageConverter process = new ImageConverter(image);
		image = process.getProcess();
		father.shootDone();
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
	
	
}