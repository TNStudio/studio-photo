package parainnage;

import edsdk.utils.CanonCamera;

public class Modele {
	
	CanonCamera slr;
	
	public Modele(){
		slr = new CanonCamera();
		slr.openSession();
	}

}
