package parainnage;

import java.io.File;

import observerPattern.MyObserver;
import joystick.JoystickAdapter;
import edsdk.utils.CanonCamera;

public class Modele implements MyObserver{
	
	public static int POLLING_TIME = 1000;
	public static File photoFolder = new File("photo/");
	
	private CanonCamera slr;
	private JoystickAdapter joystick;
	private int photo_number = 0;

	
	public Modele(){
		joystick = new JoystickAdapter(POLLING_TIME);
		joystick.addObserver(this);
		
		slr = new CanonCamera();
		slr.openSession();
		joystick.run();
	}


	@Override
	public void update() {
		// TODO when the joystick polls
		photo_number =  photoFolder.list().length;
		if(joystick.getButtons()[0]){
			System.out.println("shoot");
			slr.shoot(new File(photoFolder, "photo"+photo_number), false);
		}

		
	}

}
