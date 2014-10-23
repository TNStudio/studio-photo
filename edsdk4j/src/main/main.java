package main;

import edsdk.utils.CanonCamera;
import gettingstarted.E04_LiveView;

import java.awt.Dimension;

import PicturesGUI.PictureWindow;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CanonCamera slr = new CanonCamera();
		slr.openSession();
		slr.endLiveView();
		new PictureWindow(4, new Dimension(1600,900),slr);
		System.out.println("end");

	}

}
