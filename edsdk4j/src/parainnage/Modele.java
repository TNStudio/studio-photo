package parainnage;

import imageProcessing.ImageBuilder;
import imageProcessing.ImageConverter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import observerPattern.MyObservable;
import observerPattern.MyObserver;
import joystick.JoystickAdapter;
import edsdk.utils.CanonCamera;

public class Modele implements MyObserver, MyObservable{
	
	public static int POLLING_TIME = 1000;
	public static File photoFolder = new File("photo/");
	
	private CanonCamera slr;
	private JoystickAdapter joystick;
	private int photo_number = 0;
	private BufferedImage image = null;
	private BufferedImage canvas = null;
	private BufferedImage result = null;
	
	private ArrayList<MyObserver> obsList;

	
	public Modele(){
		obsList = new ArrayList<MyObserver>();
		joystick = new JoystickAdapter(POLLING_TIME);
		joystick.addObserver(this);
		
		slr = new CanonCamera();
		slr.openSession();
	}


	@Override
	public void update() {
		// TODO when the joystick polls
		photo_number =  photoFolder.list().length;
		if(joystick.getButtons()[0]){
			System.out.println("shoot");
			File imageFile = new File(photoFolder, "photo"+photo_number+".jpg");
			slr.shoot(imageFile, false);
			try {
				image = ImageIO.read(imageFile);
				canvas = ImageIO.read(new File("Canvas.jpg"));
				ImageBuilder builder = new ImageBuilder(canvas, image);
				//result = builder.getResult();
				result = canvas;
			} catch (IOException e) {
				e.printStackTrace();
			}
			//ImageConverter process = new ImageConverter(image);
			updateObserver();
		}

		
	}


	@Override
	public void addObserver(MyObserver obs) {
		obsList.add(obs);
		
	}


	@Override
	public void updateObserver() {
		for(int i = 0;i<obsList.size();i++){
			obsList.get(i).update();
		}
		
	}


	public JoystickAdapter getJoystick() {
		return joystick;
	}


	public void setJoystick(JoystickAdapter joystick) {
		this.joystick = joystick;
	}


	public BufferedImage getImage() {
		return image;
	}


	public void setImage(BufferedImage image) {
		this.image = image;
	}


	public BufferedImage getResult() {
		return result;
	}


	public void setResult(BufferedImage result) {
		this.result = result;
	}

	
}
