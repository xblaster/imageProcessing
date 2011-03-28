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
						"http://farm6.static.flickr.com/5293/5503399835_7a7fe33742_b.jpg"));


		BufferedImage imageTwo = new BufferedImage(imageOne.getWidth(),
				imageOne.getHeight(), BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < imageOne.getWidth(); x++) {

			for (int y = 0; y < imageOne.getHeight(); y++) {

				/* Get the values from imageOne */

				int rgb = imageOne.getRGB(x, y);

				// int green = imageOne.getGreen(x,y);

				// int blue = imageOne.getBlue(x,y);

				/* Put these values into imageTwo */
				 int red = ((rgb >> 16) & 255);
			     int green = ((rgb >> 8) & 255) ;
			     int blue = (rgb & 255);

			     
			    int greyScale = ImgUtil.getGreyScale(imageOne, x, y); 
				imageTwo.setRGB(x, y, ImgUtil.toRGB(greyScale, greyScale, greyScale));

			}

		}
		
		 JFrame frame = new JFrame();
		 
		 frame.add(new DisplayPanel(imageTwo));
		 
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setVisible(true);
		 frame.pack();

	}

}