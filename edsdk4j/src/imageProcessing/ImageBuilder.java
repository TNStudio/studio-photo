package imageProcessing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageBuilder {
	private BufferedImage canvas;
	int x;
	
	public ImageBuilder(BufferedImage canvas, BufferedImage image) {
		x = 60;
		
		// paint both images, preserving the alpha channels
		Graphics g = canvas.getGraphics();
		g.drawImage(image, 0, 0, null);
	}
	
	public void Update(BufferedImage canvas, BufferedImage image) {
		// paint both images, preserving the alpha channels
		Graphics g = canvas.getGraphics();
		g.drawImage(image, 0, 0, null);
	}

	public BufferedImage getResult() {
		return canvas;
	}
}
