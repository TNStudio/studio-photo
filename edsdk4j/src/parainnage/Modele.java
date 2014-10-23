package parainnage;

import observerPattern.MyObserver;
import joystick.JoystickAdapter;
import edsdk.utils.CanonCamera;

public class Modele implements MyObserver{
	
	public static int POLLING_TIME = 40;
	
	private CanonCamera slr;
	private JoystickAdapter joystick;

	
	public Modele(){
		joystick = new JoystickAdapter(POLLING_TIME);
		joystick.addObserver(this);
		slr = new CanonCamera();
		slr.openSession();
		
	}


	@Override
	public void update() {
		// TODO when the joystick polls
		
	}

}
