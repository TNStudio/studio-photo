package exceptions;

public class NoJoystickException extends Exception{

	public NoJoystickException(){
		System.err.println("No joystick found !");
	}
}
