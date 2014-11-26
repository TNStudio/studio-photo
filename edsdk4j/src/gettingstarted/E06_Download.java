package gettingstarted;

import java.io.File;

import edsdk.utils.CanonCamera;
import edsdk.utils.CanonTask;
import edsdk.utils.commands.DownloadTask;

/** Simple example of JNA interface mapping and usage. */
public class E06_Download {
	
	public static File photoFolder = new File("E:\\Dropbox\\Gala2014\\3");
	
	public static void main(String[] args) throws InterruptedException {
		CanonCamera slr = new CanonCamera(); 
		slr.openSession();
		DownloadTask task = new DownloadTask(photoFolder);
		slr.executeNow(task);
		slr.closeSession();
		
		CanonCamera.close(); 
	}
}