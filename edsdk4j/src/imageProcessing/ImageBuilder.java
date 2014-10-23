package imageProcessing;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageBuilder {
	private BufferedImage canvas;
	int x;
	
	public ImageBuilder(BufferedImage canvas, BufferedImage image1, BufferedImage image2, BufferedImage image3, BufferedImage image4) {
		x = 60;
		
		// paint both images, preserving the alpha channels
		Graphics g = canvas.getGraphics();
		g.drawImage(image1, x, 59, null);
		g.drawImage(image2, x, 827, null);
		g.drawImage(image3, x, 1594, null);
		g.drawImage(image4, x, 2363, null);
	}
	
	public void Update(BufferedImage canvas, BufferedImage image1, BufferedImage image2, BufferedImage image3, BufferedImage image4) {
		// paint both images, preserving the alpha channels
		Graphics g = canvas.getGraphics();
		g.drawImage(image1, x, 59, null);
		g.drawImage(image2, x, 827, null);
		g.drawImage(image3, x, 1594, null);
		g.drawImage(image4, x, 2363, null);
	}

	public BufferedImage getResult() {
		return canvas;
	}
}
