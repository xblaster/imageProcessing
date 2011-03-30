package net.lo2k.edge;

import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EdgeMain extends JPanel {

	//ball
	private static final String IMG_URL = "http://www.insolite.ca/wp-content/uploads/2009/10/soccer-ball.jpg";
	
	//ball2
	//private static final String FIND_IMG_URL = "http://www.footballpictures.net/data/media/144/Euro-2008-official-ball.jpg";
	
	//trian
	//private static final String FIND_IMG_URL = "http://www.montigny78.fr/montigny/2101/TRIANGLE3.JPG";
		
	//plage
	//private static final String FIND_IMG_URL = "http://www.photoway.com/images/martinique/plus-belle-plage.jpg";
	
	//inca ball
	private static final String FIND_IMG_URL = "http://www.incaballonlinegame.com/images/screenshots/inca-ball_1.jpg";
	//private static final String FIND_IMG_URL = "http://ca.7digital.com/cms/USEvents/Img/Stock-Soccer-Ball.jpg";
	//private static final String IMG_URL = "http://ca.7digital.com/cms/USEvents/Img/Stock-Soccer-Ball.jpg";
	
	
	public static void main(String args[]) throws MalformedURLException,
			IOException {

		BufferedImage imageOne = ImageIO.read(new URL(IMG_URL));
		BufferedImage imagePattern = ImageIO.read(new URL(FIND_IMG_URL));
		
		//resize 
		BufferedImage resized = ImgUtil.resize(imageOne, imageOne.getWidth()/2, imageOne.getHeight()/2, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		BufferedImage imageTwo;

		
		 

		/*CannyEdgeDetector detector = new CannyEdgeDetector();
		detector.setSourceImage(imageOne);
		/*detector.setLowThreshold(0.5f);
		detector.setHighThreshold(1f);*/
		/*detector.process();
		BufferedImage imageOneEdge = detector.getEdgesImage();
		
		detector = new CannyEdgeDetector();
		detector.setSourceImage(imagePattern);
		detector.process();
		BufferedImage imagePatternEdge = detector.getEdgesImage();*/

		
		/*for (int x = 0; x < resized.getWidth(); x++) {
			for (int y = 0; y < resized.getHeight(); y++) {
				//System.out.println((int)imageTwo.getRGB(x, y));
				if (ImgUtil.isWhitePixel(imageTwo, x, y)) {
					
					imageTwo.setRGB(x, y, imageOne.getRGB(x, y));
				}
				//imageTwo.setRGB(x, y, imageOne.getRGB(x, y));
			}
		}*/
		
		JFrame frame = new JFrame();

		frame.setLayout(new GridLayout(2, 2));
		//frame.add(new DisplayPanel(imageTwo));

		PatternDetector pDetector = new PatternDetector();
		
		frame.add(new DisplayPanel(pDetector.getEdgesFor(imageOne)));
		frame.add(new DisplayPanel(pDetector.getEdgesFor(imagePattern)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
		
		pDetector.setImage(imageOne);
		pDetector.setPattern(imagePattern);
		pDetector.detect();
		
		frame.add(new DisplayPanel(pDetector.getDebugImg1()));
		frame.add(new DisplayPanel(pDetector.getDebugImg2()));
		frame.validate();
		frame.pack();
	}

}