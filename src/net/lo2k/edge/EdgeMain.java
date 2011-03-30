package net.lo2k.edge;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EdgeMain extends JPanel {

	
	private static final String IMG_URL = "seek.jpg";
	private static final String FIND_IMG_URL = "pattern.jpg";
	//ball
	//private static final String IMG_URL = "http://www.insolite.ca/wp-content/uploads/2009/10/soccer-ball.jpg";
	
	//ball2
	//private static final String FIND_IMG_URL = "http://www.footballpictures.net/data/media/144/Euro-2008-official-ball.jpg";
	
	//trian
	//private static final String FIND_IMG_URL = "http://www.montigny78.fr/montigny/2101/TRIANGLE3.JPG";
		
	//plage
	//private static final String FIND_IMG_URL = "http://www.photoway.com/images/martinique/plus-belle-plage.jpg";
	
	//inca ball
	//private static final String FIND_IMG_URL = "http://www.incaballonlinegame.com/images/screenshots/inca-ball_1.jpg";
	//private static final String FIND_IMG_URL = "http://ca.7digital.com/cms/USEvents/Img/Stock-Soccer-Ball.jpg";
	//private static final String IMG_URL = "http://ca.7digital.com/cms/USEvents/Img/Stock-Soccer-Ball.jpg";
	
	
	public static void main(String args[]) throws MalformedURLException,
			IOException {

		/*BufferedImage imageOne = ImageIO.read(new URL(IMG_URL));
		BufferedImage imagePattern = ImageIO.read(new URL(FIND_IMG_URL));*/
		
		final BufferedImage imageOne = ImageIO.read(new File(IMG_URL));
		BufferedImage imagePattern = ImageIO.read(new File(FIND_IMG_URL));
		
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
		
		final JFrame frame = new JFrame();

		frame.setLayout(new GridLayout(2, 2));
		//frame.add(new DisplayPanel(imageTwo));

		Detector detector = new Detector();
		
		//frame.add(new DisplayPanel(detector.getEdgesFor(imageOne)));
		//frame.add(new DisplayPanel(detector.getEdgesFor(imagePattern)));
		frame.add(new DisplayPanel(imageOne));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.pack();
		
		
		/*hausdorffDis.setImage(imageOne);
		hausdorffDis.setPattern(imagePattern);
		hausdorffDis.getDistance();*/
		detector.setImage(imageOne);
		detector.setPattern(imagePattern);
		
		
		
		detector.setOnDetectListener(new Listener<Rectangle>() {
			
			@Override
			public void onAction(Rectangle rect) {
				//frame.removeAll();
				Graphics2D g2d = imageOne.createGraphics();
				g2d.setColor(new Color(1,0,0,0.2f));
				g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
				//frame.add(new DisplayPanel(imageOne));
				//frame.pack();
				frame.repaint();
			}
		});
		
		Rectangle rect = detector.detect();
		
		Graphics2D g2d = imageOne.createGraphics();
		g2d.setColor(Color.RED);
		g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
		
		
		//frame.add(new DisplayPanel(pDetector.getDebugImg1()));
		//frame.add(new DisplayPanel(pDetector.getDebugImg2()));
		frame.repaint();
	}

}