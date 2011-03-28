package net.lo2k.edge;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EdgeMain extends JPanel {

	public static void main(String args[]) throws MalformedURLException,
			IOException {

		BufferedImage imageOne = ImageIO
				.read(new URL(
						"http://farm2.static.flickr.com/1400/5136927929_e0ae2b21c7_b.jpg"));

		BufferedImage imageTwo = new BufferedImage(imageOne.getWidth(),
				imageOne.getHeight(), BufferedImage.TYPE_INT_RGB);

		/*
		 * for (int x = 1; x < imageOne.getWidth()-1; x++) {
		 * 
		 * for (int y = 1; y < imageOne.getHeight()-1; y++) {
		 * 
		 * int greyScale = ImgUtil.getGreyScale(imageOne, x, y); int
		 * greyScaleAround = 0; for (int i = -1; i <= 1; i++) { for (int j = -1;
		 * j <= 1; j++) { if ((i!=0 && (j!=0))) { greyScaleAround+=
		 * ImgUtil.getGreyScale(imageOne, x+i, y+j); } } } greyScaleAround =
		 * greyScaleAround/8;
		 * 
		 * 
		 * if (Math.abs(greyScaleAround-greyScale)>25) { imageTwo.setRGB(x, y,
		 * ImgUtil.toRGB(255, 255, 255)); } else { imageTwo.setRGB(x, y,
		 * ImgUtil.toRGB(0 , 0, 0)); }
		 * 
		 * 
		 * }
		 * 
		 * }
		 */

		CannyEdgeDetector detector = new CannyEdgeDetector();
		detector.setSourceImage(imageOne);
		/*detector.setLowThreshold(0.5f);
		detector.setHighThreshold(1f);*/

		detector.process();
		imageTwo = detector.getEdgesImage();

		JFrame frame = new JFrame();

		frame.add(new DisplayPanel(imageTwo));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();

	}

}