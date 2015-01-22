package imageProcessing;

import java.awt.Image;
import java.awt.image.BufferedImage;
import com.mortennobel.imagescaling.experimental.ImprovedMultistepRescaleOp;

public class ImageConverter {
	int h,w,dh,dw,fh,fw;
	int x0,y0; //translation de l'origine
	int [] pixels;
	BufferedImage result;
	
	//Données Echelle
	int hs,ws;
	int[] pixal;
	
	public ImageConverter(Image image) {
		w = image.getWidth(null);
		h = image.getHeight(null);
		dw = 711;
		dh = 710;
		fw = (dh*w)/h;
		fh = dh;
		BufferedImage source = (BufferedImage) image;
		result =  resize(source, fw, fh);
		result = crop(result, dw, dh);
	}
	
	//Resize Image
	public BufferedImage resize(BufferedImage image, int width, int height) {
	    ImprovedMultistepRescaleOp resampleOp = new ImprovedMultistepRescaleOp (width, height);
	    return resampleOp.filter(image, null);
	}
	
	//Crop Image
	public BufferedImage crop(BufferedImage image, int w, int h) {
		x0 = (image.getWidth(null)-w)/2;
		y0 = 0;
		return image.getSubimage(x0, y0, w, h);
	}
	
	//Return the result
	public BufferedImage getProcess(){
		return result;
	}
}
