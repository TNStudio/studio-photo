package parainnage;

import PicturesGUI.PictureWindow;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Modele modele = new Modele();
		PictureWindow frame = new PictureWindow(modele);
		modele.getJoystick().run();
	}

}
