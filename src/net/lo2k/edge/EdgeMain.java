package net.lo2k.edge;

import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EdgeMain extends JPanel {

	private static final String FIND_IMG_URL = "http://www.terrapixela.com/wp-content/uploads/images/icoeye-bibliotheque-ban.jpg";
	private static final String IMG_URL = "http://www.terrapixela.com/wp-content/uploads/images/pixels/icoeye-bibliotheque.jpg";
	
	
	public static void main(String args[]) throws MalformedURLException,
			IOException {

		BufferedImage imageOne = ImageIO
				.read(new URL(
						IMG_URL	));
		
		//resize 
		BufferedImage resized = ImgUtil.resize(imageOne, imageOne.getWidth()/2, imageOne.getHeight()/2, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		BufferedImage imageTwo;

		
		 

		CannyEdgeDetector detector = new CannyEdgeDetector();
		detector.setSourceImage(resized);
		/*detector.setLowThreshold(0.5f);
		detector.setHighThreshold(1f);*/

		detector.process();
		imageTwo = detector.getEdgesImage();
		
		for (int x = 0; x < resized.getWidth(); x++) {
			for (int y = 0; y < resized.getHeight(); y++) {
				//System.out.println((int)imageTwo.getRGB(x, y));
				if (ImgUtil.isWhitePixel(imageTwo, x, y)) {
					
					imageTwo.setRGB(x, y, imageOne.getRGB(x, y));
				}
				//imageTwo.setRGB(x, y, imageOne.getRGB(x, y));
			}
		}

		JFrame frame = new JFrame();

		frame.add(new DisplayPanel(imageTwo));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();

	}

}