package net.lo2k.edge;

import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Detector {
	
	HausdorffDistance hausdorffDistance;
	
	private BufferedImage pattern;
	private BufferedImage image;
	
	public Detector() {
		hausdorffDistance = new HausdorffDistance();
	}
	
	public void detect() {
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
		for (int i = 0; i < width; i=i+10) {
			for (int j = 0; j < height; j=j+10) {
				hausdorffDistance.setImage(image.getSubimage(i, j, pattern.getWidth(), pattern.getHeight()));
				//hausdorffDistance.setImage(ImgUtil.getSubimage(i, j, 100, 100));
				hausdorffDistance.setPattern(pattern);
				//System.out.println(i+", "+j+" => "+hausdorffDistance.getDistance());
				double dist = hausdorffDistance.getDistance();
				int val = (int) dist; 
				int color = ImgUtil.toRGB(val, val, val);
				draw(dbgImg, i, j, color, 10);
				
				if (dist < bestCandidate) {
					//remove old color
					int oldColor = ImgUtil.toRGB((int)bestCandidate, (int)bestCandidate, (int)bestCandidate);
					draw(dbgImg, bestX, bestY, oldColor, 10);
					
					draw(dbgImg, i, j, ImgUtil.toRGB(255, 0, 0), 10);
					bestX = i;
					bestY = j;
					bestCandidate = dist;
				}
				
				frame.repaint();
			}
		}
	}
	
	public void draw(BufferedImage img, int i, int j, int color, int size) {
		
		for (int i2 = 0; i2 < 10; i2++) {
			for (int j2 = 0; j2 < 10; j2++) {
				img.setRGB(i+50+i2, j+50+j2, color);
			}
		}
	}
	
	public BufferedImage getEdgesFor(BufferedImage img) {
		CannyEdgeDetector detector = new CannyEdgeDetector();
		//BufferedImage resized = ImgUtil.resize(img, 100,100, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
		//detector.setSourceImage(resized);
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
}
