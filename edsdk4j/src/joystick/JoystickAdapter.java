package joystick;

import java.util.ArrayList;

import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import exceptions.NoJoystickException;
import observerPattern.MyObservable;
import observerPattern.MyObserver;
import vehicleControl.SchutzoControl;
/**
 * Adapter to a joystick in the SchutzoControl software
 * @author Martin
 *
 */
public class JoystickAdapter implements Runnable, MyObservable{
	
	private int pollingTime;	//refresh in millisecond
	private GamePadController gpController;
	
	private int xPos, yPos, zPos, rxPos, ryPos; // sticks
	
	private boolean[] buttons; //buttons
	
	private int hatSwitch; //hat switch
	
	private Thread t;
	
	private ArrayList<MyObserver> observerList;
	
	private SchutzoControl control;
	
	private boolean isActivate=false;  //whether the joystick is activate or not
	
	/**
	 * Create the joystick and his thread
	 * @param pollingTime in millisecond
	 */
	public JoystickAdapter(SchutzoControl control ,int pollingTime){
		
		this.pollingTime=pollingTime;
		this.control=control;
		observerList = new ArrayList<MyObserver>();
		
		
		t = new Thread(this);
		//t.start();
	}	

	@Override
	public void run(){
		try {
			gpController = new GamePadController();
		} catch (NoJoystickException e) {
			this.getT().interrupt(); //if the joystick is not found
		}
		this.polling();	//catch the value of the joystick in a loop, see below
	}
	/**
	 * start the loop to poll the data from the controller
	 */
	public void polling(){
		isActivate = true; //put the value to true to publish the joystick as activated
		control.getLog().addToLg("Joystick started");
		while(isActivate){ //thread loop
				startPolling();
			try {
				Thread.sleep(pollingTime); // stop the thread to free the UC
			} catch (InterruptedException e) {
				System.err.println("Joystick Timer exception");
				Thread.currentThread().interrupt();
				isActivate=false; // if the thread is interrupted, put the value to false
				this.updateObserver(); //update the observers that the joystick is now disable
			}
		}
		System.out.println("end of joystick");
		control.getLog().addToLg("Joystick finished");
		this.updateObserver(); //update the observers that new data are available
	}
	/**
	 * poll the data from the controller
	 */
	public void startPolling() {
		
		try { //try to collect the joystick data
			gpController.poll(); //update the data in the controller
		
		this.xPos = (int) gpController.getPosX(); // collect the data
		this.yPos = (int) gpController.getPosY();
		this.zPos = (int) gpController.getPosZ();
		this.rxPos = (int) gpController.getPosRX();
		this.ryPos = (int) gpController.getPosRY();
		
		this.buttons=gpController.getButtons();
		
		this.hatSwitch=gpController.getHatDir();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Thread.currentThread().interrupt();
			System.out.println("ici");
			control.getLog().addToLg("Warning ! Error : no joystick found");
		}
		this.updateObserver(); //update the observers that new data are available
		
	}

	@Override
	public void addObserver(MyObserver obs) {
		observerList.add(obs);		
	}

	@Override
	public void updateObserver() {
		for(int i=0;i<observerList.size();i++){
			observerList.get(i).update();
		}
	}

	/**
	 * @return the pollingTime
	 */
	public int getPollingTime() {
		return pollingTime;
	}

	/**
	 * @param pollingTime the pollingTime to set
	 */
	public void setPollingTime(int pollingTime) {
		this.pollingTime = pollingTime;
	}

	/**
	 * @return the gpController
	 */
	public GamePadController getGpController() {
		return gpController;
	}

	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}

	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}

	/**
	 * @return the zPos
	 */
	public int getzPos() {
		return zPos;
	}

	/**
	 * @return the rxPos
	 */
	public int getRxPos() {
		return rxPos;
	}

	/**
	 * @return the ryPos
	 */
	public int getRyPos() {
		return ryPos;
	}

	/**
	 * @return the buttons
	 */
	public boolean[] getButtons() {
		return buttons;
	}

	/**
	 * @return the hatSwitch
	 */
	public int getHatSwitch() {
		return hatSwitch;
	}

	/**
	 * @return the observerList
	 */
	public ArrayList<MyObserver> getObserverList() {
		return observerList;
	}

	/**
	 * @return the t
	 */
	public Thread getT() {
		return t;
	}

	/**
	 * @param t the t to set
	 */
	public void setT(Thread t) {
		this.t = t;
	}

	/**
	 * @return the isActivate
	 */
	public boolean isActivate() {
		return isActivate;
	}

	/**
	 * @param isActivate the isActivate to set
	 */
	public void setActivate(boolean isActivate) {
		this.isActivate = isActivate;
	}
	
	

}
