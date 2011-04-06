package net.lo2k.edge;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Detector {
	
	HausdorffDistance hausdorffDistance;
	
	private BufferedImage pattern;
	private BufferedImage image;

	private Listener<Rectangle> listener;
	
	public static int INCREMENT = 5;
	
	public Detector() {
		hausdorffDistance = new HausdorffDistance();
	}
	
	public Rectangle detect() {
		JFrame frame = new JFrame();
		BufferedImage dbgImg = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		frame.add(new DisplayPanel(dbgImg));
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		
		double bestCandidate = Double.MAX_VALUE; 
		int bestX = 0;
		int bestY = 0;
		
		int width = image.getWidth() - pattern.getWidth();
		int height = image.getHeight() - pattern.getHeight();
		for (int i = 0; i < width; i=i+INCREMENT) {
			for (int j = 0; j < height; j=j+INCREMENT) {
				hausdorffDistance.setImage(image.getSubimage(i, j, pattern.getWidth(), pattern.getHeight()));
				//hausdorffDistance.setImage(ImgUtil.getSubimage(i, j, 100, 100));
				hausdorffDistance.setPattern(pattern);
				//System.out.println(i+", "+j+" => "+hausdorffDistance.getDistance());
				double dist = hausdorffDistance.getDistance();
				int val = (int) dist; 
				if(val>255) {
					val = 255;
				}
				int color = ImgUtil.toRGB(val, val, val);
				draw(dbgImg, i, j, color, INCREMENT);
				
				if (dist < bestCandidate) {
					//remove old color
					int oldColor = ImgUtil.toRGB((int)bestCandidate, (int)bestCandidate, (int)bestCandidate);
					draw(dbgImg, bestX, bestY, oldColor, INCREMENT);
					
					draw(dbgImg, i, j, ImgUtil.toRGB(255, 0, 0), INCREMENT);
					bestX = i;
					bestY = j;
					bestCandidate = dist;
					System.out.println(dist);
					this.listener.onAction(new Rectangle(bestX, bestY, pattern.getWidth(), pattern.getHeight()));
				}
				
				//if (dist < 15.5f) {
				/*if (dist < 20.5f) {
					this.listener.onAction(new Rectangle(i, j, pattern.getWidth(), pattern.getHeight()));
				}*/
				
				
				
				frame.repaint();
			}
		}
		
		return new Rectangle(bestX, bestY, pattern.getWidth(), pattern.getHeight());
	}
	
	public void draw(BufferedImage img, int i, int j, int color, int size) {
		
		for (int i2 = 0; i2 < size; i2++) {
			for (int j2 = 0; j2 < size; j2++) {
				img.setRGB(i+i2, j+j2, color);
			}
		}
	}
	
	public BufferedImage getEdgesFor(BufferedImage img) {
		CannyEdgeDetector detector = new CannyEdgeDetector();
		//BufferedImage resized = ImgUtil.resize(img, 100,100, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		//detector.setSourceImage(resized);
		//lowThreshold = 2.5f;
		//highThreshold = 7.5f;
		detector.setLowThreshold(2.5f);
		detector.setHighThreshold(10f);
		detector.setSourceImage(img);
		detector.process();
		return detector.getEdgesImage();
	}

	public BufferedImage getPattern() {
		return pattern;
	}

	public void setPattern(BufferedImage pattern) {
		this.pattern = getEdgesFor(pattern);
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = getEdgesFor(image);
	}
	
	public void setOnDetectListener(Listener<Rectangle> rectListener) {
		this.listener = rectListener;
		
	}
}
